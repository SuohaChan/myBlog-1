package com.tree.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tree.domain.Category;
import com.tree.domain.ResponseResult;
import com.tree.vo.CategoryVo;
import com.tree.vo.PageVo;

import java.util.List;

public interface CategoryService extends IService<Category> {
    //查询文章分类接口
    public ResponseResult getCategoryList();

    //后台-查询文章分类的接口
    List<CategoryVo> listAllCategory();

    //分页查询分类列表
    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}
