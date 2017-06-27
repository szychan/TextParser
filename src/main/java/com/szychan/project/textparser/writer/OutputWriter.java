package com.szychan.project.textparser.writer;

/**
 * The Interface OutputWriter.
 */
public interface OutputWriter {

	/**
	 * Writes Header of the output format if supported.
	 */
	public void writeHeader();

	/**
	 * Writes single sentence to output format.
	 *
	 * @param words the words
	 */
	public void writeSentence(String[] words);

	/**
	 * Appends specified format ending if supported.
	 */
	public void writeStopper();
}
