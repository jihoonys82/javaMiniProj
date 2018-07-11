package injung;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import injung.model.EmployeeDto;
import injung.model.InJungDao;

/*
 * 작성 일자 : 2018.07.01
 * 수정 일자 : 2018.07.05
 * 	- 테이블 셀(내용) 수정 방지
 * 수정 일자 : 2018.07.09
 *  - 상속 : JFram → JPanel
 * 수정 일자 : 2018.07.10
 *  - 패널 추가, 레이아웃 변경
 * 
 * 작성자 : 권미현
 * 
 * 레코드 조회기능 - 전체보기
 */

public class WholeView extends JPanel{

	// WholeView Panel
	private JPanel wholePanel;
	
	// -- JTable 설정 ---
	private JTable tableView; // 전체보기 Table
	private DefaultTableModel tableModel; // 추가, 삭제를 용이하게 하기 위해 설정
	// 기본 JTable 은 추가, 삭제가 안 됨
	
	private JScrollPane jScrollPane; // 컴포넌트 스크롤바
	
	// 테이블 속성
	private final String[] tableAttribute = {"이름", "부서", "직급", "직책", "메일", "전화"};
	// ------------------
	
	// 생성자
	public WholeView() {
		
		setLayout(null);	// 레이아웃
		
		// --- 프레임 구성 작업 ---
		initTablePanel(); // 전체보기 리스트(테이블) 설정
		// ------------------
		
	}
	
//	public WholeView(int employeeId) { // DTO에서 데이터를 받아올 메소드
//		this();
//		
//		InJungDao dao = InJungDao.getInstance();
//		EmployeeDto dto = new EmployeeDto();
//		
//		dto = dao.getEmployee(employeeId);
//		
//		
//	}

	private void initTablePanel() {
		wholePanel = new JPanel();
		wholePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		wholePanel.setBounds(0, 0, 1000, 600);
		
		String[][] sample = {
				{"홍길동", "관리부", "과장", "관리부과장", "aaa@naver.com", "000-0000-0000"},
				{"김아무", "생산부", "대리", "생산부과장", "bbb@naver.com", "111-1111-1111"},
				{"가나다", "생산부", "사원", "생산부사원", "ccc@naver.com", "222-2222-2222"},
		};
		
		tableModel = new DefaultTableModel(sample, tableAttribute) {

			@Override
			public boolean isCellEditable(int row, int column) { // 테이블 내용 수정 불가로 만들기
				return false;
			}
		};
		
		tableView = new JTable(tableModel); // JTable에 DefaultTableModel 설정

		for(int i = 0; i <50; i++) {
			String[] sam = {"홍길동", "관리부", "과장", "관리부과장", "aaa@naver.com", "000-0000-0000"};
			tableModel.addRow(sam);
		}
		
		tableView.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableView.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableView.getColumnModel().getColumn(2).setPreferredWidth(10);
		
//		tableView.setSize(500, 500);	// 변경X
		
		jScrollPane = new JScrollPane(tableView);
		
//		jScrollPane.setSize(500,500);
		jScrollPane.setPreferredSize(new Dimension(900, 450));
		
		wholePanel.add(jScrollPane); // wholeView Panel에 추가
		
		add(wholePanel); // wholeView Panel 추가
	}
	
}
