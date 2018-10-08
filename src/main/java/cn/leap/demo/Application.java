package cn.leap.demo;

import cn.leap.demo.common.threadpool.TaskThreadPoolConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan({"cn.leap.demo.modules.*.dao"})//设置扫描dao层范围
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
