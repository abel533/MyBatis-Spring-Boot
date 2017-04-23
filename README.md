# Spring Boot 集成 MyBatis, 分页插件 PageHelper, 通用 Mapper 

- [Spring Boot 1.5.1.RELEASE](https://github.com/spring-projects/spring-boot)
- [mybatis-spring-boot-starter 1.1.1](https://github.com/mybatis/spring-boot-starter)
- [mapper-spring-boot-starter 1.1.0](https://github.com/abel533/mapper-boot-starter)
- [pagehelper-spring-boot-starter 1.1.0](https://github.com/pagehelper/pagehelper-spring-boot)

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
    <version>1.1.0</version>
</dependency>
<!--pagehelper-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.1.0</version>
</dependency>
```
## Spring DevTools 配置
感谢[emf1002](https://github.com/emf1002)提供的解决方案。

在使用 DevTools 时，通用Mapper经常会出现 class x.x.A cannot be cast to x.x.A。

同一个类如果使用了不同的类加载器，就会产生这样的错误，所以解决方案就是让通用Mapper和实体类使用相同的类加载器即可。

DevTools 默认会对 IDE 中引入的所有项目使用 restart 类加载器，对于引入的 jar 包使用 base 类加载器，因此只要保证通用Mapper的jar包使用 restart
类加载器即可。

在 `src/main/resources` 中创建 META-INF 目录，在此目录下添加 spring-devtools.properties 配置，内容如下：
```properties
restart.include.mapper=/mapper-[\\w-\\.]+jar
restart.include.pagehelper=/pagehelper-[\\w-\\.]+jar
```
使用这个配置后，就会使用 restart 类加载加载 include 进去的 jar 包。

## 集成 MyBatis Generator
通过 Maven 插件集成的，所以运行插件使用下面的命令：
>mvn mybatis-generator:generate

Mybatis Geneator 详解:
>http://blog.csdn.net/isea533/article/details/42102297

## application.properties 配置
```properties
#mybatis
mybatis.type-aliases-package=tk.mybatis.springboot.model
mybatis.mapper-locations=classpath:mapper/*.xml

#mapper
#mappers 多个接口时逗号隔开
mapper.mappers=tk.mybatis.springboot.util.MyMapper
mapper.not-empty=false
mapper.identity=MYSQL

#pagehelper
pagehelper.helperDialect=mysql
pagehelper.reasonable=true
pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql
```

## application.yml 配置

完整配置可以参考 [src/main/resources/application-old.yml](https://github.com/abel533/MyBatis-Spring-Boot/blob/master/src/main/resources/application-old.yml) ，和 MyBatis 相关的部分配置如下：

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

## SSM集成的基础项目 
>https://github.com/abel533/Mybatis-Spring

## MyBatis工具 http://www.mybatis.tk

- 推荐使用 Mybatis 通用 Mapper3 https://github.com/abel533/Mapper

- 推荐使用 Mybatis 分页插件 PageHelper https://github.com/pagehelper/Mybatis-PageHelper

## 作者信息

- 作者博客：http://blog.csdn.net/isea533

- 作者邮箱：abel533@gmail.com

- Mybatis工具群（推荐）： <a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=7c2f018e4cddc7d4aad04fc312b2d69361a0a896a4f59219a7914953a57bffc2"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="Mybatis工具群(2)" title="Mybatis工具群(2)"></a>

- Mybatis工具群（2000 人已满）： <a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=29e4cce8ac3c65d14a1dc40c9ba5c8e71304f143f3ad759ac0b05146e0952044"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="Mybatis工具" title="Mybatis工具"></a>

