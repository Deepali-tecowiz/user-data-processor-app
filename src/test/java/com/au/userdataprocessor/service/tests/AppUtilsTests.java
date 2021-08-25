package com.au.userdataprocessor.service.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.userdataprocessor.util.AppUtils;

/**
 * This class contains all test cases for testing
 * {@link com.au.userdataprocessor.util.AppUtils}
 * 
 * @author deepalipimparkar
 *
 */
@SpringBootTest
class AppUtilsTests {
	@ParameterizedTest(name = "Run {index}: inputData={0}, prefix={1}")
	@MethodSource({ "matchingValue_Positive_Parameters" ,
					"matchingValueWithFewEmpty_Positive_Parameters"
				  })
	public void testFilterByMatchingPrefix( List<String> inputData, String prefix)  {
		Long output = AppUtils.filterByMatchingPrefix(inputData, prefix);
		assertTrue(output == 2l);
   	}
	
	
	@ParameterizedTest(name = "Run {index}: inputData={0}, lengthToConsider={1}")
	@MethodSource({ "matchingLength_Positive_Parameters" })
	public void testFilterByMatchingPrefix( List<String> inputData, int lengthToConsider)  {
		List<String> output = AppUtils.getValuesHavingLengthMoreThanGivenLength(inputData, lengthToConsider);
		assertTrue(output.size() == 2);
   	}
	
	@ParameterizedTest(name = "Run {index}: inputData={0}, lengthToConsider={1}")
	@MethodSource({ "matchingLength_Negative_Parameters" })
	public void testFilterByMatchingPrefix_Negative( List<String> inputData, int lengthToConsider)  {
		List<String> output = AppUtils.getValuesHavingLengthMoreThanGivenLength(inputData, lengthToConsider);
		assertTrue(output.size() == 5);
   	}
	/// ---Start -   scenarios for FilterByMatchingPrefix ----  ////
	
	static Stream<Arguments> matchingValue_Positive_Parameters()   {
		List<String> inputData = new ArrayList<>();
		inputData.add("Merill");
		inputData.add("Test1");
		inputData.add("Test2");
		inputData.add("Test3");
		inputData.add("movement");

		return Stream.of(Arguments.of(inputData,"M" ));
	}
	
	
	static Stream<Arguments> matchingValueWithFewEmpty_Positive_Parameters()  {
		List<String> inputData = new ArrayList<>();
		inputData.add("Merill");
		inputData.add("");
		inputData.add("Test2");
		inputData.add("");
		inputData.add("movement");

		return Stream.of(Arguments.of(inputData,"M" ));
	}
	
	/// ---Ends -   scenarios for FilterByMatchingPrefix ----  ////
	
	/// ---Start -   scenarios for get values whose length is > than given length ----  ////
		static Stream<Arguments> matchingLength_Positive_Parameters()  {
			List<String> inputData = new ArrayList<>();
			inputData.add("Merill");
			inputData.add("Test1");
			inputData.add("Test2");
			inputData.add("Test3");
			inputData.add("movement");

			return Stream.of(Arguments.of(inputData, 5 ));
		}
		static Stream<Arguments> matchingLength_Negative_Parameters()  {
			List<String> inputData = new ArrayList<>();
			inputData.add("Merill");
			inputData.add("Test1");
			inputData.add("Test2");
			inputData.add("Test3");
			inputData.add("movement");

			return Stream.of(Arguments.of(inputData, -1 ));
		}
 
		
		/// ---Ends -  scenarios for get values whose length is > than given length  ----  ////
}
