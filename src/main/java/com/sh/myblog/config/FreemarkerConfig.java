package com.sh.myblog.config;

import com.sh.myblog.templates.HotsTemplate;
import com.sh.myblog.templates.PostsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FreemarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void setUp() {
        configuration.setSharedVariable("posts", applicationContext.getBean(PostsTemplate.class));
        configuration.setSharedVariable("hots", applicationContext.getBean(HotsTemplate.class));
    }
}