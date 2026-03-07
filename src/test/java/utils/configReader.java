package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
	
	Properties properties;
	private static final String Config_File_Path=".\\src\\test\\resources\\config.properties";
	
	public configReader()
	{
		properties =new Properties();
		try(FileInputStream fs=new FileInputStream(Config_File_Path))
		{
		properties.load(fs);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Failed to load the config.properties file");
		}
		
	}
	
	public String getProperty(String key)
	{
		return properties.getProperty(key);
	}
	public int getIntProperty(String key)
	{
		return Integer.parseInt(properties.getProperty(key));
	}
}
