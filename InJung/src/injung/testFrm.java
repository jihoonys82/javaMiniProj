package injung;

import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * �α��� TEST�� ������
 * 
 * 2018.07.05
 * �ۼ���: ������ 
 */

public class testFrm extends JFrame {

	private static final long serialVersionUID = -9036513649108885032L;


	public testFrm() {
		setBounds(200,300,1000,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container root = getContentPane();
		JPanel pan = new JPanel();		
		setTitle("Login");
		JButton btn = new JButton("�α���");

		JDialog dialog = new loginPanel(this,"Login Dialog",true,getX()+50,getY()+50);
		
		btn.addActionListener(new ActionListener() {	// Ŭ���ϸ� �α��� ȭ�� â �߱� 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);	
			}
		});
		
		setSize(500,500);

		pan.add(btn);
		root.add(pan);
		setVisible(true);
		
	}
		
	

	public static void main(String[] args) {
		new testFrm();	
		}
}
