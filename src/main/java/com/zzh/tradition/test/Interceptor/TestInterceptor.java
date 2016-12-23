package com.zzh.tradition.test.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zzh.tradition.test.util.AntiSqlInjectionUtil;

public class TestInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!AntiSqlInjectionUtil.protectOrderByInjection(request)){
			throw new Exception("orderBy出错");
		}
		return true;
	}
}
