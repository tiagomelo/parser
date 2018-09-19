package com.ef.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ef.service.LoadLogIntoDatabaseService;
import com.ef.service.LogAuditService;
import com.ef.service.LogService;

/**
 * Configuration class that makes possible to inject those classes bellow.
 * 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Configuration
public class LogParserApplicationConfiguration {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public LoadLogIntoDatabaseService loadLogIntoDatabaseService() {
	    return new LoadLogIntoDatabaseService();
	}
	
	@Bean
	public LogService logService() {
		return new LogService();
	}
	
	@Bean
	public LogAuditService logAuditService() {
		return new LogAuditService();
	}
}
