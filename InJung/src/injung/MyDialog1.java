package injung;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;

class MyDialog1 extends JDialog {
	
	private JLabel lblEmail;
	private JTextField idField;
	private JPasswordField pwField;
	private JLabel lblPW;
	private JButton btnLogin;
	private JButton btnFindId;
	private JButton btnFindPW;
	private boolean bLoginCheck;
	
	public MyDialog1(JFrame frame, String title, boolean modal,int x,int y) {
		super(frame,title,true);
		setLocation(300,200);
		setSize(450,300);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		panel.setLayout(null);
		
		lblEmail =  new JLabel("이메일 : ");
		lblEmail.setBounds(46, 53, 77, 21);
		panel.add(lblEmail);
		
		lblPW = new JLabel("패스워드 : ");
		lblPW.setBounds(46, 90, 77, 21);
		panel.add(lblPW);
		
		idField = new JTextField();
		idField.setBounds(152, 53, 154, 21);
		panel.add(idField);
		
		pwField = new JPasswordField();
		pwField.setBounds(152, 90, 154, 21);
		pwField.setEchoChar('*');
		pwField.setEditable(true);
		panel.add(pwField);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(325, 89, 97, 23);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();	// 로그인 성공 여부 
				
			}
			
			private void isLoginCheck() {
				if(idField.getText().equals("test@gmail.com") && new String(pwField.getPassword()).equals("0000")) {
		            JOptionPane.showMessageDialog(null, "로그인 성공");
		            bLoginCheck = true;
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}
				
			}

		});
		
		
		
		panel.add(btnLogin);
			
		btnFindId = new JButton("ID 찾기");
		btnFindId.setBounds(86, 143, 97, 23);
		panel.add(btnFindId);
		
		btnFindPW = new JButton("패스워드 찾기");
		btnFindPW.setBounds(211, 144, 124, 21);
		panel.add(btnFindPW);
	
	
		getContentPane().add(panel);
		
	}
	

    public boolean isLogin() {     
        return bLoginCheck;
    }
    
    	
 }
	
