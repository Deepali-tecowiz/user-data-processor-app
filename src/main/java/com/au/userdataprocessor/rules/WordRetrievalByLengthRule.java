package com.au.userdataprocessor.rules;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;
import com.au.userdataprocessor.util.AppUtils;

/***
 * This class defines the business rule to retrieve words based on specific length paramter

 * @author deepalipimparkar
 *
 */
public class WordRetrievalByLengthRule implements DataRule<List<String>> {
	public int requiredLength =0;
	
	/**
	 * Constructor to initialize required Length parameter
	 * @param requiredLength
	 */
	public WordRetrievalByLengthRule (int requiredLength) {
		this.requiredLength= requiredLength;
	}
	
	/**
	 * Applies rule based on which words have no. of characters specified in the length parameter
	 * 
	 */
	@Override
	public JSONObject applyRule(List<String> data) throws JSONException, BusinessRuleFailedException {
		List<String> outputValues ;
		try {
			// Get the data values whose length is more than that of given length
			outputValues= AppUtils.getValuesHavingLengthMoreThanGivenLength(data, requiredLength);
		} catch (Exception e) {
			throw new BusinessRuleFailedException("Failure while applying WordRetrievalByLengthRule - " + e);
		}
		JSONObject outputJson = new JSONObject();
 		JSONArray jsArray = new JSONArray(outputValues);
		outputJson.put("WordsRetrievedWithRequiredLength", jsArray);
 		return outputJson;
 	}
}
