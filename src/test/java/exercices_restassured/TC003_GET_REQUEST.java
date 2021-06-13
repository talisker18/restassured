package exercices_restassured;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_REQUEST {
	
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
		
		//status code validation
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(200, statusCode);
		
		//print header information
		String headerFromResponse = response.getHeaders().toString();
		System.out.println(headerFromResponse);
		
		//verfiy some header info, like content type.
		Assert.assertEquals("application/json; charset=utf-8", response.header("Content-Type"));
		
	}

}
