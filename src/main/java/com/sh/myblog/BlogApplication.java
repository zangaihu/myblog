package com.sh.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created By Sunhu At 2020/5/4 20:16
 *
 * @author Sun
 */
//@EnableCaching
@SpringBootApplication
@MapperScan("com.sh.myblog.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class);
    }
}
