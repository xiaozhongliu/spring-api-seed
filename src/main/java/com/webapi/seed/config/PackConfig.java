package com.webapi.seed.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "package")
public class PackConfig {

    public String name;
    public String author;
    public String rootPath;
    public String baseController;

}
