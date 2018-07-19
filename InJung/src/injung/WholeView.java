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
 * �ۼ� ���� : 2018.07.01
 * ���� ���� : 2018.07.19
 *  - ���� Ŭ������ Dialog �ҷ����Բ� ����
 * 
 * �ۼ��� : �ǹ���
 * ������ : �ǹ���
 * 
 * ���ڵ� ��ȸ��� - ��ü����
 */

@SuppressWarnings("serial")
public class WholeView extends JPanel implements ActionListener, MouseListener{

	// WholeView Panel
	private JPanel wholePanel;

	// -- JTable ���� ---
	private JTable tableView; // ��ü���� Table
	private DefaultTableModel tableModel; // �߰�, ������ �����ϰ� �ϱ� ���� ����
	// �⺻ JTable �� �߰�, ������ �� ��

	private JScrollPane jScrollPane; // ������Ʈ ��ũ�ѹ�

	// ---- ���̺� �Ӽ� -----
	private Vector<String> tableAttribute;
	private static final String[] AttributeStr = { "�̸�", "�μ�", "����", "��å", "����", "��ȭ" };
	// ------------------

	// --- DAO, DTO ---
	private static InJungDao dao;
	private ArrayList<EmployeeDto> listDto;
	// ----------------

	// DTO���� �޾Ƽ� ������ Vector
	private Vector<String> vDto;

	// mouseListner ����
	private int clicktable;
	
	// JLable
	private JLabel jl;
	

	// ������
	public WholeView() {

		setLayout(null); // ���̾ƿ�

		// --- DAO, DTO ���� ---
		dao = InJungDao.getInstance();
		listDto = new ArrayList<>();
		listDto = dao.getAllEmployee();
		// -------------------

		// --- ������ ���� �۾� ---
		initTablePanel(listDto); // ��ü���� ����Ʈ(���̺�) ����
		// ------------------

	}

	private void initTablePanel(ArrayList<EmployeeDto> listDto) {

		wholePanel = new JPanel();
		wholePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		wholePanel.setBounds(0, 0, 1000, 600);

		////////////////////////////////////////////////////////////////
		

		// --- ���̺� �Ӽ� ---
		tableAttribute = new Vector<>();

		for (int i = 0; i < AttributeStr.length; i++) {
			tableAttribute.add(AttributeStr[i]);
		}
//		System.out.println(tableAttribute);
		// --------------

		// DefaultTableModel�� �Ӽ� �ֱ� (������ x)
		tableModel = new DefaultTableModel(tableAttribute, 0) {

			@Override
			public boolean isCellEditable(int row, int column) { // ���̺� ���� ���� �Ұ��� �����
				return false;
			}
		};

		tableView = new JTable(tableModel); // JTable�� DefaultTableModel ����
		tableView.addMouseListener(this);
		tableView.getTableHeader().setReorderingAllowed(false);
		tableView.getTableHeader().setResizingAllowed(false);
		tableView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableView.setBackground(new Color(235, 235, 235));
		
		// ���̺� ������ �ֱ�(row)
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
//			String[] sam = {"ȫ�浿", "������", "����", "�����ΰ���", "aaa@naver.com", "000-0000-0000"};
//			tableModel.addRow(sam);
//		}

		// �÷� ������
		tableView.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableView.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableView.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableView.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableView.getColumnModel().getColumn(4).setPreferredWidth(300);
		tableView.getColumnModel().getColumn(5).setPreferredWidth(200);
		tableView.setRowHeight(30);
		
		

//		tableView.setSize(900, 450);	// ����X

		jScrollPane = new JScrollPane(tableView);

//		jScrollPane.setSize(900, 450); // ����X
		jScrollPane.setPreferredSize(new Dimension(900, 450));

		wholePanel.add(jScrollPane); // wholeView Panel�� �߰�

		add(wholePanel); // wholeView Panel �߰�

	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1 && e.getClickCount() == 2) // �� ��ư && ���� Ŭ�� ��
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
