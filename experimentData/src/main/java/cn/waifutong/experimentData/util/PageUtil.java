package cn.waifutong.experimentData.util;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil {
    public static Object pageAgain(PageInfo pageInfo){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("count",pageInfo.getTotal());
        dataMap.put("pageMax",pageInfo.getPages());
        List<Object> list = pageInfo.getList();
        for (Object obj : list) {
            if (obj instanceof Map) {
                FormatUtil.formatMap((Map<String, Object>) obj);
            } else {
                FormatUtil.beanNullToEmpty(obj);
            }
        }
        dataMap.put("content", list);
        return dataMap;
    }
    /**
     * list分页,返回包装Map
     * @param list
     * @param pageNum  页码
     * @param pageSize  size
     * @return
     */
    public static Map<String, Object> startPage(List list, int pageNum,
                                                int pageSize) {
        if (list == null) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("count",0);
            dataMap.put("pageMax",0);
            dataMap.put("list",list);
            return dataMap;
        }
        if (list.size() == 0) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("count",0);
            dataMap.put("pageMax",0);
            dataMap.put("list",list);
            return dataMap;
        }

        int count = list.size(); // 记录总数
        int pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageCount < pageNum) {  //防止下标溢出
            pageNum = pageCount;
        }

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }
        List pageList = list.subList(fromIndex, toIndex);

        //包装
        Map<String, Object> dataMap = new HashMap<String, Object>();
        double max = Math.ceil(list.size() / (pageSize * 1.0));
        int pageMax = new Double(max).intValue();
        int count1 = list.size();
        dataMap.put("count",count1);
        dataMap.put("pageMax",pageMax);
        dataMap.put("list",pageList);
        return dataMap;
    }
}
