package cn.leap.demo.common.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/user/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		// 静态资源配置

		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/dist/**", "anon");
		filterChainDefinitionMap.put("/layer-v3.0.3/**", "anon");
		filterChainDefinitionMap.put("/layui/**", "anon");
		filterChainDefinitionMap.put("/My97DatePicker/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/plugins/**", "anon");
		// 请求连接配置
		filterChainDefinitionMap.put("/myServlet/**", "anon");
		filterChainDefinitionMap.put("/user/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger-resources", "anon");
		filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
		
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/exit", "logout");
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		log.info("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}
	
	/**
     * @description: 注意方法返回值SecurityManager为org.apache.shiro.mgt.SecurityManager, 不要导错包
     */
	@Bean
	public SecurityManager createSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(createMyRealm());
		// 自定义sessionManager
        securityManager.setSessionManager(createMySessionManager());
        // 自定义rememberMeManager
        securityManager.setRememberMeManager(createRememberMeManager());
        // 自定义cacheManager
        securityManager.setCacheManager(createCacheManager());
        log.info("配置rsecurityManager");
        return securityManager;
	}

	@Bean
	public ShiroRealm createMyRealm() {
		// 加密相关
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 散列算法
		hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtil.HASH_ALGORITHM_NAME);
		// 散列次数
		hashedCredentialsMatcher.setHashIterations(ShiroUtil.HASH_ITERATIONS);
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		ShiroRealm myRealm = new ShiroRealm();
		myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		return myRealm;
	}
	
	/**
     * @description: 自定义缓存管理器
     * @author cheng
     * @dateTime 2018/4/24 15:59
     */
	@Bean
    public RedisCacheManager createCacheManager() {
        RedisCacheManager redisShiroCacheManager = new RedisCacheManager();
        log.info("自定义CacheManager");
        return redisShiroCacheManager;
    }
    
    /**
     * @description: 自定义sessionDao
     * @author cheng
     * @dateTime 2018/4/24 10:47
     */
	@Bean
    public RedisSessionDAO createRedisShiroSessionDao() {
        RedisSessionDAO sessionDao = new RedisSessionDAO();
        // 设置缓存管理器
        sessionDao.setCacheManager(createCacheManager());
        log.info("自定义sessionDao");
        return sessionDao;
    }
    
    /**
     * @description: 自定义shiro session cookie
     * @author cheng
     * @dateTime 2018/4/24 11:09
     */
	@Bean
    public SimpleCookie createSessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie(ShiroUtil.SESSIONID_COOKIE_NAME);
        // 保证该系统不会受到跨域的脚本操作攻击
        simpleCookie.setHttpOnly(true);
        // 定义Cookie的过期时间，单位为秒，如果设置为-1表示浏览器关闭，则Cookie消失
        simpleCookie.setMaxAge(-1);
        log.info("自定义SessionIdCookie");
        return simpleCookie;
    }
    
    /**
     * @description: 自定义sessionManager
     * @author cheng
     * @dateTime 2018/4/24 10:37
     */
	@Bean
    public SessionManager createMySessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 自定义sessionDao
        sessionManager.setSessionDAO(createRedisShiroSessionDao());
        // session的失效时长,单位是毫秒
        sessionManager.setGlobalSessionTimeout(ShiroUtil.GLOBAL_SESSION_TIMEOUT);
        // 删除失效的session
        sessionManager.setDeleteInvalidSessions(true);
        // 所有的session一定要将id设置到Cookie之中，需要提供有Cookie的操作模版
        sessionManager.setSessionIdCookie(createSessionIdCookie());
        // 定义sessionIdCookie模版可以进行操作的启用
        sessionManager.setSessionIdCookieEnabled(true);
        log.info("配置sessionManager");
        return sessionManager;
    }
    
    /**
     * @description: 记住我cookie
     * @author cheng
     * @dateTime 2018/4/24 15:39
     */
    public SimpleCookie createRemeberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie(ShiroUtil.REMEBER_ME_COOKIE_NAME);
        // 保证该系统不会受到跨域的脚本操作攻击
        simpleCookie.setHttpOnly(true);
        // 定义Cookie的过期时间，单位为秒，如果设置为-1表示浏览器关闭，则Cookie消失
        simpleCookie.setMaxAge(-1);
        log.info("自定义RemeberMeCookie");
        return simpleCookie;
    }
    
    /**
     * @description: 自定义记住我
     * @author cheng
     * @dateTime 2018/4/24 15:35
     */
   public CookieRememberMeManager createRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        // 设置记住我的cookie
        cookieRememberMeManager.setCookie(createRemeberMeCookie());
        log.info("配置RemeberMeManager");
        return cookieRememberMeManager;
   }
}