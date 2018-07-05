package injung;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class photoViewPanel extends JPanel {
	
	private JPanel photoViewPane = new JPanel();	// �������� �ǳ� 
	
	private JPanel[] personPanes = new JPanel[8];
	private JLabel[] lblPhotos = new JLabel[8];
	private JLabel[] lblTeams = new JLabel[8];
	private JLabel[] lblLevels =new JLabel[8];
	private JLabel[] lblNames = new JLabel[8];
	
	
	
	public photoViewPanel() {
		setLayout(null);
		
		photoViewPane.setBounds(12, 10, 892, 479);		
		add(photoViewPane);
		photoViewPane.setLayout(new GridLayout(0, 4, 20, 20));
		
		for(int i=0;i<personPanes.length;i++) {
			personPanes[i] = new JPanel();	// ������ ������ ���� �г� 
			personPanes[i].setLayout(null);
			personPanes[i].setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			
			lblPhotos[i] = new JLabel();	// ���� ���̺� 
			lblPhotos[i].setIcon(new ImageIcon("./src/photoView/no_avatar.jpg")); 
			lblPhotos[i].setBounds(35, 10, 150, 150);
			
			lblTeams[i] = new JLabel("team"+i);	// �� ���̺� 
			lblTeams[i].setBounds(35,170, 100, 15);
			lblTeams[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			lblLevels[i] = new JLabel("level"+i);	// ���� ���̺� 
			lblLevels[i].setBounds(35, 200, 75, 15);
			lblLevels[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			lblNames[i] = new JLabel("name"+i); 	// �̸� ���̺� 
			lblNames[i].setBounds(122, 200, 75, 15);
			lblNames[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			personPanes[i].add(lblPhotos[i]);
			personPanes[i].add(lblTeams[i]);
			personPanes[i].add(lblLevels[i]);
			personPanes[i].add(lblNames[i]);
			photoViewPane.add(personPanes[i]);

		}
		
		
		JPanel btnPagePane = new JPanel();	// ��ư ������ ����
		btnPagePane.setBounds(12, 499, 956, 40);
		btnPagePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnPre = new JButton("����");		// ���� ������ ��ư ���� 
		JButton btnNext = new JButton("����");	// ���� ������ ��ư ���� 
		
		btnPagePane.add(btnPre);
		btnPagePane.add(btnNext);
		add(btnPagePane);
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setBounds(200,300,1000,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new photoViewPanel());	
		frame.setVisible(true);
		
	}
}
