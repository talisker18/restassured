package exercices_restassured;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_REQUEST {

	@Test
	public void getWeatherDetails() {
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/";
		
		//request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//response object
		Response response = httpRequest.request(Method.GET,"weather?q=London&appid=97ae01f9d209e86151cd33c9c5e9f37f");
		
		//print response
		String responseAsString=response.getBody().asString();
		System.out.println(responseAsString);
		
		//status code validation (expected,actual)
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(200, statusCode);
		
		//status line validation
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
		
	}
	
	@Test
	public void getWeatherDetails2() {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.request(Method.GET,"posts/2");
		
		System.out.println(response.getBody().asString());
	}
	
}
