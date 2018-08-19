package com.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 */
public class Result<T> implements Serializable {


    private static final long serialVersionUID = -8721312475564724557L;

    private int result;
    private int errorCode;
    private int resultSN;
    private String message;
    private T data;


    public Result setResultSN(ResultCode resultCode) {
        this.resultSN = resultCode.code;
        return this;
    }

    public int getResultSN() {
        return resultSN;
    }

    public Result setResultSN(int resultSN) {
        this.resultSN = resultSN;
        return this;
    }



    public Result setResult(ResultCode resultCode) {
        this.result = resultCode.code;
        return this;
    }

    public int getResult() {
        return result;
    }

    public Result setResult(int result) {
        this.result = result;
        return this;
    }

    public Result setErrorCode(ResultCode resultCode) {
        this.errorCode = resultCode.code;
        return this;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Result setErrorCode(int code) {
        this.errorCode = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
