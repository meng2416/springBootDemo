package cn.leap.demo.modules.system.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.leap.demo.common.shiro.ShiroUtil;
import cn.leap.demo.common.utils.EasyPage;
import cn.leap.demo.modules.system.dao.UserDao;
import cn.leap.demo.modules.system.model.User;

@Service
public class UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public User getUser(){
		User user = new User();
		user.setUserId(1L);
		return userDao.get(user);
	}

	public EasyPage<User> getUserList() {
		User user = new User();
		user.setState(100);
		
		PageHelper.startPage(2, 10);
		
		List<User> userList = userDao.getUserList(user);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		
		EasyPage<User> page = new EasyPage<>(pageInfo.getTotal(), userList);
		return page;
	}

	public User loginCheck(User user) {
		user.setPassword(ShiroUtil.encrypt(user.getPassword(), user.getLoginName()));
		return userDao.loginCheck(user);
	}

	public User doLogin(User user) {
		logger.info("zwb{UserService.doLogin}==>进入函数");
		User authUserInfo = null;
        try {
            //获得Sinro中Subject对象
            Subject subject = SecurityUtils.getSubject();
			// 判断是否已登陆，易登录则跳到首页
            if (subject.isAuthenticated()) {
                logger.info("zwb{UserService.doLogin}==>用户已经认证通过");
                authUserInfo = (User)subject.getPrincipal();
            } else {
                logger.info("zwb{UserService.doLogin}==>用户未通过认证，准备进行认证");
                //生成令牌(传入用户输入的账号和密码)
                UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
                // 通过Sinro进行身份验证,这里会加载自定义的realm,把令牌放到login里面进行查询,如果查询账号和密码时候匹配,如果匹配就把user对象获取出来,失败就抛异常
                token.setRememberMe(true);
                subject.login(token);
                logger.info("zwb{UserService.doLogin}==>用户认证完成");
                authUserInfo = (User)subject.getPrincipal();
                logger.info("zwb{UserService.doLogin}==>认证完成后获取用户信息");
            }
        } catch (AuthenticationException e) {
        	e.printStackTrace();
            logger.error("zwb{UserService.doLogin.532}==>异常信息：" + e.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("zwb{UserService.doLogin.535}==>异常信息：" + e.getMessage());
        }

        return authUserInfo;
	}
	
	public void register() {
		String loginName = "admin";
		String password = "123456";
		
		User user = new User();
		user.setLoginName(loginName);
		
		password = ShiroUtil.encrypt(password, loginName);
		user.setPassword(password);
		user.setNickName(loginName);
		user.setMainPhone("18070504998");
		userDao.insert(user);
	}
}
