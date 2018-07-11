package injung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import injung.model.EmployeeDto;
import injung.model.InJungDao;

public class FindIdPanel extends JDialog implements ActionListener {
private static final long serialVersionUID = -1581889542704938075L;
	
	private JTextField txtEmployeeId;
	private JTextField txtQuestion;
	private JTextField txtAnswer;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblNewPw;
	private JLabel lblVerifyPw;
	private JTextField txtNewPw;
	private JTextField txtVerifyPw;
	
	private InJungDao dao = InJungDao.getInstance();
	private EmployeeDto eDto;
	
	public FindIdPanel(JDialog dialog, String title, boolean modal) {
		super(dialog,title,true);
		
		getContentPane().setLayout(null);
		
		JPanel findIdPane = new JPanel();	// ��й�ȣ ã�� �г� ���� 
		findIdPane.setBounds(0, 0, 434, 261);
		findIdPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("��� :");	// ��� ���̺� ���� 
		lblEmployeeId.setBounds(83, 24, 57, 15);
		
		JLabel lblQuestion = new JLabel("�н� ���� :"); // �н� ���� ���̺� ���� 
		lblQuestion.setBounds(83, 55, 77, 15);
		
		JLabel lblAnswer = new JLabel("�н� ��� : ");	// �н� ��� ���̺� ���� 
		lblAnswer.setBounds(83, 88, 77, 15);
		
		txtEmployeeId = new JTextField();	// ��� �ؽ�Ʈ�ʵ� ���� 
		txtEmployeeId.setBounds(196, 21, 152, 21);
		txtEmployeeId.setColumns(10);
		
		txtQuestion = new JTextField();	// ���� �ؽ�Ʈ�ʵ� ���� 
		txtQuestion.setColumns(10);
		txtQuestion.setBounds(196, 52, 152, 21);
		
		txtAnswer = new JTextField();	// ��� �ؽ�Ʈ�ʵ� ���� 
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(196, 85, 152, 21);
		
		btnOk = new JButton("Ȯ��");			// Ȯ�ι�ư ���� 
		btnOk.setBounds(97, 212, 97, 23);
		btnOk.addActionListener(this);		// Ȯ�� ��ư�� �׼Ǹ����� 
		
		btnCancel = new JButton("���");		// ��ҹ�ư ���� 
		btnCancel.setBounds(240, 212, 97, 23);	// ��� ��ư�� �׼Ǹ�����
		btnCancel.addActionListener(this);
							
		lblNewPw = new JLabel("�� �н����� : ");		// ��й�ȣ ���� ���̺� ���� 
		lblNewPw.setBounds(83, 122, 77, 15);
		
		lblVerifyPw = new JLabel("�н����� Ȯ�� : ");	// ��й�ȣ Ȯ�� ���̺� ���� 
		lblVerifyPw.setBounds(83, 157, 105, 15);
		
		txtNewPw = new JTextField();	// ��й�ȣ ���� �ؽ�Ʈ�ʵ� ���� 
		txtNewPw.setColumns(10);
		txtNewPw.setBounds(196, 119, 152, 21);
		
		txtVerifyPw = new JTextField();	// ��й�ȣ Ȯ�� �ؽ�Ʈ�ʵ� ���� 
		txtVerifyPw.setColumns(10);
		txtVerifyPw.setBounds(196, 154, 152, 21);
		
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
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String strId = txtEmployeeId.getText();
		int toIntId = Integer.parseInt(strId); 
		
		if ( e.getSource().equals(btnOk)) {
			// 1. Ȯ�ι�ư ������ �� ���� ��ġ�ϸ� ��й�ȣ ���� ���� 
			//  1-1. ����й�ȣ�� ��й�ȣȮ�� �ؽ�Ʈ�ʵ尡 ��ĭ�̸� ��й�ȣ �Է� ��û
			//		1-1-1. �� �ʵ� �� �ϳ��� ��ĭ�� ���
			//		1-1-2. �� �ʵ� ��� ��ĭ�� ��� 
			//	1-2. ����й�ȣ�� ��й�ȣȮ�� �ؽ�Ʈ�ʵ尡 ����ġ�ϸ� ����ġ �޼��� 
			//  1-3. ����й�ȣ�� ��й�ȣȮ�� �ؽ�Ʈ�ʵ尡 ��ġ�ϸ� ��й�ȣ ���� 
			// 2. Ȯ�ι�ư ������ �� ���� ����ġ�ϸ� "��ġx" �޼��� ���� 
			
			if ( (txtEmployeeId.getText().equals(eDto.getEmployeeId())) && (txtQuestion.getText().equals(eDto.getPassword()))
					&& (txtAnswer.getText().equals(eDto.getLostIdAnswer()) ))  {		
			
						CreatePw(toIntId);
		
				
			} else if ( !(txtEmployeeId.getText().equals(eDto.getEmployeeId())) || !(txtQuestion.getText().equals(eDto.getLostIdQuestion()))		// ������ ��ġ���� ������ �����ڹ��� �޼��� 
					|| !(txtAnswer.getText().equals(eDto.getLostIdAnswer())) ) {	
				JOptionPane.showMessageDialog(null, "������ ��ġ���� �ʽ��ϴ� \n �����ڿ��� �����ϼ���");
			}
			
		} else if (e.getSource().equals(btnCancel)) {	// ��ҹ�ư Ŭ���� �ݱ� 
				dispose();
		}
		
		
	}
	
	private void CreatePw(int empId) {
		eDto = dao.getEmployee(empId);
			
					if( ( (txtNewPw.getText().equals("")) && (txtVerifyPw.getText().equals("")))){	// �� �ؽ�Ʈ�ʵ带 �������� �ξ��� ��  
						JOptionPane.showMessageDialog(null, "������ ��й�ȣ�� �Է��ϼ���");	
						
					} else if ( (txtNewPw.getText().equals("")) || (txtVerifyPw.getText().equals(""))) {
						JOptionPane.showMessageDialog(null, "������ ��й�ȣ�� �Է��ϼ���");	// �ϳ��� �ؽ�Ʈ�ʵ常 �������� �ξ��� �� 
						
					} else if (!(txtNewPw.getText().equals(txtVerifyPw.getText()))) {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ� \n �ٽ� �Է��ϼ���"); // ����й�ȣ�� ��й�ȣȮ���� ��ġ���� ���� �� 
						
					} else if ((txtNewPw.getText().equals(txtVerifyPw.getText()))) {	// ��й�ȣ ���� ����
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ����Ǿ����ϴ�");
	
						eDto.setPassword(txtNewPw.getText()); // ����� �� DB�� ���� 
						dispose();	// ���� �� �ݱ� 
						
					}
		

			
		
	}
}
