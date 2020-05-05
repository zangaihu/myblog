package com.sh.myblog.templates;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sh.myblog.common.templates.DirectiveHandler;
import com.sh.myblog.common.templates.TemplateDirective;
import com.sh.myblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostsTemplate extends TemplateDirective {

    @Autowired
    PostService postService;

    @Override
    public String getName() {
        return "posts";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        Long categoryId = handler.getLong("categoryId", 0);
        int pn = handler.getInteger("pn", 1);
        int size = handler.getInteger("size", 10);
        String order = handler.getString("order", "created");

        Page page = new Page(pn, size);
        IPage results = postService.paging(page, categoryId, order);
        handler.put(RESULTS, results).render();
    }
}