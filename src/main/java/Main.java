
import org.apache.logging.log4j.Logger;

import com.myeldib.core.FileProcessor;
import com.myeldib.core.IFileProcessor;

import java.io.File;

import org.apache.logging.log4j.LogManager;

public class Main {
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {

		logger.info("Starting Application.");

		if (args.length != 1) {
			logger.error("Path to input file is not provided");
			help();
			return;
		}
		File inputFile = new File(args[0]);

		if (!inputFile.exists()) {
			logger.error("Input file " + args[0] + " does not exist");
			return;
		}
	
		IFileProcessor fileProcessor = new FileProcessor(inputFile);
		boolean success = fileProcessor.process();

		if (!success) {
			logger.error("Applicatoin Failed");
		} else {
			logger.info("Applicatoin executed successfully");

		}

		
		logger.info("Ending Application.");
	}

	public static void help() {
		logger.info("Usage: java -jar BloggersvilleBusApp-0.0.1-SNAPSHOT-jar-with-dependencies.jar <inputFile>");
	}
}
