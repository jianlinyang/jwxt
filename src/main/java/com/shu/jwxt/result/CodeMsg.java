package com.shu.jwxt.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>CodeMessage</h1>
 *
 * @author yang
 * @date 2019/6/27 19:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeMsg {
    private String code;
    private String msg;
    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg("0", "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg("500100", "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg("500101", "参数校验异常: %s");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg("500102", "请求非法");

    //登录模块
    public static CodeMsg SESSION_ERROR = new CodeMsg("500210", "您还没有登录,请先登录");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg("500211", "密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg("500212", "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg("500213", "手机号错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg("500214", "手机号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg("500215", "用户名密码错误");
    public static CodeMsg ACCESS_LIMIT = new CodeMsg("500216", "访问受限");
    public static CodeMsg USER_EXIST = new CodeMsg("500217", "用户名已存在");
    public static CodeMsg HAS_LOGIN = new CodeMsg("500217", "您已登录,请勿重复登录");

    //抢课模块
    public static CodeMsg MIAO_SHA_OVER = new CodeMsg("500500", "课程库存不足");
    public static CodeMsg REPEATE_MIAO_SHA = new CodeMsg("500501", "不能重复抢课");
    public static CodeMsg MIAOSHA_FAIL = new CodeMsg("500502", "抢课失败");

    public CodeMsg fillArgs(Object... args) {
        String format = String.format(this.msg, args);
        return new CodeMsg(this.code, format);
    }
}
