package com.common;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setResult(ResultCode.SUCCESS)
                .setErrorCode(ResultCode.DEFAULT_CODE)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setResult(ResultCode.SUCCESS)
                .setErrorCode(ResultCode.DEFAULT_CODE)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setResult(ResultCode.FAIL)
                .setErrorCode(ResultCode.INTERNAL_SERVER_ERROR)
                .setMessage(message);
    }

    public static Result genFailResult(ResultCode resultCode,String message) {
        return new Result()
                .setResult(ResultCode.FAIL)
                .setErrorCode(resultCode)
                .setMessage(message);
    }

    public static Result genFailResult(int code,String message) {
        return new Result()
                .setResult(ResultCode.FAIL)
                .setErrorCode(code)
                .setMessage(message);
    }

    public static Result genFailResult(ResultCode resultCode) {
        return new Result()
                .setResult(ResultCode.FAIL)
                .setErrorCode(resultCode.getCode())
                .setMessage(resultCode.getMessage());
    }



}
