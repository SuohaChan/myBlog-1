package com.tree.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tree.domain.Link;
import com.tree.domain.ResponseResult;
import com.tree.vo.PageVo;

public interface LinkService extends IService<Link> {
    //查询所有友链
    public ResponseResult getAllLink();

    //分页查询友链
    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}
