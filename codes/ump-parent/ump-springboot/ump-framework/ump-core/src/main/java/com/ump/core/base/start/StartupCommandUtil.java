package com.ump.core.base.start;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.ump.commons.exception.StartupException;

public final class StartupCommandUtil {
	public enum StartupOption {
		HELP("help"), LOAD_DATA("load-data"), PORTOFFSET("portoffset"), SHUTDOWN("shutdown"), START("start"),
		STATUS("status"), TEST("test");

		private String name;

		private StartupOption(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private static final Option HELP = Option.builder("?").longOpt(StartupOption.HELP.getName())
			.desc("Prints this help screen to the user").hasArg(false).build();
	private static final Option LOAD_DATA = Option.builder("l").longOpt(StartupOption.LOAD_DATA.getName())
			.desc("Creates tables/load data e.g:" + System.lineSeparator() + "-l readers=seed,demo,ext"
					+ System.lineSeparator() + "-l timeout=7200" + System.lineSeparator() + "-l delegator=default"
					+ System.lineSeparator() + "-l group=org.apache.ofbiz" + System.lineSeparator()
					+ "-l dir=directory/of/files" + System.lineSeparator() + "-l file=/tmp/dataload.xml")
			.numberOfArgs(2).valueSeparator('=').optionalArg(true).argName("key=value").build();
	private static final Option PORTOFFSET = Option.builder("o").longOpt(StartupOption.PORTOFFSET.getName())
			.desc("Offsets all default ports for OFBiz").hasArg().argName("offset").optionalArg(false).build();
	private static final Option SHUTDOWN = Option.builder("d").longOpt(StartupOption.SHUTDOWN.getName())
			.desc("Shutdown OFBiz").hasArg(false).build();
	private static final Option START = Option.builder("u").longOpt(StartupOption.START.getName()).desc("Start OFBiz")
			.hasArg(false).build();
	private static final Option STATUS = Option.builder("s").longOpt(StartupOption.STATUS.getName())
			.desc("Gives the status of OFBiz").hasArg(false).build();
	private static final Option TEST = Option.builder("t").longOpt(StartupOption.TEST.getName())
			.desc("Runs the selected test or all if none selected e.g.: " + System.lineSeparator()
					+ "--test component=base --test case=somecase" + System.lineSeparator() + "or"
					+ System.lineSeparator() + "--test component=base --test suitename=xyz")
			.numberOfArgs(2).valueSeparator('=').optionalArg(true).argName("key=value").build();

	static final List<StartupCommand> parseOfbizCommands(final String[] args) throws StartupException {
		CommandLine commandLine = null;
		CommandLineParser parser = new DefaultParser();
		try {
			commandLine = parser.parse(StartupCommandUtil.getOfbizStartupOptions(), args);
		} catch (ParseException e) {
			throw new StartupException(e.getMessage());
		}
		validateAllCommandArguments(commandLine);
		return mapCommonsCliOptionsToStartupCommands(commandLine);
	}

	private static final Options getOfbizStartupOptions() {
		OptionGroup ofbizCommandOptions = new OptionGroup();
		ofbizCommandOptions.addOption(HELP);
		ofbizCommandOptions.addOption(LOAD_DATA);
		ofbizCommandOptions.addOption(SHUTDOWN);
		ofbizCommandOptions.addOption(START);
		ofbizCommandOptions.addOption(STATUS);
		ofbizCommandOptions.addOption(TEST);

		Options options = new Options();
		options.addOptionGroup(ofbizCommandOptions);
		options.addOption(PORTOFFSET);
		return options;
	}

	private static final List<StartupCommand> mapCommonsCliOptionsToStartupCommands(final CommandLine commandLine) {
		Set<Option> uniqueOptions = new HashSet<Option>(Arrays.asList(commandLine.getOptions()));
		return uniqueOptions.stream()
				.map(option -> new StartupCommand.Builder(option.getLongOpt())
						.properties(populateMapFromProperties(commandLine.getOptionProperties(option.getLongOpt())))
						.build())
				.collect(Collectors.toList());
	}

	private static final Map<String, String> populateMapFromProperties(final Properties properties) {
		return properties.entrySet().stream().collect(
				Collectors.toMap(entry -> String.valueOf(entry.getKey()), entry -> String.valueOf(entry.getValue())));
	}

	private static final void validateAllCommandArguments(CommandLine commandLine) throws StartupException {
		// Make sure no extra options are passed
		if (!commandLine.getArgList().isEmpty()) {
			throw new StartupException("unrecognized options / properties: " + commandLine.getArgList());
		}
		// PORTOFFSET validation
		if (commandLine.hasOption(StartupOption.PORTOFFSET.getName())) {
			Properties optionProperties = commandLine.getOptionProperties(StartupOption.PORTOFFSET.getName());
			try {
				int portOffset = Integer.parseInt(optionProperties.keySet().iterator().next().toString());
				if (portOffset < 0) {
					throw new StartupException("you can only pass positive integers to the option --"
							+ StartupOption.PORTOFFSET.getName());
				}
			} catch (NumberFormatException e) {
				throw new StartupException(
						"you can only pass positive integers to the option --" + StartupOption.PORTOFFSET.getName(), e);
			}
		}
	}
}
