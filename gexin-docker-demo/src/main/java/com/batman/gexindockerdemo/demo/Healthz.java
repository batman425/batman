package com.batman.gexindockerdemo.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liusongwei
 * @Title: Healthz
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/6/69:16
 */
@RestController
@RequestMapping(path = "/")
public class Healthz {
    @GetMapping(path = "/healthz",produces = MediaType.TEXT_PLAIN_VALUE)
    public String healthz(){
        return "ok";
    }
}
