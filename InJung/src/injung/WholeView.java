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
 * �ۼ� ���� : 2018.07.01
 * ���� ���� : 2018.07.05
 * 	- ���̺� ��(����) ���� ����
 * ���� ���� : 2018.07.09
 *  - ��� : JFram �� JPanel
 * ���� ���� : 2018.07.10
 *  - �г� �߰�, ���̾ƿ� ����
 * 
 * �ۼ��� : �ǹ���
 * 
 * ���ڵ� ��ȸ��� - ��ü����
 */

public class WholeView extends JPanel{

	// WholeView Panel
	private JPanel wholePanel;
	
	// -- JTable ���� ---
	private JTable tableView; // ��ü���� Table
	private DefaultTableModel tableModel; // �߰�, ������ �����ϰ� �ϱ� ���� ����
	// �⺻ JTable �� �߰�, ������ �� ��
	
	private JScrollPane jScrollPane; // ������Ʈ ��ũ�ѹ�
	
	// ���̺� �Ӽ�
	private final String[] tableAttribute = {"�̸�", "�μ�", "����", "��å", "����", "��ȭ"};
	// ------------------
	
	// ������
	public WholeView() {
		
		setLayout(null);	// ���̾ƿ�
		
		// --- ������ ���� �۾� ---
		initTablePanel(); // ��ü���� ����Ʈ(���̺�) ����
		// ------------------
		
	}
	
//	public WholeView(int employeeId) { // DTO���� �����͸� �޾ƿ� �޼ҵ�
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
				{"ȫ�浿", "������", "����", "�����ΰ���", "aaa@naver.com", "000-0000-0000"},
				{"��ƹ�", "�����", "�븮", "����ΰ���", "bbb@naver.com", "111-1111-1111"},
				{"������", "�����", "���", "����λ��", "ccc@naver.com", "222-2222-2222"},
		};
		
		tableModel = new DefaultTableModel(sample, tableAttribute) {

			@Override
			public boolean isCellEditable(int row, int column) { // ���̺� ���� ���� �Ұ��� �����
				return false;
			}
		};
		
		tableView = new JTable(tableModel); // JTable�� DefaultTableModel ����

		for(int i = 0; i <50; i++) {
			String[] sam = {"ȫ�浿", "������", "����", "�����ΰ���", "aaa@naver.com", "000-0000-0000"};
			tableModel.addRow(sam);
		}
		
		tableView.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableView.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableView.getColumnModel().getColumn(2).setPreferredWidth(10);
		
//		tableView.setSize(500, 500);	// ����X
		
		jScrollPane = new JScrollPane(tableView);
		
//		jScrollPane.setSize(500,500);
		jScrollPane.setPreferredSize(new Dimension(900, 450));
		
		wholePanel.add(jScrollPane); // wholeView Panel�� �߰�
		
		add(wholePanel); // wholeView Panel �߰�
	}
	
}
