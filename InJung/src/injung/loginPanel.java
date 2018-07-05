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
 * �ۼ���: ������ 
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
		
		JPanel loginPane = new JPanel(); // �α��� �г� ���� 
		loginPane.setBounds(0, 0, 434, 262);
		loginPane.setLayout(null);
		
		lblEmail =  new JLabel("�̸��� : "); 	// �̸��� ���̺� ���� 
		lblEmail.setBounds(46, 53, 77, 21);
		
		lblPw = new JLabel("�н����� : "); // �н����� ���̺� ���� 
		lblPw.setBounds(46, 90, 77, 21);
		
		txtEmail = new JTextField(); // �̸��� �ؽ�Ʈ ���� 
		txtEmail.setBounds(152, 53, 154, 21);
		
		txtPw = new JPasswordField(); // �н����� �ؽ�Ʈ ���� 
		txtPw.setBounds(152, 90, 154, 21);
		txtPw.setEchoChar('*');
		txtPw.setEditable(true);
		
		btnLogin = new JButton("Login"); // �α��� ��ư ���� 
		btnLogin.setBounds(325, 89, 97, 23);
		btnLogin.addActionListener(this);  // �α��� ��ư Ŭ���� �̸��ϰ� ��й�ȣ ��ġ ���� �˻�
		
		lblWarn = new JLabel();	// �α��� ���� �� �ߴ� ��� ���̺� 
		lblWarn.setBounds(46, 198, 352, 45);
			
		btnFindInfo = new JButton("���/��й�ȣ ã��");
		btnFindInfo.setBounds(152, 147, 154, 28);
		btnFindInfo.addActionListener(this);	// ���ã�� ��ư Ŭ���� �н� ���� �������� �̵� 
		
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
			
			isLoginCheck();	// �α��� ��ġ ���� 
			
		} else if (e.getSource().equals(btnFindInfo)) {
			
		   findIdPanel_1 findIddialog = new findIdPanel_1(this,"find id", true);	// ��й�ȣ ���� ���̾�α׷� �̵� 
		   findIddialog.setLocation(200, 300);
		   findIddialog.setSize(500,300);
		   findIddialog.setVisible(true);
		   
		}
		
		
		
	}
	
	private void isLoginCheck() {
		if(txtEmail.getText().equals("test@gmail.com") && new String(txtPw.getPassword()).equals("0000")) {
            JOptionPane.showMessageDialog(null, "�α��� ����");
            bLoginCheck = true;
            
		} else {
			JOptionPane.showMessageDialog(null, "�α��� ����");
			lblWarn.setText("<html> �α��� ������ ��ġ���� �ʽ��ϴ� <br/> �����ڿ��� �����ϼ���</html>");
			
		}
		
	}
	
	public boolean isLogin() {     
		return bLoginCheck;
	}
 }
	
