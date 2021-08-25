package com.au.userdataprocessor;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.au.userdataprocessor.exception.BusinessRuleFailedException;
import com.au.userdataprocessor.service.impl.ApplyRuleServiceImpl;

/**
 * Entry point to the execution
 * @author deepalipimparkar
 *
 */
@SpringBootApplication
@ComponentScan({"com.au.userdataprocessor", "controller" , "model", "service"})
public class UserDataProcessorApp   extends SpringBootServletInitializer implements CommandLineRunner{
	@Autowired
	ApplyRuleServiceImpl applyRuleService;
	/**
	 * Main class to launch UserDataProcessorAPp
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserDataProcessorApp.class, args);
	}
	
	/**
	 * Configure the application. Add the required resources.
	 */
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		 return application.sources(UserDataProcessorApp.class);
	 }
	 
	/**
	 * Run the simulation to execute data processing for one of the scenario.
	 */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n########### User Data Processor Application ###########");
		runSimulation();
		System.out.println("\n##################################################");
	}
	
	private void runSimulation() {
		System.out.println("Sample Scenario:Apply both the business rules for NBN uuid- "
				+ " Counts and returns the NUMBER of words (i.e. Strings) that start with \"M\" or \"m\"\n" + 
				"\n" + 
				"and - Returns all the words longer than 5 characters\n"  );

		List<String> inputData = new ArrayList<>();
		inputData.add("CountWords");
		inputData.add("MatchWords");
		inputData.add("misc");


		System.out.println("\nSample Data:");
		inputData.forEach(System.out::println);

		List<JSONObject> output;
		try {
			output = applyRuleService.applyRules("NBN_13456789" , inputData);
			System.out.println("\n Final Output: " + output);
		} catch (JSONException|BusinessRuleFailedException e) {
			e.printStackTrace();
		}
	}
}
