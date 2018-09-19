package com.ef.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ef.model.LogAudit;
import com.ef.repository.LogAuditRepository;

/**
 * Service class responsible for loading data into 'LOG_AUDIT' table.
 * 
 * @author tiago
 *
 */
public class LogAuditService {
	
	@Autowired
	LogAuditRepository logAuditRepository;
	
	public void log(List<Object[]> rawLogAuditRows) {
		List<LogAudit> logAuditRows = buildList(rawLogAuditRows);
		
		logAuditRepository.saveAll(logAuditRows);
	}
	
	private List<LogAudit> buildList(List<Object[]> rawLogAuditRows) {
		List<LogAudit> list = new ArrayList<LogAudit>();
		
		for (Object[] obj : rawLogAuditRows) {
			Integer threshold = Integer.parseInt(obj[1].toString());
			
			LogAudit logAudit = new LogAudit();
			logAudit.setIpAddress(obj[0].toString());
			logAudit.setThreshold(threshold);
			logAudit.setBlockReason("IP was blocked due to abuse");
			
			list.add(logAudit);
		}
		
		return list;
	}
}
