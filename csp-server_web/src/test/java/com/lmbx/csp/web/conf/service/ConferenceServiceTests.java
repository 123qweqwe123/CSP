package com.lmbx.csp.web.conf.service;

import com.lmbx.csp.web.conf.vo.UploadPersonVO;
import org.junit.Test;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Description:
 * <pre>
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/4 下午3:42
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConferenceServiceTests {

    @Test
    public void testParseExcel() throws Exception{
        String xmlPath = "template/参会人员导入解析模板.xml";
        String excelPath = "/Users/huangrupeng/Downloads/aa.xlsx";
        InputStream inputXML = new BufferedInputStream(getClass().getResourceAsStream(xmlPath));
        XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );
        InputStream inputXLS = new BufferedInputStream(getClass().getResourceAsStream(excelPath));
        List persons = new ArrayList();
        Map beans = new HashMap();
        beans.put("persons", persons);
        XLSReadStatus readStatus = mainReader.read( inputXLS, beans);

    }

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Test
    public void testFileExist() throws Exception{
        Resource xmlResource = new ClassPathResource("template/参会人员导入解析模板.xml");
        Resource inputXls = new FileSystemResource("/Users/huangrupeng/Downloads/aa.xlsx");
        try(InputStream xmlInputStream = xmlResource.getInputStream()) {
            XLSReader mainReader = ReaderBuilder.buildFromXML( xmlInputStream );
            try (InputStream inputXLS = inputXls.getInputStream()){
                List<UploadPersonVO> persons = new ArrayList();
                Map beans = new HashMap();
                beans.put("persons", persons);
                ReaderConfig.getInstance().setSkipErrors( true );
                XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
                UploadPersonVO person = persons.get(0);

                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();

                Set<ConstraintViolation<UploadPersonVO>> constraintViolations = validator.validate(person);
                constraintViolations.forEach( x -> {
                    System.out.println(x.getMessage());
                });
            }
        }
    }
}
