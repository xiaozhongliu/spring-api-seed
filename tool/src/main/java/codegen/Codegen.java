package codegen;

import codegen.domain.Config;
import codegen.domain.Pack;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * mybatis plus code generator
 *
 * @author xiaozhong
 * @since 2017-12-30
 */
@SpringBootApplication
public class Codegen {

    private static Config config;

    public static void main(String[] args) {
        SpringApplication.run(Codegen.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return evt -> {

            try {
                String ymlPath = "./api/src/main/resources/application.yml";
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                config = mapper.readValue(new File(ymlPath), Config.class);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Pack pkConfig = config.pack;
            String apiPath = config.pack.apiPath;
            Map<String, String> dsConfig = config.spring.datasource;

            GlobalConfig globalConfig = new GlobalConfig();
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            StrategyConfig strategyConfig = new StrategyConfig();
            PackageConfig packConfig = new PackageConfig();
            TemplateConfig tplConfig = new TemplateConfig();
            InjectionConfig injectConfig = new InjectionConfig() {
                @Override
                public void initMap() {
                }
            };

            globalConfig.setAuthor(pkConfig.author)
                    .setOpen(false)
                    .setOutputDir(apiPath)
                    .setEnableCache(true)
                    .setFileOverride(false)
                    .setActiveRecord(false)
                    .setBaseResultMap(true)
                    .setBaseColumnList(true)
                    .setMapperName("%sDao");
            dataSourceConfig.setDbType(DbType.MYSQL)
                    .setDriverName(dsConfig.get("driver-class-name"))
                    .setUrl(dsConfig.get("url"))
                    .setUsername(dsConfig.get("username"))
                    .setPassword(dsConfig.get("password"));
            strategyConfig.setInclude(pkConfig.tables)
                    .setCapitalMode(true)
                    .setEntityLombokModel(true)
                    .setDbColumnUnderline(true)
                    .setNaming(NamingStrategy.underline_to_camel)
                    .setSuperControllerClass(pkConfig.baseController);
            packConfig.setParent(pkConfig.name)
                    .setController("controller")
                    .setServiceImpl("service")
                    .setEntity("entity")
                    .setMapper("dao");

            // disable default generation on dao, xml, service and serviceImpl files
            tplConfig.setXml(null)
                    .setMapper(null)
                    .setService(null)
                    .setServiceImpl(null);

            // customize out dir of generated files
            List<FileOutConfig> focList = new ArrayList<>();
            focList.add(new FileOutConfig("/templates/mapper.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return String.format("%s/com/example/dao/%sDao.java", apiPath, tableInfo.getEntityName());
                }
            });
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return String.format("./api/src/main/resources/mapper/%s.xml", tableInfo.getEntityName());
                }
            });
            focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return String.format("%s/com/example/service/%sService.java", apiPath, tableInfo.getEntityName());
                }
            });
            injectConfig.setFileOutConfigList(focList);

            new AutoGenerator().setGlobalConfig(globalConfig)
                    .setDataSource(dataSourceConfig)
                    .setStrategy(strategyConfig)
                    .setPackageInfo(packConfig)
                    .setTemplate(tplConfig)
                    .setCfg(injectConfig)
                    .execute();
        };
    }

}
