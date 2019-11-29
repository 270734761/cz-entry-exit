package com.lx.springboot.filter.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ServletUtil { 

	private static Log log = LogFactory.getLog(ServletUtil.class);

	public static final String CRM_SESSION_ID = "CRMSESSIONID";

	public static final String USER_ID = "USERID";
	
	/**
	 * ThreadLocal
	 */
	private static final ThreadLocal<Map<String, ?>> threadLocal = new ThreadLocal<Map<String,?>>();
	
	public static HttpServletRequest getHttpServletRequest () {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return servletRequestAttributes.getRequest();
	}
	
	public static HttpServletResponse getHttpServletResponse () {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return servletRequestAttributes.getResponse();
	}
	
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(name)) {
					return cookies[i];
				}
			}
		}
		return null;
	}
	
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie cookie = ServletUtil.getCookie(request, cookieName);
		return cookie == null ? null : cookie.getValue();
	}
	
	public static void set(Map<String, ?> map) {
		threadLocal.set(map);
	}

	public static Map<String, ?> get() {
		return threadLocal.get();
	}
	
	/**
	 * 添加指定有效期的COOKIE
	 * @param res
	 * @param key
	 * @param value
	 * @param validDays
	 */
	public static void addCookie(HttpServletResponse res, String key, String value, int validDays) {
		addCookie(res, key, value, false);
	}
	public static void addCookie(HttpServletResponse res, String key, String value, int validDays,boolean use_lvmama_host) {
		Cookie cookie = new Cookie(key,value);
		if(use_lvmama_host){			
			cookie.setDomain(".lvmama.com");
		}
		cookie.setMaxAge(validDays*3600*24);
		cookie.setPath("/");
		res.addCookie(cookie);	
	}
	
	/**
	 * 添加一个生命周期是当前浏览器的COOKIE
	 * @param res
	 * @param key
	 * @param value
	 */
	public static void addCookie(HttpServletResponse res, String key, String value) {
		addCookie(res, key, value,false);
	}
	
	public static void addCookie(HttpServletResponse res, String key, String value,boolean use_lvmama_host) {
		Cookie cookie = new Cookie(key,value);
		if(use_lvmama_host){
			cookie.setDomain(".lvmama.com");
		}
		cookie.setPath("/");
		res.addCookie(cookie);	
	}
	


	public static String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		if(StringUtils.isNotBlank(request.getParameter(CRM_SESSION_ID))) {
			return request.getParameter(CRM_SESSION_ID);
		} else if(request.getAttribute(CRM_SESSION_ID)!=null){
			return request.getAttribute(CRM_SESSION_ID).toString();
		}else {
			return getCookieValue(request,CRM_SESSION_ID);
		}
	}

	public static String getUserId(HttpServletRequest request, HttpServletResponse response) {
		if(StringUtils.isNotBlank(request.getParameter(USER_ID))) {
			return request.getParameter(USER_ID);
		} else if(request.getAttribute(USER_ID)!=null){
			return request.getAttribute(USER_ID).toString();
		}else {
			return getCookieValue(request,USER_ID);
		}
	}


}
