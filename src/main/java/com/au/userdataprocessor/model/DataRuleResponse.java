package com.au.userdataprocessor.model;

import java.util.List;
/**
 * This is base response class after applying business rules
 * @author deepalipimparkar
 *
 */
// TODO : to have customized response for each business rule
public class DataRuleResponse {
	public String wordCount;
	public List<String> wordsRetrieved;

	protected String getWordCount() {
		return wordCount;
	}

	protected void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	protected List<String> getWordsRetrieved() {
		return wordsRetrieved;
	}

	protected void setWordsRetrieved(List<String> wordsRetrieved) {
		this.wordsRetrieved = wordsRetrieved;
	}

}