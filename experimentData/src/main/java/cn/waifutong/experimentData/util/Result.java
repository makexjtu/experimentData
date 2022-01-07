package cn.waifutong.experimentData.util;

/**
 * @Author: ZhangAiYu
 * @Description: 统一返回格式
 * @Date: 下午 4:28 2019-11-9
 */
public class Result<T> {
    //返回码
    private Integer code;
    //返回消息
    private String msg;
    //返回数据
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result failure(String code) {
        return new Result(500, "服务错误");
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
