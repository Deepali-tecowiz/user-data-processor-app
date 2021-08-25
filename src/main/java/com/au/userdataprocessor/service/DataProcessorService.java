package com.au.userdataprocessor.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;

/**
 * This service processes the data based on the business rules
 * 
 * @author deepalipimparkar
 *
 */
public interface DataProcessorService {
	/**
	 * Processes data based on uuid
	 * @param uuid
	 * @param data
	 * @return
	 * @throws JSONException
	 */
	public List<JSONObject> processData(String uuid, List<String> data) throws JSONException, BusinessRuleFailedException ;
  
}