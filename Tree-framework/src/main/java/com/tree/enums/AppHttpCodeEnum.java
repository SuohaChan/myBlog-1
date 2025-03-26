package com.tree.enums;

//统一响应格式
public enum AppHttpCodeEnum {
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"登录过期，需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONTENT_NOT_NULL(506,"发送的评论内容不能为空"),
    SERVER_ERROR(507, "服务器错误，无法从redis获得信息"),
    FILE_TYPE_ERROR(508,"图片文件类型错误，应为png" ),
    FILE_UPLOAD_ERROR(509,"图片上传失败" ),
    USERNAME_NOT_NULL(510, "用户名不能为空"),
    NICKNAME_NOT_NULL(511, "昵称不能为空"),
    PASSWORD_NOT_NULL(512, "密码不能为空"),
    EMAIL_NOT_NULL(513, "邮箱不能为空"),
    NICKNAME_EXIST(514, "昵称已存在");

    private int code;
    private String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
