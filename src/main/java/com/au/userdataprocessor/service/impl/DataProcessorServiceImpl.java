package com.au.userdataprocessor.service.impl;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;
import com.au.userdataprocessor.service.ApplyRuleService;
import com.au.userdataprocessor.service.DataProcessorService;

/**
 * This service processes the data based on uuid specific business rules.
 * @author deepalipimparkar
 *
 */
@Service
public class DataProcessorServiceImpl implements DataProcessorService {

	@Autowired
	ApplyRuleService applyRuleService;
	
	
	/**
	 * This service method is responsible for processing data for given UUID. It calls ApplyRuleService to apply business rules on data
	 */
	@Override
	public List<JSONObject> processData(String uuid, List<String> data) throws JSONException , BusinessRuleFailedException{
		return applyRuleService.applyRules(uuid, data);
	}
}