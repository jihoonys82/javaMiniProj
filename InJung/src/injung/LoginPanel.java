package injung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	public static final int LOGIN_SUCCESSED = 0;	// 로그인 성공일 때 
	public static final int LOGIN_FAILED = 1;		// 로그인 실패일 때 
	public static final int LOGIN_NO_ID = 2;		// DB에 없는 ID값일 때
	public static final int NO_NUMBER = 3;

	public LoginPanel(JFrame frame, String title, boolean modal) {
		
		super(frame, title, true);
		setTitle("Login");
		setLocation(550, 250);
		setSize(450, 300);
		setResizable(false);

		JPanel loginPane = new JPanel(); // 로그인 패널 생성
		loginPane.setBounds(0, 0, 434, 262);
		loginPane.setLayout(null);

		lblEmployeeId = new JLabel("사번 : "); // 사번 레이블 생성
		lblEmployeeId.setBounds(46, 53, 77, 21);

		lblPw = new JLabel("패스워드 : "); // 패스워드 레이블 생성
		lblPw.setBounds(46, 90, 77, 21);
		
//		NumberFormatter formatter = new NumberFormatter();
//		formatter.
//		formatter.setAllowsInvalid(false);
		
		txtEmployeeId = new JTextField(); // 사번 텍스트 생성
		txtEmployeeId.setBounds(152, 53, 154, 21);
		txtEmployeeId.addKeyListener(this);
		
		txtPw = new JPasswordField(); // 패스워드 텍스트 생성
		txtPw.setBounds(152, 90, 154, 21);
		txtPw.setEditable(true);
		txtPw.addKeyListener(this); // 패스워드 필드에서 엔터 입력시 로그인

		btnLogin = new JButton("Login"); // 로그인 버튼 생성
		btnLogin.setBounds(325, 89, 97, 23);
		btnLogin.addActionListener(this); // 로그인 버튼 클릭시 이메일과 비밀번호 일치 여부 검사

		lblWarn = new JLabel(); // 로그인 실패 시 뜨는 경고 레이블
		lblWarn.setBounds(46, 198, 352, 45);

		btnFindInfo = new JButton("패스워드 찾기");
		btnFindInfo.setBounds(152, 147, 154, 28);
		btnFindInfo.addActionListener(this); // 사번찾기 버튼 클릭시 분실 질문 페이지로 이동

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
	public void keyPressed(KeyEvent e) { // 엔터를 누를 때 로그인 체크 메소드 실행 
//		char ch = txtEmployeeId.getText().charAt(0);
//		char[] ch_1 = txtEmployeeId.getText().toCharArray();
//		char ch_2 = e.getKeyChar();
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {		
			btnLogin.doClick(); 
		}
		
//		} else if (!(Character.isDigit(ch_2))) {
//			JOptionPane.showMessageDialog(null, "문자임");
//		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	@Override
	public void keyReleased(KeyEvent e) { }

	private void loginAction() {		
		String strId = txtEmployeeId.getText();	
		int toIntId = Integer.parseInt(strId);		
		int loginCheck = isLoginCheck(toIntId);	// 텍스트필드 값을 int로 변환하여  isLoginCheck에 인자값을 넘겨줌 	
		
		if(loginCheck == LoginPanel.LOGIN_NO_ID) {	// DB에 사번 값이 없으면 경고 메세지 출력  
			JOptionPane.showMessageDialog(null, "없는 정보입니다");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLogin)) {	// 로그인 버튼을 눌렀을 때 loginAction 메소드 실행 
			loginAction();

		} else if (e.getSource().equals(btnFindInfo)) {		// 패스워드 찾기 버튼을 눌렀을 때 
			if (txtEmployeeId.getText().equals("")) {	// 1. 사번을 입력하지 않았으면 
				JOptionPane.showMessageDialog(null, "사번을 입력해주세요");					
			} else if (!(txtEmployeeId.getText().equals(""))) {	// 2. 사번이 입력되어있으면  			
				String strId = txtEmployeeId.getText().trim();			
				FindIdPanel findIddialog = new FindIdPanel(this, "Create new password", true ,strId);	// 비밀번호 변경 다이얼로그로 이동	
				findIddialog.setLocation(525, 300);
				findIddialog.setSize(500, 300);
				findIddialog.setVisible(true);

			}
		}
	}
	
	public int isLoginCheck(int empId) {	
		
//		for (char c : strTxtId.toCharArray()) {
//			if (!Character.isDigit(c)) {
//				System.out.println(c);
//				JOptionPane.showMessageDialog(null, "사번을 입력해주세요");
//				break;
//			}
//			System.out.print(c);
//		}

		eDto = dao.getEmployee(empId);
		if(eDto==null) {	// 1. DB에 없는 값이면 LOGIN_NO_ID -> 경고 메세지 출력 
			return LOGIN_NO_ID;
		}
		int login_result = LOGIN_FAILED;			
		
		String strTxtId = new String(txtEmployeeId.getText());	// ID 텍스트필드에서 가져온 값 담기
		char chTxtId = strTxtId.charAt(0);
		if (!Character.isDigit(chTxtId)) {
			return NO_NUMBER;
		}
		
		int id = eDto.getEmployeeId();
		int it = Integer.parseInt(txtEmployeeId.getText());
		
		char[] chTxtPw = txtPw.getPassword();	
		String strTxtPw = new String(chTxtPw);	// 패스워드필드에서 가져온 값 String에 담기
		String strDbPw = new String(eDto.getPassword());	// DTO에서 가져온 패스워드값 String에 담기  
		int intId = new Integer(eDto.getEmployeeId());		
		String strDbId = Integer.toString(intId);			// DTO에서 가져온 ID값 담기 
		
			
		if ((id == it)
				&& (strTxtPw.equals(strDbPw))) { // 2. 입력 사번과 DB사번이 같고, 입력 패스워드와 DB 패스워드가 같다면						
			login_result = LOGIN_SUCCESSED; // 로그인 성공
			dispose(); // 로그인창 종료
		
		} else if  (!(strTxtId.equals(strDbId))
				|| (!(strTxtPw.equals(strDbPw))) )  { 		// 3. 입력 사번과 DB사번이 다르거나, 입력 패스워드와 DB 패스워드가 다르다면					
			lblWarn.setText("<html> 로그인 정보가 일치하지 않습니다 <br/> 다시 입력해주세요 </html>"); // 경고레이블 출력
			
			char ch_0 = strTxtId.charAt(0);
			if (!(Character.isDigit(ch_0))) {
				JOptionPane.showMessageDialog(null,"X");
			}
			login_result = LOGIN_FAILED;
		} 
		return login_result; // 로그인 결과 리턴		
	} // isLoginCheck 메소드
	
	
	public int getTxtEmployeeId() { // MainFram에 넘길 ID값
		int id = Integer.parseInt(txtEmployeeId.getText());
		return id;
	}
	
}
