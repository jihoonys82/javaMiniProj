package injung;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JLabel lblServer;
	private JLabel lblPort;
	private JLabel lblHost;
	
	private JTextField txtPhotoFolder;
	private JTextField txtUrl;
	private JTextField txtId;
	private JTextField txtPw;
	private JTextField txtLoc;
	private JTextField txtPort;
	private JTextField txtHost;
	
	private JButton btnOk;
	private JButton btnCancel;
	
	private static final int SUCCESSED = 0;	// ModifyValue 메소드가 정상작동시 반환값 
	private static final int FAILED = 1;
	
	// properties를 load할 때 필요한 변수들 
	PropertiesLoad proLoad = new PropertiesLoad();	// properties를 읽어오는 클래스 
	private Properties prop = PropertiesLoad.getProperties();
	
	// properties를 store할 때 필요햔 변수들 
	PropertiesStore proStore = new PropertiesStore();	// properties를 내보내는 클래스 
	private Properties pro = PropertiesStore.setProperties();
	FileOutputStream fos;	
	
	public void loadValue() {	// String으로  Property안의 경로들 받아오기 
		String strPw = prop.getProperty("PW");
		txtPw.setText(strPw); // 패스워드 받아오기 
	
		String strId = prop.getProperty("ID");
		txtId.setText(strId); // ID 받아오기 
		
		String strUrl = prop.getProperty("URL");
		txtUrl.setText(strUrl);	// URL 경로 받아오기 
		
		String strPhoto = prop.getProperty("PHOTOPATH");
		txtPhotoFolder.setText(strPhoto);
		
		String strPort = prop.getProperty("PORT");
		txtPort.setText(strPort);
		
		String strHost = prop.getProperty("HOST");
		txtHost.setText(strHost);
		
		String strLocation = prop.getProperty("LOCATION");
		txtLoc.setText(strLocation);	// 저장소 경로 받아오기 
	}
	
	public PropertyPanel() {
		setBounds(0,0,900,500);
		setLayout(null);

		routePane = new JPanel();	// 경로 Panel 
		routePane.setBounds(-41, 10, 884, 415);
		routePane.setLayout(null);
		
		lblPhotoFolder = new JLabel("사진 폴더");	// 사진 폴더 경로 
		lblPhotoFolder.setBounds(203, 122, 99, 33);
				
		lblDbRoute = new JLabel("DB 경로");	// DB 경로 
		lblDbRoute.setBounds(203, 181, 99, 33);
		
		lblUrl = new JLabel("URL");	
		lblUrl.setBounds(314, 190, 57, 15);
		
		lblId = new JLabel("ID");
		lblId.setBounds(314, 244, 57, 15);
		
		lblPw = new JLabel("PW");
		lblPw.setBounds(314, 298, 57, 15);
		
		lblDb = new JLabel("기본저장소");
		lblDb.setBounds(292, 352, 79, 15);
		
		lblServer = new JLabel("서버");
		lblServer.setBounds(203, 25, 99, 33);
		
		lblHost = new JLabel("호스트");
		lblHost.setBounds(315, 34, 57, 15);
		
		lblPort = new JLabel("접속포트");
		lblPort.setBounds(315, 81, 57, 15);
		
		txtPhotoFolder = new JTextField();	
		txtPhotoFolder.setBounds(413, 125, 286, 27);
		txtPhotoFolder.setColumns(10);
		txtPhotoFolder.setEditable(true);
		
		txtUrl = new JTextField();	
		txtUrl.setColumns(10);
		txtUrl.setBounds(413, 184, 286, 27);
		txtUrl.setEditable(true);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(413, 238, 286, 27);
		txtId.setEditable(true);
		
		txtPw = new JTextField();
		txtPw.setColumns(10);
		txtPw.setBounds(413, 292, 286, 27);
		txtPw.setEditable(true);

		txtLoc = new JTextField();
		txtLoc.setColumns(10);
		txtLoc.setBounds(413, 349, 286, 27);
		txtLoc.setEditable(true);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(414, 78, 286, 27);
		txtPort.setEditable(true);
		
		txtHost = new JTextField();
		txtHost.setColumns(10);
		txtHost.setBounds(414, 34, 286, 27);
		txtHost.setEditable(true);

		btnPane = new JPanel();	// 버튼 Panel 
		btnPane.setBounds(0, 443, 884, 57);
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnOk = new JButton("확인");	
		btnOk.addActionListener(this);
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		
		btnPane.add(btnOk);
		btnPane.add(btnCancel);
		
		routePane.add(lblDb);
		routePane.add(lblPw);
		routePane.add(lblId);
		routePane.add(lblUrl);
		routePane.add(txtHost);
		routePane.add(txtPort);
		routePane.add(txtLoc);
		routePane.add(txtPw);
		routePane.add(txtId);
		routePane.add(txtUrl);
		routePane.add(txtPhotoFolder);
		routePane.add(lblDbRoute);
		routePane.add(lblPhotoFolder);
		routePane.add(lblServer);
		routePane.add(lblHost);
		routePane.add(lblPort);
		
		loadValue();

		add(btnPane);
		add(routePane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnCancel)) {	// 취소 버튼 클릭시 화면 지우기 
			removeAll();
			validate(); 
			repaint();
		
		} else if (e.getSource().equals(btnOk)) { // 확인버튼 클릭시 
			modifyValue();					  // 값 변경 메소드 실행 
			
			if (modifyValue() == SUCCESSED) {	// 값 변경 되었을 때 설정 저장 다이어로그 띄우기
			JOptionPane.showMessageDialog(null, "설정이 저장되었습니다");
			}	
		}	// 첫번째 if문 끝
	}
	
	public int modifyValue() {	// value 변경 메소드 
		
		int result = FAILED;
		
		pro.setProperty("ID",txtId.getText());	// 입력값 property에 set
		pro.setProperty("PW", txtPw.getText());
		pro.setProperty("PORT", txtPort.getText());
		pro.setProperty("HOST", txtHost.getText());
		pro.setProperty("LOCATION", txtLoc.getText());
		pro.setProperty("URL", txtUrl.getText());
		pro.setProperty("PHOTOPATH", txtPhotoFolder.getText());
		
		try {
			fos = new FileOutputStream("./Outcomes/Jdbc.properties");
			pro.store(fos, "EDIT");	// 변경값 저장 
			result = SUCCESSED;				// 변경 성공 시 result에 성공 반환 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return result;	
	}
	
}
