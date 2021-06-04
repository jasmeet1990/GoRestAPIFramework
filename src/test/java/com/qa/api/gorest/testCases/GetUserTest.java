package com.qa.api.gorest.testCases;

import java.util.HashMap;

import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("GET user GOREST API Feature Implementation")
@Feature("GET User API Feature")
public class GetUserTest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "0268523fee0ab5933d841e59af3e0391dfb64565ad578cc550463ab1a4b2b93e";
	
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getAllUsersAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getUsersWithQueryParamsAPITest() {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("first_name", "John");
		paramsMap.put("gender", "male");
		
		Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, paramsMap, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
}

