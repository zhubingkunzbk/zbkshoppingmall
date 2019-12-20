package com.shoppingmall;

import com.shoppingmall.util.BeanFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.List;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        BeanFactory beanFactory = BeanFactory.getInstance();
        SAXReader reader = new SAXReader();
        Document document = null;
        String base = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        String xmlPath = base + "bean.xml";
        try {
            document = reader.read(xmlPath);
        } catch (DocumentException e) {
            throw new RuntimeException("加载xml出错");
        }
        Element rootElement = document.getRootElement();
        Element element = (Element) rootElement.elements().get(0);
        String basePackage = element.attribute("base-package").getValue().replace(".",File.separator);
        beanFactory.scanClassByBasePackage(base + basePackage,sce);
        beanFactory.dependencyInjection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        BeanFactory beanFactory = BeanFactory.getInstance();
        beanFactory.destroyed(sce);
    }
}
