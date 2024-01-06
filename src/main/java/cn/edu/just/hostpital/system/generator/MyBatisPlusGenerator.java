package cn.edu.just.hostpital.system.generator;

import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Scanner;

/**
 * MyBatis Plus 代码生成器
 *
 * @author javgo.cn
 * @date 2024/1/6
 */
public class MyBatisPlusGenerator {

    private static final Props PROPS = new Props("generator.properties");

    public static void main(String[] args) {
        // "/hostpital-system" 为父项目的相对路径
        // String projectPath = System.getProperty("user.dir") + "/hostpital-system";
        // 如果是不分模块的单体项目则父项目的相对路径就是项目路径
        String projectPath = System.getProperty("user.dir");
        // 如果就是在 projectPath 下运行，则模块名输入 * 即可
        String moduleName = scanner("模块名");
        if (StringUtils.isNotEmpty(moduleName) && moduleName.contains("*")) {
            moduleName = "";
        }
        // 如果需要生成指定表，则输入表名，多个表名用英文逗号分割。或者输入 *，表示生成所有表。
        String[] tableNames = scanner("表名，多个英文逗号分割").split(",");

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator(initDataSourceConfig());
        // 全局配置
        autoGenerator.global(initGlobalConfig(projectPath));
        // 包配置
        autoGenerator.packageInfo(initPackageConfig(projectPath, moduleName));
        // 自定义配置
        autoGenerator.injection(initInjectionConfig(projectPath, moduleName));
        // 模版引擎配置
        autoGenerator.template(initTemplateConfig());
        // 策略配置
        autoGenerator.strategy(initStrategyConfig(tableNames));
        // 执行 （使用 Velocity 引擎）
        autoGenerator.execute(new VelocityTemplateEngine());
    }

    /**
     * 控制台输入
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + ": ");
        if (scanner.hasNext()) {
            String next = scanner.next();
            if (StringUtils.isNotEmpty(next)) {
                return next;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig initDataSourceConfig() {
        String url = PROPS.getStr("dataSource.url");
        String username = PROPS.getStr("dataSource.username");
        String password = PROPS.getStr("dataSource.password");

        return new DataSourceConfig.Builder(url, username, password)
                .dbQuery(new MySqlQuery())
                .build();
    }

    /**
     * 全局配置
     */
    private static GlobalConfig initGlobalConfig(String projectPath) {
        return new GlobalConfig.Builder()
                .outputDir(projectPath + "/src/main/java") // 生成文件的输出目录
                .author("javgo") // 开发人员
                .disableOpenDir() // 是否打开输出目录
                .enableSwagger() // 是否开启 swagger2 模式
                .dateType(DateType.ONLY_DATE) // 时间类型对应策略
                .build();
    }

    /**
     * 包配置
     */
    private static PackageConfig initPackageConfig(String projectPath, String moduleName) {
        return new PackageConfig.Builder()
                .moduleName(moduleName) // 模块名
                .parent(PROPS.getStr("package.base")) // 父包名
                .entity("model") // 实体类包名
                // 生成 mapper xml 文件
                // .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper/" + moduleName)) // xml 文件输出路径
                // 生成基于注解的 mapper 接口
                .pathInfo(Collections.singletonMap(OutputFile.mapper, projectPath + "/src/main/java/" + PROPS.getStr("package.base").replace(".", "/") + "/" + moduleName + "/mapper"))
                .build();
    }

    /**
     * 模版配置（可以对 Controller、Service、ServiceImpl、Mapper、Entity 等模版进行配置，设置为空表示不生成对应的文件）
     */
    private static TemplateConfig initTemplateConfig() {
        TemplateConfig.Builder builder = new TemplateConfig.Builder();
        builder.entity("/templates/entity.java.vm") // 实体类模版
                .mapper("/templates/mapper.java.vm") // mapper 模版
                .service("/templates/service.java.vm") // service 模版
                .serviceImpl("/templates/serviceImpl.java.vm") // serviceImpl 模版
                .controller("/templates/controller.java.vm") // controller 模版
                .xml(null); // xml 模版需要单独配置，这里设置为空表示不生成 xml 文件
        return builder.build();
    }

    /**
     * 策略配置
     */
    private static StrategyConfig initStrategyConfig(String[] tableNames) {
        StrategyConfig.Builder builder = new StrategyConfig.Builder();

        builder.entityBuilder() // 实体策略配置
                .enableFileOverride() // 覆盖已有文件
                .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略(下划线转驼峰)
                .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略(下划线转驼峰)
                .enableLombok() // 开启 lombok 模式
                .formatFileName("%s"); // 自定义文件名格式，%s 为占位符

        builder.mapperBuilder() // mapper 策略配置
                .enableFileOverride() // 覆盖已有文件
                .enableBaseResultMap() // 开启 BaseResultMap
                .formatMapperFileName("%sMapper") // 自定义文件名格式，%s 为占位符
                .formatXmlFileName("%sMapper"); // 自定义文件名格式，%s 为占位符

        builder.serviceBuilder() // service 策略配置
                .enableFileOverride() // 覆盖已有文件
                .formatServiceFileName("%sService") // 自定义文件名格式，%s 为占位符
                .formatServiceImplFileName("%sServiceImpl"); // 自定义文件名格式，%s 为占位符

        builder.controllerBuilder() // controller 策略配置
                .enableFileOverride() // 覆盖已有文件
                .enableRestStyle() // 开启 rest 风格
                .formatFileName("%sController"); // 自定义文件名格式，%s 为占位符

        // 表名带 * 时，可以使用通配符匹配多个表
        if (tableNames.length == 1 && tableNames[0].contains("*")) {
            // 生成 likePrefix 开头的表
            // String[] likeStr = tableNames[0].split("_");
            // String likePrefix = likeStr[0] + "_";
            // builder.likeTable(new LikeTable(likePrefix));

            // 生成全部表
            builder.addInclude(".*");
        } else {
            // 默认生成指定表
            builder.addInclude(tableNames);
        }

        return builder.build();
    }

    /**
     * 自定义配置 (用于自定义模版时设置一些属性)
     */
    private static InjectionConfig initInjectionConfig(String projectPath, String moduleName) {
        // InjectionConfig 一般用于自定义一些属性注入
        return new InjectionConfig.Builder().build();
    }
}
