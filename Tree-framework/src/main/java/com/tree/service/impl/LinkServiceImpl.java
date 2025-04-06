package com.tree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tree.constans.SystemCanstants;
import com.tree.domain.Link;
import com.tree.domain.ResponseResult;
import com.tree.mapper.LinkMapper;
import com.tree.service.LinkService;
import com.tree.utils.BeanCopyUtils;
import com.tree.vo.LinkVo;
import com.tree.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<Link>();
        queryWrapper.eq(Link::getStatus, SystemCanstants.LINK_STATUS_NORMAL);
        List<Link> list = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }

    //-----------------------------分页查询友链---------------------------------

    @Override
    public PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.hasText(link.getName()),Link::getName, link.getName());
        queryWrapper.eq(Objects.nonNull(link.getStatus()),Link::getStatus, link.getStatus());

        Page<Link> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        //转换成VO
        List<Link> categories = page.getRecords();

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(categories);
        return pageVo;
    }
}
