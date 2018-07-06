package injung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * 2018.07.05 
 * 
 * 작성자: 송주현 
 */

class loginPanel extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1393142801124421909L;
	
	private JLabel lblEmail;
	private JLabel lblPw;
	private JLabel lblWarn;			
	private JTextField txtEmail;
	private JPasswordField txtPw;	
	private JButton btnLogin;
	private JButton btnFindInfo;
	private boolean bLoginCheck;
	
	public loginPanel(JFrame frame, String title, boolean modal,int x,int y) {
		super(frame,title,true);
		setTitle("Login");
		setLocation(300,200);
		setSize(450,300);
		setResizable(false);
		
		JPanel loginPane = new JPanel(); // 로그인 패널 생성 
		loginPane.setBounds(0, 0, 434, 262);
		loginPane.setLayout(null);
		
		lblEmail =  new JLabel("이메일 : "); 	// 이메일 레이블 생성 
		lblEmail.setBounds(46, 53, 77, 21);
		
		lblPw = new JLabel("패스워드 : "); // 패스워드 레이블 생성 
		lblPw.setBounds(46, 90, 77, 21);
		
		txtEmail = new JTextField(); // 이메일 텍스트 생성 
		txtEmail.setBounds(152, 53, 154, 21);
		
		txtPw = new JPasswordField(); // 패스워드 텍스트 생성 
		txtPw.setBounds(152, 90, 154, 21);
		txtPw.setEchoChar('*');
		txtPw.setEditable(true);
		
		btnLogin = new JButton("Login"); // 로그인 버튼 생성 
		btnLogin.setBounds(325, 89, 97, 23);
		btnLogin.addActionListener(this);  // 로그인 버튼 클릭시 이메일과 비밀번호 일치 여부 검사
		
		lblWarn = new JLabel();	// 로그인 실패 시 뜨는 경고 레이블 
		lblWarn.setBounds(46, 198, 352, 45);
			
		btnFindInfo = new JButton("사번/비밀번호 찾기");
		btnFindInfo.setBounds(152, 147, 154, 28);
		btnFindInfo.addActionListener(this);	// 사번찾기 버튼 클릭시 분실 질문 페이지로 이동 
		
		loginPane.add(lblEmail);
		loginPane.add(lblPw);
		loginPane.add(lblWarn);
		loginPane.add(txtEmail);
		loginPane.add(txtPw);
		loginPane.add(btnLogin);		
		loginPane.add(btnFindInfo);
		getContentPane().add(loginPane);
		
		
	}
	



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(btnLogin)) {
			
			isLoginCheck();	// 로그인 일치 여부 
			
		} else if (e.getSource().equals(btnFindInfo)) {
			
		   findIdPanel_1 findIddialog = new findIdPanel_1(this,"find id", true);	// 비밀번호 변경 다이얼로그로 이동 
		   findIddialog.setLocation(200, 300);
		   findIddialog.setSize(500,300);
		   findIddialog.setVisible(true);
		   
		}
		
		
		
	}
	
	private void isLoginCheck() {
		if(txtEmail.getText().equals("test@gmail.com") && new String(txtPw.getPassword()).equals("0000")) {
            JOptionPane.showMessageDialog(null, "로그인 성공");
            bLoginCheck = true;
            
		} else {
			JOptionPane.showMessageDialog(null, "로그인 실패");
			lblWarn.setText("<html> 로그인 정보가 일치하지 않습니다 <br/> 관리자에게 문의하세요</html>");
			
		}
		
	}
	
	public boolean isLogin() {     
		return bLoginCheck;
	}
 }
	
