package com.common;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {

    SUCCESS(1,""),//接口调用成功
    FAIL(0,""),//接口调用失败
    DEFAULT_CODE(0,""),//接口默认返回ERROR_CODE

    CODE(2,""),//图片验证码
    RELOGIN(401,""),//重新登录
    UNAUTHORIZED(403,""),//未认证（签名错误）
    NOT_FOUND(404,""),//接口不存在
    INTERNAL_SERVER_ERROR(10000,""),//服务器内部错误
    BUSINESS_ERROR(10001,""),//业务错误
    NOT_FOUND_CODE(10002,"验证码不能为空!"),
    NOT_FOUND_PARAM(10003,"参数不能为空!"),
    TYPE_ERROR(10004,"分类类型传入错误!"),
    NOT_FOUND_PANDA(10005,"未查询到熊猫信息"),//接口不存在



    NO_EXIST_USER(20000,"用户不存在请注册"),//用户不存在请注册
    EXIST_USER(20001,"用户已存在请登录"),
    EXIST_NICK_NAME(20002,"用户名已存在!"),
    EXIST_WALLET_ACCOUT(20003,"钱包账户已存在!"),
    NOT_FOUND_NICK_NAME(20004,"昵称不能为空!"),
    NOT_FOUND_PASSWORD(20006,"密码不能为空!"),
    NOT_FOUND_CONFIRM_PASSWORD(20007,"确认密码不能为空!"),
    NOT_FOUND_MOBILE(20008,"手机号不能为空!"),
    NOT_FOUND_HEAD_PNG(20009,"用户头像不能为空!"),
    ERROR_PASSWORD(20010,"密码错误"),
    NOT_EQUAL_PASSWORD(20011,"密码与确认密码不一致"),
    NOT_ENOUGH_MONEY(20012,"代币不足");



    public int code;
    public String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
