package cn.leap.demo.common.dubbo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@Configuration
@EnableDubbo
public class DubboConfig {
	
	// 相当于<dubbo:application name="demo-producer"/>
    @Bean
    public ApplicationConfig createApplicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("prodiver");
        return applicationConfig;
    }

    // 相当于<dubbo:registry address="zookeeper://127.0.0.1:2181"/>
    @Bean
    public RegistryConfig createRegistryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        return registryConfig;
    }

    // 相当于<dubbo:protocol name="dubbo" port="20880"/>
    @Bean
    public ProtocolConfig createProtocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }
}
