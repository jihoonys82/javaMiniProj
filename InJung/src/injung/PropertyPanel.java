package injung;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * 07.15
 * 작성자: 송주현
 *  
 */

public class PropertyPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -1037341145144365055L;

	private JPanel routePane;
	private JPanel btnPane;
	
	private JLabel lblPhotoFolder;
	private JLabel lblDbRoute;
	private JLabel lblUrl;
	private JLabel lblDb;
	private JLabel lblPw;
	private JLabel lblId;
	
	private JTextField txtPhotoFolder;
	private JTextField txtUrl;
	private JTextField txtId;
	private JTextField txtPw;
	private JTextField txtDb;
	
	private JButton btnOk;
	private JButton btnCancel;
	
	PropertiesLoad proLoad = new PropertiesLoad();
	Properties property = proLoad.getProperties("C:\\Users\\지현화\\git\\새 폴더\\.project\\InJung\\Outcomes\\Jdbc.properties");	// path를 경로로 properties 호출 
//	Properties property = proLoad.getProperties("./Outcomes/Jdbc.properties");	// path를 경로로 properties 호출
//	private Properties prop = PropertiesLoad.getProperties();

	String strPw = property.getProperty("PW");	// String으로  Property안의 경로들 받아오기 
	String strId = property.getProperty("ID");
	String strUrl = property.getProperty("URL");
	String strDriver = property.getProperty("DRIVER");
	String strphoto = property.getProperty("PHOTOPATH");
	
	public PropertyPanel() {
		setBounds(0,0,900,500);
		setLayout(null);

		routePane = new JPanel();	// 경로 Panel 
		routePane.setBounds(0, 0, 884, 415);
		routePane.setLayout(null);
		
		lblPhotoFolder = new JLabel("사진 폴더");	// 사진 폴더 경로 
		lblPhotoFolder.setBounds(203, 89, 99, 33);
		
		txtPhotoFolder = new JTextField();	
		txtPhotoFolder.setBounds(413, 95, 286, 27);
		txtPhotoFolder.setColumns(10);
		txtPhotoFolder.setText(strphoto);	// 사진 폴더 경로 받아오기 
		txtPhotoFolder.setEditable(false);
		
		lblDbRoute = new JLabel("DB 경로");	// DB 경로 
		lblDbRoute.setBounds(203, 148, 99, 33);
		
		txtUrl = new JTextField();	
		txtUrl.setColumns(10);
		txtUrl.setBounds(413, 151, 286, 27);
		txtUrl.setText(strUrl);	// URL 경로 받아오기 
		txtUrl.setEditable(false);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(413, 205, 286, 27);
		txtId.setText(strId); // ID 받아오기 
		txtId.setEditable(false);
		
		txtPw = new JTextField();
		txtPw.setColumns(10);
		txtPw.setBounds(413, 259, 286, 27);
		txtPw.setText(strPw); // 패스워드 받아오기 
		txtPw.setEditable(false);

		txtDb = new JTextField();
		txtDb.setColumns(10);
		txtDb.setBounds(413, 316, 286, 27);
		txtDb.setText(strDriver);	// Driver 경로 받아오기 
		txtDb.setEditable(false);

		lblUrl = new JLabel("URL");
		lblUrl.setBounds(314, 157, 57, 15);
		
		lblId = new JLabel("ID");
		lblId.setBounds(314, 211, 57, 15);
		
		lblPw = new JLabel("PW");
		lblPw.setBounds(314, 265, 57, 15);
		
		lblDb = new JLabel("DB");
		lblDb.setBounds(314, 319, 57, 15);
		
		btnPane = new JPanel();	// 버튼 Panel 
		btnPane.setBounds(0, 443, 884, 57);
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnOk = new JButton("확인");	
		btnCancel = new JButton("취소");
		
		btnPane.add(btnOk);
		btnPane.add(btnCancel);
		
		routePane.add(lblDb);
		routePane.add(lblPw);
		routePane.add(lblId);
		routePane.add(lblUrl);
		routePane.add(txtDb);
		routePane.add(txtPw);
		routePane.add(txtId);
		routePane.add(txtUrl);
		routePane.add(txtPhotoFolder);
		routePane.add(lblDbRoute);
		routePane.add(lblPhotoFolder);

		add(btnPane);
		add(routePane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {	//TODO 액션리스너 구현 
		if (e.getSource().equals(btnCancel)) {
			
		} else if (e.getSource().equals(btnOk)) {
			
		}
	}	

}
