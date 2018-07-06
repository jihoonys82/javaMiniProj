package injung;

import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*
 * 로그인 TEST용 프레임
 * 
 * 2018.07.05
 * 작성자: 송주현 
 */

public class testFrm extends JFrame {

	private static final long serialVersionUID = -9036513649108885032L;


	public testFrm() {
		setBounds(200,300,1000,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container root = getContentPane();
		JPanel pan = new JPanel();		
		setTitle("Login");
		JButton btn = new JButton("로그인");

		JDialog dialog = new loginPanel(this,"Login Dialog",true,getX()+50,getY()+50);
		
		btn.addActionListener(new ActionListener() {	// 클릭하면 로그인 화면 창 뜨기 
			
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
