package injung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * 07.15
 * �ۼ���: ������
 *  
 */

public class PropertiesLoad {

	public static Properties getProperties(String path) {

		Properties pro = null;
		FileInputStream fis = null;

		try {
			pro = new Properties(); // properties ��ü ����
			fis = new FileInputStream(path); // path�� ���ڷ� �޾� ������ �о��
			pro.load(fis); // properties �� load

//			FileOutputStream fos = new FileOutputStream(path);
//			pro.replace("id", "sadfsa");
//			pro.store(fos, "comment");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return pro;
	}

	public static Properties getProperties() {
		Properties property = getProperties("./Outcomes/Jdbc.properties");
		return property;
	}

}
