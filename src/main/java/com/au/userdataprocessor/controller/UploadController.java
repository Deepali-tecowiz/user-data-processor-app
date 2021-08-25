package com.au.userdataprocessor.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.au.userdataprocessor.service.DataProcessorService;
import com.au.userdataprocessor.util.AppUtils;
/**
 * UploadController class is responsible for uploading CSV file and processing the file.
 * @author deepalipimparkar
 *
 */
@Controller
public class UploadController {
	
	@Autowired
	DataProcessorService dataProcessorService;
	
	@Autowired
	Environment env;
	
	/**
	 * Uploads CSV file
	 * @param id UUID it's a distinct identifer
	 * @param model Spring's Model
	 * @return String view point
	 */
	@GetMapping("/upload-csv/{id}")
	public String uploadCSV(@PathVariable("id") String uuid, Model model) {
        model.addAttribute("uuid", uuid);

		return "upload-csv";
	}
	
	/**
	 * Processes CSV file based on uuid and it's relevant business rules
	 * If the UUID is incorrect i.e. does not match with any UUID set in application.properties, show error message
	 * If the csv file is empty or not uploaded , show the error message
	 * @param file
	 * @param companyUUID
	 * @param model
	 * @return
	 */
	@PostMapping("/process-csv-file")
	public String processCSVFile(@RequestParam("file") MultipartFile file,@RequestParam("companyUUID")  String companyUUID, Model model) {
		System.out.println("UploadController.uploadCSVFile() companyUUID :" + companyUUID );
		
		// Check if companyUUID is valid or not 		
		if (AppUtils.isNullOrEmpty(env.getProperty(companyUUID))) {
			model.addAttribute("message", "Invalid UUID");
			model.addAttribute("status", false);
		// If file is empty set the status as false.	
		} else if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {
			List<String> data = new ArrayList<>();
			// parse CSV file to retrieve list of values
 			try (Scanner scanner = new Scanner(file.getInputStream())) {
  		        while(scanner.hasNextLine()){
 		        		data.add(scanner.nextLine());
 		        }
 		        scanner.close();
 		        
 		        // Call service to apply process data
 		        List<JSONObject> result = dataProcessorService.processData(companyUUID, data);
 		        setResult(model, result);
                model.addAttribute("status", true);

			} catch (Exception ex) {
				model.addAttribute("message", "An error occurred while parsing the CSV file.");
				model.addAttribute("status", false);
				
			}
		}
		return "file-upload-status";
	}
	/**
	 * Set the final result into the model
	 * @param model
	 * @param result
	 * @throws JSONException
	 */
	private void setResult (Model model,List<JSONObject> result) throws JSONException {
		for (JSONObject jsonObject : result) {
			@SuppressWarnings("unchecked")
			Iterator<String> keys = jsonObject.keys();
			while(keys.hasNext()) {
			    String key = keys.next();
			    model.addAttribute(key, jsonObject.get(key));
			}
		}
	}
	
}