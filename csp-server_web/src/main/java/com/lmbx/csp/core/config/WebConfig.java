package com.lmbx.csp.core.config;

import com.lmbx.csp.core.mvc.formatter.MyDateTimeFormatAnnotationFormatterFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * @author huangrupeng
 * @since 17/11/25 下午12:59
 */
@Configuration
@RestController
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("classpath:/static/index.html")
    private Resource              indexHtml;

    private static final String[] STATIC_RESOURCES = { "/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.png", "/**/*.svg",      // 图片
                                                       "/**/*.eot", "/**/*.ttf", "/**/*.woff"                               // 字体文件
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.setOrder(-1) // 设置静态资源映射优先级高于下面配置的 @GetMapping
                .addResourceHandler(STATIC_RESOURCES).addResourceLocations("classpath:/static/");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false); // 默认 /index 会匹配 /index.js
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new MyDateTimeFormatAnnotationFormatterFactory());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * @return
     */
    @GetMapping
    public Object index() {
        return ResponseEntity.ok().body(indexHtml);
    }

}
