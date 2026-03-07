package testneedtoremove;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class requestlog {
	
	public static void main(String[] args) {
		//Build request
		RequestSpecification requestSpecification= RestAssured.given();
		
		
		
		requestSpecification.baseUri("https://restful-booker.herokuapp.com");
		requestSpecification.basePath("/booking");
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body("{\r\n"
				+ "    \"firstname\": \"Sally\",\r\n"
				+ "    \"lastname\": \"Brown\",\r\n"
				+ "    \"totalprice\": 111,\r\n"
				+ "    \"depositpaid\": true,\r\n"
				+ "    \"bookingdates\": {\r\n"
				+ "        \"checkin\": \"2013-02-23\",\r\n"
				+ "        \"checkout\": \"2014-10-23\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\": \"Breakfast\"\r\n"
				+ "}");
		requestSpecification=requestSpecification.log().all();
	//Hit request	
	Response response=requestSpecification.post();
	
	 //Validate response
		ValidatableResponse validatableresponse= response.then().time(Matchers.lessThan(5000l));
		
		
		validatableresponse.statusCode(200);
		
		validatableresponse.log().all();
		
		
		
	}

}
