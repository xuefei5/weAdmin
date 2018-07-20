package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @des    自定义用户登录拦截器
 * @author yangsheng
 *
 */

public class LoginInterceptor implements HandlerInterceptor{
	
	private static final transient Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	//调用控制器方法之前
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		
		String userIp = (request.getHeader("x-forwarded-for") == null)?request.getRemoteAddr():request.getHeader("x-forwarded-for"); 
		String url = request.getRequestURL().toString();
		
		logger.info("用户IP:"+userIp+",请求了服务地址："+url);
		
		//拦截用户未登录状态，导向登录界面
		/*if() {
			request.getRequestDispatcher("/test").forward(request,response);
			return false;
		}*/
		

        return true;
    }
 
	//请求之后（Controller方法调用之后），视图渲染之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    	
    }
 
    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
