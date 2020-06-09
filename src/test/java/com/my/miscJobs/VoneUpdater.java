package com.my.miscJobs;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.my.base.Base;

import io.restassured.RestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.Listeners;  
@Listeners(com.my.common.ControlCenter.class)  


public class VoneUpdater  extends Base
{

	public static String requestBody;
	
	
		//Method to update Version one test with FAIL
        public void updateVoneFail(Object testId, Object StoryId) 
    	{
        	System.out.println("Test ID of the test to fail is : "+testId);
        	
        	Response resp =   RestAssured
      			  .given()
      			  .auth()
  				  .oauth2("1.BpvNfPkF1Z8rAIfsGeztxpAc//U=")
  				  .when()
  				  .get("https://www10.v1host.com/myNewV1/rest-1.v1/Data/Test?sel=ID&where=Number='"+testId+"'");

            String data = resp.asString();
        	
        	requestBody = "<Asset>\n" + 
            					 "	<Relation name=\"Status\" act=\"set\">\n" + 
            					 "		<Asset idref=\"TestStatus:155\" />\n" + 
            					 "	</Relation>\n" + 
            					 "</Asset>";
    	

        Response response = null;

        response = given()
        		  .auth()
				  .oauth2("1.BpvNfPkF1Z8rAIfsGeztxpAc//U=")
				  .contentType(ContentType.XML)
				  .accept(ContentType.XML)
				  .body(requestBody)
				  .when()
				  .post("https://www10.v1host.com/myNewV1/rest-1.v1/Data/Test/"+StoryId+"");

        System.out.println("Post Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Updated VersionOne Test Status.");
		
    	}
        
        
      //Method to update Version one test with TRUE
        public void updateVonePass(Object testId, Object StoryId) 
    	{

        	System.out.println("Test ID of the test to pass is : "+testId);
        	Response resp =   RestAssured
        			  .given()
        			  .auth()
    				  .oauth2("1.BpvNfPkF1Z8rAIfsGeztxpAc//U=")
    				  .when()
    				  .get("https://www10.v1host.com/myNewV1/rest-1.v1/Data/Test?sel=ID&where=Number='"+testId+"'");

              String data = resp.asString();
        	
             requestBody = "<Asset>\n" + 
            					 "	<Relation name=\"Status\" act=\"set\">\n" + 
            					 "		<Asset idref=\"TestStatus:129\" />\n" + 
            					 "	</Relation>\n" + 
            					 "</Asset>";
    	
    	

        Response response = null;

        response = given()
        		  .auth()
				  .oauth2("1.BpvNfPkF1Z8rAIfsGeztxpAc//U=")
				  .contentType(ContentType.XML)
				  .accept(ContentType.XML)
				  .body(requestBody)
				  .when()
				  .post("https://www10.v1host.com/myNewV1/rest-1.v1/Data/Test/"+StoryId+"");

        System.out.println("Post Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Updated VersionOne Test Status.");
		
    	}
    }
