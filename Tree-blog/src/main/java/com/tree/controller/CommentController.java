package com.tree.controller;

import com.tree.annotation.mySystemLog;
import com.tree.constans.SystemCanstants;
import com.tree.domain.Comment;
import com.tree.domain.ResponseResult;
import com.tree.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @mySystemLog(xxbusinessName = "获取文章评论")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize){
        return  commentService.commentList(SystemCanstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }

    @PostMapping
    @mySystemLog(xxbusinessName = "提交评论")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @mySystemLog(xxbusinessName = "获取友链评论")
    public ResponseResult linkCommentList(Long articleId, Integer pageNum, Integer pageSize){
        return commentService.commentList(SystemCanstants.LINK_COMMENT,null, pageNum, pageSize);
    }
}
