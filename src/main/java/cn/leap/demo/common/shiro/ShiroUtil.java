package cn.leap.demo.common.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author cheng
 * @className: ShiroUtil
 * @description: 管理shiro session的工具类
 * @dateTime 2018/4/19 10:15
 */
public class ShiroUtil {

    /**
     * shiro加密算法
     */
    public static final String HASH_ALGORITHM_NAME = "md5";
    /**
     * 散列次数
     */
    public static final int HASH_ITERATIONS = 2;
    /**
     * 全局session过期时间
     */
    public static final int GLOBAL_SESSION_TIMEOUT = 60000;
    /**
     * 自定义shiro session的cookie名称
     */
    public static final String SESSIONID_COOKIE_NAME = "SHIRO_SESSION_ID";
    /**
     * 自定义remeber me的cookie名称
     */
    public static final String REMEBER_ME_COOKIE_NAME = "REMEBER_ME";
   
    /**
     * @description: 加密密码
     * @author cheng
     * @dateTime 2018/4/23 15:01
     */
    public static String encrypt(String password, String salt) {
    	/*SimpleHash hash = new SimpleHash(HASH_ALGORITHM_NAME, password, salt, HASH_ITERATIONS);
        return hash.toString();*/
        
        Md5Hash md5Hash = new Md5Hash(password, salt, HASH_ITERATIONS);
        return md5Hash.toString();
    }

}