package CaseStudyCodingTask.JobManagementService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * Thread Safe Singleton Design Pattern applied to have only unique instance of ConfigurationManager.
 *
 */

public class ConfigurationManager {

	private static ConfigurationManager instanceOfSingleton;
	
	private static Properties properties;

	private ConfigurationManager() {

		ConfigurationManager.properties = new Properties();
		
		try {
			
			final String resourceName = "config.properties";
			ClassLoader loader = Thread.currentThread().getContextClassLoader();

			InputStream inputStream = loader.getResourceAsStream(resourceName);
			
			if(inputStream != null)
			{
				ConfigurationManager.properties = readPropertiesListFromFile(inputStream);	
			}
			else
			{
				System.out.println(String.format("ERROR: File %s not found!", resourceName));
			}
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static synchronized ConfigurationManager getInstance(){
		
        if(instanceOfSingleton == null){
        	
        	instanceOfSingleton = new ConfigurationManager();
        }
        
        return instanceOfSingleton;
    }

	private static Properties readPropertiesListFromFile(InputStream inputStream) {

		Properties properties = new Properties();

		try {
			
			properties.load(inputStream);

		} catch (IOException ex) {

			System.out.println(ex.toString());

		} finally {

			if (inputStream != null) {
				try {

					inputStream.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		
		return properties;
	}

	public static String getProperty(String property) {
		
		return properties.containsKey(property) ? properties.getProperty(property) : "DEFAULT";
	}
}