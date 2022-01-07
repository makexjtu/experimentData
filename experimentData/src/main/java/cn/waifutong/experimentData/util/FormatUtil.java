package cn.waifutong.experimentData.util;

import java.util.Map;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 字符串处理公共方法
 * 
 * @author xuedong
 * @createDate 2020-09-17
 * @version 1.0.0
 */
public class FormatUtil {

    /**
     * 适用于将map中的对象转换成字符串，如果对象为空，返回空字符串
     * 
     * @param o
     * @return
     */
    public static String toStr(Object o) {
        if (o == null) {
            return "";
        } else {
            return o.toString();
        }
    }

    /**
     * 用于字符串或者Map中的Object转换为数字
     * 
     * @param o
     * @return
     */
    public static Integer toInteger(Object o) {
        if (o == null || "".equals(o.toString())) {
            return 0;
        } else {
            try {
                return Integer.valueOf(o.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static Map<String, Object> formatMap(Map<String, Object> map) {
        // 将map中的null值转换成空字符串
        for (String key : map.keySet()) {
            Object obj = map.get(key);
            if (obj == null) {
                map.put(key, "");
            } else {
                if (obj instanceof java.math.BigDecimal) {
                    map.put(key, obj.toString());
                } else if (obj instanceof java.lang.Long) {
                    if (!"id".equals(key)) {
                        map.put(key, ((Long) obj).toString());
                    }
                }
            }
        }
        return map;
    }

    /**
     * @description: 使bean中为null的属性转换成空字符串 
     * @param [bean] 
     * @return void 
     * @throws 
     * @author TZH 
     * @date 2019/5/30 11:24
     */
    public static <T> void beanNullToEmpty(T bean) {
        Field[] field = bean.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) { // 遍历所有属性
            String name = field[j].getName(); // 获取属性的名字
            // 将属性的首字符大写，方便构造get，set方法
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = field[j].getGenericType().toString(); // 获取属性的类型
            if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                try {
                    Method mGet = bean.getClass().getMethod("get" + name);
                    String value = (String) mGet.invoke(bean); // 调用getter方法获取属性值
                    if (value == null || "".equals(value)) {
                        Method mSet = bean.getClass().getMethod("set" + name, new Class[] { String.class });
                        mSet.invoke(bean, new Object[] { new String("") });
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String toStrDefaultNum(Object o) {
        if (o == null || "".equals(o.toString())) {
            return "0";
        } else {
            return o.toString();
        }
    }

}
