package com.lmbx.csp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/29 上午10:00
 */
@SpringBootApplication
@EnableTransactionManagement
public class InterfaceServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        
        SpringApplication.run(InterfaceServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InterfaceServerApplication.class);
    }
}
