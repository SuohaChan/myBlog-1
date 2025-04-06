package com.tree.controller;


import com.tree.domain.Article;
import com.tree.domain.ResponseResult;
import com.tree.dto.AddArticleDto;
import com.tree.dto.ArticleDto;
import com.tree.service.ArticleService;
import com.tree.vo.ArticleByIdVo;
import com.tree.vo.PageVo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    @PreAuthorize("@ps.hasPermission('content:article:writer')")//权限控制
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    //-----------------------------分页查询博客文章---------------------------

    @GetMapping("/list")
    public ResponseResult list(Article article, Integer pageNum, Integer pageSize){
        PageVo pageVo = articleService.selectArticlePage(article,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }

    //---------------------------根据文章id来修改文章--------------------------

    @GetMapping(value = "/{id}")
    //①先查询根据文章id查询对应的文章
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        ArticleByIdVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }

    @PutMapping
    //②然后才是修改文章
    public ResponseResult edit(@RequestBody ArticleDto article){
        articleService.edit(article);
        return ResponseResult.okResult();
    }

    //---------------------------根据文章id来删除文章-------------------------

    @DeleteMapping
    public ResponseResult remove(@RequestParam(value = "ids")String ids) {
        if (!ids.contains(",")) {
            articleService.removeById(ids);
        } else {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                articleService.removeById(id);
            }
        }
        return ResponseResult.okResult();
    }
}

