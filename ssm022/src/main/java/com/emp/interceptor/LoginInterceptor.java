package com.emp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//登陆拦截器
/**
 * 1.preHandle在业务处理器处理请求之前被调用;
 * 2.postHandle在业务处理器处理请求执行完成后,生成视图之前执行;
 * 3.afterCompletion在DispatcherServlet完全处理完请求后被调用,可用于清理资源等 。
 *    afterCompletion()执行完成后开始渲染页面
 *  preHandle-->/emp/conditionList-->postHandle-->生成ListEmp.jsp(html)
 *  -->afterCompletion-->向浏览器回传html
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
		request.setAttribute("msg", "请先登录3!");
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
		request.setAttribute("msg", "请先登录2!");
		
	}

	@Override
	/**
	 * return true  不拦截 方形 调用业务主键  比如/emp/conditionList
	 * return false 拦截,不调用业务组件
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handle) throws Exception {
		System.out.println("preHandle");
		Object user=request.getSession().getAttribute("user");
		if(user!=null){
			//已登录了,放行
			return true;
		}
		//转发到Login.jsp
		//绑定错误消息
		request.setAttribute("msg", "请先登录!");
		String appName=request.getServletContext().getContextPath();
		System.out.println(appName);
		//转发不带工程名(项目名)
		request.getRequestDispatcher("/user/toLogin").forward(request,response);
		return false;
	}

}
