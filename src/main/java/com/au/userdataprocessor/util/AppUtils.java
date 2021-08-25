package com.au.userdataprocessor.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This utility class holds the common methods which can be used in generic way
 * 
 * @author deepalipimparkar
 *
 */

public class AppUtils {

	/**
	 * Checks if object is null or not
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean isNull(Object value) {
		return (value != null ? false : true);
	}
	
	/**
	 * Checks if collection is null or empty
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean isNullOrEmpty(Collection<?> value) {
		return ((value != null && !value.isEmpty()) ? false : true);
	}

	/**
	 * Check string is null or empty
	 * @param value
	 * @return
	 */
	public static boolean isNullOrEmpty(String value)  {
		if (value == null) {
			return true;
		} else if (value.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
 
	/**
	 * Verifies and returns the values whose length > given length
	 * @param value
	 * @param lengthToConsider
	 * @return Boolean
	 */
	public static List<String> getValuesHavingLengthMoreThanGivenLength (List<String> values, int lengthToConsider) {
		 List<String> outputValues = values
				  .stream()
				  .filter(c -> c.trim().length()   > lengthToConsider)
				  .collect(Collectors.toList());
		 return outputValues;
	}
	
	/**
	 * Count and return the no. of words that start with M or m
	 * @param prefix
	 * @return Long
	 */
	public static Long filterByMatchingPrefix (List<String> values, String prefix) {
 		long count = values.stream() .filter(x -> x.regionMatches(true, 0, prefix, 0, prefix.length())) .count();
	    return count;
 	}
 
}
