package com.tree.controller;

import com.tree.annotation.mySystemLog;
import com.tree.domain.ResponseResult;
import com.tree.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @mySystemLog(xxbusinessName = "获取文章种类")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }
}
