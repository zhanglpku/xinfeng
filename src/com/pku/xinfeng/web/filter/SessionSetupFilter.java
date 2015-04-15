package com.pku.xinfeng.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pku.xinfeng.model.User;
import com.pku.xinfeng.utils.Constant;


public class SessionSetupFilter implements Filter {
    protected FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws java.io.IOException, ServletException {
    	System.out.println("############# doFilter");
    	
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	HttpServletResponse response = (HttpServletResponse) servletResponse;
    	
    	User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
    	
    	// 请求的uri
        String uri = request.getRequestURI();
        System.out.println(user + "------------" + uri + "---------------" + uri.indexOf("/sys/login"));
    	
        if(uri.indexOf("/sys/login") == -1){//不是登陆操作
        	if(null == user){//session为空
        		response.sendRedirect(request.getContextPath()+"/sys/login.jsp");
            	return;
        	}else 
        		chain.doFilter(request, response);
        }else 
    		chain.doFilter(request, response);
        
    }
}
