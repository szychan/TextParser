package com.szychan.project.textparser.writer;

import java.io.PrintWriter;

/**
 * A factory for creating Writer objects.
 */
public class WriterFactory {

	/**
	 * Gets the writer.
	 *
	 * @param writer the writer
	 * @param format the format that specifies Writer
	 * @param maxWordsInSentence the max words in sentence if format needs such data
	 * @return the writer for specific format
	 */
	public static OutputWriter getWriter(PrintWriter writer, String format, int maxWordsInSentence) {
		OutputWriter outputWriter = null;
		
		switch (format) {
		case "csv":
			outputWriter = new CSVWriter(writer, maxWordsInSentence);
			break;
		case "xml":
			outputWriter = new XMLWriter(writer);
		default:
			break;
		}
		return outputWriter;
	}
}
