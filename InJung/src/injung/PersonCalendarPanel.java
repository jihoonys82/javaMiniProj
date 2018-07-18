package injung;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import injung.model.CalendarDto;
import injung.model.InJungDao;

/*
 * �ۼ����� : 2018.07.17
 * �������� : 2018.07.18
 * 
 * �ۼ��� : �ǹ���
 * ������ : �ǹ���
 * 
 * ���� ����
 * 
 * - �Ǻ� �޼ҵ� �߰� : initTab()
 * - JTable ������ ���� �� ������ ����, �÷� �̵� �Ұ� ����
 * 
 */

@SuppressWarnings("serial")
public class PersonCalendarPanel extends JPanel{
	
	private int id;

	// Table Panel
	private JPanel cTablePanel; // calendar
	
	// JTable ����
	private JTable calenTable; // ���� ���� Table
	private DefaultTableModel tableModel; // �߰�, ������ ����
	
	// JTable - �Ӽ�
	private Vector<String> tableAttribute;
	private static final String[] AttributeStr = {"", "�׽�ũ", "������", "���Ό����", "������", "����", "�޸�"};
	
	// JTable - ��ũ�ѹ�
	private JScrollPane jScrollPane; // ������Ʈ ��ũ�ѹ�
	
	
	// DAO, DTO (����)
	private static InJungDao dao;
	private ArrayList<CalendarDto> calendarDto;
	
	// DTO���� �޾Ƽ� ������ Vector (����)
	private static Vector<String> vDto; 
	
	
	// Tab (��������, �̴��� ����)
	private JTabbedPane tab;
	
	
	public PersonCalendarPanel(int id, String msg, boolean tab) {
		
		System.out.println("PersonCalendar getId : " + id);
		
		this.id = id;
		
		// DAO, DTO ���� (����)
		dao = InJungDao.getInstance();
		calendarDto = new ArrayList<>();
		calendarDto = dao.getAllTasks(id);
		
		if (msg == "normal") {
			initTablePanel(calendarDto, tab);
		} else if (msg == "tab") {
			initTab();
		}
		
	}
	
	private void initTablePanel(ArrayList<CalendarDto> calendarDto, boolean tab) {
		
		cTablePanel = new JPanel();
		cTablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		cTablePanel.setBounds(0, 0, 1000, 600);
		
		// --- ���̺� �Ӽ� ---
		tableAttribute = new Vector<>();
		
		for(int i = 0; i < AttributeStr.length; i++) {
			tableAttribute.add(AttributeStr[i]);
		}
		// --------------
		
		// DefaultTableModel�� �Ӽ� �ֱ� (������ x)
		tableModel = new DefaultTableModel(tableAttribute, 0) {

			@Override
			public boolean isCellEditable(int row, int column) { // ���̺� ���� ���� �Ұ��� �����
				return false;
			}
		};
		
		calenTable = new JTable(tableModel); // JTable�� DefaultTableModel ����
		System.out.println("���̺� �Ӽ����� �Ϸ�");
		
		// ���̺� ������ �ֱ�(row)
		for (CalendarDto list : calendarDto) {
			System.out.println("������ �ֱ�!!");
			vDto = new Vector<>();
			
			vDto.add(String.valueOf(list.getCalendarId()));
			vDto.add(list.getTaskName());
			vDto.add(list.getStartDate());
			vDto.add(list.getExpectEndDate());
			vDto.add(list.getActualEndDate());
			vDto.add(list.getStatus());
			vDto.add(list.getNote());
			
			tableModel.addRow(vDto); // ���ͷ� �ۿ� ���� �� �������
			System.out.println(vDto);
		}
		
		// �÷� ������
		calenTable.getTableHeader().setResizingAllowed(false); // ������ ���� ���ϰ� �����ϱ�
		calenTable.getTableHeader().setReorderingAllowed(false); // �÷� �̵� ���ϰ� �����ϱ�
		// 	�ʺ�
		calenTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		calenTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		calenTable.getColumnModel().getColumn(2).setPreferredWidth(180);
		calenTable.getColumnModel().getColumn(3).setPreferredWidth(180);
		calenTable.getColumnModel().getColumn(4).setPreferredWidth(180);
		calenTable.getColumnModel().getColumn(5).setPreferredWidth(70);
		calenTable.getColumnModel().getColumn(6).setPreferredWidth(160);
		calenTable.setRowHeight(30); // ����
		
		jScrollPane = new JScrollPane(calenTable);
		
		// calenTable ������
		// 	calenTable�� ���� jScrollPane�� �����ؾ� ũ�� ������ �ȴ�.
		if(!tab) {
			jScrollPane.setPreferredSize(new Dimension(900, 550));
		} else {
			jScrollPane.setPreferredSize(new Dimension(800, 200));
		}
		
		cTablePanel.add(jScrollPane);
		
		add(cTablePanel);	// root container �� �ֱ�
		
	}
	
	
	public void initTab() { // ���� ���� & �̴��� ���� �Ǻ�
		
		tab = new JTabbedPane();
		
//		tab.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		tab.add("���� ����", new PersonCalendarPanel(id, "normal", true));
		tab.add("�̴��� ����", new BirthdayPanel());
		
		tab.setPreferredSize(new Dimension(850, 250)); // jScrollPane(birthTable) ũ�� ����
		
		add(tab);
	}
	
}
