package injung;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dialog1 extends JFrame {

	public Dialog1() {
		setBounds(200,300,1000,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container root = getContentPane();
		JPanel pan = new JPanel();		
		setTitle("Login");
		JButton btn = new JButton("로그인");

		JDialog dialog = new MyDialog1(this,"Login Dialog",true,getX()+50,getY()+50);
		
		btn.addActionListener(new ActionListener() {	// 버튼 클릭시 dialog 보이기
			
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
		new Dialog1();	
		}
}
