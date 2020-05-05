package com.sh.myblog.service.impl;

import com.sh.myblog.entity.Post;
import com.sh.myblog.mapper.PostMapper;
import com.sh.myblog.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2020-05-04
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}
