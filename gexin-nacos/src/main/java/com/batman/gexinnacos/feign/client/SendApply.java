package com.batman.gexinnacos.feign.client;

import com.batman.gexinnacos.feign.hystrix.SendApplyFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liusongwei
 * @Title: SendApply
 * @ProjectName batman
 * @Description: TODO
 * @date 2019/5/1715:39
 */
@FeignClient(name="gexindemo",fallback = SendApplyFallBack.class)
public interface SendApply {
    @RequestMapping(value="/applyInfo", method = RequestMethod.GET)
    void applyInfo();
}
