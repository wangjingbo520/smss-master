//package com.smss.net.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * @author wjb（C）
// * describe
// */
//@Configuration
//public class WebMvcConfg extends WebMvcConfigurationSupport {
//
//    /**
//     * 注入自定义拦截器到该配置类中
//     */
//    @Autowired
//    private MyInterceptor myInterceptor;
//
//    /**
//     * 添加自定义拦截器
//     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor)
//                .addPathPatterns("/**")//拦截的访问路径，拦截所有
//                .excludePathPatterns("/static/*");//排除的请求路径，排除静态资源路径
//        super.addInterceptors(registry);
//    }
//
//    /**
//     * 添加静态资源映射路径，css、js等都放在classpath下的static中
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }
//}
//
