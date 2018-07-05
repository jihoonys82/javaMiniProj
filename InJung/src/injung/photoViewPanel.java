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
	
	private JPanel photoViewPane = new JPanel();	// 사진보기 판넬 
	
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
			personPanes[i] = new JPanel();	// 개인의 정보를 담을 패널 
			personPanes[i].setLayout(null);
			personPanes[i].setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			
			lblPhotos[i] = new JLabel();	// 사진 레이블 
			lblPhotos[i].setIcon(new ImageIcon("./src/photoView/no_avatar.jpg")); 
			lblPhotos[i].setBounds(35, 10, 150, 150);
			
			lblTeams[i] = new JLabel("team"+i);	// 팀 레이블 
			lblTeams[i].setBounds(35,170, 100, 15);
			lblTeams[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			lblLevels[i] = new JLabel("level"+i);	// 직급 레이블 
			lblLevels[i].setBounds(35, 200, 75, 15);
			lblLevels[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			lblNames[i] = new JLabel("name"+i); 	// 이름 레이블 
			lblNames[i].setBounds(122, 200, 75, 15);
			lblNames[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			personPanes[i].add(lblPhotos[i]);
			personPanes[i].add(lblTeams[i]);
			personPanes[i].add(lblLevels[i]);
			personPanes[i].add(lblNames[i]);
			photoViewPane.add(personPanes[i]);

		}
		
		
		JPanel btnPagePane = new JPanel();	// 버튼 페이지 생성
		btnPagePane.setBounds(12, 499, 956, 40);
		btnPagePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnPre = new JButton("이전");		// 이전 페이지 버튼 생성 
		JButton btnNext = new JButton("다음");	// 다음 페이지 버튼 생성 
		
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
