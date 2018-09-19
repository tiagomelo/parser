package com.ef.util;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ef.model.Log;

/**
 * Transforms the file contents into a list of objects.
 * 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class ParseFile {
	
	private final static char DELIMITER = '|';
	public static final String LOG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat(LOG_DATE_PATTERN);
	
	private static Logger LOG = LoggerFactory.getLogger(ParseFile.class);

	public static List<Log> parse(String accessLogPath) {
		List<Log> logRows = new ArrayList<Log>();
		
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withDelimiter(DELIMITER);

		try (FileReader fileReader = new FileReader(accessLogPath);
				CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat)) {

			List<CSVRecord> csvRecords = csvFileParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Log logRow = new Log();
				
				logRow.setAccessDate(LOG_DATE_FORMAT.parse(csvRecord.get(0)));
				logRow.setIpAddress(csvRecord.get(1));
				logRow.setAccessUrl(csvRecord.get(2));
				logRow.setHttpStatus(Integer.parseInt(csvRecord.get(3)));
				logRow.setAccessUserAgent(csvRecord.get(4));
				
				logRows.add(logRow);
			}

		} catch (Exception e) {
			LOG.error("Error parsing file: " + e.getMessage());
		}

		return logRows;
	}
}
