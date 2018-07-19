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
	 * 	 <�� ���ڵ� �ǳ�>
	 * �ֱ� ������Ʈ : 7.16
	 * �ۼ��� : ������
	 * ���� �۾� : �ڵ� ����
	 *
	 */
	
	private static final long serialVersionUID = 1L;
	
	//���(��ġ��)
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
	
    //���̺� �ǳ�
    private JPanel tablePane;
    private JTable tbTeamRecord;
    //���̺� �ǳ� - ��ũ�� �ǳ�
    private JScrollPane scrollPane;
    //J���̺� ����Ʈ ��, ������ ��
    DefaultTableModel tbDefault = null;

    //�Է� �ǳ�
    private JPanel inputPane;
    private JLabel lblTeam;
    private JLabel lblRole;
    private JLabel lblLeader;
    private JTextField txtTeam;
    private JTextField txtRole;
    private JComboBox<String> cbLeader;
    
    //��ư �ǳ�
    private JPanel btnPane;
    private JButton btnInsert;
    private JButton btnCancel;
    private JButton btnEdit;
    private JButton btnDelete;
    
       
    public TeamRecordPanel(){	
    	
    	//����
    	setLayout(null);   	
    		
    	//���̺��ǳ� 
    	initTablePane();
    	
    	//��ǲ�ǳ�
    	initInputPane();
    		
    	//��ư�ǳ�
    	initBtnPane();
    	  	 	
	}
   
    //���̺� �ǳ�
    private void initTablePane() { 
    	
    	//�ǳ� ����
    	tablePane = new JPanel();
    	tablePane.setBounds(PANEL_X, tablePane_Y, tablePane_WIDTH, tablePane_HEIGHT);
    	tablePane.setLayout(null);
//    	tablePane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	  	
    	//DAO��ü �޾ƿ���
    	dao = InJungDao.getInstance();
       	//DAO��ü getAllTeam�޼ҵ�� TeamDto LIST �ޱ�
    	dtos_Team = dao.getAllTeam();
    	   	
    	//���̺� ��� Vector ����
    	Vector<String> column = new Vector<>();
    	column.addElement("�� ��");
    	column.addElement("Role");
    	column.addElement("Leader");
    	column.addElement("Leader_id");
    	    	
    	//���̺� ����
    	tbDefault = new DefaultTableModel(column, 0) {  		
			private static final long serialVersionUID = 1L;
			@Override
    		public boolean isCellEditable(int row, int column) {
    			return false; //���̺� ���� �Ұ�
    		}
    	};    	
    	tbTeamRecord = new JTable(tbDefault);
    	tbTeamRecord.setFont(new Font("���",Font.BOLD,16));
    	tbTeamRecord.setRowHeight(40);
    	tbTeamRecord.setBackground(new Color(235,235,235));
    	tbTeamRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	
    	//�� Vector�� TeamDto ������ �ֱ�  	
    	for(int i=0;i< dtos_Team.size();i++) {
        	Vector<String> row = new Vector<>();
    		row.addElement(dtos_Team.get(i).getTeamName());
    		row.addElement(dtos_Team.get(i).getTeamRole());
    		row.addElement(dtos_Team.get(i).getTeamLeaderName()); 
    		row.addElement(dtos_Team.get(i).getTeamLeaderId());
    		tbDefault.addRow(row);
    	}
    	
    	// ���̺� ����(��� ����)
    	DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    	render.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	// ���̺� ���콺 ��(Ŭ���̺�Ʈ)
    	tbTeamRecord.addMouseListener(this);
    	
    	// ���̺� ��� ����
    	JTableHeader header = tbTeamRecord.getTableHeader();
    	header.setPreferredSize(new Dimension(970, 40));
    	header.setFont(new Font("���",Font.BOLD,16));
    	header.setBackground(new Color(200, 200, 200));
    	header.setResizingAllowed(false);
    	header.setReorderingAllowed(false);

    	// ���̺� �÷� ����
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
   	
    	//���̺��ǳ� - ��ũ�� �ǳ�
    	scrollPane = new JScrollPane(tbTeamRecord, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setBounds(5, 5, 970, 340);
//    	scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
    	tablePane.add(scrollPane);
    	add(tablePane);
    	
    }
       
	//��ǲ�ǳ�
    private void initInputPane() {
    	
    	//�ǳ� ����
    	inputPane = new JPanel();
    	inputPane.setBounds(PANEL_X, inputPane_Y, inputPane_WIDTH, inputPane_HEIGHT);
    	inputPane.setLayout(null);
//    	inputPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//    	inputPane.setVisible(false); //���ѿ� ���� ���߱�
    	
    	//������Ʈ
    	lblTeam = new JLabel("�� ��");
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
    	

      	//�޺��ڽ��� employee DTO����ֱ�
    	dtos_Employee=dao.getAllEmployee();
    	String data[] = new String[dtos_Employee.size()+1];
    	data[0] = "";

    	for(int i=0 ; i<dtos_Employee.size() ; i++) {
    		data[i+1] = dtos_Employee.get(i).getName()+"_���:"+dtos_Employee.get(i).getEmployeeId();
    	}
    	
    	//�޺��ڽ� ����
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
    
    //��ư�ǳ�
    private void initBtnPane() {
    	
    	//�ǳ� ����
       	btnPane = new JPanel();
    	btnPane.setBounds(btnPane_X, btnPane_Y, btnPane_WIDTH, btnPane_HEIGHT);
    	btnPane.setLayout(null);
//    	btnPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));  	
//    	btnPane.setVisible(false); //���ѿ� ���� ���߱�
    	
    	//������Ʈ
    	btnInsert = new JButton("����");
    	btnInsert.setFont(new Font("���",Font.BOLD,14));
    	btnInsert.setBounds(5, 80, 130, 30);
    	btnCancel = new JButton("���");
    	btnCancel.setFont(new Font("���",Font.BOLD,14));
    	btnCancel.setBounds(5, 120, 130, 30);
    	btnEdit = new JButton("����");
    	btnEdit.setFont(new Font("���",Font.BOLD,14));
    	btnEdit.setBounds(158, 80, 130, 30);
    	btnDelete = new JButton("����");
    	btnDelete.setFont(new Font("���",Font.BOLD,14));
    	btnDelete.setBounds(158, 120, 130, 30);		

    	btnPane.add(btnInsert);
    	btnPane.add(btnCancel);
    	btnPane.add(btnEdit);
    	btnPane.add(btnDelete);
    	 	
    	add(btnPane);
    	
    	//������ ����
    	btnInsert.addActionListener(this);
    	btnCancel.addActionListener(this);
    	btnEdit.addActionListener(this);
    	btnDelete.addActionListener(this);
    }
    
    //�� ����
    private void insertTeam(int select) {

    	if(select==0) {  	
    		//����ó��
    		if(txtTeam.getText().trim().equals("")
					||txtRole.getText().trim().equals("")
					||cbLeader.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(
						this,
						"������ �������� �Է��ϼ���",
						"�Է� ����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
    		
    		if(txtTeam.getText().trim().length()>6) {
    			JOptionPane.showMessageDialog(
						this,
						"������ �ʹ� ��ϴ�(6�� �̳�)",
						"�Է� ����",
						JOptionPane.ERROR_MESSAGE);
				return;
    		}
    		
    		if(txtRole.getText().trim().length()>33) {
    			JOptionPane.showMessageDialog(
						this,
						"Role ������ �ʹ� ��ϴ�(33�� �̳�)",
						"�Է� ����",
						JOptionPane.ERROR_MESSAGE);
				return;
    		}
    		
    		for(int i=0 ; i<tbTeamRecord.getRowCount() ; i++) {
    			boolean b = txtTeam.getText().trim().equals(
    					tbTeamRecord.getValueAt(i, 0));
    			if(b) {
    				JOptionPane.showMessageDialog(
    						this, 
    						"���� ������ �̹� �����մϴ�", 
    						"���� ����", 
    						JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    		}
    		
    		//DTO
    		dto_Team = new TeamDto();
    		String arr[] = new String[2];
    		dto_Team.setTeamName(txtTeam.getText().trim());
    		dto_Team.setTeamRole(txtRole.getText().trim());
		
    		arr = ((String)cbLeader.getSelectedItem()).split("_���:"); 
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
    
    //�� ����
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
    
    //�� ����
    private void editTeam(int select) {
    	    	
    	if(select==0) {
   		 
    		String arr[] = new String[2];
			arr = ((String)cbLeader.getSelectedItem()).split("_���:"); 
			
    		//����ó��
			if(txtTeam.getText().trim().equals("")
					||txtRole.getText().trim().equals("")
					||cbLeader.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(
						this,
						"������ �������� �Է��ϼ���",
						"�Է� ����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
    		if(txtTeam.getText().trim().length()>6) {
    			JOptionPane.showMessageDialog(
						this,
						"������ �ʹ� ��ϴ�",
						"�Է� ����",
						JOptionPane.ERROR_MESSAGE);
				return;
    		}
    		if(txtRole.getText().trim().length()>33) {
    			JOptionPane.showMessageDialog(
						this,
						"Role ������ �ʹ� ��ϴ�(33�� �̳�)",
						"�Է� ����",
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
    					"������ �������� ������ �� �����ϴ�", 
    					"���� ����", 
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
    						"���� ������ �̹� �����մϴ�", 
    						"���� ����", 
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
        
    //��ưŬ�� �̺�Ʈ - �� ����,����,����
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int select; //��ư ���� ��
		
		if(e.getSource()==btnInsert) {
			//OptionDialog		
			String[] option = {"Ȯ��","���"};
			select = JOptionPane.showOptionDialog(
					this, 
					"���� �߰��Ͻðڽ��ϱ�?",
					"����",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					option,
					option[0]);
			
			//�� ���� �żҵ� ȣ��
			insertTeam(select);
			
		}
		
		if(e.getSource()==btnCancel) {
			//����
			txtTeam.setText("");
			txtRole.setText("");
			cbLeader.setSelectedIndex(0);
		}
		
		if(e.getSource()==btnDelete) {
			//OptionDialog
			String[] option = {"Ȯ��","���"};
			select = JOptionPane.showOptionDialog(
					this, 
					"������ ���� �����Ͻðڽ��ϱ�?",
					"�� ����",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					option,
					option[0]);
			
			//�� ���� �żҵ� ȣ��
			deleteTeam(select);
		}
		
		if(e.getSource()==btnEdit) {
    		//OptionDialog
			String[] option = {"Ȯ��","���"};
			select = JOptionPane.showOptionDialog(
					this, 
					"������ ���� �����Ͻðڽ��ϱ�?",
					"�� ����",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					option,
					option[0]);
			
			//�� ���� �޼ҵ� ȣ��
			editTeam(select);
		}
	}

	//���콺 Ŭ�� �̺�Ʈ - ���̺� �� ����
	@Override
	public void mouseClicked(MouseEvent e) {
		txtTeam.setText((String) tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 0));
		txtRole.setText((String) tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 1));
		
		String cb = (String) tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 2);
		cb += "_���:"+tbTeamRecord.getValueAt(tbTeamRecord.getSelectedRow(), 3) ;
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



