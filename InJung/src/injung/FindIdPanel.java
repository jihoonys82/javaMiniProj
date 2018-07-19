package injung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import injung.model.EmployeeDto;
import injung.model.InJungDao;

/*
 * 07.15
 * �ۼ���: ������
 *  
 */

public class FindIdPanel extends JDialog implements ActionListener, KeyListener {
	private static final long serialVersionUID = -1581889542704938075L;

	private JTextField txtEmployeeId;
	private JTextField txtQuestion;
	private JTextField txtAnswer;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblNewPw;
	private JLabel lblVerifyPw;
	private JPasswordField txtNewPw;
	private JPasswordField txtVerifyPw;
	
	private InJungDao dao = InJungDao.getInstance();
	private EmployeeDto eDto;
	
	public FindIdPanel(JDialog dialog, String title, boolean modal,String str) {
		super(dialog, title, true);

		getContentPane().setLayout(null);

		JPanel findIdPane = new JPanel(); // ��й�ȣ ã�� �г� ����
		findIdPane.setBounds(0, 0, 434, 261);
		findIdPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("��� :"); // ��� ���̺� ����
		lblEmployeeId.setBounds(83, 24, 57, 15);

		JLabel lblQuestion = new JLabel("�н� ���� :"); // �н� ���� ���̺� ����
		lblQuestion.setBounds(83, 55, 77, 15);

		JLabel lblAnswer = new JLabel("�н� ��� : "); // �н� ��� ���̺� ����
		lblAnswer.setBounds(83, 88, 77, 15);

		txtEmployeeId = new JTextField(); // ��� �ؽ�Ʈ�ʵ� ����
		txtEmployeeId.setText(str);
		txtEmployeeId.setBounds(196, 21, 152, 21);
		txtEmployeeId.setColumns(10);
		txtEmployeeId.setEditable(false);
		
		txtQuestion = new JTextField(); // ���� �ؽ�Ʈ�ʵ� ����
		txtQuestion.setColumns(10);
		txtQuestion.setBounds(196, 52, 152, 21);
		txtQuestion.setEditable(false);

		txtAnswer = new JTextField(); // ��� �ؽ�Ʈ�ʵ� ����
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(196, 85, 152, 21);

		btnOk = new JButton("Ȯ��"); // Ȯ�ι�ư ����
		btnOk.setBounds(97, 212, 97, 23);
		btnOk.addActionListener(this); // Ȯ�� ��ư�� �׼Ǹ�����

		btnCancel = new JButton("���"); // ��ҹ�ư ����
		btnCancel.setBounds(240, 212, 97, 23); // ��� ��ư�� �׼Ǹ�����
		btnCancel.addActionListener(this);

		lblNewPw = new JLabel("�� �н����� : "); // ��й�ȣ ���� ���̺� ����
		lblNewPw.setBounds(83, 122, 77, 15);

		lblVerifyPw = new JLabel("�н����� Ȯ�� : "); // ��й�ȣ Ȯ�� ���̺� ����
		lblVerifyPw.setBounds(83, 157, 105, 15);

		txtNewPw = new JPasswordField(); // ��й�ȣ ���� �ؽ�Ʈ�ʵ� ����
		txtNewPw.setColumns(10);
		txtNewPw.setBounds(196, 119, 152, 21);

		txtVerifyPw = new JPasswordField(); // ��й�ȣ Ȯ�� �ؽ�Ʈ�ʵ� ����
		txtVerifyPw.setColumns(10);
		txtVerifyPw.setBounds(196, 154, 152, 21);
		txtVerifyPw.addKeyListener(this);

		findIdPane.add(txtVerifyPw);
		findIdPane.add(txtNewPw);
		findIdPane.add(lblVerifyPw);
		findIdPane.add(lblNewPw);
		findIdPane.add(btnCancel);
		findIdPane.add(btnOk);
		findIdPane.add(txtAnswer);
		findIdPane.add(txtQuestion);
		findIdPane.add(txtEmployeeId);
		findIdPane.add(lblAnswer);
		findIdPane.add(lblQuestion);
		findIdPane.add(lblEmployeeId);

		getContentPane().add(findIdPane);

		int logid = Integer.parseInt(str);	// LoginPanel���� ���� ���� ID�ؽ�Ʈ�ʵ尪(���)�� int������ ��ȯ
		showData(logid);	// �ش� ������ ���� �н������� ȣ��
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnOk)) { // Ȯ�� ��ư Ŭ���� ���� ��ġ ���� Ȯ��
			String strId = txtEmployeeId.getText();
			int intId = Integer.parseInt(strId);
			eDto = dao.getEmployee(intId);
			String toString = String.valueOf(intId);
			
			if ((txtEmployeeId.getText().equals(toString)) && (txtQuestion.getText().equals(eDto.getLostIdQuestion()))
					&& (txtAnswer.getText().equals(eDto.getLostIdAnswer()))) { // ���/�н�����/�нǴ���� ��� ��ġ�ϸ� ��й�ȣ ���� ����

					createPw(intId);	// ��й�ȣ ���� �޼ҵ� ����

			} else if (!(txtEmployeeId.getText().equals(toString))
					|| !(txtQuestion.getText().equals(eDto.getLostIdQuestion()))
					|| !(txtAnswer.getText().equals(eDto.getLostIdAnswer()))) { // ���/�н�����/�нǴ�� �ϳ��� ��ġ���� ������ �����ڹ��� �޼���
				JOptionPane.showMessageDialog(null, "������ ��ġ���� �ʽ��ϴ� \n �����ڿ��� �����ϼ���");
			}

		} else if (e.getSource().equals(btnCancel)) { // ��ҹ�ư Ŭ���� �ݱ�
			dispose();
		}
	}

	private void createPw(int empId) {
		eDto = dao.getEmployee(empId);

		char[] chPw_1 = txtNewPw.getPassword(); // getPassword�� char[]�� ���� �� new String
		String strNewPw = new String(chPw_1);
		char[] chPw_2 = txtVerifyPw.getPassword();
		String strVerPw = new String(chPw_2);

		if ((strNewPw.equals("")) && (strVerPw.equals(""))) { // �� �ؽ�Ʈ�ʵ带 �������� �ξ��� ��
			JOptionPane.showMessageDialog(null, "������ ��й�ȣ�� �Է��ϼ���");

		} else if ((!(strNewPw.length() > 0)) || (!(strVerPw.length() > 0))) { // �ϳ��� �ؽ�Ʈ�ʵ常 �������� �ξ��� ��
			JOptionPane.showMessageDialog(null, "������ ��й�ȣ�� �Է��ϼ���");

		} else if (!(strNewPw.equals(strVerPw))) { // ����й�ȣ�� ��й�ȣȮ���� ��ġ���� ���� ��
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ� \n �ٽ� �Է��ϼ���");

		} else if (strNewPw.equals(strVerPw)) { // ��й�ȣ ���� ����
			changeData(); // ��й�ȣ ������ ���� �޼ҵ� ����
			dispose(); // ���� �� �ݱ�

		}
	} // createPw

	private void changeData() { // DB�� ������ �����ϴ� �޼ҵ�
		int intId = Integer.parseInt(txtEmployeeId.getText());

		char[] chNewPw = txtNewPw.getPassword();
		String strNewPw = new String(chNewPw);

		int result = dao.setNewPassword(intId, strNewPw); // DAO�� ID�� ���ο� PW �� ���ڷ� ����

		if (result == InJungDao.INSERT_DATA_SUCCESS) { // DB �� ���� ���� ����
			JOptionPane.showMessageDialog(null, "��й�ȣ��  ����Ǿ����ϴ�");
		} else if (result == InJungDao.INSERT_DATA_FAILED) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ������ �� �����ϴ� \n �����ڿ��� �����ϼ���");
		}

	} // changeData
	
	public void showData(int empId) {	// ���� ���� ��� ���� ���ڷ� �ش� ����� ���� DB�� �н� ���� ȣ��  �޼ҵ� 
		eDto = dao.getEmployee(empId);		
			
		if (eDto == null) {		// ���� ������� ��й�ȣã�⸦ �õ��ϸ� ��� ���̾�α� ȣ�� 
			JOptionPane.showMessageDialog(null,"���� �����Դϴ�");	
		} else {
			txtQuestion.setText(eDto.getLostIdQuestion());				
		}
		
	}	// showData
	

	@Override
	public void keyPressed(KeyEvent e) { // ���� ���Ű
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			String strId = txtEmployeeId.getText();	
			int id = Integer.parseInt(strId);
			eDto = dao.getEmployee(id);
			
			createPw(id);	// ��й�ȣ ���� �޼ҵ� ���� 
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }
}
