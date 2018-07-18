package injung;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import injung.model.EmployeeDto;
import injung.model.InJungDao;

/*
 * 작성일자 : 2018.07.17
 * 수정일자 : 2018.07.18
 * 
 * 작성자 : 권미현
 * 수정자 : 권미현
 * 
 * 이달의 생일 기능 (JTable)
 * 
 * - JLabel 제거
 * 
 */

public class BirthdayPanel extends JPanel {

	private static final long serialVersionUID = -826773397242782896L;

	// Date
	@SuppressWarnings("unused")
	private static Date month;
	@SuppressWarnings("unused")
	private static SimpleDateFormat dateFormat; // 현재 달만 출력
	
	// Table Panel
	private JPanel bTablePanel; // birth
	
	
	// JTable 설정
	private JTable birthTable; // 이달의 생일 Table
	private DefaultTableModel tableModel;	// 추가, 삭제를 용이
	
	// JTable - 속성
	private Vector<String> tableAttribute;
	private static final String[] AttributeStr = {"생일", "이름", "부서", "직급", "직책"};
	
	// JTable - 스크롤바
	private JScrollPane jScrollPane; // 컴포넌트 스크롤바
	
	
	//DAO, DTO (생일)
	private static InJungDao dao;
	private ArrayList<EmployeeDto> birthDto;
	
	// DTO에서 받아서 저장할 Vector (생일)
	private static Vector<String> vDto; 
	
	
	public BirthdayPanel() {
		
		//Date 설정
		month = new Date();
		dateFormat = new SimpleDateFormat("MM"); // 현재 달만 출력
		
		//DAO, DTO 설정 (생일)
		dao = InJungDao.getInstance();
		birthDto = new ArrayList<>();
//		birthDto = dao.getEmpBirth(Integer.parseInt((dateFormat.format(month))));
//		System.out.println(Integer.parseInt((dateFormat.format(month)))); // 현재 달, 확인 완료
		birthDto = dao.getEmpBirth(8);
		
		initTablePanel(birthDto);
		
	}
	
	private void initTablePanel(ArrayList<EmployeeDto> birthDto) {
		
		bTablePanel = new JPanel();
		bTablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		bTablePanel.setPreferredSize(new Dimension(800, 200)); // bTablePanel 크기 지정
		
		
		// --- 테이블 속성 ---
		tableAttribute = new Vector<>();
		
		for(int i = 0; i < AttributeStr.length; i++) {
			tableAttribute.add(AttributeStr[i]);
		}
		// --------------
		
		
		// DefaultTableModel에 속성 넣기 (데이터 x)
		tableModel = new DefaultTableModel(tableAttribute, 0) {

			private static final long serialVersionUID = 7171482468923032071L;

			@Override
			public boolean isCellEditable(int row, int column) { // 테이블 내용 수정 불가로 만들기
				return false;
			}
		};
		
		birthTable = new JTable(tableModel); // JTable에 DefaultTableModel 설정
		
		// 테이블에 데이터 넣기(row)
		for(EmployeeDto list : birthDto) {
			
			vDto = new Vector<>();

			vDto.add(list.getBirth());
			vDto.add(list.getName());
			vDto.add(list.getTeam());
			vDto.add(list.getLevel());
			vDto.add(list.getRole());
			
			tableModel.addRow(vDto);
			
		}
		
		// 컬럼 사이즈
		birthTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		birthTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		birthTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		birthTable.getColumnModel().getColumn(3).setPreferredWidth(110);
		birthTable.getColumnModel().getColumn(4).setPreferredWidth(180);
		birthTable.setRowHeight(30);
		
		
		jScrollPane = new JScrollPane(birthTable);
		
		jScrollPane.setPreferredSize(new Dimension(800, 200)); // jScrollPane(birthTable) 크기 지정
		
		bTablePanel.add(jScrollPane);
		
		add(bTablePanel);
	}
	
}
