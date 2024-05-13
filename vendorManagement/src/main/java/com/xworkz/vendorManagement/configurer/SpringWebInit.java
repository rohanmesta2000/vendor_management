package com.xworkz.vendorManagement.configurer;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringWebInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("Invoking getRootConfigClasses");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("Invoking getServletConfigClasses");
		return new Class[] { BeanConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		log.info("Invoking getServletMappings");
		return new String[] { "/" };
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}