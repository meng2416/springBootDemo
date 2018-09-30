package cn.leap.demo.modules.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController // 标明这是一个SpringMVC的Controller控制器
@Api(value = "HelloAction", description = "用于演示的action")
public class HelloApplication {
	
	@ApiOperation(value = "测试代码", notes = "注意问题")
	@ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")
	@GetMapping(value = "/hello")
	public String hello(String name) {
		return "hello world";
	}
}
