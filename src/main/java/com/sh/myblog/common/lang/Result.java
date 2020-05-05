package com.sh.myblog.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * Created By Sunhu At 2020/5/5 10:22
 *
 * 返回结果
 * @author Sun
 */
@Data
public class Result implements Serializable {


    /**
     * 状态码
     */
    private String code;
    /**
     * 信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;


    /**
     * 成功
     * @param data
     * @return
     */
    public static Result succ(Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMessage("操作成功");
        return m;
    }

    /**
     * 成功
     * @param msg
     * @param data
     * @return
     */
    public static Result succ(String msg, Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMessage(msg);

        return m;
    }

    /**
     * 失败
     * @param mess
     * @return
     */
    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(null);
        m.setMessage(mess);

        return m;
    }

    /**
     * 失败
     * @param mess
     * @param data
     * @return
     */
    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(data);
        m.setMessage(mess);

        return m;
    }
}
