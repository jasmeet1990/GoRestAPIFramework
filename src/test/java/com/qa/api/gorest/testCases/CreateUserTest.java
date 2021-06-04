package com.qa.api.gorest.testCases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.POJO.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "0268523fee0ab5933d841e59af3e0391dfb64565ad578cc550463ab1a4b2b93e";

	@DataProvider
	public Object[][] getUserData() {
		Object userData[][] = ExcelUtil.createTestData("UserData");
		return userData;
	}
	@Test(dataProvider = "getUserData")
	public  void createUserAPIPostTest(String firstname,String lastname,String gender,String dob
			,String email,String phoneNo,String website,String address,String status) {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		//User user = new User("Aditya","Kumar","male","01-01-1990","aditya@gmail.com","912345678","http://adita@www.com","Test Addess Banglore","active");
		User user = new User(firstname,lastname,gender,dob,email,phoneNo,website,address,status);
		Response response =RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println("==================================");
	}
}
