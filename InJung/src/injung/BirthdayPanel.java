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
 * �ۼ����� : 2018.07.17
 * �������� : 2018.07.18
 * 
 * �ۼ��� : �ǹ���
 * ������ : �ǹ���
 * 
 * �̴��� ���� ��� (JTable)
 * 
 * - JLabel ����
 * 
 */

public class BirthdayPanel extends JPanel {

	private static final long serialVersionUID = -826773397242782896L;

	// Date
	@SuppressWarnings("unused")
	private static Date month;
	@SuppressWarnings("unused")
	private static SimpleDateFormat dateFormat; // ���� �޸� ���
	
	// Table Panel
	private JPanel bTablePanel; // birth
	
	
	// JTable ����
	private JTable birthTable; // �̴��� ���� Table
	private DefaultTableModel tableModel;	// �߰�, ������ ����
	
	// JTable - �Ӽ�
	private Vector<String> tableAttribute;
	private static final String[] AttributeStr = {"����", "�̸�", "�μ�", "����", "��å"};
	
	// JTable - ��ũ�ѹ�
	private JScrollPane jScrollPane; // ������Ʈ ��ũ�ѹ�
	
	
	//DAO, DTO (����)
	private static InJungDao dao;
	private ArrayList<EmployeeDto> birthDto;
	
	// DTO���� �޾Ƽ� ������ Vector (����)
	private static Vector<String> vDto; 
	
	
	public BirthdayPanel() {
		
		//Date ����
		month = new Date();
		dateFormat = new SimpleDateFormat("MM"); // ���� �޸� ���
		
		//DAO, DTO ���� (����)
		dao = InJungDao.getInstance();
		birthDto = new ArrayList<>();
//		birthDto = dao.getEmpBirth(Integer.parseInt((dateFormat.format(month))));
//		System.out.println(Integer.parseInt((dateFormat.format(month)))); // ���� ��, Ȯ�� �Ϸ�
		birthDto = dao.getEmpBirth(8);
		
		initTablePanel(birthDto);
		
	}
	
	private void initTablePanel(ArrayList<EmployeeDto> birthDto) {
		
		bTablePanel = new JPanel();
		bTablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		bTablePanel.setPreferredSize(new Dimension(800, 200)); // bTablePanel ũ�� ����
		
		
		// --- ���̺� �Ӽ� ---
		tableAttribute = new Vector<>();
		
		for(int i = 0; i < AttributeStr.length; i++) {
			tableAttribute.add(AttributeStr[i]);
		}
		// --------------
		
		
		// DefaultTableModel�� �Ӽ� �ֱ� (������ x)
		tableModel = new DefaultTableModel(tableAttribute, 0) {

			private static final long serialVersionUID = 7171482468923032071L;

			@Override
			public boolean isCellEditable(int row, int column) { // ���̺� ���� ���� �Ұ��� �����
				return false;
			}
		};
		
		birthTable = new JTable(tableModel); // JTable�� DefaultTableModel ����
		
		// ���̺� ������ �ֱ�(row)
		for(EmployeeDto list : birthDto) {
			
			vDto = new Vector<>();

			vDto.add(list.getBirth());
			vDto.add(list.getName());
			vDto.add(list.getTeam());
			vDto.add(list.getLevel());
			vDto.add(list.getRole());
			
			tableModel.addRow(vDto);
			
		}
		
		// �÷� ������
		birthTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		birthTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		birthTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		birthTable.getColumnModel().getColumn(3).setPreferredWidth(110);
		birthTable.getColumnModel().getColumn(4).setPreferredWidth(180);
		birthTable.setRowHeight(30);
		
		
		jScrollPane = new JScrollPane(birthTable);
		
		jScrollPane.setPreferredSize(new Dimension(800, 200)); // jScrollPane(birthTable) ũ�� ����
		
		bTablePanel.add(jScrollPane);
		
		add(bTablePanel);
	}
	
}
