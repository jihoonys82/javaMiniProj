package injung;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class CreditDialog extends JDialog {

	//������Ʈ �ʵ�
	JLabel lblPhoto = null;
	JButton btnClose = null;
	JTextArea txtContent = null;
	
	public CreditDialog(JFrame frame,String title,boolean modal,int x, int y) {
		
		//���̾�α� ����
		super(frame,title,modal);
		setLocation(x, y);
		setLayout(null);
		setSize(800, 500);
		
		//txtContent ����
		txtContent = new JTextArea();
		txtContent.setBounds(10, 10, 360, 420);
		txtContent.setFont(new Font("����",Font.BOLD,16));
		txtContent.setEditable(false);
		txtContent.setLineWrap(true);
		txtContent.setBackground(new Color(238, 238, 238));
		
		String txt = "\t<������ �Ұ�>\r\n" + 
				"\r\n" + 
				"        3�� - Mini Project\r\n" + 
				"        ���� : ����� ���ŵ�\r\n" + 
				"\r\n" + 
				"        ������ - jihoonys82@gmail.com \r\n" + 
				"        �ǹ��� - kwon_mi_hyun@naver.com\r\n" + 
				"        ������ - ccolog531@gmail.com\r\n" + 
				"        ��âȯ - sindy6639@naver.com\r\n" + 
				"        ������ - hidjoker@naver.com\r\n" + 
				"        Ư������ - �ۿ���\r\n" + 
				"\r\n" + 
				"                   <�̴� ������Ʈ �� �ּ�>\r\n" + 
				"\r\n"+
				"  https://github.com/jihoonys82/javaMiniProj\r\n";
		txtContent.setText(txt);
		
		//lblPhoto ����
		lblPhoto = new JLabel(new ImageIcon("./photo/credit.jpg"));
		lblPhoto.setBounds(370, 30, 400, 300);
		
		//btnClose ����
		btnClose = new JButton("�ݱ�");
		btnClose.setBounds(660, 360, 100, 50);
		
		add(txtContent);
		add(lblPhoto);
		add(btnClose);
		
	}
	
}