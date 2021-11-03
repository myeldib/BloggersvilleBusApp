package com.myeldib.core;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import com.myeldib.core.FileProcessor;
import com.myeldib.core.IFileProcessor;

public class TestFileProcessor {

	private void runProcess(File inputFile) {
		try {

			assertTrue(inputFile.exists());

			IFileProcessor fileProcessor = new FileProcessor(inputFile);

			//delete output file
			File outputFile = new File(inputFile.getParent());
			outputFile.delete();

			boolean success = fileProcessor.process();
			assertTrue(success);

			assertTrue(outputFile.exists());
			BufferedReader bufferReader = new BufferedReader(new FileReader(outputFile));

			String line = null;

			// read service records
			while ((line = bufferReader.readLine()) != null) {

				if (!line.equals("\n") && !line.equals("-EOF-")) {
					String[] entries = line.split(" ");
					assertEquals(3, entries.length);
				}

			}
			assertEquals("-EOF-", line);

			bufferReader.close();

		} catch (Exception e) {

		}
	}

	@Test
	public void testProcess() {

		File inputFile = new File("src/test/resources/valid_input.txt");
		runProcess(inputFile);

	}

	@Test
	public void testEmptyFile() {
		File inputFile = new File("src/test/resources/empty_input.txt");
		runProcess(inputFile);
	}
	
	@Test
	public void testManyGrottyOnePosh() {
		File inputFile = new File("src/test/resources/many_grotty_one_posh.txt");
		runProcess(inputFile);
	}
	
	@Test
	public void testManyGrottyManyPosh() {
		File inputFile = new File("src/test/resources/many_grotty_many_posh.txt");
		runProcess(inputFile);
	}

}
