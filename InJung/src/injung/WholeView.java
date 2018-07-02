package injung;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * 작성 일자 : 2018.07.01
 * 수정 일자 :
 * 
 * 작성자 : 권미현
 * 
 * 레코드 조회기능 - 전체보기
 */

public class WholeView extends JFrame{

	private JPanel panelWholeView;	// 전체보기 Panel
	
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
		//메인 프레임 설정
		setTitle("전체보기");
		setBounds(300, 100, 1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));	// 레이아웃
		
		// --- 프레임 구성 작업 ---
		initTable(); // 전체보기 리스트(테이블) 설정
		// ------------------
		
		setVisible(true);
		
	}

	private void initTable() {
		
		String[][] sample = {
				{"홍길동", "관리부", "과장", "관리부과장", "aaa@naver.com", "000-0000-0000"},
				{"김아무", "생산부", "대리", "생산부과장", "bbb@naver.com", "111-1111-1111"},
				{"가나다", "생산부", "사원", "생산부사원", "ccc@naver.com", "222-2222-2222"},
		};
		
		tableModel = new DefaultTableModel(sample, tableAttribute);
		tableView = new JTable(tableModel); // JTable에 DefaultTableModel 설정

		for(int i = 0; i <50; i++) {
			String[] sam = {"홍길동", "관리부", "과장", "관리부과장", "aaa@naver.com", "000-0000-0000"};
			tableModel.addRow(sam);
		}
		
		tableView.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableView.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableView.getColumnModel().getColumn(2).setPreferredWidth(10);
		
		tableView.setSize(500, 500);	// 변경X
		
		jScrollPane = new JScrollPane(tableView);
		
//		jScrollPane.setSize(500,500);
		jScrollPane.setPreferredSize(new Dimension(900, 450));
		
		add(jScrollPane);
	}
	
	public static void main(String[] args) {
		new WholeView();	// 생성자 호출
	}
	
}
