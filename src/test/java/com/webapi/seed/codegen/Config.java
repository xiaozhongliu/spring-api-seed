package com.webapi.seed.codegen;

import lombok.Data;

import java.util.Map;

@Data
public class Config {

    public Map<String, String> pack;
    public Spring spring;

}

@Data
class Spring {

    public Map<String, String> datasource;

}
