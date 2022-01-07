package cn.waifutong.experimentData.util;

public class Constants {
    public static final int RESULT_CODE_SUCCESS = 200;  // 成功处理请求
    public static final int RESULT_CREATED_CODE_SUCCESS = 201;  // 创建成功
    //public static final int RESULT_DELETE_CODE_SUCCESS = 204;  // 删除成功
    public static final int RESULT_CODE_BAD_REQUEST = 400;  // 请求的地址不存在或者包含不支持的参数
    public static final int RESULT_UNAUTHORIZED_CODE_REQUEST = 401;  // 未授权
    public static final int RESULT_FORBIDDEN_CODE_REQUEST = 403;  // 被禁止访问
    public static final int RESULT_NOT_FOUND_CODE_REQUEST = 404;  // 请求资源不存在
    public static final int RESULT_CODE_NOT_LOGIN = 402;  // 未登录
    public static final int RESULT_CODE_PARAM_ERROR = 501;  // 传参错误
    public static final int RESULT_CODE_CHUNK_EXIST = 298;  // 自定义code 分片已存在
    public static final int RESULT_CODE_CHUNK_NOTEXIST = 299;  // 自定义code 分片不存在
    public static final int RESULT_CODE_SERVER_ERROR = 500;  // 服务器错误
    public static final int RESULT_NOT_TOKEN_ERROR = 301;  // 获取不到token，token认证失败

}
