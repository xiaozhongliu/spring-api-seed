package com.webapi.seed.tool;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * Mybatis Plus Code Generator
 * @author xiaozhong
 * @since 2017-12-30
 */
public class MpGenerator {

    public static void main(String[] args) {
        generateByTables("com.webapi.seed", "account", "account_role", "bookmark");
    }

    private static void generateByTables(String packageName, String... tableNames){

        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        StrategyConfig strategyConfig = new StrategyConfig();
        PackageConfig packConfig = new PackageConfig();
        TemplateConfig tplConfig = new TemplateConfig();
        InjectionConfig injectConfig = new InjectionConfig() {
            @Override public void initMap() {}
        };

        config.setAuthor("xiaozhong")
            .setOutputDir("./src/main/java/")
            .setFileOverride(false)
            .setEnableCache(true)
            .setActiveRecord(false)
            .setBaseResultMap(true)
            .setBaseColumnList(true)
            .setMapperName("%sDao");
        dataSourceConfig.setDbType(DbType.MYSQL)
            .setDriverName("com.mysql.jdbc.Driver")
            .setUrl("jdbc:mysql://120.55.240.107:3306/test")
            .setUsername("liuxiaozhong")
            .setPassword("JbKRTggMsuFOwPsJPjZK");
        strategyConfig.setInclude(tableNames)
            .setCapitalMode(true)
            .setEntityLombokModel(true)
            .setDbColumnUnderline(true)
            .setNaming(NamingStrategy.underline_to_camel);
        packConfig
            .setParent(packageName)
            .setController("controller")
            .setEntity("entity")
            .setMapper("dao");

        // close default generation on dao and xml files
        tplConfig.setMapper(null).setXml(null);

        // customize out dir of generated files
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("./src/main/java/com/webapi/seed/dao/%sDao.java", tableInfo.getEntityName());
            }
        });
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("./src/main/resources/mapper/%s.xml", tableInfo.getEntityName());
            }
        });
        injectConfig.setFileOutConfigList(focList);

        new AutoGenerator().setGlobalConfig(config)
            .setDataSource(dataSourceConfig)
            .setStrategy(strategyConfig)
            .setPackageInfo(packConfig)
            .setTemplate(tplConfig)
            .setCfg(injectConfig)
            .execute();
    }

}