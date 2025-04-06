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
import com.tree.utils.BeanCopyUtils;
import com.tree.vo.PageVo;
import com.tree.vo.TagVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    private final TagMapper tagMapper;

    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

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

    @Override
    public void myRemoveById(Long id) {
        // 通过数据id查找数据
        Tag tag  = tagMapper.selectById(id);
        // 把值传入数据库进行更新
        if (tag != null){
            // 把 def_flag=1 为逻辑删除
            int flag = 1;
            tagMapper.myUpdateById(id,flag);
        }
    }

    //---------------------------写博文-查询文章标签的接口---------------------------
    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);//在查询时只返回 Tag 实体的 id 和 name 字段对应的数据
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }
}
