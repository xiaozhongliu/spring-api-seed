package com.webapi.seed.domain;

import org.springframework.http.ResponseEntity;

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

    public static ResponseEntity Ok() {
        return ResponseEntity.ok(new Result(1, "success"));
    }

    public static ResponseEntity Ok(Object data) {
        return ResponseEntity.ok(new Result(1, "success", data));
    }

}
