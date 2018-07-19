package injung;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import injung.model.CalendarDto;
import injung.model.InJungDao;

/*
 * 작성일자 : 2018.07.17
 * 수정일자 : 2018.07.19
 * 
 * 작성자 : 권미현
 * 수정자 : 권미현
 * 
 * 개인 일정
 * 
 * - 일정 추가 Dialog 연결 : NewTaskDialog
 * - 창 사이즈 조절 못하게 하기
 * - 마우스 이벤트 추가 : Update(finishTask)
 * - 이미 완료된 일정일 경우에 대한 처리 추가
 * 
 */

public class PersonCalendarPanel extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 4045852819553979355L;
	
	private int id;
	private int finish;
	private int calendarId;
	// ------------------------- //
	
	// Date
	private Date date; // 날짜
	private SimpleDateFormat dateFormat; // 날짜 형식
	
	// NewTaskDialog
	private NewTaskDialog dialog;
	
	
	// Button Panel
	private JPanel cButtonPanel;
	
	// Table Panel
	private JPanel cTablePanel; // calendar
	
	
	// JButton
	private JButton addButton;	// 개인 일정 추가 Button
	
	
	// JTable 설정
	private JTable calenTable; // 개인 일정 Table
	private DefaultTableModel tableModel; // 추가, 삭제를 용이
	
	// JTable - 속성
	private Vector<String> tableAttribute;
	private static final String[] AttributeStr = {"", "테스크", "시작일", "종료예정일", "종료일", "상태", "메모"};
	
	// JTable - 스크롤바
	private JScrollPane jScrollPane; // 컴포넌트 스크롤바
	
	
	// Tab (개인일정, 이달의 생일)
	private JTabbedPane tab;
	
	
	// DAO, DTO (일정)
	private static InJungDao dao;
	private ArrayList<CalendarDto> calendarDto;
	
	// DTO에서 받아서 저장할 Vector (일정)
	private static Vector<String> vDto; 
	
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
	
	
	public PersonCalendarPanel(int id, String msg, boolean tab) {
		
//		System.out.println("PersonCalendar getId : " + id);	// 테스트 완료
		
		this.id = id;
		
		// DAO, DTO 설정 (일정)
		dao = InJungDao.getInstance();
		calendarDto = new ArrayList<>();
		calendarDto = dao.getAllTasks(id);
		
		if (msg == "normal") {
			initTablePanel(calendarDto, tab);
		} else if (msg == "tab") {
			initTab();
		}
		
	}
	
	private void initButtonPanel() {
		
		cButtonPanel = new JPanel();
		
		addButton = new JButton("일정 추가");
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("addButton get ID : " + id); // id 값을 받아왔는지 테스트_완료
				
				dialog = new NewTaskDialog(id);

				dialog.setSize(420, 400);
				dialog.setResizable(false); // 창 사이즈 조절 못하게 하기
				
				// 중간에 배치하기
				dialog.setLocation((screenSize.width - dialog.getWidth())/2, 
						(screenSize.height - dialog.getHeight())/2);
				
				dialog.setVisible(true);
				
			}
		});
	
		cButtonPanel.add(addButton);
		
		add(cButtonPanel);	// root container 에 넣기
		
	}
	
	@SuppressWarnings("serial")
	private void initTablePanel(ArrayList<CalendarDto> calendarDto, boolean tab) {
		
		cTablePanel = new JPanel();
		
		
		// --- 테이블 속성 ---
		tableAttribute = new Vector<>();
		
		for(int i = 0; i < AttributeStr.length; i++) {
			tableAttribute.add(AttributeStr[i]);
		}
		// --------------
		
		// DefaultTableModel에 속성 넣기 (데이터 x)
		tableModel = new DefaultTableModel(tableAttribute, 0) {

			@Override
			public boolean isCellEditable(int row, int column) { // 테이블 내용 수정 불가로 만들기
				return false;
			}
		};
		
		calenTable = new JTable(tableModel); // JTable에 DefaultTableModel 설정
		
		// 테이블에 데이터 넣기(row)
		for (CalendarDto list : calendarDto) {
			vDto = new Vector<>();
			
			vDto.add(String.valueOf(list.getCalendarId()));
			vDto.add(list.getTaskName());
			vDto.add(list.getStartDate());
			vDto.add(list.getExpectEndDate());
			vDto.add(list.getActualEndDate());
			vDto.add(list.getStatus());
			vDto.add(list.getNote());
			
			tableModel.addRow(vDto); // 벡터로 밖에 값을 못 집어넣음
//			System.out.println(vDto);	// vDto에 값이 제대로 넣어졌는지... 테스트 완료
		}
		
		// 컬럼 사이즈
		calenTable.getTableHeader().setResizingAllowed(false); // 사이즈 조절 못하게 고정하기
		calenTable.getTableHeader().setReorderingAllowed(false); // 컬럼 이동 못하게 고정하기
		// 	너비
		calenTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		calenTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		calenTable.getColumnModel().getColumn(2).setPreferredWidth(180);
		calenTable.getColumnModel().getColumn(3).setPreferredWidth(180);
		calenTable.getColumnModel().getColumn(4).setPreferredWidth(180);
		calenTable.getColumnModel().getColumn(5).setPreferredWidth(70);
		calenTable.getColumnModel().getColumn(6).setPreferredWidth(160);
		calenTable.setRowHeight(30); // 높이
		
		// MouseListener
		calenTable.addMouseListener(this);
		
		jScrollPane = new JScrollPane(calenTable);
		
		// calenTable 사이즈
		// 	calenTable를 넣은 jScrollPane를 수정해야 크기 조절이 된다.
		if(!tab) {
			setLayout(new FlowLayout(FlowLayout.TRAILING, 40, 5));	// root 레이아웃 설정
			initButtonPanel();
			jScrollPane.setPreferredSize(new Dimension(900, 500));
		} else {
			jScrollPane.setPreferredSize(new Dimension(800, 200));
		}
		
		cTablePanel.add(jScrollPane);
		
		add(cTablePanel);	// root container 에 넣기
		
	}
	
	
	public void initTab() { // 개인 일정 & 이달의 생일 탭뷰
		
		tab = new JTabbedPane();
		
		tab.add("개인 일정", new PersonCalendarPanel(id, "normal", true));
		tab.add("이달의 생일", new BirthdayPanel());
		
		tab.setPreferredSize(new Dimension(850, 250)); // jScrollPane(birthTable) 크기 지정
		
		add(tab);
		
	}

	
	// --- 마우스 이벤트 ---
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getButton() == 1 && e.getClickCount() == 2) { // 마우스 왼 버튼 && 더블 클릭 시 
			
			// 현재 일정을 완료할건지 묻는  Dialog
			Object[] options = {"확인", "취소"};
	 		int yesNo = JOptionPane.showOptionDialog(
					this, 
					"현재 일정을 완료하시겠습니까?", 
					"", // Dialog Title
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, 
					options,
					options[0]);
	 		
	 		// 확인을 누를시
	 		if (yesNo == 0) {
	 			
	 			date = new Date();
	 			dateFormat = new SimpleDateFormat("yyyy.MM.dd");	
//	 			System.out.println(dateFormat.format(date)); // 현재 날짜 출력 확인_테스트 완료
	 			
	 			calendarId = calendarDto.get(calenTable.getSelectedRow()).getCalendarId();
//	 			System.out.println("Calendar ID : " + calendarId); // 클릭한 로우 Calendar ID_테스트 완료
	 			
	 			
	 			if (calendarDto.get(calenTable.getSelectedRow()).getActualEndDate() != null) { 
	 				// 종료일이 null이 아닐 경우 = 종료일이 있을 경우 = 완료
	 				JOptionPane.showMessageDialog(this,
	 						"이미 완료된 일정입니다.",
	 						"경고", // Dialog Title
	 						JOptionPane.WARNING_MESSAGE);
	 			} else {
	 				// 종료일이 null일 경우 = 종료일이 없을 경우 = 완료 x
	 				finish = dao.finishTask(calendarId, 
	 						String.valueOf(dateFormat.format(date)), 
	 						"완료");
	 				
	 				if (finish == 1) {
	 					JOptionPane.showMessageDialog(this,
	 							"현재 일정을 완료했습니다.",
	 							"", // Dialog Title
	 							JOptionPane.INFORMATION_MESSAGE);
	 				} // finish if문
	 			}
	 			
	 		} // yesNo if문
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	// ----------------
	
}
