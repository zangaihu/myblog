package com.sh.myblog.service.impl;

import com.sh.myblog.entity.Comment;
import com.sh.myblog.mapper.CommentMapper;
import com.sh.myblog.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
