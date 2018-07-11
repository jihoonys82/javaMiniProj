package injung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * �ۼ� ���� : 2018.07.05 
 * ���� ���� : 2018.07.08
 * 	- �α��� ���� �޽��� �� ��, Ȯ�� ������ dialog ����
 * 
 * �ۼ��� : ������ 
 * ������ : �ǹ���
 */

class loginPanel extends JDialog implements ActionListener {

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
	
//	private boolean bLoginCheck;
	
	public loginPanel(JFrame frame, String title, boolean modal,int x,int y) {
		super(frame,title,true);
		setTitle("Login");
		setLocation(300,200);
		setSize(450,300);
		setResizable(false);
		
		JPanel loginPane = new JPanel(); // �α��� �г� ���� 
		loginPane.setBounds(0, 0, 434, 262);
		loginPane.setLayout(null);
		
		lblEmployeeId =  new JLabel("��� : "); 	// �̸��� ���̺� ���� 
		lblEmployeeId.setBounds(46, 53, 77, 21);
		
		lblPw = new JLabel("�н����� : "); // �н����� ���̺� ���� 
		lblPw.setBounds(46, 90, 77, 21);
		
		txtEmployeeId = new JTextField(); // �̸��� �ؽ�Ʈ ���� 
		txtEmployeeId.setBounds(152, 53, 154, 21);
		
		txtPw = new JPasswordField(); // �н����� �ؽ�Ʈ ���� 
		txtPw.setBounds(152, 90, 154, 21);
		txtPw.setEditable(true);
		
		btnLogin = new JButton("Login"); // �α��� ��ư ���� 
		btnLogin.setBounds(325, 89, 97, 23);
		btnLogin.addActionListener(this);  // �α��� ��ư Ŭ���� �̸��ϰ� ��й�ȣ ��ġ ���� �˻�
		
		lblWarn = new JLabel();	// �α��� ���� �� �ߴ� ��� ���̺� 
		lblWarn.setBounds(46, 198, 352, 45);
			
		btnFindInfo = new JButton("�н����� ã��");
		btnFindInfo.setBounds(152, 147, 154, 28);
		btnFindInfo.addActionListener(this);	// ���ã�� ��ư Ŭ���� �н� ���� �������� �̵� 
		
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
			
			isLoginCheck(toIntId);	// �α��� ��ġ ���� 
			
		} else if (e.getSource().equals(btnFindInfo)) {
			
		   findIdPanel_1 findIddialog = new findIdPanel_1(this,"Create new password", true);	// ��й�ȣ ���� ���̾�α׷� �̵� 
		   findIddialog.setLocation(200, 300);
		   findIddialog.setSize(500,300);
		   findIddialog.setVisible(true);
		   
		}
	}
	
	public int isLoginCheck(int empId) {
		
		eDto = dao.getEmployee(empId);	
		int login_result = LOGIN_FAILED;
	
		if (txtEmployeeId.getText().equals(((Integer)eDto.getEmployeeId()).toString())){

			if (txtPw.getPassword().toString().equals(eDto.getPassword())) {
			
				// �α��� ���� 
				// TODO ��� ��� Ȱ��ȭ -> �߰� 

				login_result = LOGIN_SUCCESSED;
			
			} else if( !(txtPw.getPassword().toString() == eDto.getPassword()) ) {
				// �α��� ����
				lblWarn.setText("<html> �α��� ������ ��ġ���� �ʽ��ϴ� <br/> �н����� ã�� ��ư�� �����ּ��� </html>");
			}
				
		}	
			return login_result;	
	}
	
//	public boolean isLogin() {     
//		return bLoginCheck;
//	}
 }
	
