package com.au.userdataprocessor.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;

/**
 * This service deals with applying data rules based on UUID
 * 
 * @author deepalipimparkar
 *
 */
public interface ApplyRuleService {
	/**
	 * Apply one or more rules based on uuid specific rules
	 * @param uuid
	 * @param data
	 * @return
	 * @throws JSONException
	 */
	public List<JSONObject>  applyRules(String uuid , List<String> data) throws JSONException, BusinessRuleFailedException;
  
}