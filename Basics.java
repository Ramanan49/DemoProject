import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;

import static io.restassured.RestAssured.*;

public class Basics {

	public static void main(String[] args) {
		// Validate the ADD place API is working as Expected
		
		//given-All input details
		//when-Submit the API
		//then-Validate the response

		//POST-Add the new address	
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response= given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		
		String newAddress="70 winter walk, USA";
		//PUT-Update the address
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		
	// GET the address
		
	String getPlaceresponse=	given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid)
		.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	JsonPath js1=new JsonPath(getPlaceresponse);
	String actualAddress=	js1.getString("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, newAddress);
	
}
}