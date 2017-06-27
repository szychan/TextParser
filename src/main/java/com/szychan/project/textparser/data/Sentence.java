package com.szychan.project.textparser.data;

import java.util.ArrayList;
import java.util.List;

public class Sentence {

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


}
