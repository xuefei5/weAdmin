package com.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @des 自定义用户登录拦截器
 * @author yangsheng
 * 
 */

public class LoginInterceptor implements HandlerInterceptor {

	private static final transient Logger logger = Logger
			.getLogger(LoginInterceptor.class);

	// 调用控制器方法之前
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String userIp = (request.getHeader("x-forwarded-for") == null) ? request
				.getRemoteAddr() : request.getHeader("x-forwarded-for");

		// 获得请求的URI
		String url = request.getRequestURL().toString();
		logger.info("用户IP:" + userIp + ",请求了服务地址：" + url);
		// 获取session
		HttpSession session = request.getSession();
		// 获取用户登录信息
		Object obj = session.getAttribute("user");
		// 如果是登录页面或者登录操作
		if (url.contains("toLoginPage")||url.contains("login")) {
			return true;
		}
		// 如果未登录
		if (obj == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script  type=" + "text/javascript" + ">");
			out.println("window.open ('/user/toLoginPage','_top')");
			out.println("</script>");
			out.println("</html>");
			// res.setHeader("refresh", "0;url=login.jsp");
			return false;
		}

		return true;
	}

	// 请求之后（Controller方法调用之后），视图渲染之前
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	// 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
