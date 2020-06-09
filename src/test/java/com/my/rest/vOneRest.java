package com.my.rest;


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

public class vOneRest extends Base
{

	public static String requestBody;
	public static String testId = "AT-01053";
	
	
	
	
	@Test // Name: Test Salesforce Login.  ID: AT-01053
	public void executeTest()
	{
		
		System.out.println("Log in.");
		salesforceLogin(false);
	}
	
	
	public void salesforceLogin(boolean status)
	{
		if(status)
		{
			System.out.println("Test passed.");
			updateTestStatus(true);
		}
		else
		{
			System.out.println("Test failed.");
			updateTestStatus(false);
			Assert.fail();
		}
	}
	
    public void updateTestStatus(boolean testStatus) 
    {

    	
    	Response resp =   RestAssured
    			  .given()
    			  .auth()
				  .oauth2("1.yMg3WUW5al+BbmZt2jQFa9FlvSg=")
				  .when()
				  .get("https://www454.v1host.com/Test/rest-1.v1/Data/Test?sel=ID&where=Number='"+testId+"'");

    	String data = resp.asString();
    	System.out.println("Data is: "+data);

    	
    	if(testStatus)
    	{

            requestBody = "<Asset>\n" + 
            					 "	<Relation name=\"Status\" act=\"set\">\n" + 
            					 "		<Asset idref=\"TestStatus:129\" />\n" + 
            					 "	</Relation>\n" + 
            					 "</Asset>";
    	}
    	else
    	{

            requestBody = "<Asset>\n" + 
            					 "	<Relation name=\"Status\" act=\"set\">\n" + 
            					 "		<Asset idref=\"TestStatus:155\" />\n" + 
            					 "	</Relation>\n" + 
            					 "</Asset>";
    	}

        Response response = null;

        response = given()
        		  .auth()
				  .oauth2("1.yMg3WUW5al+BbmZt2jQFa9FlvSg=")
				  .contentType(ContentType.XML)
				  .accept(ContentType.XML)
				  .body(requestBody)
				  .when()
				  .post("https://www454.v1host.com/Test/rest-1.v1/Data/Test/1542");

        System.out.println("Post Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Updated VersionOne Test Status.");
    }

}