package domain;

import lombok.Data;

import java.util.Map;

@Data
public class Config {

    public Map<String, String> pack;
    public Spring spring;

}
