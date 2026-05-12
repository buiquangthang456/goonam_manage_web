package com.goonamvina.goonamvina;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


import java.util.Locale;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cấu hình để phục vụ các file từ thư mục "uploads/images/"
        registry.addResourceHandler("/uploads/images/**")
                .addResourceLocations("file:/domains/goonamvina.com/uploads/images/");

        // Cấu hình để phục vụ các file từ thư mục "uploads/"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/domains/goonamvina.com/uploads/");
    }
    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8");
        ms.setBasenames("classpath:messages"); // Đường dẫn tới các tệp message bundles
        return ms;
    }
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("vi")); // Ngôn ngữ mặc định là tiếng Việt
        resolver.setCookieName("lang");
        resolver.setCookieMaxAge(60 * 60 * 24 * 365); // 1 năm
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
