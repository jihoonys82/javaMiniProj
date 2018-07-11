package injung;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import injung.model.EmployeeDto;
import injung.model.InJungDao;

/*
 * 업로드 날짜 : 2018.07.05
 * 
 * 작성자 : 송주현
 * 
 */

public class PhotoViewPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -3765992720872642104L;

	private final int photoIdx  = 8;
	
	private JPanel photoViewPane = new JPanel();	// 사진보기 판넬 
	
	private JPanel[] personPanes 	= new JPanel[photoIdx];
	private JLabel[] lblPhotos 		= new JLabel[photoIdx];
	private JLabel[] lblTeams 		= new JLabel[photoIdx];
	private JLabel[] lblLevels 		= new JLabel[photoIdx];
	private JLabel[] lblNames 		= new JLabel[photoIdx];

	private JPanel btnPagePane = new JPanel();	// 버튼 페이지 생성
	private JButton btnPre 	= new JButton("이전");		// 이전 페이지 버튼 생성 
	private JButton btnNext = new JButton("다음");	// 다음 페이지 버튼 생성
	private JLabel lblCurIdx = new JLabel("00");
	private JLabel	lblMaxIdx = new JLabel("/00");
	
	//For DB handling and page indexing 
	private InJungDao dao = InJungDao.getInstance();
	private EmployeeDto dto;
	private ArrayList<EmployeeDto> dtos;
	private int pageIndex;
	private int maxPageIndex;
	private int[] lastEmpIds;
	
	private String photoPath = "./Photo/"; 
	
	public PhotoViewPanel() {
		initPanel();
		
		setPageIndex();
		
		loadPage(lastEmpIds[0]);
	}
	
	private void setPageIndex() {
		int empNum = dao.countEmployee();
		if(empNum%photoIdx == 0) {
			maxPageIndex = empNum/photoIdx;
		} else {
			maxPageIndex = (empNum/photoIdx)+1;
		}
		pageIndex = 1;
		lblCurIdx.setText(((Integer)pageIndex).toString());
		lblMaxIdx.setText("/" + ((Integer)maxPageIndex).toString());
		lastEmpIds = new int[maxPageIndex];
		lastEmpIds[0] = 1;
	}

	private void loadPage(int lastEmpId) {
		if(lastEmpId>0) {
			dtos = dao.getEmployees(lastEmpId, photoIdx);
			
			for(int i=0;i<dtos.size();i++) {
				dto = dtos.get(i);
				lblPhotos[i].setIcon(new ImageIcon(photoPath+dto.getPhoto()));
				lblTeams[i].setText(dto.getTeam());
				lblLevels[i].setText(dto.getLevel());
				lblNames[i].setText(dto.getName());
			}
			if(pageIndex<maxPageIndex) 
				lastEmpIds[pageIndex]=dtos.get(photoIdx-1).getEmployeeId();
		}
	}

	private void initPanel() {
		setLayout(null);
		
		photoViewPane.setBounds(12, 20, 900, 500);		
		add(photoViewPane);
		photoViewPane.setLayout(new GridLayout(0, 4, 55, 10));
		
		for(int i=0;i<photoIdx;i++) {
			personPanes[i] = new JPanel();	// 개인의 정보를 담을 패널 
			personPanes[i].setLayout(null);
			personPanes[i].setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			
			lblPhotos[i] = new JLabel();	// 사진 레이블 
			lblPhotos[i].setIcon(new ImageIcon("./Photo/no_avatar.jpg")); 
			lblPhotos[i].setBounds(15, 5, 150, 200);
			
			lblTeams[i] = new JLabel("team"+i);	// 팀 레이블 
			lblTeams[i].setBounds(15, 210, 150, 15);
			lblTeams[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			lblLevels[i] = new JLabel("level"+i);	// 직급 레이블 
			lblLevels[i].setBounds(15, 225, 75, 15);
			lblLevels[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			lblNames[i] = new JLabel("name"+i); 	// 이름 레이블 
			lblNames[i].setBounds(90, 225, 75, 15);
			lblNames[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			personPanes[i].add(lblPhotos[i]);
			personPanes[i].add(lblTeams[i]);
			personPanes[i].add(lblLevels[i]);
			personPanes[i].add(lblNames[i]);
			photoViewPane.add(personPanes[i]);

		}
		
		btnPagePane.setBounds(12, 520, 900, 40);
		btnPagePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnPre.addActionListener(this);
		btnNext.addActionListener(this);
		
		btnPagePane.add(btnPre);
		btnPagePane.add(lblCurIdx);
		btnPagePane.add(lblMaxIdx);
		btnPagePane.add(btnNext);
		add(btnPagePane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnPre) {
			if(pageIndex>1) {
				pageIndex--;
				loadPage(lastEmpIds[pageIndex-1]);
				
				lblCurIdx.setText(((Integer)pageIndex).toString());
				this.validate();
				this.repaint();
			}
		} else if(e.getSource()==btnNext) {
			if(pageIndex<maxPageIndex) {
				pageIndex++;
				loadPage(lastEmpIds[pageIndex-1]);
				lblCurIdx.setText(((Integer)pageIndex).toString());
				this.validate();
				this.repaint();
			}
		}
	}

//	public static void main(String[] args) {
//		
//		JFrame frame = new JFrame();
//		frame.setBounds(200,300,1000,600);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		frame.getContentPane().add(new PhotoViewPanel());	
//		frame.setVisible(true);
//		
//	}
}
