package injung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * 07.15
 * 작성자: 송주현
 *  
 */

public class PropertiesLoad {
	
	public static Properties getProperties (String path) {
		
		Properties pro = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			pro = new Properties();				// properties 객체 생성 
			fis = new FileInputStream(path);	// path를 인자로 받아 파일을 읽어옴
			pro.load(fis);						// properties 에 load
			
			System.out.println(pro.getProperty("ID"));
			System.out.println("pro.getProperty : " + pro.getProperty("ID"));
			
			String str = pro.getProperty("ID");
			pro.setProperty("ID", str);
			fos = new FileOutputStream(path);
			pro.store(fos, "EDIT");
			
			System.out.println("strID : " + str);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis!= null) fis.close();
				if (fos!= null) fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			}	
		}
		return pro;
	}
	
	public static Properties getProperties () {
		Properties property = getProperties("./Outcomes/Jdbc.properties");
		return property;
	}
		
}
