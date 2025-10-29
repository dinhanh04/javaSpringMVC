package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
        // ViewResolver cho JSP
        @Bean
        public InternalResourceViewResolver viewResolver() {
            InternalResourceViewResolver vr = new InternalResourceViewResolver();
            vr.setViewClass(JstlView.class);
            vr.setPrefix("/WEB-INF/view/");  // đúng với cấu trúc thư mục ở trên
            vr.setSuffix(".jsp");
            return vr;
        }

        // (tuỳ chọn) map tài nguyên tĩnh nếu bạn đặt ngoài /static, /public
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            registry.viewResolver(viewResolver());
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/css/**")
                    .addResourceLocations("resources/css/");
            registry
                    .addResourceHandler("/js/**")
                    .addResourceLocations("resources/js/");
    }


}


