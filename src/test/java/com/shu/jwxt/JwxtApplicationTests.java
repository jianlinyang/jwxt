package com.shu.jwxt;

import com.shu.jwxt.entity.Admin;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JwxtApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("test.xml"));
        Admin admin =(Admin) beanFactory.getBean("admin");
        admin.setUserName("yy");
        System.out.println(admin.getUserName());
    }

}
