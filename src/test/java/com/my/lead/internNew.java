package com.my.lead;

import org.testng.Assert;
import org.testng.annotations.Test;

public class internNew 
{
		
		String vCity, vState, vZip;
		@Test
		public void verifyAddressMapping(String vCityStateZip)
		{
			
			
			String reg = ", ";
			// Reads Santa Clara, California, 56895
String[] vCity1 = vCityStateZip.split(reg);
vCity = vCity1[0];
vState = vCity1[1];
vZip = vCity1[2];
System.out.println("City is: "+vCity);
System.out.println("State is: "+vState);
System.out.println("Zip is: "+vZip);
			
						// Reads Santa Clara, California, 56895
			String[] CityStateZip = vCityStateZip.split(",",2); 		// To extract Santa Clara
			vCity = CityStateZip[0]; 									//Stores Santa Clara
			String vStateZip = CityStateZip[1]; 						// Stores California, 56895
			String [] vStateZipArray =vStateZip.split("\\d+", 2); 		//Extracts California, before 1st occurance of a digit
			vState= vStateZipArray[0].trim(); 							//Removes leading and trailing spaces
			vZip = vStateZip.substring(vState.length() + 1).trim(); 	//Stores 56895
			
			System.out.println("City is: "+vCity);
			System.out.println("State is: "+vState);
			System.out.println("Zip is: "+vZip);
			
			
			if(vCity.equals("Santa Clara") && vState.equals("California") && vZip.equals("56895"))
			{
				System.out.println("Address values are matching.");
			}
			else
			{
				Assert.fail("Address values are NOT matching ----- TEST FAILED.");
			}
		}
}
