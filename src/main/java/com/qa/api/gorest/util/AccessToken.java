package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AccessToken {
	
	public static Map<Object,Object> appTokenMap;
	public static Map<String,String> tokenMap = new HashMap<String,String>();
	public static String clientId;
	@Test
	public static Map<Object,Object> generateToken() {
		
		Map<String,String> formParams =  new HashMap<String,String>();
		formParams.put("refresh_token", "a3bfe80cd16a66003882de0ad2f1a6a0e17dc1a0");
		formParams.put("client_id", "afed4137efa884c");
		formParams.put("client_secret", "8f0e21c346f6a25b736b23f518898d321694b237");
		formParams.put("grant_type", "refresh_token");
		
		JsonPath tokenJson = given().
		formParams(formParams)
		.when()
		.post("https://api.imgur.com/oauth2/token")
		.then()
		.extract().jsonPath();
		
		System.out.println(tokenJson.getMap(""));
			
		appTokenMap = tokenJson.getMap("");
		return appTokenMap;
	}
	
	public static Map<String,String> getAuthToken() {
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Auth Token ====>"+authToken);
		tokenMap.put("Authorization","Bearer"+authToken);
		return tokenMap;
	}
	
	public static Map<String,String> getClientId() {
		System.out.println("Client ID is ====>"+clientId);
		tokenMap.put("Authorization","Client-Id"+clientId);
		return tokenMap;
	}
	
	
	
			

}
