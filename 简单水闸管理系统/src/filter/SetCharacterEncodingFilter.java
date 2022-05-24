package filter;

import other.Methods;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author xie
 * @create 2022-05-20-22:16
 */
@WebFilter(filterName = "SetCharacterEncodingFilter")
public class SetCharacterEncodingFilter implements Filter {
    private final String encoding = "utf-8";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println(Methods.getCurrentTime()+": SetCharacterEncodingFilter运行完毕!");
        resp.setCharacterEncoding(encoding);
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
