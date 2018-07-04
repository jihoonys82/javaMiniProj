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

	//���
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
	
	  
    //���̺� �ǳ�
    private JPanel tablePane;
    //���̺� �ǳ� - ������Ʈ
    private JTable tbTeamRecord;
    //���̺� �ǳ� - ��ũ�� �ǳ�
    private JScrollPane scrollPane;
    
    //�Է� �ǳ�
    private JPanel inputPane;
    private JLabel lblTeam;
    private JLabel lblRole;
    private JLabel lblLeader;
    private JTextField txtTeam;
    private JTextField txtRole;
    private JTextField txtLeader;
    
    //��ư �ǳ�
    private JPanel btnPane;
    private JButton btnInsert;
    private JButton btncancel;
    
    
    public TeamRecordPanel() {
	
    	setLayout(null);
    	
    	
    	//�����ڵ� �ǳ�
    	//�����ڵ� �ǳ� - ����

    	tablePane = new JPanel();
    	tablePane.setBounds(PANEL_X, tablePane_Y, tablePane_WIDTH, tablePane_HEIGHT);
    	tablePane.setLayout(null);
    	tablePane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	//�����ڵ� �ǳ� - ������Ʈ
    	//		������
    	String[] columnNames = {"�μ�","Role","Leader","����","���"};
    	Object[][] rowData = {
    			{"�����","����","ȫ�浿"},
    			{"�繫�濵��","�繫�濵 �Ѱ�","�̼���"},
    			{"�λ������","�λ����","���־�"},
    			{"����1��","���α׷� ����","������"},
    			{"����2��","���α׷� ����","���ʾ�"},
    			{"��ȹ��","���α׷� ��ȹ","���̸���"}
    	};
    	
    	//		���̺� ����
    	DefaultTableModel tbDefault = new DefaultTableModel(rowData, columnNames); 
    	tbTeamRecord = new JTable(tbDefault);
    	tbTeamRecord.setFont(new Font("���",Font.BOLD,20));
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
    	
 
    	
    	
    	//�����ڵ��ǳ� - ��ũ�� �ǳ�
    	scrollPane = new JScrollPane(tbTeamRecord, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setBounds(5, 5, 970, 340);
    	scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	

    	
    	tablePane.add(scrollPane);
    	add(tablePane);
    	
    		
    	//��ǲ�ǳ�
    	//��ǲ�ǳ� - ����
    	
    	inputPane = new JPanel();
    	inputPane.setBounds(PANEL_X, inputPane_Y, inputPane_WIDTH, inputPane_HEIGHT);
    	inputPane.setLayout(null);
    	inputPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	
    	//��ǲ�ǳ� - ������Ʈ
    	
    	lblTeam = new JLabel("�� ��");
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
    	
    	//��ư�ǳ�
    	//��ư�ǳ� - ����
       	btnPane = new JPanel();
    	btnPane.setBounds(btnPane_X, btnPane_Y, btnPane_WIDTH, btnPane_HEIGHT);
    	btnPane.setLayout(null);
    	btnPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));  	
    	
    	
    	//��ư�ǳ� - ������Ʈ
    	btnInsert = new JButton("����/����");
    	btnInsert.setBounds(5, 5, 150, 70);
    	btncancel = new JButton("���");
    	btncancel.setBounds(5, 80, 150, 70);

    	btnPane.add(btnInsert);
    	btnPane.add(btncancel);
    	
    	add(btnPane);
   	
    	
	}
	
	
}
