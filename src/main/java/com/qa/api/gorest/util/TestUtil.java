package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	public static String getSerializeJSON(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			System.out.println("JSON BODY PAYLOAD IS" +jsonString);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
}
