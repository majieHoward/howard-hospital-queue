package com.howard.www.core.base.web.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @ClassName:  FrameworkContextInterceptor   
 * @Description:TODO拦截所有的请求将请求的传参封装到IDataTransferObject对象中   
 * @author: mayijie
 * @date:   2017年2月9日 上午11:03:19   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class FrameworkContextInterceptor extends HandlerInterceptorAdapter {
	
	protected final Logger log = LoggerFactory
			.getLogger(FrameworkContextInterceptor.class);

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		/**完成请求处理后的回调,即渲染后的视图.将调用处理程序执行的任何结果,"
          *从而允许正确的资源清理.注意:只有当这个拦截器的preHandle方法已经成功完成并返回时,"
          *才会被调用true!与该postHandle方法一样,"
          *将以相反的顺序在链中的每个拦截器上调用该方法,"
          *因此第一个拦截器将是最后一个被调用的拦截器.
          **/
		super.afterCompletion(request, response, handler, ex);
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/**拦截处理程序的执行.HandlerAdapter之后调用了调用处理程序,"
          *但在DispatcherServlet渲染视图之前.可以通过给定的ModelAndView将附加的模型对象公开到视图."
          *DispatcherServlet处理执行链中的一个处理程序,"
          *由任意数量的拦截器组成,处理程序本身在最后.使用这种方法,"
          *每个拦截器都可以对执行进行后处理,按照执行链的相反顺序进行应用.
          **/
		super.postHandle(request, response, handler, modelAndView);
	}
    /**
     * 
     * <p>Title: preHandle</p>   
     * <p>Description: 拦截处理程序的执行.在HandlerMapping之后调用确定一个适当的处理程序对象
     * ,但在HandlerAdapter调用处理程序之前.DispatcherServlet处理执行链中的一个处理程序,
     * 由任意数量的拦截器组成,处理程序本身在最后.通过这种方法,
     * 每个拦截器可以决定中止执行链,通常发送HTTP错误或编写自定义响应</p>   
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception   
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * handler is access Action Controller Class Object
		 */
		/**拦截处理程序的执行.在HandlerMapping之后调用确定一个适当的处理程序对象"
          *,但在HandlerAdapter调用处理程序之前.DispatcherServlet处理执行链中的一个处理程序,"
          *由任意数量的拦截器组成,处理程序本身在最后.通过这种方法,"
          *每个拦截器可以决定中止执行链,通常发送HTTP错误或编写自定义响应"
          **/
		return super.preHandle(request, response, handler);
	}

}
