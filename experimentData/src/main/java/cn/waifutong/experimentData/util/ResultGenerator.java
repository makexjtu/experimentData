package cn.waifutong.experimentData.util;

import org.springframework.util.StringUtils;

public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";


    public static Result genSuccessResult() {
        Result result = new Result();
        result.setCode(Constants.RESULT_CODE_SUCCESS);
        result.setMsg(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static Result genSuccessResult(String message) {
        Result result = new Result();
        result.setCode(Constants.RESULT_CODE_SUCCESS);
        result.setMsg(message);
        return result;
    }


    public static Result genSuccessResult(Object data) {
        Result result = new Result();
        result.setCode(Constants.RESULT_CODE_SUCCESS);
        result.setMsg(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static Result genSuccessMore(Object data,String message) {
        Result result = new Result();
        result.setCode(Constants.RESULT_CODE_SUCCESS);
        result.setMsg(message);
        result.setData(data);
        return result;
    }


    public static Result genFailResult(String message) {
        Result result = new Result();
        result.setCode(Constants.RESULT_CODE_SERVER_ERROR);
        if (StringUtils.isEmpty(message)) {
            result.setMsg(DEFAULT_FAIL_MESSAGE);
        } else {
            result.setMsg(message);
        }
        return result;
    }

    public static Result genNullResult(String message) {
        Result result = new Result();
        result.setCode(Constants.RESULT_CODE_BAD_REQUEST);
        result.setMsg(message);
        result.setData("");
        return result;
    }

    public static Result genErrorResult(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        result.setData("");
        return result;
    }
    
    public static Result genResult(int code, String message, Object object) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        result.setData(object);
        return result;
    }
}
