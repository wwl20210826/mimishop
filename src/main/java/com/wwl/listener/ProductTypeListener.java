package com.wwl.listener;

import com.wwl.pojo.ProductType;
import com.wwl.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //从spring容器获取ProductTypeServiceImpl
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService productTypeService = (ProductTypeService)context.getBean("ProductTypeServiceImpl");
        List<ProductType> list = productTypeService.getAll();
        //把数据放入全局作用域对象中
        servletContextEvent.getServletContext().setAttribute("ptlist",list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
