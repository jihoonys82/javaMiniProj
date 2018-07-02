package injung;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * �ۼ� ���� : 2018.07.01
 * ���� ���� :
 * 
 * �ۼ��� : �ǹ���
 * 
 * ���ڵ� ��ȸ��� - ��ü����
 */

public class WholeView extends JFrame{

	private JPanel panelWholeView;	// ��ü���� Panel
	
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
		//���� ������ ����
		setTitle("��ü����");
		setBounds(300, 100, 1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));	// ���̾ƿ�
		
		// --- ������ ���� �۾� ---
		initTable(); // ��ü���� ����Ʈ(���̺�) ����
		// ------------------
		
		setVisible(true);
		
	}

	private void initTable() {
		
		String[][] sample = {
				{"ȫ�浿", "������", "����", "�����ΰ���", "aaa@naver.com", "000-0000-0000"},
				{"��ƹ�", "�����", "�븮", "����ΰ���", "bbb@naver.com", "111-1111-1111"},
				{"������", "�����", "���", "����λ��", "ccc@naver.com", "222-2222-2222"},
		};
		
		tableModel = new DefaultTableModel(sample, tableAttribute);
		tableView = new JTable(tableModel); // JTable�� DefaultTableModel ����

		for(int i = 0; i <50; i++) {
			String[] sam = {"ȫ�浿", "������", "����", "�����ΰ���", "aaa@naver.com", "000-0000-0000"};
			tableModel.addRow(sam);
		}
		
		tableView.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableView.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableView.getColumnModel().getColumn(2).setPreferredWidth(10);
		
		tableView.setSize(500, 500);	// ����X
		
		jScrollPane = new JScrollPane(tableView);
		
//		jScrollPane.setSize(500,500);
		jScrollPane.setPreferredSize(new Dimension(900, 450));
		
		add(jScrollPane);
	}
	
	public static void main(String[] args) {
		new WholeView();	// ������ ȣ��
	}
	
}
