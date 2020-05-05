package com.sh.myblog;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.sh.myblog.entity.Post;
import com.sh.myblog.entity.User;
import com.sh.myblog.service.PostService;
import com.sh.myblog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {


    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Test
    public void contextLoads() {
        User user = userService.getById(1L);
        System.out.println(user.toString());
    }

    @Test
    public void initIndexWeekRank() {

        Post  post=postService.getById(1L);


            String key="day_rank"+DateUtil.format(post.getCreated(), DatePattern.PURE_DATE_PATTERN);
            //设置有效期
            long between=DateUtil.between(new Date(),post.getCreated(), DateUnit.DAY);
            long expireTime=(7-between)*24*60*60;
            System.out.println(post.getCreated());
            System.out.println("betweeen"+between);
            System.out.println("expireTime"+expireTime);



    }

}
