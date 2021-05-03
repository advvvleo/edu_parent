package com.la.TestDemo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.junit.Test;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @since 2021/04/15
 */
public class CodeGenerator {

    @Test
    public void run() {
        //1、创建代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 2、配置 GlobalConfig
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("LA");//添加作者名
        globalConfig.setOpen(false);//生成后是否打开资源管理器
        globalConfig.setFileOverride(false);//是否覆盖原文件
        globalConfig.setServiceName("%sService");    //去掉Service接口的首字母I
        globalConfig.setIdType(IdType.ASSIGN_ID); //主键策略
        globalConfig.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        globalConfig.setSwagger2(true);//开启Swagger2模式
        generator.setGlobalConfig(globalConfig);//将全局设置传入到生成器中

        // 3、配置数据库信息
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/eduonline?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("liao138138");
        dataSourceConfig.setDbType(DbType.MYSQL);
        generator.setDataSource(dataSourceConfig);//将数据库设置传入到生成器中
        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.la");
        pc.setModuleName("eduservice"); //模块名
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        generator.setPackageInfo(pc);//将包设置传入到生成器中

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("edu_teacher");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        generator.setStrategy(strategy);//将生成策略设置传入到生成器中
        // 6、自定义设置
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 7、执行
        generator.execute();
    }
}
