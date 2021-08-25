package com.au.userdataprocessor.service.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;
import com.au.userdataprocessor.service.impl.ApplyRuleServiceImpl;

/**
 * This class contains all test cases for testing
 * {@link com.au.userdataprocessor.service.impl.ApplyRuleServiceImpl}
 * 
 * @author deepalipimparkar
 *
 */
@SpringBootTest
class ApplyRuleServiceImplTests {
	@Autowired
	ApplyRuleServiceImpl applyRuleService;

	@ParameterizedTest(name = "Run {index}: uuid={0}, inputData={1}")
	@MethodSource({ "singleValueForNBN_Positive_Parameters",
				"singleValueForVodaphone_Positive_Parameters",
				"singleValueForTelstra_Positive_Parameters" })
	public void testApplyRules_Positive_Parameters(String uuid, List<String> inputData) throws Throwable {
		List<JSONObject> processedData = applyRuleService.applyRules(uuid, inputData);

		assertFalse(processedData.isEmpty());
  	}
	

	@ParameterizedTest(name = "Run {index}: uuid={0}, inputData={1}")
	@MethodSource({ "singleValueForNBN_Negative_Parameters",
				"singleValueForVodaphone_Negative_Parameters",
				"singleValueForTelstra_Negative_Parameters" })
	public void testApplyRules_Negative_Paramters(String uuid, List<String> inputData) throws Throwable {
		List<JSONObject> processedData = applyRuleService.applyRules(uuid, inputData);
		System.out.println("ApplyRuleServiceImplTests.testApplyRules_Negative_Paramters()" + processedData.size());
		assertTrue(processedData.isEmpty());
   	}
	 
	@ParameterizedTest(name = "Run {index}: uuid={0}, inputData={1}")
	@MethodSource({ "singleValueForNBN_TotalWordCount_Parameters",
				"singleValueForTelstra_TotalWordCount_Parameters" })
	public void testApplyRules_TotalWordCount(String uuid, List<String> inputData) throws Throwable {
		List<JSONObject> processedData = applyRuleService.applyRules(uuid, inputData);
		System.out.println("ApplyRuleServiceImplTests.testApplyRules_Negative_Paramters()" + processedData.size());
		
		assertFalse(processedData.isEmpty());
		
		String output = processedData.get(0).get("TotalWordsCount").toString();
		assertTrue (output.equalsIgnoreCase("2")) ;
   	}	
	
	@ParameterizedTest(name = "Run {index}: uuid={0}, inputData={1}")
	@MethodSource({ "singleValueForNBN_WordsRetrievedMatchingPrefix_Parameters",
				"singleValueForVodaphone_WordsRetrievedMatchingPrefix_Parameters" })
	public void testApplyRules_WordsRetrievedMatchingPrefix(String uuid, List<String> inputData) throws JSONException, BusinessRuleFailedException {
		List<JSONObject> processedData = applyRuleService.applyRules(uuid, inputData);
		
		assertFalse(processedData.isEmpty());
		
		for (JSONObject jsonObject : processedData) {
			@SuppressWarnings("unchecked")
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				System.out.println("Key :" +  key + " Value:"  +  jsonObject.get(key));
				if (key.equalsIgnoreCase("WordsRetrievedWithRequiredLength")) {
					assertTrue(((JSONArray)jsonObject.get(key)).length() ==2);
				}
				if (key.equalsIgnoreCase("TotalWordsCount")) {
					assertTrue(jsonObject.get(key).toString().equals("2"));
				}
			}
		}
		
 	}	
	
	
	/// ---Start -  Basic scenarios  with non empty List of data for different UUIDs ----  ////
		
	static Stream<Arguments> singleValueForNBN_Positive_Parameters() throws JSONException {
		List<String> inputData = new ArrayList<>();
		inputData.add("NBN_13456789");

		return Stream.of(Arguments.of("NBN_13456789", inputData));
	}
	
	static Stream<Arguments> singleValueForVodaphone_Positive_Parameters() throws Throwable {
		List<String> inputData = new ArrayList<>();
		inputData.add("Vodafone_78912345");

		return Stream.of(Arguments.of("Vodafone_78912345", inputData));
	}
	
	static Stream<Arguments> singleValueForTelstra_Positive_Parameters() throws Throwable {
		List<String> inputData = new ArrayList<>();
		inputData.add("Telstra_34577886");

		return Stream.of(Arguments.of("Telstra_34577886", inputData));
	}
	/// ---Ends -  Basic scenarios  with non empty List of data for different UUIDs ----  ////
	
	/// ---Start - Scenarios  with  empty List of data for different UUIDs ----  ////

	static Stream<Arguments> singleValueForNBN_Negative_Parameters() throws Throwable {
		List<String> inputData = new ArrayList<>();
		return Stream.of(Arguments.of("NBN_13456789", inputData));
	}
	
	static Stream<Arguments> singleValueForVodaphone_Negative_Parameters() throws Throwable {
		List<String> inputData = new ArrayList<>();
		return Stream.of(Arguments.of("Vodafone_78912345", inputData));
	}
	
	static Stream<Arguments> singleValueForTelstra_Negative_Parameters() throws Throwable {
		List<String> inputData = new ArrayList<>();

		return Stream.of(Arguments.of("Telstra_34577886", inputData));
	}
	/// ---Ends - Scenarios  with  empty List of data for different UUIDs ----  ////

	/// ---Start -   scenarios for total word count matching string with prefix for different UUIDs ----  ////
	
		static Stream<Arguments> singleValueForNBN_WordsRetrievedMatchingPrefix_Parameters() throws Throwable {
			List<String> inputData = new ArrayList<>();
			inputData.add("Merill");
			inputData.add("Test1");
			inputData.add("Test2");
			inputData.add("Test3");
			inputData.add("movement");

			return Stream.of(Arguments.of("NBN_13456789", inputData));
		}
		
		static Stream<Arguments> singleValueForVodaphone_WordsRetrievedMatchingPrefix_Parameters() throws Throwable {
			List<String> inputData = new ArrayList<>();
			inputData.add("Merill");
			inputData.add("Test1");
			inputData.add("Test2");
			inputData.add("Test3");
			inputData.add("movement");
			return Stream.of(Arguments.of("Vodafone_78912345", inputData));
		}
		
		static Stream<Arguments> singleValueForTelstra_WordsRetrievedMatchingPrefix_Parameters() throws Throwable {
			List<String> inputData = new ArrayList<>();
			inputData.add("Merill");
			inputData.add("Test1");
			inputData.add("Test2");
			inputData.add("Test3");
			inputData.add("movement");
			return Stream.of(Arguments.of("Telstra_34577886", inputData));
		}
		///  ---Ends -   scenarios for total word count matching string with prefix for different UUIDs ----  ////
		
		
		/// ---Start -   scenarios for TotalWordCount for different UUIDs ----  ////
		
			static Stream<Arguments> singleValueForNBN_TotalWordCount_Parameters() throws Throwable {
				List<String> inputData = new ArrayList<>();
				inputData.add("Merill");
				inputData.add("Test1");
				inputData.add("Test2");
				inputData.add("Test3");
				inputData.add("movement");

				return Stream.of(Arguments.of("NBN_13456789", inputData));
			}
			
			static Stream<Arguments> singleValueForVodaphone_TotalWordCount_Parameters() throws Throwable {
				List<String> inputData = new ArrayList<>();
				inputData.add("Merill");
				inputData.add("Test1");
				inputData.add("Test2");
				inputData.add("Test3");
				inputData.add("movement");
				return Stream.of(Arguments.of("Vodafone_78912345", inputData));
			}
			
			static Stream<Arguments> singleValueForTelstra_TotalWordCount_Parameters() throws Throwable {
				List<String> inputData = new ArrayList<>();
				inputData.add("Merill");
				inputData.add("Test1");
				inputData.add("Test2");
				inputData.add("Test3");
				inputData.add("movement");
				return Stream.of(Arguments.of("Telstra_34577886", inputData));
			}
		 
			/// ---Ends -   scenarios for TotalWordCount for different UUIDs ----  ////
 
}
