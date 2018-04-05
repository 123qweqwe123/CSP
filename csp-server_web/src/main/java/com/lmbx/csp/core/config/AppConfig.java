package com.lmbx.csp.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
@PropertySource(value = "classpath:/messages.properties", encoding = "UTF-8")
public class AppConfig {

}
