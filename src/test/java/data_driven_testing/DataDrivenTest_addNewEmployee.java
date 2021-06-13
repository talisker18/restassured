package data_driven_testing;

import org.json.simple.JSONObject;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//getEmployeeData funktioniert nur mit TestNG
//dieser test wird mit TestNG 3x ausgeführt, da wir 3 testdatensets haben bei getEmployeeData (=data provider)

public class DataDrivenTest_addNewEmployee {
	
	@Test(dataProvider="empdataprovider")
	void addnewEmployee(String name, String salary, String age) {
		
		//prepare
		RestAssured.baseURI="some api";
		
		//prepare request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//test data
		JSONObject requestParams = new JSONObject();
		
		//first test object
		requestParams.put("name", name);
		requestParams.put("salary", salary);
		requestParams.put("age", age);
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString()); //toJSONString needed because we need json format with {} etc
		
		//send post request
		Response resp=httpRequest.request(Method.POST,"/create");
		
		//validate
		
		
	}
	
	@DataProvider(name="empdataprovider")
	String[][] getEmployeeData(){
		String empData [][]= {{"joel2","120000","34"},{"joel3","130000","35"},{"joel","150000","36"}};
		return empData;
	}

}
