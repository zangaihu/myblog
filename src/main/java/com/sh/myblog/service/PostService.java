package com.sh.myblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sh.myblog.entity.Post;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2020-05-04
 */
public interface PostService extends IService<Post> {



    /**
     * 初始化首页排行榜
     */
    void initIndexWeekRank();

    /**
     * 分页查询
     * @param page
     * @param categoryId
     * @param order
     * @return
     */
    public IPage paging(Page page, Long categoryId, String order);
}
