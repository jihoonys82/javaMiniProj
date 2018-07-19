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
 * 수정일자 : 2018.07.19
 * 수정자 : 권미현
 * 
 *  - 생성자 통합
 *  - 메소드 setCalendar() 수정 : dto.setActualEndDate("NULL"); → 삭제
 *  
 */

public class NewTaskDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = -6801677544922886092L;
	
	private final JLabel lblGuide = new JLabel("모든 항목은 필수 항목입니다.");
	
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
	private String[] status = { "대기중", "진행중", "완료", "일정초과", "보류", "취소" };
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
	public NewTaskDialog(int employeeId) {	// 2018.07.19 : 생성자 통합
		
		this.empId = employeeId; 
		
		setTitle("새 태스크");
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
			valid+= "제목이 너무 짧습니다.\n";
		}
		
		int startMM = Integer.parseInt(txtStartDate.getText().substring(5, 7));
		int startDD = Integer.parseInt(txtStartDate.getText().substring(8, 10));
		if(startMM<0 || startMM>12 || startDD<0 || startDD>31) {
			valid += "시작일을 정확히 넣어주세요 \n";
		}
		
		int endMM = Integer.parseInt(txtEndDate.getText().substring(5, 7));
		int endDD = Integer.parseInt(txtEndDate.getText().substring(8, 10));
		if(endMM<0 || endMM>12 || endDD<0 || endDD>31) {
			valid += "종료예정일을 정확히 넣어주세요 \n";
		}
		
		if(txtNote.getText().trim().length()<1) {
			valid += "상세내용을 적어주세요.";
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
				System.out.println("result : " + result); // 입력 실패
				System.out.println("dto : " + dto);
				
				if(result==InJungDao.INSERT_DATA_SUCCESS) {
					JOptionPane.showMessageDialog(this, "입력 성공");
				} else {
					JOptionPane.showMessageDialog(this, "입력 실패");
				}
				
			}
			
		}
		if(e.getSource().equals(btnCancel)) {
			String[] options = { "삭제", "취소" };
			int selected = JOptionPane.showOptionDialog(this, "모든 내용이 삭제됩니다. 진행하시겠습니까?", "Confirm",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (selected == 0) {
				this.dispose();
			}
		}
		
	}
}
