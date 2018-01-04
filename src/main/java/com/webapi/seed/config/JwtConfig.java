package com.webapi.seed.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    public String header;
    public String secret;
    public Long expiration;
    public String tokenHead;

}
