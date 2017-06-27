package com.szychan.project.textparser.data;

import java.util.ArrayList;
import java.util.List;

public class Sentence implements Comparable<Sentence> {

	private List<String> words = new ArrayList<>();

	public Sentence(List<String> words) {
		this.words=words;
	}

	public void addWord(String word) {
		words.add(word);
	}

	public List<String> getWords() {
		return words;
	}

	@Override
	public int hashCode() {
		int result = 1;
		for( String s : words )
		{
		    result = result * 31 + s.hashCode();
		}
		return super.hashCode();
	}

	@Override
	public int compareTo(Sentence o) {
		Integer sentence1=words.size();
		Integer sentence2=words.size();
		return sentence1.compareTo(sentence2);
	}

}
