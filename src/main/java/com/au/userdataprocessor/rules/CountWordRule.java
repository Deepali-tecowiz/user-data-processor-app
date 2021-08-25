package com.au.userdataprocessor.rules;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;
import com.au.userdataprocessor.util.AppUtils;
/**
 * This rule counts and returns the NUMBER of words (i.e. Strings) that start specific prefix
 * @author deepalipimparkar
 *
 */
public class CountWordRule implements DataRule<List<String>> {
	public String prefix ;
	
	/**
	 * Constructor to initialize prefix to match
	 * @param prefixToMatch
	 */
	public CountWordRule (String prefixToMatch) {
		prefix=prefixToMatch;
	}
	
	/**
	 * Applies rule to filter out values whose prefix matches with the input prefix
	 * @throws BusinessRuleFailedException 
	 */
	@Override
	public JSONObject applyRule(List<String> data) throws JSONException, BusinessRuleFailedException{
		Long countWords = 0l;
		try {
			// filter each data value based on prefix and return the total count.
			countWords = AppUtils.filterByMatchingPrefix(data, prefix);
		} catch (Exception e) {
			throw new  BusinessRuleFailedException("Failure while applying CountWordRule -" + e);
		}
		
		JSONObject countWordDataJson = new JSONObject();
		countWordDataJson.put("TotalWordsCount" , countWords );
		return countWordDataJson;
	}
}
