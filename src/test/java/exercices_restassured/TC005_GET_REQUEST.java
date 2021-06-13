package exercices_restassured;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_REQUEST {
	
	@Test
	public void getWeatherDetails() {
		
		//base uri
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/";
		
		//request obj
		RequestSpecification httpRequest = RestAssured.given();
		
		//response
		Response response = httpRequest.request(Method.GET,"weather?q=London&appid=97ae01f9d209e86151cd33c9c5e9f37f");
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		Assert.assertEquals(true, responseBody.contains("GB"));
		
		//extract individual fields and values of response
		
		JsonPath jsonpath = response.jsonPath(); //root json node
		
		System.out.println(jsonpath.get("coord"));
		
		//System.out.println(jsonpath.get("country")); --> gibt null zurück da country innerhalb eines json node
		//man kann benötigtes child node kriegen indem mit punkten durch die hierarchie iteriert
		
		System.out.println(jsonpath.get("coord.lon"));

		List<Integer> ids = jsonpath.get("weather.id");
		
		for (Integer id: ids) {
			System.out.println(id);
		}
		
		String country = jsonpath.get("sys.country");
		
		System.out.println(country);
		
		
		
	}

}
