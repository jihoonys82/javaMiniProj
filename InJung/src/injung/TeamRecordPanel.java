package injung;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import injung.model.EmployeeDto;
import injung.model.InJungDao;
import injung.model.TeamDto;

public class TeamRecordPanel extends JPanel implements ActionListener, MouseListener{

	/**
	 * 	 <팀 레코드 판넬>
	 * 최근 업데이트 : 7.16
	 * 작성자 : 이현우
	 * 진행 작업 : 코딩 정리
	 *
	 */
	
	private static final long serialVersionUID = 1L;
	
	//상수(위치값)
	private static final int tablePane_Y=0;
	private static final int tablePane_HEIGHT=350;
	private static final int tablePane_WIDTH=980;

	private static final int inputPane_Y=355;
	private static final int inputPane_HEIGHT=155;
	private static final int inputPane_WIDTH=660;
	
	private static final int btnPane_X=665;
	private static final int btnPane_Y=355;
	private static final int btnPane_HEIGHT=155;
	private static final int btnPane_WIDTH=310;
			
	private static final int PANEL_X=0;
	
	//DAO & DTO
	private InJungDao dao = null;
	private TeamDto dto_Team = null;
	private ArrayList<TeamDto> dtos_Team = null;
	private ArrayList<EmployeeDto> dtos_Employee = null;
	
    //테이블 판넬
    private JPanel tablePane;
    private JTable tbTeamRecord;
    //테이블 판넬 - 스크롤 판넬
    private JScrollPane scrollPane;
    //J테이블 디폴트 모델, 셀렉션 모델
    DefaultTableModel tbDefault = null;

    //입력 판넬
    private JPanel inputPane;
    private JLabel lblTeam;
    private JLabel lblRole;
    private JLabel lblLeader;
    private JTextField txtTeam;
    private JTextField txtRole;
    private JComboBox<String> cbLeader;
    
    //버튼 판넬
    private JPanel btnPane;
    private JButton btnInsert;
    private JButton btnCancel;
    private JButton btnEdit;
    private JButton btnDelete;
    
       
    public TeamRecordPanel(){	
    	
    	//설정
    	setLayout(null);   	
    		
    	//테이블판넬 
    	initTablePane();
    	
    	//인풋판넬
    	initInputPane();
    		
    	//버튼판넬
    	initBtnPane();
    	  	 	
	}
   
    //테이블 판넬
    private void initTablePane() { 
    	
    	//판넬 설정
    	tablePane = new JPanel();
    	tablePane.setBounds(PANEL_X, tablePane_Y, tablePane_WIDTH, tablePane_HEIGHT);
    	tablePane.setLayout(null);
//    	tablePane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	  	
    	//DAO객체 받아오기
    	dao = InJungDao.getInstance();
       	//DAO객체 getAllTeam메소드로 TeamDto LIST 받기
    	dtos_Team = dao.getAllTeam();
    	   	
    	//테이블 헤더 Vector 설정
    	Vector<String> column = new Vector<>();
    	column.addElement("부 서");
    	column.addElement("Role");
    	column.addElement("Leader");
    	column.addElement("Leader_id");
    	    	
    	//테이블 설정
    	tbDefault = new DefaultTableModel(column, 0) {  		
			private static final long serialVersionUID = 1L;
			@Override
    		public boolean isCellEditable(int row, int column) {
    			return false; //테이블 수정 불가
    		}
    	};    	
    	tbTeamRecord = new JTable(tbDefault);
    	tbTeamRecord.setFont(new Font("고딕",Font.BOLD,16));
    	tbTeamRecord.setRowHeight(40);
    	tbTeamRecord.setBackground(new Color(235,235,235));
    	tbTeamRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	
    	//행 Vector에 TeamDto 데이터 넣기  	
    	for(int i=0;i< dtos_Team.size();i++) {
        	Vector<String> row = new Vector<>();
    		row.addElement(dtos_Team.get(i).getTeamName());
    		row.addElement(dtos_Team.get(i).getTeamRole());
    		row.addElement(dtos_Team.get(i).getTeamLeaderName()); 
    		row.addElement(dtos_Team.get(i).getTeamLeaderId());
    		tbDefault.addRow(row);
    	}
    	
    	// 테이블 렌더(가운데 정렬)
    	DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    	render.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	// 테이블 마우스 모델(클릭이벤트)
    	tbTeamRecord.addMouseListener(this);
    	
    	// 테이블 헤더 설정
    	JTableHeader header = tbTeamRecord.getTableHeader();
    	header.setPreferredSize(new Dimension(970, 40));
    	header.setFont(new Font("고딕",Font.BOLD,16));
    	header.setBackground(new Color(200, 200, 200));
    	header.setResizingAllowed(false);
    	header.setReorderingAllowed(false);

    	// 테이블 컬럼 설정
    	tbTeamRecord.setRowSelectionInterval(1, 0);
    	TableColumn firstColoumn = tbTeamRecord.getColumnModel().getColumn(0);
    	firstColoumn.setPreferredWidth(200);
    	firstColoumn.setMinWidth(200);
    	firstColoumn.setMaxWidth(200);
    	firstColoumn.setCellRenderer(render);
    	TableColumn secondColoumn = tbTeamRecord.getColumnModel().getColumn(1);
    	secondColoumn.setPreferredWidth(600);
    	secondColoumn.setMinWidth(600);
    	secondColoumn.setMaxWidth(600);
    	TableColumn thirdColoumn = tbTeamRecord.getColumnModel().getColumn(2);
    	thirdColoumn.setPreferredWidth(170);
    	thirdColoumn.setMinWidth(170);
    	thirdColoumn.setMaxWidth(170);
    	thirdColoumn.setCellRenderer(render);
    	TableColumn FourthColoumn = tbTeamRecord.getColumnModel().getColumn(3);
    	FourthColoumn.setPreferredWidth(0);
    	FourthColoumn.setMinWidth(0);
    	FourthColoumn.setMinWidth(0);
   	
    	//테이블판넬 - 스크롤 판넬
    	scrollPane = new JScrollPane(tbTeamRecord, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setBounds(5, 5, 970, 340);
//    	scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
    	tablePane.add(scrollPane);
    	add(tablePane);
    	
    }
       
	//인풋판넬
    private void initInputPane() {
    	
    	//판넬 설정
    	inputPane = new JPanel();
    	inputPane.setBounds(PANEL_X, inputPane_Y, inputPane_WIDTH, inputPane_HEIGHT);
    	inputPane.setLayout(null);
//    	inputPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//    	inputPane.setVisible(false); //권한에 따라 감추기
    	
    	//컴포넌트
    	lblTeam = new JLabel("부 서");
    	lblTeam.setFont(lblTeam.getFont().deriveFont(16.0f));
    	lblTeam.setBounds(new Rectangle(5, 5, 140, 45));
    	lblTeam.setHorizontalAlignment(JLabel.CENTER);
    	lblTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK));   	
    	
    	lblRole = new JLabel("Role");
    	lblRole.setFont(lblRole.getFont().deriveFont(16.0f));
    	lblRole.setBounds(new Rectangle(5, 55, 140, 45));
    	lblRole.setHorizontalAlignment(JLabel.CENTER);
    	lblRole.setBorder(BorderFactory.createLineBorder(Color.BLACK));   	
    	
    	lblLeader = new JLabel("Leader");
    	lblLeader.setFont(lblLeader.getFont().deriveFont(16.0f));
    	lblLeader.setBounds(new Rectangle(5, 105, 140, 45));
    	lblLeader.setHorizontalAlignment(JLabel.CENTER);
    	lblLeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));   	
    	
    	txtTeam = new JTextField();
    	txtTeam.setBounds(150, 5, 500, 45);
    	txtTeam.setFont(lblTeam.getFont());
    	txtTeam.setEditable(true);
    	txtTeam.setBackground(Color.white);
    	txtTeam.setForeground(Color.BLACK);
    	
    	txtRole = new JTextField();
    	txtRole.setBounds(150, 55, 500, 45);
    	txtRole.setFont(lblRole.getFont());
    	txtRole.setEditable(true);
    	txtRole.setBackground(Color.white);
    	txtRole.setForeground(Color.BLACK);
    	

      	//콤보박스에 employee DTO집어넣기
    	dtos_Employee=dao.getAllEmployee();
    	String data[] = new String[dtos_Employee.size()+1];
    	data[0] = "";

    	for(int i=0 ; i<dtos_Employee.size() ; i++) {
    		data[i+1] = dtos_Employee.get(i).getName()+"_사번:"+dtos_Employee.get(i).getEmployeeId();
    	}
    	
    	//콤보박스 설정
    	cbLeader = new JComboBox<>(data);
    	cbLeader.setBounds(150, 105, 500, 45);
    	cbLeader.setFont(lblLeader.getFont());
    	cbLeader.setEditable(false);
    	cbLeader.setBackground(Color.WHITE);
    	cbLeader.setForeground(Color.BLACK);
    	
    	inputPane.add(lblTeam);
    	inputPane.add(lblRole);
    	inputPane.add(lblLeader);
    	inputPane.add(txtTeam);
    	inputPane.add(txtRole);
    	inputPane.add(cbLeader);
    	
    	add(inputPane);
    }
    
    //버튼판넬
    private void initBtnPane() {
    	
    	//판넬 설정
       	btnPane = new JPanel();
    	btnPane.setBounds(btnPane_X, btnPane_Y, btnPane_WIDTH, btnPane_HEIGHT);
    	btnPane.setLayout(null);
//    	btnPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));  	
//    	btnPane.setVisible(false); //권한에 따라 감추기
    	
    	//컴포넌트
    	btnInsert = new JButton("생성");
    	btnInsert.setFont(new Font("고딕",Font.BOLD,14));
    	btnInsert.setBounds(5, 80, 130, 30);
    	btnCancel = new JButton("취소");
    	btnCancel.setFont(new Font("고딕",Font.BOLD,14));
    	btnCancel.setBounds(5, 120, 130, 30);
    	btnEdit = new JButton("수정");
    	btnEdit.setFont(new Font("고딕",Font.BOLD,14));
    	btnEdit.setBounds(158, 80, 130, 30);
    	btnDelete = new JButton("삭제");
    	btnDelete.setFont(new Font("고딕",Font.BOLD,14));
    	btnDelete.setBounds(158, 120, 130, 30);		

    	btnPane.add(btnInsert);
    	btnPane.add(btnCancel);
    	btnPane.add(btnEdit);
    	btnPane.add(btnDelete);
    	 	
    	add(btnPane);
    	
    	//리스너 설정
    	btnInsert.addActionListener(this);
    	btnCancel.addActionListener(this);
    	btnEdit.addActionListener(this);
    	btnDelete.addActionListener(this);
    }
    
    //팀 생성
    private void insertTeam(int select) {

    	if(select==0) {  	
    		//예외처리
    		if(txtTeam.getText().trim().equals("")
					||txtRole.getText().trim().equals("")
					||cbLeader.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(
						this,
						"내용을 빠짐없이 입력하세요",
						"입력 오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
    		
    		if(txtTeam.getText().trim().length()>6) {
    			JOptionPane.showMessageDialog(
						this,
						"팀명이 너무 깁니다(6자 이내)",
						"입력 오류",
						JOptionPane.ERROR_MESSAGE);
				return;
    		}
    		
    		if(txtRole.getText().trim().length()>33) {
    			JOptionPane.showMessageDialog(
						this,
						"Role 내용이 너무 깁니다(33자 이내)",
						"입력 오류",
						JOptionPane.ERROR_MESSAGE);
				return;
    		}
    		
    		for(int i=0 ; i<tbTeamRecord.getRowCount() ; i++) {
    			boolean b = txtTeam.getText().trim().equals(
    					tbTeamRecord.getValueAt(i, 0));
    			if(b) {
    				JOptionPane.showMessageDialog(
    						this, 
    						"같은 팀명이 이미 존재합니다", 
    						"수정 실패", 
    						JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    		}
    		
    		//DTO
    		dto_Team = new TeamDto();
    		String arr[] = new String[2];
    		dto_Team.setTeamName(txtTeam.getText().trim());
    		dto_Team.setTeamRole(txtRole.getText().trim());
		
    		arr = ((String)cbLeader.getSelectedItem()).split("_사번:"); 
    		dto_Team.setTeamLeaderName(arr[0]);
    		dto_Team.setTeamLeaderId(arr[1]);
		
    		dao.insertTeam(dto_Team);
		
    		//JTABLE
    		Vector<String> v = new Vector<>();
    		v.addElement(txtTeam.getText().trim());
    		v.addElement(txtRole.getText().trim());
    		v.addElement(arr[0]);
    		v.addElement(arr[1]);
    		tbDefault.addRow(v);
    	
    	}
    	
	}
    
    //팀 삭제
    private void deleteTeam(int select) {
    	
    	if(select == 0) {
    		
	    	//DTO
			String teamName;
			teamName = (String)tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 0);
			
			dao.deleteTeam(teamName);
			
			txtTeam.setText("");
			txtRole.setText("");
			cbLeader.setSelectedIndex(0);
			
			//JTABLE
			tbDefault.removeRow(tbTeamRecord.getSelectedRow());
	    	tbTeamRecord.setRowSelectionInterval(1, 0);
	    	
    	}
  
    }
    
    //팀 수정
    private void editTeam(int select) {
    	    	
    	if(select==0) {
   		 
    		String arr[] = new String[2];
			arr = ((String)cbLeader.getSelectedItem()).split("_사번:"); 
			
    		//예외처리
			if(txtTeam.getText().trim().equals("")
					||txtRole.getText().trim().equals("")
					||cbLeader.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(
						this,
						"내용을 빠짐없이 입력하세요",
						"입력 오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
    		if(txtTeam.getText().trim().length()>6) {
    			JOptionPane.showMessageDialog(
						this,
						"팀명이 너무 깁니다",
						"입력 오류",
						JOptionPane.ERROR_MESSAGE);
				return;
    		}
    		if(txtRole.getText().trim().length()>33) {
    			JOptionPane.showMessageDialog(
						this,
						"Role 내용이 너무 깁니다(33자 이내)",
						"입력 오류",
						JOptionPane.ERROR_MESSAGE);
				return;
    		}
    		
    		if(txtTeam.getText().trim().equals(
    				tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 0))
    			&& txtRole.getText().trim().equals(
    				tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 1))
    			&& arr[0].equals(
    				tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 2))
    			&& arr[1].equals(
    				tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 3))
    		){
    			JOptionPane.showMessageDialog(
    					this,
    					"동일한 내용으로 수정할 수 없습니다", 
    					"수정 실패", 
    					JOptionPane.ERROR_MESSAGE);
    			return;	
    		}
    		
    		for(int i=0 ; i<tbTeamRecord.getRowCount() ; i++) {
    			if(i==tbTeamRecord.getSelectedRow()) continue;
    			boolean b = txtTeam.getText().trim().equals(
    					tbTeamRecord.getValueAt(i, 0));
    			if(b) {
    				JOptionPane.showMessageDialog(
    						this, 
    						"같은 팀명이 이미 존재합니다", 
    						"수정 실패", 
    						JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    		}		   		
    		//DTO
    		String prevTeam = (String) tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 0);
			dto_Team = new TeamDto();
			dto_Team.setTeamName(txtTeam.getText().trim());
			dto_Team.setTeamRole(txtRole.getText().trim());
		
			dto_Team.setTeamLeaderName(arr[0]);
			dto_Team.setTeamLeaderId(arr[1]);
			
			dao.updateTeam(dto_Team, prevTeam);
			
			//JTABLE
			tbTeamRecord.setValueAt(txtTeam.getText().trim(), tbTeamRecord.getSelectedRow(), 0);
			tbTeamRecord.setValueAt(txtRole.getText().trim(), tbTeamRecord.getSelectedRow(), 1);
			tbTeamRecord.setValueAt(arr[0], tbTeamRecord.getSelectedRow(), 2);
    	}
    
    }
        
    //버튼클릭 이벤트 - 팀 생성,수정,삭제
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int select; //버튼 선택 값
		
		if(e.getSource()==btnInsert) {
			//OptionDialog		
			String[] option = {"확인","취소"};
			select = JOptionPane.showOptionDialog(
					this, 
					"팀을 추가하시겠습니까?",
					"생성",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					option,
					option[0]);
			
			//팀 생성 매소드 호출
			insertTeam(select);
			
		}
		
		if(e.getSource()==btnCancel) {
			//비우기
			txtTeam.setText("");
			txtRole.setText("");
			cbLeader.setSelectedIndex(0);
		}
		
		if(e.getSource()==btnDelete) {
			//OptionDialog
			String[] option = {"확인","취소"};
			select = JOptionPane.showOptionDialog(
					this, 
					"선택한 팀을 삭제하시겠습니까?",
					"팀 삭제",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					option,
					option[0]);
			
			//팀 삭제 매소드 호출
			deleteTeam(select);
		}
		
		if(e.getSource()==btnEdit) {
    		//OptionDialog
			String[] option = {"확인","취소"};
			select = JOptionPane.showOptionDialog(
					this, 
					"선택한 팀을 수정하시겠습니까?",
					"팀 수정",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					option,
					option[0]);
			
			//팀 수정 메소드 호출
			editTeam(select);
		}
	}

	//마우스 클릭 이벤트 - 테이블 값 띄우기
	@Override
	public void mouseClicked(MouseEvent e) {
		txtTeam.setText((String) tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 0));
		txtRole.setText((String) tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 1));
		
		String cb = (String) tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 2);
		cb += "_사번:"+tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 3) ;
		cbLeader.setSelectedItem((Object)cb);
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}



