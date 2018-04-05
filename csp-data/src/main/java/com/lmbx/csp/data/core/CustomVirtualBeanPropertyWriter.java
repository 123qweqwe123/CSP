package com.lmbx.csp.data.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Annotations;
import com.lmbx.csp.core.utils.SpringContextHolder;
import com.lmbx.csp.data.sys.domain.SysAccount;
import com.lmbx.csp.data.sys.mapper.SysAccountMapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * 
 * <pre>
 *     目前系统所有表基本都有 createBy 和 updateBy 字段，该字段保存的是 accountId
 *     本 writer 的作用是在 pojo 序列化的时候，动态将 id 转化为名称
 * </pre>
 *
 * @author javahuang
 * @since 2017/11/29 下午4:03
 */
public class CustomVirtualBeanPropertyWriter extends VirtualBeanPropertyWriter {

    Map<String, String> idName = new HashMap<>(32);

    public CustomVirtualBeanPropertyWriter(){
    }

    public CustomVirtualBeanPropertyWriter(BeanPropertyDefinition propDef, Annotations contextAnnotations,
                                           JavaType declaredType){
        super(propDef, contextAnnotations, declaredType);
    }

    @Override
    protected Object value(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
        String fieldName = this.getName();
        if ("createName".equals(fieldName)) {
            return getNameById(getValueByFieldName(bean, "createBy"));
        }
        if ("updateName".equals(fieldName)) {
            return getNameById(getValueByFieldName(bean, "updateBy"));
        }
        return null;
    }

    @Override
    public VirtualBeanPropertyWriter withConfig(MapperConfig<?> config, AnnotatedClass declaringClass,
                                                BeanPropertyDefinition propDef, JavaType type) {
        return new CustomVirtualBeanPropertyWriter(propDef, null, type);
    }

    private String getNameById(String id) {
        if (id == null) {
            return null;
        }
        String name = idName.get(id);
        if (name != null) {
            return name;
        }
        SysAccountMapper sysAccountMapper = SpringContextHolder.getBean(SysAccountMapper.class);
        SysAccount account = sysAccountMapper.selectByPrimaryKey(id);
        if (account != null) {
            name = account.getName();
            idName.put(id, name);
            return name;
        }
        return null;
    }

    private String getValueByFieldName(Object bean, String fieldName) {
        try {
            Field field = bean.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(bean);
            return (String) value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
