package com.tree.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tree.domain.Category;
import com.tree.domain.ResponseResult;

public interface CategoryService extends IService<Category> {
    //查询文章分类接口
    public ResponseResult getCategoryList();
}
