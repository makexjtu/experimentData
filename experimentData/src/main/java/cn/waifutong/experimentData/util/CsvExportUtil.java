package cn.waifutong.experimentData.util;


import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import cn.waifutong.experimentData.entity.DataAll;
import org.apache.commons.collections4.CollectionUtils;


/**
 * csv导出工具类
 *
 * @author
 *
 */
public class CsvExportUtil {

    /**
     * CSV文件列分隔符
     */
    private static final String CSV_COLUMN_SEPARATOR = ",";

    /**
     * CSV文件行分隔符
     */
    private static final String CSV_ROW_SEPARATOR = "\r\n";

    /**
     * @param dataList
     *            集合数据
     * @param titles
     *            表头部数据
     * @param keys
     *            表内容的键值
     * @param os
     *            输出流
     */
    public static void doExport(List<Map<String, Object>> dataList, String titles, String keys, OutputStream os)
            throws Exception {

        // 保证线程安全
        StringBuffer buf = new StringBuffer();


        String[] titleArr = null;
        String[] keyArr = null;

        titleArr = titles.split(",");
        keyArr = keys.split(",");
        //表头集合
        List<String> list = new ArrayList<>();
        //数据集合
        List<String> lists = new ArrayList<>();
        StringBuffer bufs = new StringBuffer();
        // 组装数据
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> data : dataList) {  //3
                for (String key : keyArr) {  //所有的字段
                    if ("".equals(data.get(key)) || data.get(key) == null){
                        continue;
                    }else{
                        list.add(key);
                        bufs.append(data.get(key)+CSV_COLUMN_SEPARATOR);
                        //lists.add(data.get(key).toString()+CSV_COLUMN_SEPARATOR);
                       // buf.append(data.get(key)).append(CSV_COLUMN_SEPARATOR);
                    }
                }
                bufs.append(CSV_ROW_SEPARATOR);
            }
        }
        //表头去重
        for  ( int  i = 0; i < list.size(); i++)
        {
            for  (  int  j = list.size() - 1;j>i;j--)
            {
                if  (list.get(i) == list.get(j))
                {
                    list.remove(j);
                }
            }
        }
        //表头赋值
             for (int a = 0; a < list.size(); a++) {
            buf.append(list.get(a)+ CSV_COLUMN_SEPARATOR);
        }
        buf.append(CSV_ROW_SEPARATOR);
        buf.append(bufs);


        // 写出响应
        os.write(buf.toString().getBytes("GBK"));
        os.flush();
    }
/*    public static void doExport(List<Map<String, Object>> dataList, String titles, String keys, OutputStream os)
            throws Exception {

        // 保证线程安全
        StringBuffer buf = new StringBuffer();

        String[] titleArr = null;
        String[] keyArr = null;

        titleArr = titles.split(",");
        keyArr = keys.split(",");

        // 组装表头
        for (String title : titleArr) {
            buf.append(title).append(CSV_COLUMN_SEPARATOR);
        }
        buf.append(CSV_ROW_SEPARATOR);

        // 组装数据
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> data : dataList) {
                for (String key : keyArr) {
                    buf.append(data.get(key)).append(CSV_COLUMN_SEPARATOR);
                }
                buf.append(CSV_ROW_SEPARATOR);
            }
        }

        // 写出响应
        os.write(buf.toString().getBytes("GBK"));
        os.flush();
    }*/



    /**
     * 设置Header
     *
     * @param fileName
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void responseSetProperties(String fileName, HttpServletResponse response)
            throws UnsupportedEncodingException {
        // 设置文件后缀
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fn = fileName + sdf.format(new Date()) + ".csv";
        // 读取字符编码
        String utf = "UTF-8";

        // 设置响应
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
    }

}
