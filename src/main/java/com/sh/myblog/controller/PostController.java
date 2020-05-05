package com.sh.myblog.controller;


import com.sh.myblog.common.lang.Result;
import com.sh.myblog.util.RedisUtil;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/post")
public class PostController extends BaseController {


    @Resource
    RedisUtil redisUtil;


    @ResponseBody
    @GetMapping("/hots")
    public Result hotPosts(){

        Set<ZSetOperations.TypedTuple> lastWeekRank=redisUtil.getZSetRank("last_week_rank",0,6);

        List<Map<String, Object>> hotPosts=new ArrayList<>();

        for (ZSetOperations.TypedTuple typedTuple : lastWeekRank) {

            Map<String, Object> map=new HashMap();
            map.put("comment_count",typedTuple.getScore());
            map.put("id",redisUtil.hget("rank_post_"+typedTuple.getValue(),"post:id"));
            map.put("title",redisUtil.hget("rank_post_"+typedTuple.getValue(),"post:title"));

            hotPosts.add(map);
        }
        return Result.succ("查询成功",hotPosts);




    }

}
