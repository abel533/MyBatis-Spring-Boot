package tk.mybatis.springboot.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.springboot.mapper.CountryMapper;
import tk.mybatis.springboot.model.Country;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * 这个过滤器用于验证在 init 时注入的 mapper 已经可用
 *
 * @author liuzh
 * @since 2017/10/8.
 */
@Component
public class MapperFilter implements Filter {
    @Autowired
    private CountryMapper countryMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<Country> countries = countryMapper.selectAll();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
