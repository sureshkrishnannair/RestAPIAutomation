package dynamicJSON;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.Payload;


public class dynamicJSON {
	
	String id;
	
	@Test(dataProvider="bookdata")
	public void addbook(String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String  response=given().header("Content-Type","application/json")
		.body(Payload.addbook(aisle))
		.when().post("Library/Addbook.php")
		.then().extract().response().asString();
		
		System.out.println("Response is "+response);
		
		JsonPath jpath=new JsonPath(response);
		String ID=jpath.getString("ID");
		this.id=ID;
		
	}
	
	
	@DataProvider(name="bookdata")
	public Object[][] addBookTD() {
		return new Object[][] {{"abc"},{"def"},{"efg"}};
	}
	
	/*@Test
	public void deleteBook() {
		
		System.out.println("id is"+id);
		String deleteResponse=given().header("Content-Type","application/json")
		.body("{\"ID\" : \""+id+"\"} ")
		.when().post("/Library/DeleteBook.php")
		.then().extract().response().asString();
		
		System.out.println("DeletedResponse is"+deleteResponse);
		
	}*/
	

}
