package injung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import injung.model.CalendarDto;
import injung.model.InJungDao;

/**
 * Input new Task
 * @since 2018-07-19
 * @author Jihoon Jeong
 *
 */

/*
 * �������� : 2018.07.19
 * ������ : �ǹ���
 * 
 *  - ������ ����
 *  - �޼ҵ� setCalendar() ���� : dto.setActualEndDate("NULL"); �� ����
 *  
 */

public class NewTaskDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = -6801677544922886092L;
	
	private final JLabel lblGuide = new JLabel("��� �׸��� �ʼ� �׸��Դϴ�.");
	
	private JLabel lblTaskName = new JLabel("TaskName");
	private JTextField txtTaskName= new JTextField();
	
	private JLabel lblTaskOwner = new JLabel("Task Owner");
	private JTextField txtOwner = new JTextField();
	
	private JLabel lblStartDate = new JLabel("Start Date");
	private JFormattedTextField txtStartDate;
	
	private JLabel lblExpectEndDate = new JLabel("Expect End Date");
	private JFormattedTextField txtEndDate;

	private MaskFormatter dateFormat = null;
	
	private JLabel lblStatus = new JLabel("Status");
	private String[] status = { "�����", "������", "�Ϸ�", "�����ʰ�", "����", "���" };
	private JComboBox<String> cbStatus = new JComboBox<>();
	
	
	private JLabel lblNote = new JLabel("Note");
	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea txtNote = new JTextArea();
	
	private JButton btnConfirm = new JButton("Confirm");
	private JButton btnCancel = new JButton("Cancel");
	
	//
	private int empId; 
	private InJungDao dao = InJungDao.getInstance();
	private CalendarDto dto = new CalendarDto();
	
	
	/**
	 * Input new task dialog for employee
	 * employeeId is required.
	 * @param employeeId
	 */
	public NewTaskDialog(int employeeId) {	// 2018.07.19 : ������ ����
		
		this.empId = employeeId; 
		
		setTitle("�� �½�ũ");
		getContentPane().setLayout(null);
		
		// Guide 
		lblGuide.setBounds(211, 10, 171, 15);
		getContentPane().add(lblGuide);
		
		// TaskName
		lblTaskName.setBounds(12, 35, 160, 15);
		getContentPane().add(lblTaskName);
		
		txtTaskName.setBounds(22, 60, 360, 21);
		getContentPane().add(txtTaskName);
		
		// TaskOwner
		lblTaskOwner.setBounds(12, 91, 160, 15);
		getContentPane().add(lblTaskOwner);

		txtOwner.setBounds(22, 116, 150, 21);
		txtOwner.setText(dao.getEmployee(empId).getName());
		txtOwner.setEditable(false);
		getContentPane().add(txtOwner);
		
		// Start Date and Expected End Date
		try {
			dateFormat = new MaskFormatter("####.##.##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lblStartDate.setBounds(222, 91, 160, 15);
		getContentPane().add(lblStartDate);

		txtStartDate = new JFormattedTextField(dateFormat);
		txtStartDate.setBounds(232, 116, 150, 21);
		
		getContentPane().add(txtStartDate);
		
		lblExpectEndDate.setBounds(222, 147, 160, 15);
		getContentPane().add(lblExpectEndDate);
		
		txtEndDate = new JFormattedTextField(dateFormat);
		txtEndDate.setBounds(232, 172, 150, 21);
		getContentPane().add(txtEndDate);
		
		// Status
		lblStatus.setBounds(12, 147, 160, 15);
		getContentPane().add(lblStatus);
		
		cbStatus.setBounds(22, 172, 150, 21);
		getContentPane().add(cbStatus);
		for(String str : status) {
			cbStatus.addItem(str);
		}
		
		// Note 
		scrollPane.setBounds(22, 228, 360, 80);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(txtNote);
				
		lblNote.setBounds(12, 203, 160, 15);
		getContentPane().add(lblNote);
		
		
		// Buttons
		btnConfirm.setBounds(176, 323, 97, 23);
		getContentPane().add(btnConfirm);
		btnConfirm.addActionListener(this);
		
		btnCancel.setBounds(285, 323, 97, 23);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(this);
		
		setVisible(true);
		
	}
	
	private boolean fieldValidation() {
		boolean rtn = true;
		String valid = null; 
		
		if(txtTaskName.getText().trim().length()<5) {
			valid+= "������ �ʹ� ª���ϴ�.\n";
		}
		
		int startMM = Integer.parseInt(txtStartDate.getText().substring(5, 7));
		int startDD = Integer.parseInt(txtStartDate.getText().substring(8, 10));
		if(startMM<0 || startMM>12 || startDD<0 || startDD>31) {
			valid += "�������� ��Ȯ�� �־��ּ��� \n";
		}
		
		int endMM = Integer.parseInt(txtEndDate.getText().substring(5, 7));
		int endDD = Integer.parseInt(txtEndDate.getText().substring(8, 10));
		if(endMM<0 || endMM>12 || endDD<0 || endDD>31) {
			valid += "���Ό������ ��Ȯ�� �־��ּ��� \n";
		}
		
		if(txtNote.getText().trim().length()<1) {
			valid += "�󼼳����� �����ּ���.";
		}
		
		if(valid!=null) {
			JOptionPane.showMessageDialog(this, valid);
			rtn = false;
		}
		
		return rtn;
	}
	
	private void setCalendar() {
		dto.setTaskName(txtTaskName.getText().trim());
		dto.setStartDate(txtStartDate.getText());
		dto.setExpectEndDate(txtEndDate.getText());
		dto.setStatus(cbStatus.getSelectedItem().toString());
		dto.setNote(txtNote.getText());
		dto.setOwnerId(empId);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnConfirm)) {
			if(fieldValidation()) {
				setCalendar();
				int result = dao.insertCalendar(dto);
				System.out.println("result : " + result); // �Է� ����
				System.out.println("dto : " + dto);
				
				if(result==InJungDao.INSERT_DATA_SUCCESS) {
					JOptionPane.showMessageDialog(this, "�Է� ����");
				} else {
					JOptionPane.showMessageDialog(this, "�Է� ����");
				}
				
			}
			
		}
		if(e.getSource().equals(btnCancel)) {
			String[] options = { "����", "���" };
			int selected = JOptionPane.showOptionDialog(this, "��� ������ �����˴ϴ�. �����Ͻðڽ��ϱ�?", "Confirm",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (selected == 0) {
				this.dispose();
			}
		}
		
	}
}
