package com.ef.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ef.model.Log;
import com.ef.repository.LogRepository;
import com.ef.util.DateUtils;

/**
 * Service class responsible to query for blocked ips.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class LogService {
	
	@Autowired
	LogRepository logRepository;
	
	public void log(List<Log> logRows) {
		logRepository.saveAll(logRows);
	}
	
	public List<Object[]> countByIpAddress(String from, int hours, Long threshold) throws ParseException {
		Date fromDate = DateUtils.getStartDateFromString(from);
		Date toDate = DateUtils.getEndDateFromString(from, hours);
	    
		return logRepository.countByIpAddress(fromDate, toDate, threshold);
	}
}
