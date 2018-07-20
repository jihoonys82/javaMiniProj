package injung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class AboutInjungDialog extends JDialog implements ActionListener{
	
	/**
	 * ����?�������� ���Ͽ�
	 * �ֱ� �ۼ��� : 7.17
	 * �ۼ��� : ������
	 */
	
	private static final long serialVersionUID = 1L;
	
	//������Ʈ ����ʵ�
	JTextArea txtContent = null;
	JPanel btnPane = null;
	JTextArea txtMaker = null;
	JButton btnCancel = null;
	JLabel lblLogo = null;
	
	//������
	public AboutInjungDialog(Frame frame,String title,boolean modal,int x,int y) {
		
		//���̾�α� ������
		super(frame, title, modal);
		setLocation(x, y);
		setSize(400, 300);
		setLayout(null);
		
		
		//TxtContent ����
		String txt = "\r\n    ���α׷��� : ����? ������\r\n"
					+ "    �������� : Injung Release(v1.0)\r\n"
					+ "    �Ұ� : �� ���α׷��� ���� Mini Project\r\n "
					+ "             ���� ������� ����� �λ������ ����������\r\n"
					+ "              ����� �׷� ���� �پ��ϰ� �����մϴ�\r\n";
		
		txtContent = new JTextArea();
		txtContent.setBounds(0, 0, 400, 130);
		txtContent.setColumns(20);
		txtContent.setRows(10);
		txtContent.setFont(new Font("���",Font.BOLD,14));
		txtContent.setEditable(false);
		txtContent.setBackground(new Color(245, 245, 245));
		txtContent.setText(txt);
		
		
		//btnPane ����
		btnPane = new JPanel();
		btnPane.setLayout(null);
		btnPane.setBounds(0, 130, 400, 150);
		btnPane.setBackground(new Color(245, 245, 245));
		
		//txtMaker ����
		txtMaker = new JTextArea();
		txt = " ���� : ����ѳ��ŵ� ";
		txtMaker.setFont(new Font("���",Font.BOLD,14));
		txtMaker.setBounds(180, 20, 200, 30);
		txtMaker.setEditable(false);
		txtMaker.setBackground(new Color(245, 245, 245));
		txtMaker.setText(txt);
		
		//btnCancel ����
		btnCancel = new JButton("�ݱ�");		
		btnCancel.setBounds(280, 60, 80, 40);
				
		//lblLogo ����
		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon("./photo/logo.jpg"));
		lblLogo.setBounds(5, 2, 120, 120);
		lblLogo.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		btnPane.add(btnCancel);
		btnPane.add(lblLogo);
		btnPane.add(txtMaker);

		add(txtContent);
		add(btnPane);
		
		btnCancel.addActionListener(this);		
	}

	//Ŭ�� �̺�Ʈ
	@Override
	public void actionPerformed(ActionEvent e) {
		//��ư �ݱ�
		if(e.getSource()==btnCancel) {
			dispose();
		}
		
	}	
}
