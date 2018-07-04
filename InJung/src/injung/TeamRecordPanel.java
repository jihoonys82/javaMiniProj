package injung;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class TeamRecordPanel extends JPanel{

	//상수
	private static final int tablePane_Y=0;
	private static final int tablePane_HEIGHT=350;
	private static final int tablePane_WIDTH=980;

	private static final int inputPane_Y=360;
	private static final int inputPane_HEIGHT=155;
	private static final int inputPane_WIDTH=660;
	
	private static final int btnPane_X=670;
	private static final int btnPane_Y=360;
	private static final int btnPane_HEIGHT=155;
	private static final int btnPane_WIDTH=310;
			
	private static final int PANEL_X=0;
	
	  
    //테이블 판넬
    private JPanel tablePane;
    //테이블 판넬 - 컴포넌트
    private JTable tbTeamRecord;
    //테이블 판넬 - 스크롤 판넬
    private JScrollPane scrollPane;
    
    //입력 판넬
    private JPanel inputPane;
    private JLabel lblTeam;
    private JLabel lblRole;
    private JLabel lblLeader;
    private JTextField txtTeam;
    private JTextField txtRole;
    private JTextField txtLeader;
    
    //버튼 판넬
    private JPanel btnPane;
    private JButton btnInsert;
    private JButton btncancel;
    
    
    public TeamRecordPanel() {
	
    	setLayout(null);
    	
    	
    	//팀레코드 판넬
    	//팀레코드 판넬 - 설정

    	tablePane = new JPanel();
    	tablePane.setBounds(PANEL_X, tablePane_Y, tablePane_WIDTH, tablePane_HEIGHT);
    	tablePane.setLayout(null);
    	tablePane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	//팀레코드 판넬 - 컴포넌트
    	//		데이터
    	String[] columnNames = {"부서","Role","Leader","수정","취소"};
    	Object[][] rowData = {
    			{"사장실","사장","홍길동"},
    			{"재무경영팀","재무경영 총괄","이순신"},
    			{"인사관리팀","인사관리","제주쓰"},
    			{"개발1팀","프로그램 개발","윌리암"},
    			{"개발2팀","프로그램 개발","오초아"},
    			{"기획팀","프로그램 기획","네이마르"}
    	};
    	
    	//		테이블 설정
    	DefaultTableModel tbDefault = new DefaultTableModel(rowData, columnNames); 
    	tbTeamRecord = new JTable(tbDefault);
    	tbTeamRecord.setFont(new Font("고딕",Font.BOLD,20));
    	tbTeamRecord.setRowHeight(30);

    	TableColumn firstColoumn = tbTeamRecord.getColumnModel().getColumn(0);
    	firstColoumn.setPreferredWidth(200);
    	firstColoumn.setMinWidth(200);
    	firstColoumn.setMaxWidth(200);
    	TableColumn secondColoumn = tbTeamRecord.getColumnModel().getColumn(1);
    	secondColoumn.setPreferredWidth(450);
    	secondColoumn.setMinWidth(450);
    	secondColoumn.setMaxWidth(450);
    	TableColumn thirdColoumn = tbTeamRecord.getColumnModel().getColumn(2);
    	thirdColoumn.setPreferredWidth(150);
    	thirdColoumn.setMinWidth(150);
    	thirdColoumn.setMaxWidth(150);
    	
 
    	
    	
    	//팀레코드판넬 - 스크롤 판넬
    	scrollPane = new JScrollPane(tbTeamRecord, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setBounds(5, 5, 970, 340);
    	scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	

    	
    	tablePane.add(scrollPane);
    	add(tablePane);
    	
    		
    	//인풋판넬
    	//인풋판넬 - 설정
    	
    	inputPane = new JPanel();
    	inputPane.setBounds(PANEL_X, inputPane_Y, inputPane_WIDTH, inputPane_HEIGHT);
    	inputPane.setLayout(null);
    	inputPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	
    	//인풋판넬 - 컴포넌트
    	
    	lblTeam = new JLabel("부 서");
    	lblTeam.setFont(lblTeam.getFont().deriveFont(18.0f));
    	lblTeam.setBounds(new Rectangle(5, 5, 140, 45));
    	lblTeam.setHorizontalAlignment(JLabel.CENTER);
    	lblTeam.setBorder(BorderFactory.createLineBorder(Color.BLUE));   	
    	
    	lblRole = new JLabel("Role");
    	lblRole.setFont(lblRole.getFont().deriveFont(18.0f));
    	lblRole.setBounds(new Rectangle(5, 55, 140, 45));
    	lblRole.setHorizontalAlignment(JLabel.CENTER);
    	lblRole.setBorder(BorderFactory.createLineBorder(Color.BLUE));   	
    	
    	lblLeader = new JLabel("Leader");
    	lblLeader.setFont(lblLeader.getFont().deriveFont(18.0f));
    	lblLeader.setBounds(new Rectangle(5, 105, 140, 45));
    	lblLeader.setHorizontalAlignment(JLabel.CENTER);
    	lblLeader.setBorder(BorderFactory.createLineBorder(Color.BLUE));   	
    	
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
    	
    	txtLeader = new JTextField();
    	txtLeader.setBounds(150, 105, 500, 45);
    	txtLeader.setFont(lblLeader.getFont());
    	txtLeader.setEditable(true);
    	txtLeader.setBackground(Color.white);
    	txtLeader.setForeground(Color.BLACK);
    	

    	
    	inputPane.add(lblTeam);
    	inputPane.add(lblRole);
    	inputPane.add(lblLeader);
    	inputPane.add(txtTeam);
    	inputPane.add(txtRole);
    	inputPane.add(txtLeader);


    	add(inputPane);
    	
    	//버튼판넬
    	//버튼판넬 - 설정
       	btnPane = new JPanel();
    	btnPane.setBounds(btnPane_X, btnPane_Y, btnPane_WIDTH, btnPane_HEIGHT);
    	btnPane.setLayout(null);
    	btnPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));  	
    	
    	
    	//버튼판넬 - 컴포넌트
    	btnInsert = new JButton("생성/수정");
    	btnInsert.setBounds(5, 5, 150, 70);
    	btncancel = new JButton("취소");
    	btncancel.setBounds(5, 80, 150, 70);

    	btnPane.add(btnInsert);
    	btnPane.add(btncancel);
    	
    	add(btnPane);
   	
    	
	}
	
	
}
