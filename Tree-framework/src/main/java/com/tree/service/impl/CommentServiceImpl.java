package com.tree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tree.constans.SystemCanstants;
import com.tree.domain.Comment;
import com.tree.domain.ResponseResult;
import com.tree.domain.User;
import com.tree.enums.AppHttpCodeEnum;
import com.tree.exception.SystemException;
import com.tree.mapper.CommentMapper;
import com.tree.service.CommentService;
import com.tree.service.UserService;
import com.tree.utils.BeanCopyUtils;
import com.tree.vo.CommentVo;
import com.tree.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    //必须commentType为0的时候才增加articleId的
    //判断，并且增加了一个评论类型的添加。
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断
        queryWrapper.eq(SystemCanstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        //根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId,-1);
        //品论类型
        queryWrapper.eq(Comment::getType, commentType);

        //分页查询
        Page<Comment> page = new Page(pageNum,pageSize);
        page(page,queryWrapper);


        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        //查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据根评论的id查询所对应的子评论的集合
     * @param id 根评论的id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }


    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);

        //收集需要查询的id
        List<Long> userIds = commentVos.stream()
                .map(CommentVo::getCreateBy)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 检查 userIds 是否为空
        Map<Long, String> userIdToNicknameMap = new HashMap<>();
        if (!userIds.isEmpty()) {
            // 批量查询用户信息
            List<User> users = userService.listByIds(userIds);
            // 构建用户 ID 到昵称的映射
            userIdToNicknameMap = users.stream()
                    .collect(Collectors.toMap(User::getId, User::getNickName));
        }

        // 设置用户名信息
        for (CommentVo commentVo : commentVos) {
            Long createBy = commentVo.getCreateBy();
            //通过creatyBy赋值
            if ( userIdToNicknameMap.containsKey(createBy)) {
                commentVo.setUsername(userIdToNicknameMap.get(createBy));
            }
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            Long toCommentUserId = commentVo.getToCommentUserId();
            if ( toCommentUserId != -1 && userIdToNicknameMap.containsKey(toCommentUserId)) {
                commentVo.setToCommentUserName(userIdToNicknameMap.get(toCommentUserId));
            }
        }
        return commentVos;
    }

}
