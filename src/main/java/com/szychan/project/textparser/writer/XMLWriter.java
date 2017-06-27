package com.szychan.project.textparser.writer;

import java.io.PrintWriter;

/**
 * The Class XMLWriter.
 */
public class XMLWriter implements OutputWriter {

	/** The Constant with all characters that will be replaced with &apos; wich is standard for xml files. */
	private final static String replaceApostrophesRegexp = "['’]";

	/** The writer. */
	private PrintWriter writer;

	/**
	 * Instantiates a new XML writer.
	 *
	 * @param writer the writer
	 */
	public XMLWriter(PrintWriter writer) {
		this.writer = writer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.szychan.project.textparser.writer.OutputWriter#writeHeader()
	 */
	@Override
	public void writeHeader() {
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		writer.println("<text>");
		writer.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.szychan.project.textparser.writer.OutputWriter#writeSentence(java.
	 * lang.String[])
	 */
	@Override
	public void writeSentence(String[] words) {
		writer.print("<sentence>");
		writer.flush();
		for (String word : words) {
			writer.print("<word>");
			writer.print(encode(word));
			writer.print("</word>");
		}
		writer.println("</sentence>");
		writer.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.szychan.project.textparser.writer.OutputWriter#writeStopper()
	 */
	@Override
	public void writeStopper() {
		writer.println("</text>");
		writer.flush();
	}

	/**
	 * encode String to required format specifications.
	 *
	 * @param text for encoding
	 * @return the encoded String
	 */
	private String encode(String text) {
		return text.replaceAll(replaceApostrophesRegexp, "&apos;");
	}
}
