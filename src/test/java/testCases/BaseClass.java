package testCases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes.Routes;
import utils.configReader;

public class BaseClass {
	
	RequestLoggingFilter requestloggingfilter;
	ResponseLoggingFilter responseloggingfilter;
	
	
	configReader configreader;
	
	@BeforeClass
	public void setup() throws FileNotFoundException
	{
		RestAssured.baseURI=Routes.BASE_URL;
		configreader =new configReader();
		
		FileOutputStream fos=new FileOutputStream(".\\log\\test_logging.log");
		PrintStream log=new PrintStream(fos,true);
		requestloggingfilter=new RequestLoggingFilter(log);
		responseloggingfilter=new ResponseLoggingFilter(log);
		RestAssured.filters(requestloggingfilter,responseloggingfilter);
	}
	
	//helper methods
	
	boolean issortedDescending(List<Integer> list)
	{
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)<list.get(i+1))
			{
				return false;
			}
		}
		return true;  
	}
	boolean issortedAscending(List<Integer> list)
	{
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)>list.get(i+1))
			{
				return false;
			}
		}
		return true;
	}

}
