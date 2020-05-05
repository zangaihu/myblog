package com.sh.myblog.vo;

import com.sh.myblog.entity.Post;
import lombok.Data;

@Data
public class PostVo extends Post {

    private Long authorId;
    private String authorName;
    private String authorAvatar;
    private String categoryName;

}