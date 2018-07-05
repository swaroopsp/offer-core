package com.worldpay.offerapp.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.worldpay.offerapp.OfferCoreApplication;
import com.worldpay.offerapp.model.Offer;
import com.worldpay.offerapp.repository.OfferRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = OfferCoreApplication.class)
public class OfferResourceIntegrationTest {
    @Autowired
    private OfferRepository offerRepository;
    
    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void shouldSaveTheOffer() throws InterruptedException{
        Map<String,Object> offerBody = new HashMap<>();
        offerBody.put("description", "Test Desc");
        offerBody.put("offerPrice", 1.1);
        offerBody.put("currency", "GBP");
        int id = given().contentType(ContentType.JSON)
		        .body(offerBody)
		        .when()
		        .post("/offer")
		        .then()
		        .body("description", equalTo("Test Desc"))
		        .body("currency", equalTo("GBP"))
		        .body("cancelled", equalTo(false))
                .statusCode(200)
                .extract().
                path("id");
        assertTrue(id != 0);
    }
    
    @Test
    public void shouldGetTheOfferByOfferId(){
        long id = offerRepository.save(Offer.builder().description("Test Desc").currency("GBP").offerPrice(1.1).build()).getId();
        
        given().contentType(ContentType.JSON)
        .param("id", id)
        .when()
        .get("/offer")
        .then()
        .body("id", equalTo((int)id))
        .body("description", equalTo("Test Desc"))
        .body("currency", equalTo("GBP"))
        .body("cancelled", equalTo(false))
        .statusCode(200);
        
        offerRepository.deleteById(Long.valueOf(id));
    }
    
    @Test
    public void shouldCancelTheOfferByOfferId(){
        long id = offerRepository.save(Offer.builder().description("Test Desc").currency("GBP").offerPrice(1.1).build()).getId();
        
        given().contentType(ContentType.JSON)
        .param("id", id)
        .when()
        .put("/offer")
        .then()
        .statusCode(200)
        .body("statusCode", equalTo(200))
        .body("message", equalTo("Offer with Id - " + id + " is cancelled"));
        
        offerRepository.deleteById(Long.valueOf(id));
    }
    
    @Test
    public void shouldReturnErrorMessageWhenOfferIsSearchedWithInvaidOfferId(){
    	 Random random = new Random();
    	 int id = random.nextInt(100000);
    	 given().contentType(ContentType.JSON)
	        .param("id", id)
	        .when()
	        .get("/offer")
	        .then()
	        .body("status", equalTo(404))
	        .body("error", equalTo("Not Found"))
	        .body("message", equalTo("Can not find offer with Id - " + id))
	        .statusCode(404);
    }
    
    @Test
    public void shouldReturnErrorMessageWhenOfferIsDeletedWithInvaidOfferId(){
    	 Random random = new Random();
    	 int id = random.nextInt(100000);
    	 given().contentType(ContentType.JSON)
	        .param("id", id)
	        .when()
	        .delete("/offer")
	        .then()
	        .body("status", equalTo(404))
	        .body("error", equalTo("Not Found"))
	        .body("message", equalTo("Offer with Id - " + id + " can not be deleted"))
	        .statusCode(404);
    }
    
    @Test
    public void shouldReturnErrorMessageWhenOfferIsCancelledWithInvaidOfferId(){
    	 Random random = new Random();
    	 int id = random.nextInt(100000);
    	 given().contentType(ContentType.JSON)
	        .param("id", id)
	        .when()
	        .put("/offer")
	        .then()
	        .body("status", equalTo(404))
	        .body("error", equalTo("Not Found"))
	        .body("message", equalTo("Offer with Id - " + id + " can not be cancelled"))
	        .statusCode(404);
    }
}