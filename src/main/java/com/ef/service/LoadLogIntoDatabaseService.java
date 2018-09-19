package com.ef.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.model.Log;
import com.ef.util.ParseFile;

/**
 * Service class responsible for loading the access log file into database.
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
@Service
public class LoadLogIntoDatabaseService {

	@Autowired
	LogService logService;

	public void log(String accessLogPath) {
		List<Log> logRows = ParseFile.parse(accessLogPath);
		
		logService.log(logRows);
	}
}
