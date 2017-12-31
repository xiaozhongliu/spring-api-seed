package com.webapi.seed.domain;

public class Result {

    public int code;
    public String msg;
    public Object data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
