package com.tree.controller;

import com.tree.annotation.mySystemLog;
import com.tree.domain.Article;
import com.tree.domain.ResponseResult;
import com.tree.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> list() {
        List<Article> list = articleService.list();
        return list;
    }

    @GetMapping("/hotArticleList")
    @mySystemLog(xxbusinessName = "热门文章")
    public ResponseResult hotArticleList() {
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

//-------------------------分页查询文章的列表---------------------------
    @GetMapping("/articleList")
    @mySystemLog(xxbusinessName = "分页查询")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }


//文章内容Detail
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable Long id) {
        return articleService.getArticleDetail(id);
    }

    @PutMapping("/updateViewCount/{id}")
    @mySystemLog(xxbusinessName = "根据文章id从mysql查询文章")//接口描述，用于'日志记录'功能
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }

    //@PutMapping("/updateViewCount/{id}")

}
