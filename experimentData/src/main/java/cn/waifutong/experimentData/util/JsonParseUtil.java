package cn.waifutong.experimentData.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * json格式字符串的相关转换公共方法
 * @author xuedong
 * @createDate 2020-08-21
 * @version 1.0.0
 */
public class JsonParseUtil {

    /**
     * json字符串转换成List<Map<String, Object>>，用来格式化前台传来的json数据
     * @param jsonStr
     * @return
     */
    public static List<Map<String, Object>> jsonStrToList(String jsonStr){
        Gson gson = new Gson();
        System.out.println(jsonStr.toString());
        return gson.fromJson(jsonStr, new TypeToken<List<Map<String, Object>>>() {}.getType());
    }
    
    /**
     * List转换成json，用于后台给前台返回结构化数据
     * @param list
     * @return
     */
    public static String listToJsonStr(List<Map<String, Object>> list) {
        Gson gson = new Gson();  
        return gson.toJson(list);  
    }

    /**
     * json字符串转换成Map<String, Object>，用来格式化前台传来的json数据
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> jsonStrToObject(String jsonStr) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr,  new TypeToken<Map<String, Object>>() {}.getType());
    }
    
    /**
     * Map转换成json，用于后台给前台返回结构化数据
     * @param map
     * @return
     */
    public static String mapToJsonStr(Map<String, Object> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    /**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
    
    public static String jsonFormatStr(String s) {
        int level = 0;
        // 存放格式化的json字符串
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int index = 0; index < s.length(); index++)// 将字符串中的字符逐个按行输出
        {
            // 获取s中的每个字符
            char c = s.charAt(index);

            // level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            // 遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
            case '{':
            case '[':
                jsonForMatStr.append(c + "\n");
                level++;
                break;
            case ',':
                jsonForMatStr.append(c + "\n");
                break;
            case '}':
            case ']':
                jsonForMatStr.append("\n");
                level--;
                jsonForMatStr.append(getLevelStr(level));
                jsonForMatStr.append(c);
                break;
            default:
                jsonForMatStr.append(c);
                break;
            }
        }
        return jsonForMatStr.toString();
    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
    
}
