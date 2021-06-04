package com.qa.api.gorest.POJO;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;

import io.restassured.response.Response;

public class UserResult {
	
	@Test
	public void createUserWithFullJSON() {
		
		String token = "0268523fee0ab5933d841e59af3e0391dfb64565ad578cc550463ab1a4b2b93e";
	
	Self sf = new Self("http://www.sf.com");
	Edit ed = new Edit("https://www.ed.com");
	Avatar av = new Avatar("http://www.av.com");
	
	Links ln = new Links(sf,ed,av);
	
	UsersInfo uf = new UsersInfo("Female", "14-07-1990", "jas11@gmail.com", "8123456789", "www.jas.com",
			"56 Chandigarh", "active", "Jas", "Kaur", ln);
	
	String UserJsonPayload = TestUtil.getSerializeJSON(uf);
	System.out.println(UserJsonPayload);
	
	Map<String,String> authTokenMap = new HashMap<String,String>();
	authTokenMap.put("Authorization", "Bearer " +token);
	Response response =RestClient.doPost("JSON", "https://gorest.co.in", "/public-api/users", authTokenMap, null, true, UserJsonPayload);
	System.out.println(response.prettyPrint());
	System.out.println(response.getStatusCode());

}
}
