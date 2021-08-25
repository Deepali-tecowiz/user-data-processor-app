package com.au.userdataprocessor.rules;

import org.json.JSONException;
import org.json.JSONObject;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;

/**
 * Rules interface. This base interface defines method(s) which will be implemented by implementation Data Rule classes 
 * @author deepalipimparkar
 *
 */
public interface DataRule<T> {
	/**
	 * Applies specific rule
	 * @param data
	 * @return
	 * @throws JSONException
	 */
	public JSONObject applyRule (T data) throws JSONException, BusinessRuleFailedException;
}
