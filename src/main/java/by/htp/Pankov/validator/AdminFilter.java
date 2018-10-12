package by.htp.Pankov.validator;


import by.htp.Pankov.entity.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"changeRole", "createSuiteSize", "createSuiteCategory", "createSuite", "create"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            if (isUserAdmin(servletRequest)) {
                allowAccess(servletRequest, servletResponse, filterChain);
            } else {
                goBack(servletResponse);
            }
        } else {
            allowAccess(servletRequest, servletResponse, filterChain);
        }
    }

    @Override
    public void destroy() {

    }

    private void goBack(ServletResponse servletResponse) throws IOException {
        ((HttpServletResponse) servletResponse).sendRedirect("/main");
    }

    private void allowAccess(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isUserAdmin(ServletRequest servletRequest) {
        User currentUser = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("currentUser");
        return currentUser.getRoleId() == 1;
    }
}
