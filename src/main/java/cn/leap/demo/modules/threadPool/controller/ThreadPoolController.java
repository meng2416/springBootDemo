package cn.leap.demo.modules.threadPool.controller;

import cn.leap.demo.modules.threadPool.service.ThreadPoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadPool")
@Api(value = "ThreadPoolController", description = "线程池demo")
public class ThreadPoolController {
    @Autowired
    private ThreadPoolService threadPoolService;

    /**
     * 访问链接：/threadPool/test
     * @return
     */
    @ApiOperation(value = "测试代码", notes = "线程测试")
    @GetMapping(value = "/test")
    public String hello() {
        threadPoolService.produceTask();
        //threadPoolService.comsumerTask();
        return "线程池测试";
    }
}
