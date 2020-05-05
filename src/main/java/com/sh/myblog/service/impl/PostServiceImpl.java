package com.sh.myblog.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.myblog.entity.Post;
import com.sh.myblog.mapper.PostMapper;
import com.sh.myblog.service.PostService;
import com.sh.myblog.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2020-05-04
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {


    @Resource
    RedisUtil redisUtil;

    @Resource
    PostMapper postMapper;

    /**
     * 初始化首页排行榜
     */
    @Override
    public void initIndexWeekRank() {
        List<Post> last7DaysPosts = this.list(new QueryWrapper<Post>()
                .ge("created", DateUtil.offsetDay(new Date(), -10).toJdkDate())
                .select("id, title, user_id, comment_count, view_count, created")
        );

        for (Post post : last7DaysPosts) {
            String key = "day_rank:" + DateUtil.format(post.getCreated(), DatePattern.PURE_DATE_PATTERN);
            //设置有效期
            long between = DateUtil.between(new Date(), post.getCreated(), DateUnit.DAY);
            long expireTime = (10 - between) * 24 * 60 * 60;
            System.out.println(post);
            System.out.println("betweeen"+between);
            System.out.println("expireTime"+expireTime);
            //缓存进redis
            redisUtil.zSet(key, post.getId(), post.getCommentCount());
            //设置过期时间
            redisUtil.expire(key, expireTime);
            this.hashCachePostIdAndTitle(post);
        }
        this.zUnionAndStoreLast7DaysForLastWeekRank();
    }


    /**
     * hash结构存贮文章标题和id
     *
     * @param post
     */
    private void hashCachePostIdAndTitle(Post post) {

        //先查询是否存在
        boolean isExist = redisUtil.hasKey("rank_post" + post.getId());
        if (!isExist) {
            redisUtil.hset("rank_post_" + post.getId(), "post:id", post.getId());
            redisUtil.hset("rank_post_" + post.getId(), "post:title", post.getTitle());
            redisUtil.hset("rank_post_" + post.getId(), "post:commentcount", post.getCommentCount());
        }
    }

    /**
     * 把最近7天的文章评论数量统计一下
     * 用于首页的7天评论排行榜
     */
    private void zUnionAndStoreLast7DaysForLastWeekRank() {
        String prifix = "day_rank:";
        List keys = new ArrayList();
        String key = prifix + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        for (int i = -7; i < 0; i++) {
            Date date = DateUtil.offsetDay(new Date(), i).toJdkDate();
            keys.add(prifix + DateUtil.format(date, DatePattern.PURE_DATE_PATTERN));
        }
        redisUtil.zUnionAndStore(key, keys, "last_week_rank");

    }


    //@Cacheable(cacheNames = "cache_post", key = "'page_' + #page.current + '_' + #page.size " +
    //        "+ '_query_' +#categoryId + '_' + #order")
    @Override
    public IPage paging(Page page, Long categoryId, String order) {

        QueryWrapper<Post> wrapper
                = new QueryWrapper<Post>()
                .eq(categoryId != 0, "category_id", categoryId)
                .orderByDesc(order);

        return postMapper.selectPosts(page, wrapper);
    }


}
