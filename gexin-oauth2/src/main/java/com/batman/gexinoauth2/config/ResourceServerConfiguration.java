package com.batman.gexinoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author liusongwei
 * @Title: ResourceServerConfiguration
 * @Description: TODO
 * @date 2018/11/1615:18
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 不需要令牌,直接访问资源
        http.authorizeRequests().anyRequest().permitAll();
       /* http.authorizeRequests()
                .antMatchers("/oauth/**", "/ok").permitAll()
                .antMatchers("/**").authenticated()
                .anyRequest().authenticated();*/
    }

}
