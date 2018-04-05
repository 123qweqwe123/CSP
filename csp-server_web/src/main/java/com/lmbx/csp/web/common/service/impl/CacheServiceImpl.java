package com.lmbx.csp.web.common.service.impl;

import com.lmbx.csp.web.common.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * Description:
 * 
 * <pre>
 *     系统缓存默认实现，基于配置使用 Caffeine
 *     常用的注解有:
 *     {@link org.springframework.cache.annotation.Cacheable}   创建 cache
 *     {@link CachePut}    更新 cache
 *     {@link org.springframework.cache.annotation.CacheEvict}  移除 cache
 *     {@link org.springframework.cache.annotation.CacheConfig}
 * </pre>
 * 
 * Author: huangrupeng Create: 17/6/22 下午3:39
 */
@Service
@CachePut
public class CacheServiceImpl implements CacheService {

}
