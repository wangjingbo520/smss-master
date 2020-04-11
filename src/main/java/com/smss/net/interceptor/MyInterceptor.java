//package com.smss.net.interceptor;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author wjb（C）
// * describe  自定义拦截器
// */
//@Component
//public class MyInterceptor implements HandlerInterceptor {
//
//    /**
//     * 在整个请求结束之后被调用，DispatcherServlet 渲染视图之后执行（进行资源清理工作）
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
//
//    }
//
//    /**
//     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
//     */
//    @Override
//    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
//        StringBuffer requestURL = arg0.getRequestURL();
//        System.out.println("request end: request_url =" + requestURL);
//    }
//
//
//    /**
//     * 在请求处理之前进行调用（Controller方法调用之前）
//     *
//     * @return 返回true才会继续向下执行，返回false取消当前请求
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//        //   String rootPath = request.getContextPath();
//        //设备唯一标识
//        String device_id = request.getHeader("device_id");
//        StringBuffer requestURL = request.getRequestURL();
//        System.out.println("request begin: request_url =" + requestURL);
//        System.out.println("request begin: device_id =" + device_id);
//        //设置前端的全局地址，可以是request.getContextPath(),也可以是包含ip、端口号、项目名等等的全路径
//        //   request.setAttribute("rootPath", rootPath);
//        return true;
//    }
//
//
////    getRequestURL()	返回客户端发出请求时的完整URL。
////    getRequestURI()	返回请求行中的参数部分。
////    getQueryString ()	返回发出请求的客户机的IP地址。
////    getRemoteHost()	返回发出请求的客户机的完整主机名。
////    getRemoteAddr()	返回发出请求的客户机的IP地址。
////    getPathInfo()	返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以"/"开头。
////    getRemotePort()	返回客户机所使用的网络端口号。
////    getLocalAddr()	返回WEB服务器的IP地址。
////    getLocalName()	返回WEB服务器的主机名。
//
//}