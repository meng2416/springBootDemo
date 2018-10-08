package cn.leap.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.leap.demo.modules.*.dao"})//设置扫描dao层范围
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
