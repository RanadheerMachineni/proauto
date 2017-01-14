package com.esteeminfo.proauto.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.esteeminfo.proauto.*" })
public class WebConfig extends WebMvcConfigurerAdapter{

	 @Bean
	 public DataSource dataSource() { 
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	     dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	     dataSource.setUsername("proauto");
	     dataSource.setPassword("proauto");
	     dataSource.setUrl("jdbc:mysql://localhost:3306/proauto_db");
	     return dataSource;
	} 
	 
	 	private Properties jpaProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        // properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	        properties.put("hibernate.show_sql", "true");
	        properties.put("hibernate.format_sql", "true");
	        return properties;
	    } 
	
	   @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
	        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	        factoryBean.setDataSource(dataSource());
	        factoryBean.setPackagesToScan(new String[] { "com.esteeminfo.proauto.entity" });
	        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	        factoryBean.setJpaProperties(jpaProperties());
	        return factoryBean;
	    }
	 
	   @Bean
	    public JpaVendorAdapter jpaVendorAdapter() {
	        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
	        return hibernateJpaVendorAdapter;
	    }

	
	   @Bean
	    @Autowired
	    public PlatformTransactionManager transactionManager() throws NamingException {
	        JpaTransactionManager txManager = new JpaTransactionManager();
	        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
	        return txManager;
	    }
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}

	
	/*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");

    }*/
	
	@Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("login");
	  }
	
}