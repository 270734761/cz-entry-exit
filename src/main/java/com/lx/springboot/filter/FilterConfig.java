//package com.lx.springboot.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.lx.springboot.entity.Customer;
//import com.lx.springboot.filter.contants.SystemErrorCode;
//import com.lx.springboot.filter.exception.SystemException;
//import com.lx.springboot.service.CustomerService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by zhangjun on 2019/9/17 0014.
// */
//@Slf4j
//@Configuration
//public class FilterConfig {
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(FilterConfig.class);
//
//
//    /**
//     * cookie默认域名
//     */
//
//    private static final String DOMAIN = ".lvmama.com";
//    public static final String CZ_SESSION_ID = "CZSESSIONID";
//    public static final String ALIPAY_ID = "ALIPAYID";
//
//
//    @Autowired
//    private CustomerService customerService;
//
//    //排除不拦截的url
//    //private static final String[] excludePathPatterns = {"/.*/alipayApplet/.*", "/.*/member/memberAuth", "/.*/member/querymemberPhone", "/.*/member/travellerManage/query", "/.*/memberManage/query", "/.*/memberManage/queryMemberOrderInfo", "/.*/listTemplate/.*"};
//    private static final String[] excludePathPatterns = {"/.*/alipayApplet/.*"};
//    @Bean
//    public FilterRegistrationBean createVstSessionFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new LoginFilter());
//        registrationBean.setName("login-filter");
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        return registrationBean;
//    }
//
//
//    class LoginFilter implements Filter {
//
//        public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
//
//        }
//
//        public void destroy() {
//
//        }
//
//       public void queryMemberAndSetSessionId(HttpServletRequest request, HttpServletResponse response, String alipayId,String sessionid) throws IOException {
//           Map<String,String> param=new HashMap<String,String>();
//           param.put("alipay_id",alipayId);
//           param.put("cz_session_id",sessionid);
//           List<Customer> list= customerService.getCustomerByParams(param);
//            if (CollectionUtils.isNotEmpty(list)) {
//                LOGGER.info("LoginFilter queryMemberAndSetSessionId value:"+JSONObject.toJSONString(param));
//            } else {
//                LOGGER.warn("LoginInterceptor queryMemberAndSetSessionId customer is null sessionid:"+sessionid+",alipayId:" + alipayId);
//                throw new SystemException(SystemErrorCode.member_illegal, SystemErrorCode.member_illegal.getErrorMessage());
//            }
//        }
//
//        @Override
//        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//            HttpServletRequest req = (HttpServletRequest) request;
//            HttpServletResponse res = (HttpServletResponse) response;
//            String sessionId = req.getHeader(CZ_SESSION_ID);
//            String alipayId = req.getHeader(ALIPAY_ID);
//            // 获取请求url地址，不拦截excludePathPatterns中的url
//            String url = req.getRequestURI();
//            LOGGER.info("LoginFilter doFilter sessionid:" + sessionId + ",alipayId:" + alipayId + ",url:" + url);
//            for (String urlPattern : Arrays.asList(excludePathPatterns)) {
//                if (url != null && urlPattern != null && url.matches(urlPattern)) {
//                    LOGGER.info("LoginFilter doFilter excludePath sessionid:" + sessionId + ",alipayId:" + alipayId + ",url:" + url);
//                    chain.doFilter(request, response);
//                    return;
//                }
//            }
//            if (StringUtils.isEmpty(alipayId) || StringUtils.isEmpty(sessionId)) {
//                LOGGER.info("LoginFilter doFilter userId sessionid is null sessionid:" + sessionId + ",alipayId:" + alipayId);
//                throw new SystemException(SystemErrorCode.auth_illegal, SystemErrorCode.auth_illegal.getErrorMessage());
//            }
//            queryMemberAndSetSessionId(req, res, alipayId, sessionId);
//            LOGGER.info("LoginFilter doFilter sessionid:" + sessionId + ",alipayId:" + alipayId + ",url:" + url);
//
//            chain.doFilter(request, response);
//
//
//            LOGGER.info("LoginFilter doFilter end sessionid:" + sessionId + ",alipayId:" + alipayId + ",url:" + url);
//        }
//
//    }
//}
//
