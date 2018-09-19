package com.ef.cli;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.ef.util.DateUtils;

/**
 * This class does all the command line interface handling.
 * 
 * 
 * @author Tiago Melo (tiagoharris@gmail.com)
 *
 */
public class Cli {
	private String[] args;

	private Options options = new Options();

	private String accessLogPath;

	private String startDateStr;

	private String duration;

	private String threshold;

	public Cli(String[] args) {
        setArgs(args);

		Option accessLogPath = new Option("a", "accesslog", true, "path to access log file");
		accessLogPath.setRequired(true);
		accessLogPath.setValueSeparator('=');
		accessLogPath.setArgName("FILE PATH");
		getOptions().addOption(accessLogPath);

		Option startDateStr = new Option("s", "startDate", true, "start date");
		startDateStr.setRequired(true);
		startDateStr.setValueSeparator('=');
		startDateStr.setArgName("yyyy-MM-dd.HH:mm:ss");
		getOptions().addOption(startDateStr);

		Option duration = new Option("d", "duration", true, "observation period");
		duration.setRequired(true);
		duration.setValueSeparator('=');
		duration.setArgName("HOURLY|DAILY");
		getOptions().addOption(duration);

		Option threshold = new Option("t", "threshold", true, "number of requests");
		threshold.setRequired(true);
		threshold.setValueSeparator('=');
		threshold.setArgName("NUMBER OF REQUESTS");
		getOptions().addOption(threshold);
	}

	public void parse() throws ParseException, IllegalArgumentException {
		final CommandLineParser commandLineParser = new DefaultParser();
		CommandLine commandLine;

		commandLine = commandLineParser.parse(getOptions(), getArgs());

		setAccessLogPath(commandLine.getOptionValue("accesslog"));
		setStartDateStr(commandLine.getOptionValue("startDate"));
		setDuration(commandLine.getOptionValue("duration"));
		setThreshold(commandLine.getOptionValue("threshold"));

		validateParams();
	}

	private void validateParams() throws IllegalArgumentException {
		checkIfValidFile();
		checkIfValidDate();
		checkIfValidDuration();
		checkIfValidThreshold();
	}

	private void checkIfValidFile() throws IllegalArgumentException {
		File f = new File(getAccessLogPath());

		if(!f.isFile()) {
			throw new IllegalArgumentException("File " + accessLogPath + " is not a valid file");
		}
	}

	private void checkIfValidDate() throws IllegalArgumentException {
		try {
			DateUtils.formatter.parse(getStartDateStr());
		} catch (java.text.ParseException e) {
			throw new IllegalArgumentException("Date " + startDateStr + " is not a valid date");
		}
	}

	private void checkIfValidDuration() throws IllegalArgumentException {
		if(! ("daily".equalsIgnoreCase(getDuration())) && ! ("hourly".equalsIgnoreCase(getDuration()))) {
			throw new IllegalArgumentException("Duration " + duration + " is not a valid duration");
		}
	}

	private void checkIfValidThreshold() throws IllegalArgumentException {
		try {
			Integer.parseInt(getThreshold());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Threshold " + threshold + " is not a valid integer");
		}
	}

	public void printHelp() {
		final String cmdLineSyntax = "java -cp \"parser.jar\" com.ef.Parser";
		final HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp(cmdLineSyntax, getOptions());
	}

	public String getAccessLogPath() {
		return accessLogPath;
	}

	public void setAccessLogPath(String accessLogPath) {
		this.accessLogPath = accessLogPath;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}
}
