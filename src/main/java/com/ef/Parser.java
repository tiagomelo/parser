package com.ef;

import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ef.cli.Cli;
import com.ef.service.LoadLogIntoDatabaseService;
import com.ef.service.LogAuditService;
import com.ef.service.LogService;

/**
 * This is the main spring boot application class.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Parser implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(Parser.class);
	
	@Autowired
	LoadLogIntoDatabaseService loadLogIntoDatabaseService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	LogAuditService logAuditService;
	
	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION...");
		SpringApplication.run(Parser.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	@Transactional
	public void run(String... args) {
		Cli cli = new Cli(args);
		
		try {
			cli.parse();
			
			LOG.info("LOADING ACCESS LOG FILE INTO DATABASE...");
			loadLogIntoDatabaseService.log(cli.getAccessLogPath());
			LOG.info("...DONE.");
			
			LOG.info("BLOCKED IPS:");
			int hours = cli.getDuration().equalsIgnoreCase("daily") ? 24 : 1;
			List<Object[]> ipList = logService.countByIpAddress(cli.getStartDateStr(), hours, Long.valueOf(cli.getThreshold()));
			
			for (Object[] result : ipList) {
				LOG.info("IP: " + result[0] + " --- NUMBER OF ACCESSES: " + result[1]);
			}
			
			LOG.info("SAVING BLOCKED IPs INTO DATABASE...");
			logAuditService.log(ipList);
			LOG.info("...DONE.");
		} catch (org.apache.commons.cli.ParseException | IllegalArgumentException e) {
			LOG.error(e.getMessage());
			cli.printHelp();
		} catch (ParseException e) {
			LOG.error(e.getMessage());
		} 
	}
}
