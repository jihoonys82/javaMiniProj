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
		
		JPanel findIdPane = new JPanel();	// 비밀번호 찾기 패널 생성 
		findIdPane.setBounds(0, 0, 434, 261);
		findIdPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("사번 :");	// 사번 레이블 생성 
		lblEmployeeId.setBounds(83, 24, 57, 15);
		
		JLabel lblQuestion = new JLabel("분실 질문 :"); // 분실 질문 레이블 생성 
		lblQuestion.setBounds(83, 55, 77, 15);
		
		JLabel lblAnswer = new JLabel("분실 대답 : ");	// 분실 대답 레이블 생성 
		lblAnswer.setBounds(83, 88, 77, 15);
		
		txtEmployeeId = new JTextField();	// 사번 텍스트필드 생성 
		txtEmployeeId.setBounds(196, 21, 152, 21);
		txtEmployeeId.setColumns(10);
		
		txtQuestion = new JTextField();	// 질문 텍스트필드 생성 
		txtQuestion.setColumns(10);
		txtQuestion.setBounds(196, 52, 152, 21);
		
		txtAnswer = new JTextField();	// 대답 텍스트필드 생성 
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(196, 85, 152, 21);
		
		btnOk = new JButton("확인");			// 확인버튼 생성 
		btnOk.setBounds(97, 212, 97, 23);
		btnOk.addActionListener(this);		// 확인 버튼에 액션리스너 
		
		btnCancel = new JButton("취소");		// 취소버튼 생성 
		btnCancel.setBounds(240, 212, 97, 23);	// 취소 버튼에 액션리스너
		btnCancel.addActionListener(this);
							
		lblNewPw = new JLabel("새 패스워드 : ");		// 비밀번호 변경 레이블 생성 
		lblNewPw.setBounds(83, 122, 77, 15);
		
		lblVerifyPw = new JLabel("패스워드 확인 : ");	// 비밀번호 확인 레이블 생성 
		lblVerifyPw.setBounds(83, 157, 105, 15);
		
		txtNewPw = new JTextField();	// 비밀번호 변경 텍스트필드 설정 
		txtNewPw.setColumns(10);
		txtNewPw.setBounds(196, 119, 152, 21);
		
		txtVerifyPw = new JTextField();	// 비밀번호 확인 텍스트필드 생성 
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
			// 1. 확인버튼 눌렀을 때 값이 일치하면 비밀번호 변경 가능 
			//  1-1. 새비밀번호나 비밀번호확인 텍스트필드가 빈칸이면 비밀번호 입력 요청
			//		1-1-1. 두 필드 중 하나만 빈칸인 경우
			//		1-1-2. 두 필드 모두 빈칸인 경우 
			//	1-2. 새비밀번호와 비밀번호확인 텍스트필드가 불일치하면 불일치 메세지 
			//  1-3. 새비밀번호와 비밀번호확인 텍스트필드가 일치하면 비밀번호 변경 
			// 2. 확인버튼 눌렀을 때 값이 불일치하면 "일치x" 메세지 띄우기 
			
			if ( (txtEmployeeId.getText().equals(eDto.getEmployeeId())) && (txtQuestion.getText().equals(eDto.getPassword()))
					&& (txtAnswer.getText().equals(eDto.getLostIdAnswer()) ))  {		
			
						CreatePw(toIntId);
		
				
			} else if ( !(txtEmployeeId.getText().equals(eDto.getEmployeeId())) || !(txtQuestion.getText().equals(eDto.getLostIdQuestion()))		// 정보가 일치하지 않으면 관리자문의 메세지 
					|| !(txtAnswer.getText().equals(eDto.getLostIdAnswer())) ) {	
				JOptionPane.showMessageDialog(null, "정보가 일치하지 않습니다 \n 관리자에게 문의하세요");
			}
			
		} else if (e.getSource().equals(btnCancel)) {	// 취소버튼 클릭시 닫기 
				dispose();
		}
		
		
	}
	
	private void CreatePw(int empId) {
		eDto = dao.getEmployee(empId);
			
					if( ( (txtNewPw.getText().equals("")) && (txtVerifyPw.getText().equals("")))){	// 두 텍스트필드를 공백으로 두었을 때  
						JOptionPane.showMessageDialog(null, "변경할 비밀번호를 입력하세요");	
						
					} else if ( (txtNewPw.getText().equals("")) || (txtVerifyPw.getText().equals(""))) {
						JOptionPane.showMessageDialog(null, "변경할 비밀번호를 입력하세요");	// 하나의 텍스트필드만 공백으로 두었을 때 
						
					} else if (!(txtNewPw.getText().equals(txtVerifyPw.getText()))) {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다 \n 다시 입력하세요"); // 새비밀번호와 비밀번호확인이 일치하지 않을 때 
						
					} else if ((txtNewPw.getText().equals(txtVerifyPw.getText()))) {	// 비밀번호 변경 가능
						JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다");
	
						eDto.setPassword(txtNewPw.getText()); // 변경된 값 DB로 전송 
						dispose();	// 변경 후 닫기 
						
					}
		

			
		
	}
}
