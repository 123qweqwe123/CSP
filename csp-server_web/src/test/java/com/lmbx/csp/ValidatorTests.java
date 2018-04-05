package com.lmbx.csp;

import com.lmbx.csp.core.validator.annotation.TrimSpace;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2018/1/5 上午10:00
 */
public class ValidatorTests {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 将对象字段里面的空格去掉
     */
    @Test
    public void testTrimSpace() {
        Person person = new Person();
        person.setAddress("北  京市");
        person.setAge(20);
        person.setName(" anti huang ");

        Set<ConstraintViolation<Person>> constraintViolations = constraintViolations = validator.validate(person);
        // assertEquals(0, constraintViolations.size());
        // assertEquals("may not be null", constraintViolations.iterator().next().getMessage());

        String name = "张小明";
        assertEquals(name, person.getName());
        System.out.println(person.getAddress());
        System.out.println(person.getName());
    }

    /**
     * 判断是否是英文
     */
    @Test
    public void testIsEnglish() {
        String a = " xxy y";
        a = a.replaceAll("\\s+", "");
        System.out.println(a.matches("[A-Za-z]+"));
        System.out.println(a.trim());
    }

    @TrimSpace
    static public class Person {
        @NotNull
        private String name;
        private int    age;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }


}
