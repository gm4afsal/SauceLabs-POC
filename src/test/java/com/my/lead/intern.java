package com.my.lead;

import org.testng.Assert;

public class intern extends internNew 
{
	static String vCityStateZip = "Santa Clara, California, 56895";
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("Test");
		intern in = new intern();
		in.verifyAddressMapping(vCityStateZip);
		
		
			
	}

}
