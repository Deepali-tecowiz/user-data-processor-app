package com.au.userdataprocessor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;
import com.au.userdataprocessor.rules.CountWordRule;
import com.au.userdataprocessor.rules.WordRetrievalByLengthRule;
import com.au.userdataprocessor.service.ApplyRuleService;
import com.au.userdataprocessor.util.AppUtils;
import com.au.userdataprocessor.util.DataRuleEnum;

/***
 * This service class is responsible for applying rules which are relevant for given UUID
 * @author deepalipimparkar
 *
 */
@Service
public class ApplyRuleServiceImpl implements ApplyRuleService {
	@Autowired
	private Environment env;

	/***
	 * Applies rules for given uuid. UUID specific properties are retrieved to know which rules will be applied for this UUID.
	 */
	@Override
	public List<JSONObject> applyRules(String uuid, List<String> data) throws JSONException ,BusinessRuleFailedException {
		List<JSONObject> result = new ArrayList<JSONObject>();
		// Ensure list is not empty
		if (!AppUtils.isNullOrEmpty(data)) {
			for (String dataRule : getDataRules(uuid)) {
				// Check for different rule instances based on UUID specific rules defined
				if (dataRule.equalsIgnoreCase(DataRuleEnum.WORDRETRIEVALBYLENGTHRULE.name())) {
					WordRetrievalByLengthRule rule = new WordRetrievalByLengthRule(new Integer(env.getProperty(uuid + ".wordRetrievalByLength" )));
					result.add(rule.applyRule(data));
				}
				
				if (dataRule.equalsIgnoreCase(DataRuleEnum.COUNTWORDRULE.name())) {
					CountWordRule countWordRule = new CountWordRule(env.getProperty(uuid + ".countWord.prefix"));
					result.add(countWordRule.applyRule(data));
				}
	 		}
		}
		return result;
	}
	/***
	 * Get data rules from UUID specific properties
	 * @param uuid
	 * @return
	 */
	protected String[] getDataRules(String uuid) {
		String rule = env.getProperty(uuid);
		String[] rules = rule.split(",");
		return rules;
	}
}
