package com.language.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

/**
 * Handles requests
 */
@Controller
public class FileUploadAndLanguageDetectionController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadAndLanguageDetectionController.class);

	
	/**
	 *  File upload and language detection using Spring Controller
	 */
	@RequestMapping(value = "/uploadFilesAndDetectLanguage", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFilesAnDetectLanguageHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {
		
		String message = "";
		if (!file.isEmpty()) {
			try {
					String lang ="";
					byte[] data = file.getBytes();
					
					 CharsetDetector detector = new CharsetDetector();
			         detector.setText(data);
	
		            CharsetMatch cm = detector.detect();
	
		            if (cm != null) {
		                    lang = cm.getLanguage();
		            }

				message = message + "The language of uploaded file " + name + " is " + lang;
				logger.info("message " + message );
						
			} catch (Exception e) {
				return "Failed to upload file " + name + " => " + e.getMessage();
			}
		}else{
			return "The uploaded file " + name + " is empty" ;
		}
		
		return message;
	}
}
