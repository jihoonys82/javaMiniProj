package injung;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * 07.17
 * 작성자: 송주현
 *  
 */

public class PropertiesStore {
	
	public static Properties setProperties(String path) {
		Properties pro = null;
		FileOutputStream fos = null;

		try {
			pro = new Properties();	// properties 객체 생성 
			fos = new FileOutputStream(path);	// path를 인자로 받아 내보냄  	
			pro.store(fos, "EDIT");	
			
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
