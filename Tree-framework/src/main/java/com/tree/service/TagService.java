package com.tree.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tree.domain.ResponseResult;
import com.tree.domain.Tag;
import com.tree.dto.TagListDto;
import com.tree.vo.PageVo;
import com.tree.vo.TagVo;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/2 0002 21:14
 */

public interface TagService extends IService<Tag> {
//查询标签列表
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult updateTagName(Long id,String name,String remark);
    //删除标签
    void myRemoveById(Long id);

    //写博文-查询文章标签的接口
    List<TagVo> listAllTag();
}