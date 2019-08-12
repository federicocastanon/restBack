package federico.levels.restBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import federico.levels.restBack.domain.Note;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestBackApplicationTests {

	
	static String filter = "milk";
	
	private final String CONTEXT_PATH = "/api/notes";
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void isAlive(){	
		
		given().
	    when().
	        get(CONTEXT_PATH + "/alive").
	    then().
	        assertThat()
	        .statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void isDBAlive(){
		//testing for id 1, which is the one we insert on startup
		given().
	    when().
	        get(CONTEXT_PATH + "/dbAliveAndWell").
	    then().body("id",equalTo(1));
	}
		
	@Test
	public void getNoteById(){
		//testing for our first note
		given().
	    when().
	        get(CONTEXT_PATH + "/1").
	    then().body("id",equalTo(1)).and().body("body", equalTo("Ask Larry about the TPS reports."));
	}
	
	@Test
	public void insertNewNote(){
		String newBody = "Pick up milk!";
		Map<String, String>  jsonAsMap = new HashMap<String, String>();
		jsonAsMap.put("body", newBody );
		
		given().
	       contentType("application/json").
	       body(jsonAsMap).
	    when().
	        post(CONTEXT_PATH + "/").
	    then().body("id",not(1)).and().body("body", equalTo(newBody));
	}
	
	@Test
	public void editNote(){
		
		String newBody = "This body is now different!";
		Map<String, String>  jsonAsMap = new HashMap<String, String>();
		jsonAsMap.put("body", newBody );
		jsonAsMap.put("id", "1" );
		
		given().
	       contentType("application/json").
	       body(jsonAsMap).
	    when().
	        put(CONTEXT_PATH + '/').
	    then().body("errorCode",equalTo(0));
	}
	
	@Test
	public void getAllNotes(){
		// Extract
		List<Note> notes = given()
				.contentType("application/json")
	            .when()
	                .get(CONTEXT_PATH + "/")
	            .then()
	                .extract().body()
	                .jsonPath().getList(".", Note.class);
				
		//We test for more than one note
		assertTrue(notes.size()>0);
	}

	@Test
	public void getAllNotesWithFilter(){
		String queryString = "milk";
		List<Note> notes = given()
			       .contentType("application/json")
			       .queryParam("query", queryString)
			       .when()
	                .get(CONTEXT_PATH + "/")
	            .then()
	                .extract().body()
	                .jsonPath().getList(".", Note.class);
		//every note we get should contain the query string
		for(Note n : notes){
			assertTrue(n.getBody().contains(queryString));
		}
		
	}
	
	@Test
	public void deleteNote(){
		//we delete the second record, the one we inserted before
		given().
	       contentType("application/json").
	    when().
	        delete(CONTEXT_PATH + "/2").
	    then().
	    body("errorCode",equalTo(0));
		
		//we try to delete it again and we get an error code 1
		given().
	    when().
	    delete(CONTEXT_PATH + "/2").
	    then().
	    body("errorCode",equalTo(1));
	}
	
	
}
