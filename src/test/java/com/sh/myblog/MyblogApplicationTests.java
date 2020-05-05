package com.sh.myblog;

import com.sh.myblog.entity.User;
import com.sh.myblog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {


    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        User user = userService.getById(1L);
        System.out.println(user.toString());
    }

}
