package injung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import injung.model.EmployeeDto;
import injung.model.InJungDao;

/*
 * 작성 일자 : 2018.07.01
 * 수정 일자 : 2018.07.19
 *  - 더블 클릭으로 Dialog 불려오게끔 수정
 * 
 * 작성자 : 권미현
 * 수정자 : 권미현
 * 
 * 레코드 조회기능 - 전체보기
 */

@SuppressWarnings("serial")
public class WholeView extends JPanel implements ActionListener, MouseListener{

	// WholeView Panel
	private JPanel wholePanel;

	// -- JTable 설정 ---
	private JTable tableView; // 전체보기 Table
	private DefaultTableModel tableModel; // 추가, 삭제를 용이하게 하기 위해 설정
	// 기본 JTable 은 추가, 삭제가 안 됨

	private JScrollPane jScrollPane; // 컴포넌트 스크롤바

	// ---- 테이블 속성 -----
	private Vector<String> tableAttribute;
	private static final String[] AttributeStr = { "이름", "부서", "직급", "직책", "메일", "전화" };
	// ------------------

	// --- DAO, DTO ---
	private static InJungDao dao;
	private ArrayList<EmployeeDto> listDto;
	// ----------------

	// DTO에서 받아서 저장할 Vector
	private Vector<String> vDto;

	// mouseListner 변수
	private int clicktable;
	
	// JLable
	private JLabel jl;
	

	// 생성자
	public WholeView() {

		setLayout(null); // 레이아웃

		// --- DAO, DTO 설정 ---
		dao = InJungDao.getInstance();
		listDto = new ArrayList<>();
		listDto = dao.getAllEmployee();
		// -------------------

		// --- 프레임 구성 작업 ---
		initTablePanel(listDto); // 전체보기 리스트(테이블) 설정
		// ------------------

	}

	private void initTablePanel(ArrayList<EmployeeDto> listDto) {

		wholePanel = new JPanel();
		wholePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		wholePanel.setBounds(0, 0, 1000, 600);

		////////////////////////////////////////////////////////////////
		

		// --- 테이블 속성 ---
		tableAttribute = new Vector<>();

		for (int i = 0; i < AttributeStr.length; i++) {
			tableAttribute.add(AttributeStr[i]);
		}
//		System.out.println(tableAttribute);
		// --------------

		// DefaultTableModel에 속성 넣기 (데이터 x)
		tableModel = new DefaultTableModel(tableAttribute, 0) {

			@Override
			public boolean isCellEditable(int row, int column) { // 테이블 내용 수정 불가로 만들기
				return false;
			}
		};

		tableView = new JTable(tableModel); // JTable에 DefaultTableModel 설정
		tableView.addMouseListener(this);
		tableView.getTableHeader().setReorderingAllowed(false);
		tableView.getTableHeader().setResizingAllowed(false);
		tableView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableView.setBackground(new Color(235, 235, 235));
		
		// 테이블에 데이터 넣기(row)
		for (EmployeeDto list : listDto) {

			vDto = new Vector<>();

			vDto.add(list.getName());
			vDto.add(list.getTeam());
			vDto.add(list.getLevel());
			vDto.add(list.getRole());
			vDto.add(list.geteMail());
			vDto.add(list.getWorkPhone());

			tableModel.addRow(vDto);
//			System.out.println(vDto);

		}

//		for(int i = 0; i <50; i++) {
//			String[] sam = {"홍길동", "관리부", "과장", "관리부과장", "aaa@naver.com", "000-0000-0000"};
//			tableModel.addRow(sam);
//		}

		// 컬럼 사이즈
		tableView.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableView.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableView.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableView.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableView.getColumnModel().getColumn(4).setPreferredWidth(300);
		tableView.getColumnModel().getColumn(5).setPreferredWidth(200);
		tableView.setRowHeight(30);
		
		

//		tableView.setSize(900, 450);	// 변경X

		jScrollPane = new JScrollPane(tableView);

//		jScrollPane.setSize(900, 450); // 변경X
		jScrollPane.setPreferredSize(new Dimension(900, 450));

		wholePanel.add(jScrollPane); // wholeView Panel에 추가

		add(wholePanel); // wholeView Panel 추가

	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1 && e.getClickCount() == 2) // 왼 버튼 && 더블 클릭 시
		 {
		    clicktable = tableView.getSelectedRow();
				
			JDialog dialog = new DetailDialog(new EmployeeInfoPanel(listDto.get(clicktable).getEmployeeId(), true));
			dialog.setSize(700, 400);
			dialog.setVisible(true);
		 }
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
