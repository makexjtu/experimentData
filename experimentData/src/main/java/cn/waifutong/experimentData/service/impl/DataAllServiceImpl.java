package cn.waifutong.experimentData.service.impl;

import cn.waifutong.experimentData.entity.DataAll;
import cn.waifutong.experimentData.entity.User;
import cn.waifutong.experimentData.repository.DataAllMapper;
import cn.waifutong.experimentData.service.DataAllService;
import cn.waifutong.experimentData.util.MyException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DataAllServiceImpl implements DataAllService {

    @Autowired
    private DataAllMapper dataAllMapper;

    @Override
    public List<Map<String, Object>> selectDataAll() {
        List<Map<String, Object>> maps = dataAllMapper.selectDataAll();
        System.out.println(maps);
        return maps;
    }

    public String excelImport(MultipartFile file) throws IOException {
        if (file.getSize() == 0) {
            return "excel表格异常";
        }
        //实体的集合，把csv中的列装在list里。
        List list = new ArrayList<>();
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (suffix.equals(".csv")) {
            InputStream inputStream = file.getInputStream();
            //编码格式要是用GBK
            InputStreamReader is = new InputStreamReader(inputStream, "GBK");
            BufferedReader reader = new BufferedReader(is);
            reader.readLine();  //第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while ((line = reader.readLine()) != null) {
                //实体类
                //InvestmentProject entity = new InvestmentProject();
                DataAll dataAll = new DataAll();
                //名称
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                if (getValue(item, 0) != null) {
                    //getValue(item, 0)   就是文件中去掉标题行的第一列的数据
                    dataAll.setDelta1(getValue(item, 0));
                }
                list.add(dataAll);
            }
        }
        return "chengle";
    }
    public static String getValue(String[] item,int index){

        if(item.length > index){
            String value = item[index];
            return value;
        }
        return "";
    }
}
