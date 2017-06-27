package com.szychan.project.textparser.parser;

import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The Class SentenceParser parses input data and writes to temp file.
 */
public class SentenceParser {

	/** The Constant endSentencePattern. */
	private final static Pattern endSentencePattern = Pattern.compile(".+[\\.!?]([ ]+)?");

	/** The Constant replaceToEmptyStringRegexp. */
	private final static String replaceToEmptyStringRegexp = "[\\(\\):-]";

	/** The Constant replaceToSpacesRegexp. */
	private final static String replaceToSpacesRegexp = ",";

	/** The pw. */
	private PrintWriter pw;

	/** The temp text. */
	private String tempText = "";

	/** for formats that needs word count. */
	private int maxWords = 0;

	/**
	 * Instantiates a new sentence parser.
	 *
	 * @param pw the writer
	 */
	public SentenceParser(PrintWriter pw) {
		this.pw = pw;
	}

	/**
	 * Parses the text.
	 *
	 * @param text the parsed text
	 */
	public void parse(String text) {
		if (text.isEmpty())
			return;
		boolean endsWithDelimiter = endSentencePattern.matcher(text).matches();

		tempText += text;
		tempText = tempText.trim();
		tempText = replaceSpecialWords(tempText);

		String[] splittedSentences = tempText.split("[.!?]+");

		int numberOfSentencesToWrite = getNumberOfSentencesToWrite(endsWithDelimiter, splittedSentences);
		for (int s = 0; s < numberOfSentencesToWrite; s++) {
			String[] words = splittedSentences[s].split("\\s", 0);
			List<String> listWords = Arrays.asList(words);

			Collections.sort(listWords, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareToIgnoreCase(o2);
				}
			});
			int counter = 0;

			for (int w = 0; w < words.length; w++) {
				if (!listWords.get(w).isEmpty()) {
					pw.print(listWords.get(w) + " ");
					counter++;
				}
			}
			checkMaxWords(counter);
			pw.print("\n");
			pw.flush();
		}
		if (!endsWithDelimiter) {
			tempText = splittedSentences[splittedSentences.length - 1] + " ";
		} else {
			tempText = "";
		}

	}

	/**
	 * Gets the max count of words in single sentence written by writer.
	 *
	 * @return the max words
	 */
	public int getMaxWords() {
		return maxWords;
	}

	/**
	 * Check max words just replaces current maxWord.
	 *
	 * @param maxWords the max words
	 */
	private void checkMaxWords(int maxWords) {
		if (this.maxWords < maxWords) {
			this.maxWords = maxWords;
		}
	}

	/**
	 * Gets the number of sentences to write. When input text does not contain
	 * delimiter at end of input last part of sentence is not whole, therefore
	 * it needs to be stored and combined with next input text passed to Parser.
	 * Otherwise sentences are directly written to temp file.
	 *
	 * @param endsWithDelimiter the ends with delimiter
	 * @param sentences the sentences
	 * @return the number of sentences to write
	 */
	private int getNumberOfSentencesToWrite(boolean endsWithDelimiter, String[] sentences) {
		if (endsWithDelimiter) {
			return sentences.length;
		} else {
			return sentences.length - 1;
		}
	}

	/**
	 * Replace special words.
	 *
	 * @param string the string
	 * @return the string
	 */
	private String replaceSpecialWords(String string) {

		return string.replaceAll(replaceToEmptyStringRegexp, "").replaceAll(replaceToSpacesRegexp, " ")
				.replaceAll("Mrs.", "Mrs ").replaceAll("Mr.", "Mr ").replaceAll("Ms.", "Ms ");
	}

}
