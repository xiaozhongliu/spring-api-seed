package com.webapi.seed.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private int code;
    private String msg;
    private Object data;

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(int code, String msg, Object data) {
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

    public static ResponseEntity fail(String msg) {
        return ResponseEntity.ok(new Result(-1, msg));
    }

}
