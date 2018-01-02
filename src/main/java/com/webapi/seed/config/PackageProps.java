package com.webapi.seed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "package")
public class PackageProps {

    private String name;
    private String author;
    private String rootPath;
    private String baseController;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getBaseController() {
        return baseController;
    }

    public void setBaseController(String baseController) {
        this.baseController = baseController;
    }

}
