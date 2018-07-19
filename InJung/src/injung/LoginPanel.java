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
 * �ۼ� ���� : 2018.07.05 
 * ���� ���� : 2018.07.08
 * 
 * 
 * �ۼ��� : ������ 
 * ������ : �ǹ���
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
	
	public static final int LOGIN_SUCCESSED = 0;	// �α��� ������ �� 
	public static final int LOGIN_FAILED = 1;		// �α��� ������ �� 
	public static final int LOGIN_NO_ID = 2;		// DB�� ���� ID���� ��
	public static final int NO_NUMBER = 3;

	public LoginPanel(JFrame frame, String title, boolean modal) {
		
		super(frame, title, true);
		setTitle("Login");
		setLocation(550, 250);
		setSize(450, 300);
		setResizable(false);

		JPanel loginPane = new JPanel(); // �α��� �г� ����
		loginPane.setBounds(0, 0, 434, 262);
		loginPane.setLayout(null);

		lblEmployeeId = new JLabel("��� : "); // ��� ���̺� ����
		lblEmployeeId.setBounds(46, 53, 77, 21);

		lblPw = new JLabel("�н����� : "); // �н����� ���̺� ����
		lblPw.setBounds(46, 90, 77, 21);
		
//		NumberFormatter formatter = new NumberFormatter();
//		formatter.
//		formatter.setAllowsInvalid(false);
		
		txtEmployeeId = new JTextField(); // ��� �ؽ�Ʈ ����
		txtEmployeeId.setBounds(152, 53, 154, 21);
		txtEmployeeId.addKeyListener(this);
		
		txtPw = new JPasswordField(); // �н����� �ؽ�Ʈ ����
		txtPw.setBounds(152, 90, 154, 21);
		txtPw.setEditable(true);
		txtPw.addKeyListener(this); // �н����� �ʵ忡�� ���� �Է½� �α���

		btnLogin = new JButton("Login"); // �α��� ��ư ����
		btnLogin.setBounds(325, 89, 97, 23);
		btnLogin.addActionListener(this); // �α��� ��ư Ŭ���� �̸��ϰ� ��й�ȣ ��ġ ���� �˻�

		lblWarn = new JLabel(); // �α��� ���� �� �ߴ� ��� ���̺�
		lblWarn.setBounds(46, 198, 352, 45);

		btnFindInfo = new JButton("�н����� ã��");
		btnFindInfo.setBounds(152, 147, 154, 28);
		btnFindInfo.addActionListener(this); // ���ã�� ��ư Ŭ���� �н� ���� �������� �̵�

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
	public void keyPressed(KeyEvent e) { // ���͸� ���� �� �α��� üũ �޼ҵ� ���� 
//		char ch = txtEmployeeId.getText().charAt(0);
//		char[] ch_1 = txtEmployeeId.getText().toCharArray();
//		char ch_2 = e.getKeyChar();
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {		
			btnLogin.doClick(); 
		}
		
//		} else if (!(Character.isDigit(ch_2))) {
//			JOptionPane.showMessageDialog(null, "������");
//		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	@Override
	public void keyReleased(KeyEvent e) { }

	private void loginAction() {		
		String strId = txtEmployeeId.getText();	
		int toIntId = Integer.parseInt(strId);		
		int loginCheck = isLoginCheck(toIntId);	// �ؽ�Ʈ�ʵ� ���� int�� ��ȯ�Ͽ�  isLoginCheck�� ���ڰ��� �Ѱ��� 	
		
		if(loginCheck == LoginPanel.LOGIN_NO_ID) {	// DB�� ��� ���� ������ ��� �޼��� ���  
			JOptionPane.showMessageDialog(null, "���� �����Դϴ�");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLogin)) {	// �α��� ��ư�� ������ �� loginAction �޼ҵ� ���� 
			loginAction();

		} else if (e.getSource().equals(btnFindInfo)) {		// �н����� ã�� ��ư�� ������ �� 
			if (txtEmployeeId.getText().equals("")) {	// 1. ����� �Է����� �ʾ����� 
				JOptionPane.showMessageDialog(null, "����� �Է����ּ���");					
			} else if (!(txtEmployeeId.getText().equals(""))) {	// 2. ����� �ԷµǾ�������  			
				String strId = txtEmployeeId.getText().trim();			
				FindIdPanel findIddialog = new FindIdPanel(this, "Create new password", true ,strId);	// ��й�ȣ ���� ���̾�α׷� �̵�	
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
//				JOptionPane.showMessageDialog(null, "����� �Է����ּ���");
//				break;
//			}
//			System.out.print(c);
//		}

		eDto = dao.getEmployee(empId);
		if(eDto==null) {	// 1. DB�� ���� ���̸� LOGIN_NO_ID -> ��� �޼��� ��� 
			return LOGIN_NO_ID;
		}
		int login_result = LOGIN_FAILED;			
		
		String strTxtId = new String(txtEmployeeId.getText());	// ID �ؽ�Ʈ�ʵ忡�� ������ �� ���
		char chTxtId = strTxtId.charAt(0);
		if (!Character.isDigit(chTxtId)) {
			return NO_NUMBER;
		}
		
		int id = eDto.getEmployeeId();
		int it = Integer.parseInt(txtEmployeeId.getText());
		
		char[] chTxtPw = txtPw.getPassword();	
		String strTxtPw = new String(chTxtPw);	// �н������ʵ忡�� ������ �� String�� ���
		String strDbPw = new String(eDto.getPassword());	// DTO���� ������ �н����尪 String�� ���  
		int intId = new Integer(eDto.getEmployeeId());		
		String strDbId = Integer.toString(intId);			// DTO���� ������ ID�� ��� 
		
			
		if ((id == it)
				&& (strTxtPw.equals(strDbPw))) { // 2. �Է� ����� DB����� ����, �Է� �н������ DB �н����尡 ���ٸ�						
			login_result = LOGIN_SUCCESSED; // �α��� ����
			dispose(); // �α���â ����
		
		} else if  (!(strTxtId.equals(strDbId))
				|| (!(strTxtPw.equals(strDbPw))) )  { 		// 3. �Է� ����� DB����� �ٸ��ų�, �Է� �н������ DB �н����尡 �ٸ��ٸ�					
			lblWarn.setText("<html> �α��� ������ ��ġ���� �ʽ��ϴ� <br/> �ٽ� �Է����ּ��� </html>"); // ����̺� ���
			
			char ch_0 = strTxtId.charAt(0);
			if (!(Character.isDigit(ch_0))) {
				JOptionPane.showMessageDialog(null,"X");
			}
			login_result = LOGIN_FAILED;
		} 
		return login_result; // �α��� ��� ����		
	} // isLoginCheck �޼ҵ�
	
	
	public int getTxtEmployeeId() { // MainFram�� �ѱ� ID��
		int id = Integer.parseInt(txtEmployeeId.getText());
		return id;
	}
	
}
