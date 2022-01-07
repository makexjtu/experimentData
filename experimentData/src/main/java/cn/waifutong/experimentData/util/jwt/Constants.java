package cn.waifutong.experimentData.util.jwt;

import java.util.UUID;

public class Constants {

    /**
     * 给jwt用的
     */
    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "tjwftsupplychain2021";
    
    /**
     * 令牌有效期
     */
    public static final int JWT_TTL = 2*60*60*1000;  //millisecond

}
