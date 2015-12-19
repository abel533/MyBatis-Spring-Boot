package tk.mybatis.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.springboot.conf.WebMvcConfig;

/**
 * @author liuzh
 * @since 2015-12-12 18:22
 */
@Controller
@EnableWebMvc
@ComponentScan
@EnableAutoConfiguration
public class Application extends WebMvcConfigurerAdapter {

    @RequestMapping("/")
    String home() {
        //return "Spring-Boot集成 MyBatis分页插件和通用Mapper";
        return "redirect:countries";
    }

    public static void main(String[] args) {
//        new SpringApplicationBuilder().main(Application.class).build().run(args);
        SpringApplication.run(Application.class, args);
    }
}
