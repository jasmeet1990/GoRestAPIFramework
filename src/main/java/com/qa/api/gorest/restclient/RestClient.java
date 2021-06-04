package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	
	public static Response doGet(String contentType, String baseURI, String basePath, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {

		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, paramsMap, log);
			return getResponse("GET", request, basePath);
		}
		return null;
	}
	
	public static Response doPost(String contentType,String baseURI,String basePath,Map<String,String> token,Map<String,String> paramsMap,boolean log,Object obj) {
		if(setBaseURI(baseURI)) {
		RequestSpecification request = createRequest(contentType,token,paramsMap,log);
		addRequestPayLoad(request,obj);
		return getResponse("POST",request,basePath);
		}
		return null;
	}
	
	public static void addRequestPayLoad(RequestSpecification request,Object obj) {
		if(obj instanceof Map) {
				request.formParams((Map<String,String>)obj);
		}
		else {
		String jsonPayload = TestUtil.getSerializeJSON(obj);
		request.body(jsonPayload);
		}
	}
	
	private static boolean setBaseURI(String baseURI) {
		if(baseURI==null || baseURI.isEmpty()) {
			System.out.println("Please pass the coorect HTTP Method,either it is null,blank or empty");
			return false;
		}
		try {
		RestAssured.baseURI = baseURI;
		return true;
		}catch (Exception e) {
			System.out.println("Some exception got occured while assigning the base URI with RestAssured");
			return false;
		}
	}
	
	private static RequestSpecification createRequest(String contentType, Map<String, String> token,
			Map<String, String> paramsMap, boolean log) {
		RequestSpecification request;
		
		if(log) {
			request = RestAssured.given().log().all();
		}
		else {
			request = RestAssured.given();
		}
		
		if(token.size()>0) {
			//request.header("Authorization","Bearer "+token);
			request.headers(token);
		}
		
		if(!(paramsMap==null)) {
			request.queryParams(paramsMap);
		}
		
		if(contentType!=null) {
		if(contentType.equalsIgnoreCase("JSON")) {
			request.contentType(ContentType.JSON);
		}
		else if(contentType.equalsIgnoreCase("XML")) {
			request.contentType(ContentType.XML);
		}
		else if (contentType.equalsIgnoreCase("TEXT")) {
			request.contentType(ContentType.TEXT);
		}
		else if(contentType.equalsIgnoreCase("multipart")) {
			request.multiPart("image",new File("C:\\Users\\JASMEET KAUR\\Desktop\\Signature.jpg"));
		}
		}
		
		return request;
	}
	
	private static Response getResponse(String httpMethod,RequestSpecification request,String basePath) {
		return executeAPI(httpMethod,request,basePath);
	}
	
private static Response executeAPI(String httpMethod,RequestSpecification request,String basePath) {
	Response response = null;
	switch(httpMethod) {
	case "GET":
		response = request.get(basePath);
		break;
	case "POST":
		response = request.post(basePath);
		break;
	case "PUT":
		response = request.put(basePath);
		break;
	case "DELETE":
		response = request.delete(basePath);
		break;
		
		default:
			System.out.println("Please pass the correct httpMethod");
			break;
	}
	return response;
	}
}
