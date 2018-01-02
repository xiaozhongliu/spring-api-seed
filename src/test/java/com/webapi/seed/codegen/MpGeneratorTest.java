package com.webapi.seed.codegen;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.webapi.seed.Application;
import com.webapi.seed.config.PackageProps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis Plus Code Generator
 *
 * @author xiaozhong
 * @since 2017-12-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MpGeneratorTest {

    @Autowired
    private PackageProps packProps;
    @Autowired
    private DataSourceProperties dsProps;

    @Test
    public void generate() {
        generateInner(
                packProps.getName(),
                "account",
                "account_role",
                "bookmark"
        );
    }

    private void generateInner(String packageName, String... tableNames) {

        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        StrategyConfig strategyConfig = new StrategyConfig();
        PackageConfig packConfig = new PackageConfig();
        TemplateConfig tplConfig = new TemplateConfig();
        InjectionConfig injectConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        config.setAuthor(packProps.getAuthor())
                .setOutputDir(packProps.getRootPath())
                .setFileOverride(false)
                .setEnableCache(true)
                .setActiveRecord(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setMapperName("%sDao");
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName(dsProps.getDriverClassName())
                .setUrl(dsProps.getUrl())
                .setUsername(dsProps.getUsername())
                .setPassword(dsProps.getPassword());
        strategyConfig.setInclude(tableNames)
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setSuperControllerClass(packProps.getBaseController());
        packConfig.setParent(packageName)
                .setController("controller")
                .setEntity("entity")
                .setMapper("dao");

        // close default generation on dao and xml files
        tplConfig.setMapper(null).setXml(null);

        // customize out dir of generated files
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format("%s/com/webapi/seed/dao/%sDao.java", packProps.getRootPath(), tableInfo.getEntityName());
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
