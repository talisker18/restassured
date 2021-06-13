package exercices_restassured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_REQUEST {
	
	@Test
	public void postSomething() {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		
		//request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//request payload sending with post request
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("title", "some title");
		requestParams.put("body", "some body");
		requestParams.put("userId", 1);
		
		//we need to send header information as well
		httpRequest.header("Content-Type","application/json");
		
		//define body to send
		httpRequest.body(requestParams.toJSONString()); //attach above data to the request
		
		//send the request
		Response response = httpRequest.request(Method.POST,"posts");
		
		
		//print response
		String responseAsString=response.getBody().asString();
		System.out.println(responseAsString);
		
		//status code validation
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(201, statusCode);
		
		//extract success code. this is a part of the response body
		//in this case the response equals the full body we already sent
		String titleOfResponse= response.jsonPath().get("title");
		System.out.println(titleOfResponse);
		Assert.assertEquals("some title", titleOfResponse);
		
		
	}

}
