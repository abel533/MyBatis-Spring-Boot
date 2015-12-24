#Spring Boot集成MyBatis的基础项目

#MyBatis3.3.0

#Spring Boot 1.3.0.RELEASE

项目使用Spring Boot 1.3.0.RELEASE + Mybatis3.3.0

项目集成了Mybatis分页插件和通用Mapper插件

项目使用的mysql数据库，根据需要可以切换为其他数据库

##说明

虽然MyBatis官方提供了`mybatis-spring-boot-starter`，但是该配置的可以控制的地方太少，因此短时间不会直接使用该`starter`。

在集成MyBatis配置`MapperScannerConfigurer`需要特别注意，将该类单独放在一个配置文件中，例如本项目中的`MyBatisMapperScannerConfig`类：

```java
@Configuration
//注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
//MyBatisConfig.class是一个包含了SqlSessionFactory配置的类
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("tk.mybatis.springboot.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.springboot.util.MyMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        //这里使用的通用Mapper的MapperScannerConfigurer，所有有下面这个方法
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
```

##SSM集成的基础项目

###https://github.com/abel533/Mybatis-Spring

##MyBatis工具

###http://www.mybatis.tk

##推荐使用Mybatis通用Mapper3

###https://github.com/abel533/Mapper

##推荐使用Mybatis分页插件PageHelper

###https://github.com/pagehelper/Mybatis-PageHelper

##作者信息

- 作者博客：http://blog.csdn.net/isea533

- 作者邮箱：abel533@gmail.com

- Mybatis工具群： 211286137 (Mybatis相关工具插件等等)
