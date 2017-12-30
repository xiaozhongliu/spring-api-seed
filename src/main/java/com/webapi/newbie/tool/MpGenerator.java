package com.webapi.newbie.tool;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * Mabatis Plus 代码生成器
 */
public class MpGenerator {

    public static void main(String[] args) {
        generateByTables("com.webapi.newbie", "account", "account_role", "bookmark");
    }

    private static void generateByTables(String packageName, String... tableNames){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        StrategyConfig strategyConfig = new StrategyConfig();
        TemplateConfig tplConfig = new TemplateConfig();
        GlobalConfig config = new GlobalConfig();

        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://120.55.240.107:3306/test")
                .setUsername("liuxiaozhong")
                .setPassword("JbKRTggMsuFOwPsJPjZK")
                .setDriverName("com.mysql.jdbc.Driver");
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);
        config.setActiveRecord(false)
                .setAuthor("xiaozhong")
                .setOutputDir("./src/main/java/")
                .setMapperName("%sDao")
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setEnableCache(false)
                .setFileOverride(false);
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setTemplate(tplConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

}