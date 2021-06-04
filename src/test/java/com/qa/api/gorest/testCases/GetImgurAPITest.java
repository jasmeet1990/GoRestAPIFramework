package com.qa.api.gorest.testCases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.AccessToken;

import io.restassured.response.Response;

public class GetImgurAPITest {

	Map<Object,Object> tokenMap;
	String accessToken;
	String accountUserName;
	String refreshToken;
	
	@BeforeMethod
	public void setUp() {
		tokenMap = AccessToken.generateToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUserName = tokenMap.get("account_username").toString();
		refreshToken = tokenMap.get("refresh_token").toString();
	}
	
	@Test
	public void getAccountBlockStatusTest() {
		Map<String, String> authTokenMap = AccessToken.getAuthToken();
		Response response = RestClient.doGet(null, "https://api.imgur.com", "/account/v1/" + accountUserName + "/block",
				authTokenMap, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	@Test
	public void getAccountImagesTest() {
		Map<String, String> authTokenMap = AccessToken.getAuthToken();
		Response response = RestClient.doGet(null, "https://api.imgur.com", "/3/account/me/images", authTokenMap, null,
				true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	@Test
	public void uploadImagePostAPITest() {
		Map<String,String> clientIdMap = AccessToken.getClientId();
		Map<String,String> formMap = new HashMap<String,String>();
		
		formMap.put("title", "test title API");
		formMap.put("description", "test description API");
		
		Response response=RestClient.doPost("multipart", "https://api.imgur.com", "/3/upload",clientIdMap,null,true,formMap);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
}
