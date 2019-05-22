package com.batman.gexinloges.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liusongwei
 * @Title: LogController
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/2014:00
 */
@RestController
public class LogController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/log")
    public String sayHello(){
        logger.info("hello world");
        return "Hello,World!";
    }
}
