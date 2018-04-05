package com.lmbx.csp.core.mvc.formatter;

import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * 
 * <pre>
 *     &#64;MyDateTimeFormat 会自动判断前端传递过来的日期类型进行格式化
 *     使用传统的  @SimpleDateFormat 需要根据前端传递过来的数据类型设置 pattern
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:37
 */
public class MyDateTimeFormatAnnotationFormatterFactory extends EmbeddedValueResolutionSupport implements AnnotationFormatterFactory<MyDateTimeFormat> {

    private static final Set<Class<?>> FIELD_TYPES;

    static {
        // Create the set of field types that may be annotated with @DateTimeFormat.
        Set<Class<?>> fieldTypes = new HashSet<Class<?>>(8);
        fieldTypes.add(Date.class);
        FIELD_TYPES = Collections.unmodifiableSet(fieldTypes);
    }

    @Override
    public final Set<Class<?>> getFieldTypes() {
        return FIELD_TYPES;
    }

    @Override
    public Printer<?> getPrinter(MyDateTimeFormat annotation, Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Parser<?> getParser(MyDateTimeFormat annotation, Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    /**
     * Factory method used to create a {@link DateTimeFormatter}.
     * 
     * @param annotation the format annotation for the field
     * @param fieldType the declared type of the field
     * @return a {@link DateTimeFormatter} instance
     */
    protected Formatter<Date> getFormatter(MyDateTimeFormat annotation, Class<?> fieldType) {
        return new MyDateTimeFormatter();
    }

}
