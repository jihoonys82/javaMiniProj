package injung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import injung.model.EmployeeDto;
import injung.model.InJungDao;

/*
 * 작성 일자 : 2018.07.05 
 * 수정 일자 : 2018.07.08
 * 
 * 
 * 작성자 : 송주현 
 * 수정자 : 권미현
 */

class LoginPanel extends JDialog implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1393142801124421909L;
	
	private JLabel lblEmployeeId;
	private JLabel lblPw;
	private JLabel lblWarn;			
	private JTextField txtEmployeeId;
	private JPasswordField txtPw;	
	private JButton btnLogin;
	private JButton btnFindInfo;
	
	private InJungDao dao = InJungDao.getInstance();
	private EmployeeDto eDto = new EmployeeDto();
	
	public static final int LOGIN_SUCCESSED = 0;
	public static final int LOGIN_FAILED = 1;
	
	public LoginPanel(JFrame frame, String title, boolean modal) {
		super(frame,title,true);
		setTitle("Login");
		setLocation(550,250);
		setSize(450,300);
		setResizable(false);
		
		JPanel loginPane = new JPanel(); // 로그인 패널 생성 
		loginPane.setBounds(0, 0, 434, 262);
		loginPane.setLayout(null);
		
		lblEmployeeId =  new JLabel("사번 : "); 	// 이메일 레이블 생성 
		lblEmployeeId.setBounds(46, 53, 77, 21);
		
		lblPw = new JLabel("패스워드 : "); // 패스워드 레이블 생성 
		lblPw.setBounds(46, 90, 77, 21);
		
		txtEmployeeId = new JTextField(); // 이메일 텍스트 생성 
		txtEmployeeId.setBounds(152, 53, 154, 21);
		
		txtPw = new JPasswordField(); // 패스워드 텍스트 생성 
		txtPw.setBounds(152, 90, 154, 21);
		txtPw.setEditable(true);
		txtPw.addKeyListener(this);	// 패스워드 필드에서 엔터 입력시 로그인 
		
		btnLogin = new JButton("Login"); // 로그인 버튼 생성 
		btnLogin.setBounds(325, 89, 97, 23);
		btnLogin.addActionListener(this);  // 로그인 버튼 클릭시 이메일과 비밀번호 일치 여부 검사
		
		lblWarn = new JLabel();	// 로그인 실패 시 뜨는 경고 레이블 
		lblWarn.setBounds(46, 198, 352, 45);
			
		btnFindInfo = new JButton("패스워드 찾기");
		btnFindInfo.setBounds(152, 147, 154, 28);
		btnFindInfo.addActionListener(this);	// 사번찾기 버튼 클릭시 분실 질문 페이지로 이동 
		
		loginPane.add(lblEmployeeId);
		loginPane.add(lblPw);
		loginPane.add(lblWarn);
		loginPane.add(txtEmployeeId);
		loginPane.add(txtPw);
		loginPane.add(btnLogin);		
		loginPane.add(btnFindInfo);
		getContentPane().add(loginPane);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {	
		if (e.getSource().equals(btnLogin)) {
			String strId = txtEmployeeId.getText();
			int toIntId = Integer.parseInt(strId); 
			
			isLoginCheck(toIntId);	// 로그인 일치 여부 
			
		} else if (e.getSource().equals(btnFindInfo)) {
			
		   FindIdPanel findIddialog = new FindIdPanel(this,"Create new password", true);	// 비밀번호 변경 다이얼로그로 이동 
		   findIddialog.setLocation(470, 300);
		   findIddialog.setSize(500,300);
		   findIddialog.setVisible(true);
	   
		} 		
	}		
	
	public int isLoginCheck(int empId) {
		
		eDto = dao.getEmployee(empId);	
		int login_result = LOGIN_FAILED;
		String strPW = String.valueOf(txtPw.getPassword());	// 패스워드필드에서 가져온 값 string에 담기 
		
		if ( (txtEmployeeId.getText().equals(Integer.toString(eDto.getEmployeeId()))) 
				&& (strPW.equals(eDto.getPassword()))){		//  1. 입력 사번과 DB사번이 같고, 입력 패스워드와 DB 패스워드가 같다면	
		
				login_result = LOGIN_SUCCESSED;		// 로그인 성공 		
				dispose();							// 로그인창 종료 
				
		} else if ( !(txtEmployeeId.getText().equals(Integer.toString(eDto.getEmployeeId()))) 
				|| !(strPW.equals(eDto.getPassword()))){	// 2. 입력 사번과 DB사번이 다르거나, 입력 패스워드와 DB 패스워드가 다르다면 
			
			lblWarn.setText("<html> 로그인 정보가 일치하지 않습니다 <br/> 다시 입력해주세요 </html>");		// 경고레이블 출력 
			
		}	//  if문 끝 
			return login_result;	// 로그인 성공 결과 리턴 
	}	// isLoginCheck 메소드  
	
	
	public int getTxtEmployeeId() {		// MainFram에 넘길 ID값
		int id = Integer.parseInt(txtEmployeeId.getText());
		return id;
	}

	
	@Override
	public void keyPressed(KeyEvent e) {	// 엔터를 누를 때 로그인 체크 메소드 실행 
		if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
			String strId = txtEmployeeId.getText();
			int toIntId = Integer.parseInt(strId); 
			
			isLoginCheck(toIntId);	
		}	
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }
	@Override
	public void keyReleased(KeyEvent e) { }

 }
	
