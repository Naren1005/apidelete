package testCases;
import pojo.productpojo;
import routes.Routes;
import utils.configReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.payload;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;
import java.util.List;
import org.testng.ITestContext;


public class ProductTest extends BaseClass{
	
	
	//1) get all the products 
	@Test 
public void  testGetallProducts()
{
	given()
	.when()
			.get(Routes.Get_all_products)
	.then()
			.statusCode(200)
			.body("size()",greaterThan(0))
	.log().body();
	//.log().all();
}
	//2) test to retrieve single product by id
	@Test
	public void  testGetProductsbyID()
	{
	int product_id=	 configreader.getIntProperty("productID");
	
	System.out.println("fetching single product");
		given()
				.pathParam("id",2)
		.when()
				.get(Routes.Get_product_by_id)
		.then()
				.statusCode(200)
				.body("size()",greaterThan(2))
				.log().body()
		;
		//.log().all();
	}
	
	//3) test to retrieve single product by id
	@Test
	public void  testGetProductlimit()
	{
	int x=3;
		given()
				.pathParam("x",x)
		.when()
		 		.get(Routes.Get_product_limit)
		.then()
				.statusCode(200)
				.body("size()",equalTo(3))
				.log().body();
		//.log().all();
	}
	//4)test to retrivew the products in the sorted orders.
	
	@Test
	public void  testGetProducssortedDescendingorder()
	{
	 System.out.println("descending order sorting");
		Response response=
		given()
				.pathParam("order","desc")
		.when()
		 		.get(Routes.Get_products_sorted)
		.then()
				.statusCode(200)
		//.body("size()",greaterThan(2))
				.log().body()
				.extract().response();
		//.log().all();
		List<Integer> productID=response.jsonPath().getList("id",Integer.class);
		
		assertThat(issortedDescending(productID),is(true));
		
}  //5) Ascendingorder
	@Test
	public void  testGetProducssortedAscendingorder()
	{
	 System.out.println("Ascending order sorting");
		Response response=
		given()
				.pathParam("order","asc")
		.when()
		 		.get(Routes.Get_products_sorted)
		.then()
				.statusCode(200)
				//.body("size()",greaterThan(2))
				.log().body()
				.extract().response();
				//.log().all();
		List<Integer> productID=response.jsonPath().getList("id",Integer.class);
		
		assertThat(issortedAscending(productID),is(true));
}
	
//6)test to retrieve all products categories.
	
	@Test
	public void  testGetProducscategories()
	{
	
		given()
		
		.when()
				.get(Routes.Get_all_categories)
		.then()
				.statusCode(200)
		//.body("size()",greaterThan(2))
				.log().body();
		//.log().all();
	}
	
//7)test to retrieve the products categories.	
	@Test
	public void  testGetProductsByCategory()
	{
	
		given()
		 		.pathParam("category", "electronics")
		.when()
		 		.get(Routes.Get_product_by_category)
		.then()
				.statusCode(200)
				.body("size()",greaterThan(0))
				.body("category",everyItem(notNullValue()))
				.body("category", everyItem(equalTo("electronics")))
				.log().body();
				//.log().all();
	}
// 8) Add new product 
	int productid;
	@Test
	public void testAddNewProduct()
	{
	productpojo  newProduct=payload.productPaylod();
		 productid=
		given()
				 .contentType(ContentType.JSON)
				 .body(newProduct)
		.when()
		 		.post(Routes.Creat_product)
		.then()
				.log().body()
				.statusCode(201)
				.body("id",notNullValue())
				.body("title",equalTo(newProduct.getTitle()))
				.extract().jsonPath().getInt("id");
			}
	
	//9) update the product
	@Test
	public void testupdateProduct()
	{
		int product_id=	 configreader.getIntProperty("productID");
		     productpojo  updatedpayload=payload.productPaylod();
		 given()
				 .contentType(ContentType.JSON)
				 .body(updatedpayload)
				 .pathParam("id",product_id)
		.when()
		 		.put(Routes.Update_products)
		.then()
				.log().body()
				.statusCode(200)
				.body("id",notNullValue())
				.body("title",equalTo(updatedpayload.getTitle()))
			;
	}
	
	@Test
	public void testdeleteProduct()
	{
		int product_id=	 configreader.getIntProperty("productID");
		     //productpojo  deletedpayload=payload.productPaylod();
		 given()
				 .contentType(ContentType.JSON)
				
				 .pathParam("id",product_id)
		.when()
		 		.delete(Routes.Delete_products)
		.then()
				.log().body()
				.statusCode(200);
	}
	
}
