package com.szychan.project.textparser.writer;

import java.io.PrintWriter;

/**
 * The Class CSVWriter.
 */
public class CSVWriter implements OutputWriter {

	/** The max words in sentence. */
	private int maxWordsInSentence;

	/** The writer. */
	private PrintWriter writer;

	/** The sentence counter. */
	private int sentenceCounter = 1;

	/**
	 * Instantiates a new CSV writer.
	 *
	 * @param writer the writer
	 * @param maxWordsInSentence the max words in sentence
	 */
	public CSVWriter(PrintWriter writer, int maxWordsInSentence) {
		this.maxWordsInSentence = maxWordsInSentence;
		this.writer = writer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.szychan.project.textparser.writer.OutputWriter#writeHeader()
	 */
	@Override
	public void writeHeader() {
		StringBuilder sb = new StringBuilder("");

		for (int i = 1; i <= maxWordsInSentence; i++) {
			sb.append(", Word ").append(i);
		}

		writer.println(sb.toString());
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
		if (maxWordsInSentence < words.length) {
			throw new IllegalArgumentException(
					"Too much words in sentence. Max words for this instance of CSV Writer is:" + maxWordsInSentence);
		}
		StringBuilder sb = new StringBuilder("Sentence ");
		sb.append(sentenceCounter);

		sentenceCounter++;

		for (int i = 0; i < words.length; i++) {
			sb.append(", ").append(words[i]);
		}

		writer.println(sb.toString());
		writer.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.szychan.project.textparser.writer.OutputWriter#writeStopper()
	 */
	@Override
	public void writeStopper() {
	}

}
