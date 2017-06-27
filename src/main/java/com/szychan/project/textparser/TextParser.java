package com.szychan.project.textparser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

import com.szychan.project.textparser.parser.SentenceParser;
import com.szychan.project.textparser.writer.OutputWriter;
import com.szychan.project.textparser.writer.WriterFactory;

/**
 * The Class TextParser reads text data parses it then writes in specified
 * output formats.
 */
public class TextParser {

	/** The Constant supportedFormats. */
	private final static String supportedFormats = "csv,xml";

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(TextParser.class);

	/** name of temp file that stores prepared parsed data. */
	private final static String tempFileName = "temp.txt";

	/**
	 * Parses input text data to specified format.
	 *
	 * @param args wich expects output format parameter to be passed
	 */
	public static void main(String[] args) {
		String format = args[0];
		formatSupported(format);

		logger.info("Starting Text Parser");
		logger.info("Output format set to \"" + format + "\"");
		logger.info("Started parsing input data to temp file");

		int maxWordsInSentence = parseToTempFile();

		logger.info("Writing result as \"." + format + "\" format");

		writeResultFromTempFile(format, maxWordsInSentence);
		File file = new File(tempFileName);
		file.delete();

		logger.info("Application finished... see You again");
	}

	/**
	 * Validate input format parameter.
	 *
	 * @param format the format
	 */
	private static void formatSupported(String format) {
		if (!supportedFormats.contains(format)) {
			logger.error("Fail to initialize application");
			throw new IllegalArgumentException("Specified Format is not supported by application");
		}
	}

	/**
	 * Parses input text data to temp file.
	 *
	 * @return the int with max number of words in single sentence during
	 *         parsing phase
	 */
	private static int parseToTempFile() {
		int wordsCounter = 0;
		try (InputStreamReader isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr);

				FileOutputStream fos = new FileOutputStream(tempFileName);
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				PrintWriter pw = new PrintWriter(osw, true)) {

			SentenceParser sentenceParser = new SentenceParser(pw);

			while (br.ready()) {
				String inputLine = br.readLine();
				sentenceParser.parse(inputLine);
			}
			wordsCounter = sentenceParser.getMaxWords();

		} catch (IOException e) {
			logger.error("There was problem during initial parsing to temp file", e);
		}
		return wordsCounter;
	}

	/**
	 * Write result from temp file and formats it.
	 *
	 * @param format the name of format
	 * @param maxWordsInSentence the max words in single sentence
	 */
	private static void writeResultFromTempFile(String format, int maxWordsInSentence) {
		try (FileInputStream fis = new FileInputStream(tempFileName);
				BufferedInputStream bf = new BufferedInputStream(fis);
				BufferedReader reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));

				OutputStreamWriter osw = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
				PrintWriter writer = new PrintWriter(osw);) {

			OutputWriter outputWriter = WriterFactory.getWriter(writer, format, maxWordsInSentence);

			outputWriter.writeHeader();
			while (reader.ready()) {
				String inputLine = reader.readLine();

				outputWriter.writeSentence(inputLine.split(" ", 0));
			}
			outputWriter.writeStopper();
		} catch (IOException e) {
			logger.error("Failed to read or write data", e);
		}

	}

}
