package exercices_restassured;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_REQUEST {

	@Test
	public void getWeatherDetails() {
		
		//define base URL
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/";
		
		//request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//make request and store response 
		Response response = httpRequest.request(Method.GET,"weather?q=London&appid=97ae01f9d209e86151cd33c9c5e9f37f");
		
		//print header info of response
		String headerInfo = response.getHeaders().toString();
		System.out.println(headerInfo);
		
		System.out.println("-------------------------------");
		
		//alternative
		Headers allHeaders = response.headers();
		
		for (Header header: allHeaders) {
			System.out.print(header.getName()+": ");
			System.out.print(header.getValue());
			System.out.println();
		}
		
	}
	
}
