# Spring Boot 集成 MyBatis, 分页插件 PageHelper, 通用 Mapper 

- [Spring Boot 1.4.3.RELEASE](https://github.com/spring-projects/spring-boot)
- [mybatis-spring-boot-starter 1.1.1](https://github.com/mybatis/spring-boot-starter)
- [mapper-spring-boot-starter 1.0.0](https://github.com/abel533/mapper-boot-starter)
- [pagehelper-spring-boot-starter 1.0.0](https://github.com/pagehelper/pagehelper-spring-boot)

## 项目依赖
```xml
<!--mybatis-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.1.1</version>
</dependency>
<!--mapper-->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
<!--pagehelper-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## application.yml 配置

完整配置可以参考 [src/main/resources/application.yml](https://github.com/abel533/MyBatis-Spring-Boot/blob/master/src/main/resources/application.yml) ，和 MyBatis 相关的部分配置如下：

```yaml
mybatis:
    type-aliases-package: tk.mybatis.springboot.model
    mapper-locations: classpath:mapper/*.xml

mapper:
    mappers:
        - tk.mybatis.springboot.util.MyMapper
    not-empty: false
    identity: MYSQL

pagehelper:
    helperDialect: mysql
    reasonable: true
    supportMethodsArguments: true
    params: count=countSql
```

注意 mapper 配置，因为参数名固定，所以接收参数使用的对象，按照 Spring Boot 配置规则，大写字母都变了带横线的小写字母。针对如 IDENTITY（对应i-d-e-n-t-i-t-y）提供了全小写的 identity 配置，如果 IDE 能自动提示，看自动提示即可。

注意 pagehelper 配置，因为分页插件根据自己的扩展不同，支持的参数也不同，所以不能用固定的对象接收参数，所以这里使用的 `Map<String,String>`，因此参数名是什么这里就写什么，IDE 也不会有自动提示。

## SSM集成的基础项目 
>https://github.com/abel533/Mybatis-Spring

## MyBatis工具 http://www.mybatis.tk

- 推荐使用 Mybatis 通用 Mapper3 https://github.com/abel533/Mapper

- 推荐使用 Mybatis 分页插件 PageHelper https://github.com/pagehelper/Mybatis-PageHelper

## 作者信息

- 作者博客：http://blog.csdn.net/isea533

- 作者邮箱：abel533@gmail.com

- Mybatis工具群： 211286137 (Mybatis相关工具插件等等)
