package com.xworkz.vendorManagement.configurer;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan("com.xworkz.vendorManagement")
@EnableWebMvc
@Slf4j
public class BeanConfiguration {
	public BeanConfiguration() {
		log.info("Created BeanConfiguration");
	}

	@Bean
	public ViewResolver viewResolver() {
		log.info("Invoking viewResolver().....");
		ViewResolver resolver = new InternalResourceViewResolver("/", ".jsp");
		return resolver;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean bean() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setPackagesToScan("com.xworkz.vendorManagement.entity");
		return bean;

	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/vmanagementproject");
		dataSource.setUsername("root");
		dataSource.setPassword("Rohanme1@");
		return dataSource;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		System.out.println("Registering the multipart resolver in to spring");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576); // 1MB
		return multipartResolver;
	}

}