package injung;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *  2018.07.05 
 *  작성자: 송주현 
 */

public class findIdPanel_1 extends JDialog {

	private static final long serialVersionUID = -1581889542704938075L;
	
	private JTextField txtEmployeeId;
	private JTextField txtQuestion;
	private JTextField txtAnswer;
	private JButton btnOk;
	private JButton btnCancel;
	
	public findIdPanel_1(JDialog dialog, String title, boolean modal) {
		
		super(dialog,title,true);
		
		getContentPane().setLayout(null);
		
		JPanel findIdPane = new JPanel();	// 비밀번호 찾기 패널 생성 
		findIdPane.setBounds(0, 0, 434, 261);
		findIdPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("사번 :");	// 사번 레이블 생성 
		lblEmployeeId.setBounds(72, 59, 57, 15);
		
		JLabel lblQuestion = new JLabel("분실 질문 :"); // 분실 질문 레이블 생성 
		lblQuestion.setBounds(72, 94, 77, 15);
		
		JLabel lblAnswer = new JLabel("분실 대답 : ");	// 분실 대답 레이블 생성 
		lblAnswer.setBounds(72, 134, 77, 15);
		
		txtEmployeeId = new JTextField();	// 사번 텍스트필드 생성 
		txtEmployeeId.setBounds(187, 56, 152, 21);
		txtEmployeeId.setColumns(10);
		
		txtQuestion = new JTextField();	// 질문 텍스트필드 생성 
		txtQuestion.setColumns(10);
		txtQuestion.setBounds(187, 91, 152, 21);
		
		txtAnswer = new JTextField();	// 대답 텍스트필드 생성 
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(187, 131, 152, 21);
		
		btnOk = new JButton("확인");		
		btnOk.setBounds(95, 191, 97, 23);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(242, 191, 97, 23);
		
			
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
}
