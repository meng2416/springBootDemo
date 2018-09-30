package cn.leap.demo.modules.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.leap.demo.common.utils.EasyPage;
import cn.leap.demo.modules.system.model.User;
import cn.leap.demo.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/UserController", description = "用户功能")
@RequestMapping("/user")
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	/**
	 * 访问链接：/user/get
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "获取用户", notes = "根据用户名，获取用户")
	@ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")
	@GetMapping(value = "/get")
	public User getUser(String name){
		log.info("测试获取数据");
		return userService.getUser();
	}

	/**
	 * 访问链接：/user/getList
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "获取用户list", notes = "测试，获取用户list")
	@ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")
	@GetMapping(value = "/getList")
	public EasyPage<User> getUserList(String name){
		return userService.getUserList();
	}

	/**
	 * 访问链接：/user/doLogin?loginName=admin&password=123456
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "登入", notes = "根据用户名，密码登入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "loginName", value = "登入名", required = true, dataType = "String"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
	})
	@GetMapping(value = "/doLogin")
	public User doLogin(User user){
		User login = userService.doLogin(user);
		return login;
	}
	
	@ApiOperation(value = "注册", notes = "测试代码，后台给参数")
	@PostMapping(value = "/register")
	public String register(){
		userService.register();
		return "注册成功";
	}
}
