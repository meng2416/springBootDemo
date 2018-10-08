package cn.leap.demo.modules.threadPool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadPoolService {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolService.class);

    @Async("myTaskAsyncPool")
    public void produceTask(){
        logger.info("product start");
        System.out.println("任务生产...");
        logger.info("product end");
    }

    @Async("myTaskAsyncPool")
    public void comsumerTask(){
        logger.info("comsumer start");
        System.out.println("任务消费...");
        logger.info("comsumer end");
    }
}
