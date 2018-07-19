package injung;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import injung.model.CalendarDto;
import injung.model.InJungDao;

/*
 * �ۼ����� : 2018.07.17
 * �������� : 2018.07.19
 * 
 * �ۼ��� : �ǹ���
 * ������ : �ǹ���
 * 
 * ���� ����
 * 
 * - ���� �߰� Dialog ���� : NewTaskDialog
 * - â ������ ���� ���ϰ� �ϱ�
 * - ���콺 �̺�Ʈ �߰� : Update(finishTask)
 * - �̹� �Ϸ�� ������ ��쿡 ���� ó�� �߰�
 * 
 */

public class PersonCalendarPanel extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 4045852819553979355L;
	
	private int id;
	private int finish;
	private int calendarId;
	// ------------------------- //
	
	// Date
	private Date date; // ��¥
	private SimpleDateFormat dateFormat; // ��¥ ����
	
	// NewTaskDialog
	private NewTaskDialog dialog;
	
	
	// Button Panel
	private JPanel cButtonPanel;
	
	// Table Panel
	private JPanel cTablePanel; // calendar
	
	
	// JButton
	private JButton addButton;	// ���� ���� �߰� Button
	
	
	// JTable ����
	private JTable calenTable; // ���� ���� Table
	private DefaultTableModel tableModel; // �߰�, ������ ����
	
	// JTable - �Ӽ�
	private Vector<String> tableAttribute;
	private static final String[] AttributeStr = {"", "�׽�ũ", "������", "���Ό����", "������", "����", "�޸�"};
	
	// JTable - ��ũ�ѹ�
	private JScrollPane jScrollPane; // ������Ʈ ��ũ�ѹ�
	
	
	// Tab (��������, �̴��� ����)
	private JTabbedPane tab;
	
	
	// DAO, DTO (����)
	private static InJungDao dao;
	private ArrayList<CalendarDto> calendarDto;
	
	// DTO���� �޾Ƽ� ������ Vector (����)
	private static Vector<String> vDto; 
	
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // ����� ������
	
	
	public PersonCalendarPanel(int id, String msg, boolean tab) {
		
//		System.out.println("PersonCalendar getId : " + id);	// �׽�Ʈ �Ϸ�
		
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
	
	private void initButtonPanel() {
		
		cButtonPanel = new JPanel();
		
		addButton = new JButton("���� �߰�");
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("addButton get ID : " + id); // id ���� �޾ƿԴ��� �׽�Ʈ_�Ϸ�
				
				dialog = new NewTaskDialog(id);

				dialog.setSize(420, 400);
				dialog.setResizable(false); // â ������ ���� ���ϰ� �ϱ�
				
				// �߰��� ��ġ�ϱ�
				dialog.setLocation((screenSize.width - dialog.getWidth())/2, 
						(screenSize.height - dialog.getHeight())/2);
				
				dialog.setVisible(true);
				
			}
		});
	
		cButtonPanel.add(addButton);
		
		add(cButtonPanel);	// root container �� �ֱ�
		
	}
	
	@SuppressWarnings("serial")
	private void initTablePanel(ArrayList<CalendarDto> calendarDto, boolean tab) {
		
		cTablePanel = new JPanel();
		
		
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
		
		// ���̺� ������ �ֱ�(row)
		for (CalendarDto list : calendarDto) {
			vDto = new Vector<>();
			
			vDto.add(String.valueOf(list.getCalendarId()));
			vDto.add(list.getTaskName());
			vDto.add(list.getStartDate());
			vDto.add(list.getExpectEndDate());
			vDto.add(list.getActualEndDate());
			vDto.add(list.getStatus());
			vDto.add(list.getNote());
			
			tableModel.addRow(vDto); // ���ͷ� �ۿ� ���� �� �������
//			System.out.println(vDto);	// vDto�� ���� ����� �־�������... �׽�Ʈ �Ϸ�
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
		
		// MouseListener
		calenTable.addMouseListener(this);
		
		jScrollPane = new JScrollPane(calenTable);
		
		// calenTable ������
		// 	calenTable�� ���� jScrollPane�� �����ؾ� ũ�� ������ �ȴ�.
		if(!tab) {
			setLayout(new FlowLayout(FlowLayout.TRAILING, 40, 5));	// root ���̾ƿ� ����
			initButtonPanel();
			jScrollPane.setPreferredSize(new Dimension(900, 500));
		} else {
			jScrollPane.setPreferredSize(new Dimension(800, 200));
		}
		
		cTablePanel.add(jScrollPane);
		
		add(cTablePanel);	// root container �� �ֱ�
		
	}
	
	
	public void initTab() { // ���� ���� & �̴��� ���� �Ǻ�
		
		tab = new JTabbedPane();
		
		tab.add("���� ����", new PersonCalendarPanel(id, "normal", true));
		tab.add("�̴��� ����", new BirthdayPanel());
		
		tab.setPreferredSize(new Dimension(850, 250)); // jScrollPane(birthTable) ũ�� ����
		
		add(tab);
		
	}

	
	// --- ���콺 �̺�Ʈ ---
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getButton() == 1 && e.getClickCount() == 2) { // ���콺 �� ��ư && ���� Ŭ�� �� 
			
			// ���� ������ �Ϸ��Ұ��� ����  Dialog
			Object[] options = {"Ȯ��", "���"};
	 		int yesNo = JOptionPane.showOptionDialog(
					this, 
					"���� ������ �Ϸ��Ͻðڽ��ϱ�?", 
					"", // Dialog Title
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, 
					options,
					options[0]);
	 		
	 		// Ȯ���� ������
	 		if (yesNo == 0) {
	 			
	 			date = new Date();
	 			dateFormat = new SimpleDateFormat("yyyy.MM.dd");	
//	 			System.out.println(dateFormat.format(date)); // ���� ��¥ ��� Ȯ��_�׽�Ʈ �Ϸ�
	 			
	 			calendarId = calendarDto.get(calenTable.getSelectedRow()).getCalendarId();
//	 			System.out.println("Calendar ID : " + calendarId); // Ŭ���� �ο� Calendar ID_�׽�Ʈ �Ϸ�
	 			
	 			
	 			if (calendarDto.get(calenTable.getSelectedRow()).getActualEndDate() != null) { 
	 				// �������� null�� �ƴ� ��� = �������� ���� ��� = �Ϸ�
	 				JOptionPane.showMessageDialog(this,
	 						"�̹� �Ϸ�� �����Դϴ�.",
	 						"���", // Dialog Title
	 						JOptionPane.WARNING_MESSAGE);
	 			} else {
	 				// �������� null�� ��� = �������� ���� ��� = �Ϸ� x
	 				finish = dao.finishTask(calendarId, 
	 						String.valueOf(dateFormat.format(date)), 
	 						"�Ϸ�");
	 				
	 				if (finish == 1) {
	 					JOptionPane.showMessageDialog(this,
	 							"���� ������ �Ϸ��߽��ϴ�.",
	 							"", // Dialog Title
	 							JOptionPane.INFORMATION_MESSAGE);
	 				} // finish if��
	 			}
	 			
	 		} // yesNo if��
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	// ----------------
	
}
