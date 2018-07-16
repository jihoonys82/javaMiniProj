package injung;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesStore {
	
	public static Properties setProperties(String path) {
		Properties pro = null;
		FileOutputStream fos = null;
		
		PropertiesLoad proLoad = new PropertiesLoad();
		Properties prop = PropertiesLoad.getProperties();
		
		try {
			pro = new Properties();
//			System.out.println(pro.getProperty("ID"));
			fos = new FileOutputStream(path);
			
			pro.store(fos, "EDIT CONFIG");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return pro;
	}
	
	public static Properties setProperties() {
		Properties property = setProperties("./Outcomes/Jdbc.properties");
		return property;
	}
	
	
}
