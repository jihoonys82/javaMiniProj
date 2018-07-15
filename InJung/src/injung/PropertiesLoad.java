package injung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 * 07.15
 * 작성자: 송주현
 *  
 */

public class PropertiesLoad {
	
	public Properties getProperties (String path) {
		
		Properties pro = null;
		FileInputStream fis = null;
		
		try {
			pro = new Properties();				// properties 객체 생성 
			fis = new FileInputStream(path);	// path를 인자로 받아 파일을 읽어옴
			pro.load(fis);						// properties 에 load 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			if (fis!= null) fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
		return pro;
	}
		
}
