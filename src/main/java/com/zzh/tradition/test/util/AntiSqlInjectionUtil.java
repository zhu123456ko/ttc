package com.zzh.tradition.test.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({"unchecked","rawtypes"})
public class AntiSqlInjectionUtil {
	
	public static boolean protectOrderByInjection(HttpServletRequest request){
		
		String[] orderByPossibleNames={"orderBy","orderby"};//前台给orderBy传参可能用的参数名
		boolean flag=false;
		String anchName=null;
		for(String name:orderByPossibleNames){
			if(request.getParameter(name)!=null){
				flag=true;
				anchName=name;
			}
		}
		if(!flag){
			return true;
		}
		
		try{
	        //String origOrderByValue=request.getParameter(anchName);
			String[] origOrderByValues=request.getParameterValues(anchName);
	        
			//针对 【request.getParameterMap】 取参方法修改参数
			Map requestParams=request.getParameterMap();
			Method method=requestParams.getClass().getMethod("setLocked",new Class[]{boolean.class});
			method.invoke(requestParams,new Object[]{new Boolean(false)});
			String[] newOrderByValues=new String[origOrderByValues.length];
			for (int i = 0; i < newOrderByValues.length; i++) {
				newOrderByValues[i]=origOrderByValues[i].replace("kk", "ss");
			}
			requestParams.put(anchName,newOrderByValues);
			method.invoke(requestParams,new Object[]{new Boolean(true)});
			
			//针对 【request.getParameter】、【getParameterValues】、【getParameterNames】 等取参方法修改参数
			//获取到内部request对象
			Class clazz = request.getClass();
	        Field requestField = clazz.getDeclaredField("request");
	        requestField.setAccessible(true);
	        Object innerRequest = requestField.get(request);
	        
	        //设置尚未初始化 (否则在获取一些参数的时候，可能会导致不一致)
	        Field field = innerRequest.getClass().getDeclaredField("parametersParsed");
	        field.setAccessible(true);
	        field.setBoolean(innerRequest , false);
	        
	        //获取到coyoteRequest对象
	        Field coyoteRequestField = innerRequest.getClass().getDeclaredField("coyoteRequest");
	        coyoteRequestField.setAccessible(true);
	        Object coyoteRequestObject = coyoteRequestField.get(innerRequest);
	        
	        //获取到parameter的对象
	        Field parametersField = coyoteRequestObject.getClass().getDeclaredField("parameters");
	        parametersField.setAccessible(true);
	        Object parameterObject = parametersField.get(coyoteRequestObject);
	        
	        //获取内部map来完成对参数变量的修改
	        Field hashTabArrField = parameterObject.getClass().getDeclaredField("paramHashValues");
	        hashTabArrField.setAccessible(true);
	        Map map = (Map)hashTabArrField.get(parameterObject);
	        ArrayList list=new ArrayList();
	        for(String value:origOrderByValues){
	        	list.add(value.replace("kk", "ss"));
	        }
	        map.put("orderBy" ,list);
	        
	        field.setBoolean(innerRequest , true);
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
