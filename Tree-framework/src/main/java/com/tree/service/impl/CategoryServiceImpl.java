package com.tree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tree.constans.SystemCanstants;
import com.tree.domain.Article;
import com.tree.domain.Category;
import com.tree.domain.ResponseResult;
import com.tree.mapper.CategoryMapper;
import com.tree.service.ArticleService;
import com.tree.service.CategoryService;
import com.tree.utils.BeanCopyUtils;
import com.tree.vo.CategoryVo;
import com.tree.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>  implements CategoryService  {

    @Lazy
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        //要求查的是getStatus字段的值为1
        articleWrapper.eq(Article::getStatus, SystemCanstants.ARTICLE_STATUS_NORMAL);
        //查询文章列表，条件就是上一行的articleWrapper
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id,去重。使用stream流来处理上一行得的文章表集合
        Set<Long> categoryIds = articleList.stream()
                .map(new Function<Article, Long>() {
                    @Override
                    public Long apply(Article article) {
                        return article.getCategoryId();
                    }
                })
                //把文章的分类id转换成set集合
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemCanstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //封装成CategoryVo实体类后返回给前端，CategoryVo的作用是只返回前端需要的字段
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
    //----------------------------后台-查询文章分类的接口-------------------------------------
    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemCanstants.NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;
    }

    @Override
    public PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Category> qureyWrapper = new LambdaQueryWrapper<>();

        qureyWrapper.like(StringUtils.hasText(category.getName()), Category::getName, category.getName());
        qureyWrapper.eq(Objects.nonNull(category.getStatus()),Category::getStatus, category.getStatus());

        Page<Category> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, qureyWrapper);

        List<Category> categoryList = page.getRecords();

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(categoryList);

        return pageVo;
    }
}
