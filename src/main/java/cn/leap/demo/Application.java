package cn.leap.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication
@MapperScan({"cn.leap.demo.modules.*.dao"})//设置扫描dao层范围
@DubboComponentScan(basePackages = {"cn.leap.demo.modules.system.service"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
