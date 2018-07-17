package injung;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutInjungDialog extends JDialog implements ActionListener{
	
	JTextArea txtContent = null;
	JButton btnCancel = null;
	JPanel btnPane = null;
	
	public AboutInjungDialog(Frame frame,String title,boolean modal,int x,int y) {
		
		super(frame, title, modal);
		setLocation(x, y);
				
		setLayout(new GridLayout(2, 0));
		
		String txt = " ���α׷��� : ����? ������\r\n"
					+ " ���� : (������)Injung Release(v1.0)\r\n"
					+ " ���� : �� ���α׷��� ���� Mini Project\r\n "
					+ "      ���� ������� ����� �λ������\r\n"
					+ "      ���������� ����� �� ������ �����մϴ�\r\n";
		
		System.out.println(txt);
		
		txtContent = new JTextArea();
		btnCancel = new JButton();
		
		txtContent.setText(txt);
		
		btnPane = new JPanel();
		btnPane.add(btnCancel);
		
		add(txtContent);
		add(btnPane);
		pack();
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnCancel) {
			dispose();
		}
		
	}	
}
