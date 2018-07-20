package injung;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HelpDialog extends JDialog implements ListSelectionListener, ActionListener{

	/**
	 * ���� ���̾�α�
	 * �ֱ� �ۼ��� : 7. 17
	 * �ۼ��� : ������
	 * �ʿ��� �۾� : Ķ���� ����
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
		String[] data = {"File�޴�","Record�޴�","View�޴�","Calender","Help�޴�"};
		list = new JList<>(data);
		list.setBounds(10, 20, 80, 105);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(new Color(238, 238, 238));
		list.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
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
    	scrollPane.setBorder(new EtchedBorder());
    	
    	//btnClose ����
    	btnClose = new JButton("�ݱ�");
		btnClose.setBounds(380, 290, 80, 30);
		
		add(scrollPane);
		add(list);
		add(btnClose);
		
		btnClose.addActionListener(this);
		list.addListSelectionListener(this);

	}

	
	//Ŭ���̺�Ʈ ó��
	@Override
	public void valueChanged(ListSelectionEvent e) {
		String txt;
		//���ϸ޴� ����
		if(list.getSelectedIndex()==0) {
			txt = "<�α���/�α׾ƿ�>\r\n" + 
					"����� ���� �α��� �Ͽ� ���α׷� ��� ������ ���� �� �ֽ��ϴ�. "
					+ "�н����� �н� �� �н����� �缳���� �� �ֽ��ϴ�.\r\n"
					+ "\r\n" + 
					"<��������>\r\n" + 
					"�ܺ��� ������ �̿��Ͽ� ��� ������ �ҷ��� �� �ֽ��ϴ�."
					+"���� ������ ������ �ڷ�� dat�� �����ϰ� ������  jpg�� �����մϴ�. \r\n"
					+ "\r\n" + 
					"<��������>\r\n" + 
					"��� �λ������� ���Ϸ� �����Ͽ� ������ �� �ֽ��ϴ�. ���� ������ dat, csv Ȯ���ڸ� �����մϴ�.\r\n"
					+ "\r\n" + 
					"<ȯ�漳��>\r\n" + 
					"������ DB, ���丮���� ������ �� �ֽ��ϴ�.\r\n"
					+ "\r\n"
					+ "<����>\r\n"
					+ "���α׷��� �����մϴ�";
			txtHelp.setText(txt);
			txtHelp.setCaretPosition(0);
		}
		//���ڵ�޴� ����
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
			txtHelp.setCaretPosition(0);
		}
		//���� �޴� ����
		if(list.getSelectedIndex()==2) {
			txt="<�� ���� ����>\r\n" + 
				"�α��ε� ����� ���� ������ Ȯ���մϴ�\r\n"
				+"\r\n" + 
				"<3�� ����>\r\n" + 
				"��ü ����� ������ 3�� ������ Ȯ���� �� �ֽ��ϴ�"
				+"�󼼺��� ����� ���� ���ϴ� ����� ������ ���� Ȯ���� �� �ֽ��ϴ�.\r\n"
				+"\r\n" + 
				"<���� ����>\r\n" + 
				"��ü ����� ��ϵ� ���������� Ȯ���� �� �ֽ��ϴ�\r\n"
				+"\r\n" + 
				"<��ü����Ʈ ����>\r\n" + 
				"��ü����� ������ �� ���̺� ��� Ȯ�� �� �� �ֽ��ϴ�."
				+" ���ϴ� ����� ������ ����Ŭ���ϸ� �󼼺��⸦ �� �� �ֽ��ϴ�\r\n"
				+"\r\n" + 
				"<���� ����>\r\n" + 
				"���� ����� ������ 3�� ������ Ȯ���� �� �ֽ��ϴ�"
				+"�󼼺��� ����� ���� ���ϴ� ����� ������ ���� Ȯ���� �� �ֽ��ϴ�.\r\n";
			txtHelp.setText(txt);
			txtHelp.setCaretPosition(0);
			
		}
		//Ķ���� �޴� ����
		if(list.getSelectedIndex()==3) {
			txt="<���� ���� ����>\r\n";
			txtHelp.setText(txt);
			txtHelp.setCaretPosition(0);
			
		}
		//���� �޴� ����
		if(list.getSelectedIndex()==4) {
			txt="<����>\r\n" + 
				"��� ���߱� ��� �����մϴ�\r\n"
				+ "\r\n" + 
				"<����>\r\n" + 
				"���α׷� ���� ��ɿ� ���� ������ �����մϴ�\r\n"
				+ "\r\n" + 
				"<ũ����>\r\n" + 
				"ũ������ Ȯ���մϴ�\r\n"
				+ "\r\n" + 
				"<����? �������� ���Ͽ�>\r\n" + 
				"���α׷� ���� �������� Ȯ���մϴ�";
			txtHelp.setText(txt);
			txtHelp.setCaretPosition(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnClose)
			dispose();
		
	}
}
