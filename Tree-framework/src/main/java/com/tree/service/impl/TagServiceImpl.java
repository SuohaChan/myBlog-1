package com.tree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tree.domain.ResponseResult;
import com.tree.domain.Tag;
import com.tree.dto.TagListDto;
import com.tree.mapper.TagMapper;
import com.tree.service.TagService;
import com.tree.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<Tag>();
        //第二、三个参数是互相比较。第一个参数是判空，当用户没有查询条件时，就不去比较后面两个参数
        queryWrapper.like(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        //第二、三个参数是互相比较。第一个参数是判空，当用户没有查询条件时，就不去比较后面两个参数
        queryWrapper.like(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult updateTagName(Long id, String name, String remark) {
        LambdaUpdateWrapper<Tag> queryWrapper = new LambdaUpdateWrapper<Tag>();
        queryWrapper.eq(Tag::getId,id);
        queryWrapper.set(Tag::getName,name);
        queryWrapper.set(Tag::getRemark,remark);
        this.update(queryWrapper);
        return ResponseResult.okResult();
    }
}
