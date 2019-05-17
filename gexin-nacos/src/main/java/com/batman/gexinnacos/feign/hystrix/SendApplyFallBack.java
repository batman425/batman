package com.batman.gexinnacos.feign.hystrix;

import com.batman.gexinnacos.feign.client.SendApply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author liusongwei
 * @Title: SendApply
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/1715:39
 */
@Component
public class SendApplyFallBack implements SendApply {
    private Logger log = LoggerFactory.getLogger(SendApplyFallBack.class);
    @Override
    public void applyInfo() {
        log.info("打印异常信息");
    }
}
