package com.tree.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tree.domain.Comment;
import com.tree.domain.ResponseResult;

public interface CommentService extends IService<Comment> {
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);
    ResponseResult addComment(Comment comment);
}
