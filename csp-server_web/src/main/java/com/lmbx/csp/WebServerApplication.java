package com.lmbx.csp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/24 上午10:11
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class WebServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebServerApplication.class);
    }
}
