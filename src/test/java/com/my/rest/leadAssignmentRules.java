package com.my.rest;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONTokener;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;



public class leadAssignmentRules {
 
    static final String USERNAME     = "my@my.com.dev";
    static final String PASSWORD     = "mydsfsdterreg324235245";
    static final String LOGINURL     = "https://test.salesforce.com";
    static final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
    static final String CLIENTID	 = "242t43trwfdsf44OcvXtDoUO.Ac17Y4";
    static final String CLIENTSECRET = "etret";
    
    
    private static String REST_ENDPOINT = "/services/data" ;
    private static String API_VERSION = "/v32.0" ;
    private static String baseUri;
    private static Header oauthHeader;
    private static Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
    private static String[] leadId= new String[99] ;
    private static String leadFirstName;
    private static String leadLastName;
    private static String leadCompany;
    private static String leadOwner;
    private static String leadOwnerName;
    private static Header assignmentRule = new BasicHeader("Sforce-Auto-Assign", "TRUE");
    private static String QueueOwner;
    private static String Rule_Number;
 
    @Test
    public void DMLReSTTest()
    {
        HttpClient httpclient = HttpClientBuilder.create().build();
 
        // Assemble the login request URL
        String loginURL = LOGINURL +
                          GRANTSERVICE +
                          "&client_id=" + CLIENTID +
                          "&client_secret=" + CLIENTSECRET +
                          "&username=" + USERNAME +
                          "&password=" + PASSWORD;
 
        // Login requests must be POSTs
        HttpPost httpPost = new HttpPost(loginURL);
        HttpResponse response = null;
        httpPost.addHeader(assignmentRule);
 
        try 
        {
            // Execute the login POST request
            response = httpclient.execute(httpPost);
        } 
        catch (ClientProtocolException cpException) 
        {
            cpException.printStackTrace();
        } 
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
 
        // verify response is HTTP OK
        final int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK)
        {
            System.out.println("Error authenticating to Force.com: "+statusCode);
            return;
        }
 
        String getResult = null;
        try 
        {
            getResult = EntityUtils.toString(response.getEntity());
        } 
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
 
        JSONObject jsonObject = null;
        String loginAccessToken = null;
        String loginInstanceUrl = null;
 
        try {
            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
            loginAccessToken = jsonObject.getString("access_token");
            loginInstanceUrl = jsonObject.getString("instance_url");
        	}
        catch (JSONException jsonException)
        {
            jsonException.printStackTrace();
        }
 
        baseUri = loginInstanceUrl + REST_ENDPOINT + API_VERSION ;
        oauthHeader = new BasicHeader("Authorization", "OAuth " + loginAccessToken) ;
        System.out.println("oauthHeader1: " + oauthHeader);
        System.out.println("\n" + response.getStatusLine());
        System.out.println("Successful login");
        System.out.println("instance URL: "+loginInstanceUrl);
        System.out.println("access token/session ID: "+loginAccessToken);
        System.out.println("baseUri: "+ baseUri);        
 
        // Run code to insert, query records in Salesforce using REST API
        createLeads();
       
        httpPost.releaseConnection();
    }
    
    
 // Create Leads using REST HttpPost
    public static void createLeads() 
    {
        System.out.println("\n_______________ Lead INSERT _______________");
 
        String uri = baseUri + "/sobjects/Lead/";

        
        try {
            //Parsing the contents of the JSON file to fetch records
        	
        	org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();

        	FileReader reader = new FileReader("../myAutomation/payload/leadSFDC.json");

        	Object obj=parser.parse(reader);
        	org.json.simple.JSONObject insertObj=(org.json.simple.JSONObject) obj;
        	
        	org.json.simple.JSONArray insertArray = (org.json.simple.JSONArray) insertObj.get("records");
        	
        	
        	//loop to get each attribute of lead record and inserting the record using the JSON object through REST API
        	//and calling the queryLeads() method and AssignmentTest() method
        	
        	for(int i=0;i<insertArray.size();i++)
        	{
        		
        	org.json.simple.JSONObject records = (org.json.simple.JSONObject) insertArray.get(i);
            String FirstName = (String) records.get("FirstName");
            String LastName = (String) records.get("LastName");
            String Company = (String) records.get("Company");
            String Domain = (String) records.get("Domain");
            String city = (String) records.get("city");
            String country = (String) records.get("country");
            String Title = (String) records.get("Title");
            String Phone = (String) records.get("Phone");
            String countryCode = (String) records.get("countryCode");
            String postalCode = (String) records.get("postalCode");
            String state = (String) records.get("state");
            String stateCode = (String) records.get("stateCode");
            String street = (String) records.get("street");
            String Value_Adds__c = (String) records.get("Value_Adds__c");
            String Partner_Portal_Request__c = (String) records.get("Partner_Portal_Request__c");
            String Federal_Influenced__c = (String) records.get("Federal_Influenced__c");
            Rule_Number = (String) records.get("Rule_Number");
            
            //Calling currentDateTime() to create unique Email-id
            String Email= currentDateTime()+Domain ;
            FirstName=FirstName+"  "+currentDateTime();
            Phone=Phone+generateRandomDigits(7);
            street=street+"  "+generateRandomDigits(5);
            
            
            System.out.println("######Record inserting for rule number"+Rule_Number+"##########\n################################\n\n");
 
            //create the JSON object containing the new lead details.
            JSONObject lead2 = new JSONObject();
            	lead2.put("FirstName" , FirstName);
            	lead2.put("LastName" , LastName);
            	lead2.put("Company" ,Company);
            	lead2.put("Email" ,Email);
            	lead2.put("city" ,city);
            	lead2.put("country" ,country);
            	lead2.put("countryCode" ,countryCode);
            	lead2.put("Title" ,Title);
            	lead2.put("Phone" ,Phone);
            	lead2.put("postalCode",postalCode);
            	lead2.put("state", state);
            	lead2.put("stateCode", stateCode);
            	lead2.put("street", street);
            	lead2.put("Value_Adds__c" , Value_Adds__c);
            	lead2.put("Partner_Portal_Request__c", Partner_Portal_Request__c);
            	lead2.put("Federal_Influenced__c", Federal_Influenced__c);
            	
            	
            	
            System.out.println("JSON for lead record to be inserted:\n" + lead2.toString(1));
            
            //Construct the objects needed for the request
            HttpClient httpClient = HttpClientBuilder.create().build();
 
            HttpPost httpPost = new HttpPost(uri);
            httpPost.addHeader(oauthHeader);
            httpPost.addHeader(prettyPrintHeader);
            // The message we are going to post
            StringEntity body = new StringEntity(lead2.toString(1));
            body.setContentType("application/json");
            httpPost.setEntity(body);
 
            //Make the request
            HttpResponse response = httpClient.execute(httpPost);
 
            //Process the results
            int statusCode = response.getStatusLine().getStatusCode();
            
            if (statusCode == 201) {
                String response_string = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(response_string);
                // Store the retrieved lead id to use when we update the lead.
                leadId[i] = json.getString("id");
                System.out.println("New Lead id from response: " + leadId[i]);
                
                // Calling the queryLeads() to get the leadID and LeadOwner of the record inserted
                queryLeads(i);
                //Testing the Owner assigned by passing the Rule_Number to fetch expected owner from JSON file
                AssignmentTest(Rule_Number);
                

                
            } else {
               Assert.fail("Insertion unsuccessful. Status code returned is " + statusCode);
            }
         }
        }  catch (org.json.simple.parser.ParseException e) {
        	e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
      }
    }
 

    
 
    // Query Leads using REST HttpGet
    public static void queryLeads(int k) {
        System.out.println("\n_______________ Lead QUERY _______________");
        try {
 
            //Set up the HTTP objects needed to make the request.
            HttpClient httpClient = HttpClientBuilder.create().build();  
            
 
            String uri = baseUri + "/query?q=Select+Id+,+OwnerId+,+Owner__c+,FirstName+,+LastName+,+Company+,+Owner.Name+From+Lead+WHERE+Id='"+leadId[k]+"'";
            System.out.println("Query URL: " + uri);
            HttpGet httpGet = new HttpGet(uri);
            System.out.println("oauthHeader2: " + oauthHeader);
            httpGet.addHeader(oauthHeader);
            httpGet.addHeader(prettyPrintHeader);
 
            // Make the request.
            HttpResponse response = httpClient.execute(httpGet);
 
            // Process the result
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String response_string = EntityUtils.toString(response.getEntity());
                try {
                    JSONObject json = new JSONObject(response_string);
                    System.out.println("JSON result of Query:\n" + json.toString(1));
                    JSONArray j = json.getJSONArray("records");
                    for (int i = 0; i < j.length(); i++){
                        leadId[k] = json.getJSONArray("records").getJSONObject(i).getString("Id");
                        leadFirstName = json.getJSONArray("records").getJSONObject(i).getString("FirstName");
                        leadLastName = json.getJSONArray("records").getJSONObject(i).getString("LastName");
                        leadCompany = json.getJSONArray("records").getJSONObject(i).getString("Company");
                        leadOwner = json.getJSONArray("records").getJSONObject(i).getString("OwnerId");
                        leadOwnerName = json.getJSONArray("records").getJSONObject(i).getJSONObject("Owner").getString("Name");
                        System.out.println("Lead record is: " + (i+1) + ". " + leadId[k] + " " + leadFirstName + " " + leadLastName + " " + leadCompany + " "+ leadOwner +""+leadOwnerName);
                    }
                    
                } catch (JSONException je) {
                    je.printStackTrace();
                }
            } else {
                System.out.println("Query was unsuccessful. Status code returned is " + statusCode);
                Assert.fail("An error has occured. Http status: " + response.getStatusLine().getStatusCode());
                System.out.println(getBody(response.getEntity().getContent()));
                System.exit(-1);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
   
    private static String getBody(InputStream inputStream) {
        String result = "";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(inputStream)
            );
            String inputLine;
            while ( (inputLine = in.readLine() ) != null ) {
                result += inputLine;
                result += "\n";
            }
            in.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
    
    public static void AssignmentTest(String Rule_Number)
    {
    	System.out.println("\n_______________ Lead Assignment Test _______________");
    	System.out.println("\nActual Lead owner is "+leadOwnerName+"\n");
    	
    	//Calling Queue() with Rule_Number to get the expected Queue Owner from the JSON file
    	QueueOwner=Queue(Rule_Number);
    	
    	//Comparing the actual and expected lead owner
    	Assert.assertEquals(leadOwnerName, QueueOwner);
    	System.out.println("\nTEST PASSED");
    	
    	
    	
    	System.out.println("\n_______________  _______________");
    	System.out.println("\n_______________  _______________");
    	System.out.println("\n");
    	leadOwnerName="";
    }
    
    public static String currentDateTime(){
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("hmmassSSS"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}
    
    public static String Queue(String Rule_Number) {
        //Creating a JSONParser object to get the expected queue owner from the JSON file
  	   
  	   String Owner = null;
  	   
        try {
        	org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();

        	FileReader reader = new FileReader("../myAutomation/payload/leadSFDC.json");

        	Object obj=parser.parse(reader);
        	org.json.simple.JSONObject insertObj=(org.json.simple.JSONObject) obj;
        	
        	org.json.simple.JSONObject OwnerObj = (org.json.simple.JSONObject) insertObj.get("QueueOwner");
        	
        	Owner= (String) OwnerObj.get(Rule_Number);
        	
        	System.out.println("Expected lead Owner is "+Owner);
        	
        	
         
        	
        } catch (FileNotFoundException e) {
              e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (ParseException e) {
           e.printStackTrace();
        }
		return Owner;
        
     }
    
    public static String generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return String.valueOf(m + new Random().nextInt(9 * m));
    }
}

