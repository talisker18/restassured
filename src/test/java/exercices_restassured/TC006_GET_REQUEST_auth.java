package exercices_restassured;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//example for basic auth (username + pw)
/*
 * 
 * Basic authentication is a simple authentication scheme built into the HTTP protocol. 
 * The client sends HTTP requests with the Authorization header that contains the word Basic word 
 * followed by a space and a base64-encoded string username:password .
 * 
 * */
public class TC006_GET_REQUEST_auth {
	
	@Test
	public void authorizeTest() {
		//base uri
		RestAssured.baseURI="some authentication api";
		
		//before specify request obj: specify basic auth
		PreemptiveBasicAuthScheme authObj = new PreemptiveBasicAuthScheme();
		authObj.setUserName("joel");
		authObj.setPassword("test");
		
		RestAssured.authentication=authObj; //Set an authentication scheme that should be used for each request. 
		
		//request obj
		RequestSpecification httpRequest = RestAssured.given();
		
		//response
		Response response = httpRequest.request(Method.GET,"/");
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
	}

}
