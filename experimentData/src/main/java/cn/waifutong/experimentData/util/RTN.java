package cn.waifutong.experimentData.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * 用于包装需要返回的各类数据
 */
public class RTN {
    public static JSONObject rtnSuccess(int count) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","SUCCESS");
        jsonObject.put("data",count);
        return jsonObject;
    }

    public static JSONObject rtnSuccess(PageInfo pageInfo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","SUCCESS");
        jsonObject.put("data", PageUtil.pageAgain(pageInfo));
        return jsonObject;
    }

    public static Object rtnSuccess(Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","SUCCESS");
        jsonObject.put("data", object);
        return jsonObject;
    }

    public static JSONObject rtnSuccess(List list) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","SUCCESS");
        jsonObject.put("data", list);
        return jsonObject;
    }
    public static JSONObject rtn501(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",501);
        jsonObject.put("msg",msg);
        jsonObject.put("data","");
        return jsonObject;
    }

    public static Object rtnNullObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",204);
        jsonObject.put("msg","SUCCESS");
        jsonObject.put("data","");
        return jsonObject;
    }
    
    public static Object rtn(int code, String msg, Object obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("data", obj);
        return jsonObject;
    }
}
