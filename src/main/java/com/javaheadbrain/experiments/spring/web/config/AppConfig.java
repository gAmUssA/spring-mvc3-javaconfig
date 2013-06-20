package com.javaheadbrain.experiments.spring.web.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;


/**
 * @Configuration classes =~ <beans/> xml documents
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.javaheadbrain.experiments.spring.service",
        "com.javaheadbrain.experiments.spring.repositories.internal", "com.javaheadbrain.experiments.spring"})
@MapperScan("com.javaheadbrain.experiments.spring.mapper")
public class AppConfig {
    // @Bean methods ~= <bean/> elements

    @Bean
    public JndiObjectFactoryBean dataSourceJndiFactory() {
        JndiObjectFactoryBean objectFactoryBean = new JndiObjectFactoryBean();
        objectFactoryBean.setJndiName("jdbc/cleardb");
        objectFactoryBean.setExpectedType(javax.sql.DataSource.class);
        objectFactoryBean.setResourceRef(true);
        return objectFactoryBean;
    }

    @Bean(name = "sessionFactory")
    public SqlSessionFactory getSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource((DataSource) dataSourceJndiFactory().getObject());
        sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sessionFactory.getObject();
    }

    /*@Bean
    public AccountMapper accountMapper() throws Exception {
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(getSessionFactory());
        return sessionTemplate.getMapper(AccountMapper.class);
    }*/
}