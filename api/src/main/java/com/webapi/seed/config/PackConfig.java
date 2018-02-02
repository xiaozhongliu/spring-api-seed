package com.webapi.seed.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "pack")
public class PackConfig {

    public String name;
    public String author;
    public String apiPath;
    public String baseController;
    public String[] tables;
    public String test;

}
