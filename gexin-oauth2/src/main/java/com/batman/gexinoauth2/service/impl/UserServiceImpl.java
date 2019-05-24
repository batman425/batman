package com.batman.gexinoauth2.service.impl;

import com.batman.gexinoauth2.model.User;
import com.batman.gexinoauth2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author liusongwei
 * @Title: UserServiceImpl
 * @ProjectName claimoauth
 * @Description: TODO
 * @date 2018/11/1615:12
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    //@Autowired
  //  private UsersService usersService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("start oauth UserServiceImpl: " + username);

       /* SysUsers sysUsers2 = usersService.queryUser(sysUsers);
        if (sysUsers2 == null) {
            log.error("Could not find the user : " + username);
            throw new UsernameNotFoundException("Could not find the user '" + username + "'");
        }*/

       // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
       // String encodedPassword = passwordEncoder.encode(sysUsers2.getPassword().trim());
      //  Boolean bl = passwordEncoder.matches("123456",sysUsers2.getPassword().trim());
      //  System.out.println("密钥是否相同" + bl);
        User user = new User();
        user.setUsername("111");
        user.setPassword("2222");

        return new CustomUserDetails(user, true, true, true, true, null);
    }

}
