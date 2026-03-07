package testCases;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONObject;

import org.testng.annotations.Test;

public class dataDriven {
	private static final String AUTH_TOKEN = "Bearer 3f83a50a477f692e93260f815b436c8626d12b6d3ee671eed969a89777a5c3e5";
	 private static final String BASE_URL = "https://simple-books-api.glitch.me/orders";
	 
	 //@Test(dataProvider="excelDataProvider", dataProviderClass=utils.dataProvider.class)
	 public void testWithExcelData(String bookId, String customerName)
	 {
		 testSubmitAndDeleteOrder(bookId,customerName);
	 }
	
	 @Test(dataProvider="jsonDataProvider", dataProviderClass=utils.dataProvider.class)
	 public void testWithJsonData(Map<String,String> data)
	 {
		 testSubmitAndDeleteOrder(data.get("BookID"), data.get("CustomerName"));
	 }
	 void testSubmitAndDeleteOrder(String bookId, String customerName)
	 {
		//Submitting order
		 JSONObject requestBody=new JSONObject();
		 requestBody.put("bookId", Integer.parseInt(bookId));
		 requestBody.put("customerName", customerName);
		 
		  String orderId=given()
		  	.contentType("application/json")
		  	.header("Authorization",AUTH_TOKEN)
		  	.body(requestBody.toString())
		  	
		 .when()
		 	.post(BASE_URL)
		 .then()
		 	.statusCode(201)
		 	.log().body()
		 	.extract().jsonPath().getString("orderId")
		 	;
		  
		  //Deleting order
		  given()
		  	.header("Authorization",AUTH_TOKEN)
		  	.pathParam("orderId", orderId)
		  	
		  .when()
		  		.delete(BASE_URL+"/{orderId}")
		  .then()
		  	.statusCode(204);
	 }
	
		 
}

