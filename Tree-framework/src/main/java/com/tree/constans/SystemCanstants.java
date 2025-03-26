package com.tree.constans;

/**
 * @author 35238
 * @date 2023/7/19 0019 19:14
 */
//字面值(代码中的固定值)处理，把字面值都在这里定义成常量
public class SystemCanstants {

    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;

    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    //文章列表当前查询页数
    public static final int ARTICLE_STATUS_CURRENT = 1;

    /**
     * 文章列表每页显示的数据条数
     */
    public static final int ARTICLE_STATUS_SIZE = 10;

    /**
     * 分类表的分类状态是正常状态
     */
    public static final String STATUS_NORMAL = "0";

    /**
     * 友链状态为审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     * 友链状态为审核未通过
     */
    public static final String LINK_STATUS_E = "1";
    /**
     * 友链状态为未审核
     */
    public static final String LINK_STATUS_N = "2";

    /**
     * 评论区的某条评论是根评论
     */
    public static final String COMMENT_ROOT = "-1";
    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";

    /**
     * 权限类型，菜单
     */
    public static final String TYPE_MENU = "C";

    /**
     * 权限类型，按钮
     */
    public static final String TYPE_BUTTON = "F";
}