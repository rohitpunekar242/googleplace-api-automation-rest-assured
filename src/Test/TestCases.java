package Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;

public class TestCases {

	public static void main(String[] args) {
		
		///////////////////////////////////////////////////////////////////////////////////////////////

		//TestCase 1: Add Place
		
        //Declare base URI  
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Declare given, when and then data //Validate response //extract response in a variable
		String AddPlaceResponse = given().queryParam("key", "qaclick123").body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Indian\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"Pune\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		//extract the place id from response
		JsonPath js = new JsonPath(AddPlaceResponse);
		String place_id = js.getString("place_id");
		System.out.println("Place added successfully");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		//TestCase2: Update Place
		
		String newAddress = "Banglore";
		
		given().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		.body("{\r\n"
				+ "    \"place_id\": \""+place_id+"\",\r\n"
				+ "    \"address\": \""+newAddress+"\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalToIgnoringCase("Address successfully updated"));
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		
		//TestCase3: Get Place & validate the updated place 
		
		String GetPlaceResponse = given().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		.when().get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js2 = new JsonPath(GetPlaceResponse);
		String ActualAddress = js2.getString("address");
		Assert.assertEquals(ActualAddress, newAddress);
		System.out.println("Place updated successfully");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//TestCase4: Delete Place & validate response
		
		given().queryParam("key", "qaclick123")
		.body("{\r\n"
				+ "		    \"place_id\": \""+place_id+"\"\r\n"
				+ "		}")
		.when().delete("/maps/api/place/delete/json")
		.then().assertThat().statusCode(200).body("status", equalToIgnoringCase("OK"));
		System.out.println("Place deleted successfully");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		

	}

}
