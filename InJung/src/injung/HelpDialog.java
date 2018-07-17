package injung;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HelpDialog extends JDialog implements ListSelectionListener{

	/**
	 * ���� ���̾�α�
	 * �ֱ� �ۼ��� : 7. 17
	 * �ۼ��� : ������
	 * �۾���...
	 */
	private static final long serialVersionUID = 1L;
	
	//������Ʈ ���
	JList<String> list;
	JTextArea txtHelp;
	JScrollPane scrollPane;
	JButton btnClose;
	
	
	public HelpDialog(JFrame frame,String title,boolean modal,int x,int y) {
		
		//���̾�α� ����
		super(frame,title,modal);
		setLocation(x, y);
		setSize(500,400);
		setLayout(null);
		
		//JList ����
		String[] data = {"File�޴�","Record�޴�","View�޴�","Help�޴�"};
		list = new JList<>(data);
		list.setBounds(10, 20, 80, 80);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		
		//TextArea ����
		txtHelp = new JTextArea();
		txtHelp.setFont(new Font("���",Font.BOLD,14));
		txtHelp.setEditable(false);
		txtHelp.setLineWrap(true);
		txtHelp.setBackground(new Color(245, 245, 245));	
		
		//scrollPane ����
		scrollPane = new JScrollPane(txtHelp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scrollPane.setBounds(100, 20, 360, 250);
    	
    	//btnClose ����
    	btnClose = new JButton("�ݱ�");
		btnClose.setBounds(380, 290, 80, 30);
		
		add(scrollPane);
		add(list);
		add(btnClose);
		
		list.addListSelectionListener(this);

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String txt;
		if(list.getSelectedIndex()==0) {
			txt = "<�α���/�α׾ƿ�>\r\n" + 
					"����� ���� �α��� �Ͽ� ���α׷� \r\n" + 
					"��� ������ ���� �� �ֽ��ϴ�.\r\n" + 
					"�н����� �н� �� �н����� �缳���� �� \r\n" + 
					"�ֽ��ϴ�.\r\n" + 
					"<��������>\r\n" + 
					"\r\n" + 
					"<��������>\r\n" + 
					"��� �λ������� ���Ϸ� �����Ͽ� ������ �� �ֽ��ϴ�. ���� ������ dat, csv Ȯ���ڸ� �����մϴ�.\r\n" + 
					"<ȯ�漳��>\r\n" + 
					"������ DB, ���丮���� ������ �� �ֽ��ϴ�.\r\n";
			txtHelp.setText(txt);
		}
		if(list.getSelectedIndex()==1) {
			txt="<�űԵ��>\r\n" + 
				"�ű� ��������� ����մϴ�.\r\n" + 
				"���� ���ε� ����� �����ϸ� \r\n" + 
				"���� ��Ͻ� ����� �ڵ� ��ϵ˴ϴ�.\r\n" + 
				"\r\n" + 
				"<��������>\r\n" + 
				"�μ��� ����,����,������ �� �ֽ��ϴ�.\r\n" + 
				"������ �μ��� ���� ����� �� ������ ����\r\n" + 
				"�� �� �ֽ��ϴ�.\r\n";
			txtHelp.setText(txt);
		}
		if(list.getSelectedIndex()==2) {
			txt="<�� ���� ����>\r\n" + 
				"�α��� �� �� ���� ���� ����� Ȱ��ȭ�˴ϴ�.\r\n"
				+ " �α��� �� ����� ���������� Ȯ���� �� �ֽ��ϴ�.\r\n" + 
				"<3�� ����>\r\n" + 
				"��ü ����� ������ 3�� ������ Ȯ���� �� �ֽ��ϴ�.\r\n "
				+ "�󼼺��� ����� ���� ���ϴ� ����� ������ ���� Ȯ���� �� �ֽ��ϴ�.\r\n" + 
				"<���� ����>\r\n" + 
				"��ü ����� ��ϵ� ���������� Ȯ���� �� �ֽ��ϴ�.\r\n" + 
				"<��ü����Ʈ ����>\r\n" + 
				"��ü����� ������ �� ���� �����ϰ� Ȯ�� �� �� �ֽ��ϴ�.\r\n" + 
				"<���� ����>\r\n" + 
				"���� ����� ������ 3�� ������ Ȯ���� �� �ֽ��ϴ�.\r\n"
				+ " �󼼺��� ����� ���� ���ϴ� ����� ������ ���� Ȯ���� �� �ֽ��ϴ�.\r\n";
			txtHelp.setText(txt);
		}
		if(list.getSelectedIndex()==3) {
			txt="<����>\r\n" + 
				"��� ���߱� ��� �����մϴ�.\r\n" + 
				"<����>\r\n" + 
				"���α׷� ���� ��ɿ� ���� ������ �����մϴ�.\r\n" + 
				"<ũ����>\r\n" + 
				"ũ������ Ȯ���մϴ�\r\n" + 
				"<����? �������� ���Ͽ�>\r\n" + 
				"���α׷� ���� �������� Ȯ���մϴ�";
			txtHelp.setText(txt);
		}
		
	}
}
