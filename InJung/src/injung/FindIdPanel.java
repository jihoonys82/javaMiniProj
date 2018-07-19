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
 * 작성자: 송주현
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

		JPanel findIdPane = new JPanel(); // 비밀번호 찾기 패널 생성
		findIdPane.setBounds(0, 0, 434, 261);
		findIdPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("사번 :"); // 사번 레이블 생성
		lblEmployeeId.setBounds(83, 24, 57, 15);

		JLabel lblQuestion = new JLabel("분실 질문 :"); // 분실 질문 레이블 생성
		lblQuestion.setBounds(83, 55, 77, 15);

		JLabel lblAnswer = new JLabel("분실 대답 : "); // 분실 대답 레이블 생성
		lblAnswer.setBounds(83, 88, 77, 15);

		txtEmployeeId = new JTextField(); // 사번 텍스트필드 생성
		txtEmployeeId.setText(str);
		txtEmployeeId.setBounds(196, 21, 152, 21);
		txtEmployeeId.setColumns(10);
		txtEmployeeId.setEditable(false);
		
		txtQuestion = new JTextField(); // 질문 텍스트필드 생성
		txtQuestion.setColumns(10);
		txtQuestion.setBounds(196, 52, 152, 21);
		txtQuestion.setEditable(false);

		txtAnswer = new JTextField(); // 대답 텍스트필드 생성
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(196, 85, 152, 21);

		btnOk = new JButton("확인"); // 확인버튼 생성
		btnOk.setBounds(97, 212, 97, 23);
		btnOk.addActionListener(this); // 확인 버튼에 액션리스너

		btnCancel = new JButton("취소"); // 취소버튼 생성
		btnCancel.setBounds(240, 212, 97, 23); // 취소 버튼에 액션리스너
		btnCancel.addActionListener(this);

		lblNewPw = new JLabel("새 패스워드 : "); // 비밀번호 변경 레이블 생성
		lblNewPw.setBounds(83, 122, 77, 15);

		lblVerifyPw = new JLabel("패스워드 확인 : "); // 비밀번호 확인 레이블 생성
		lblVerifyPw.setBounds(83, 157, 105, 15);

		txtNewPw = new JPasswordField(); // 비밀번호 변경 텍스트필드 설정
		txtNewPw.setColumns(10);
		txtNewPw.setBounds(196, 119, 152, 21);

		txtVerifyPw = new JPasswordField(); // 비밀번호 확인 텍스트필드 생성
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

		int logid = Integer.parseInt(str);	// LoginPanel에서 전달 받은 ID텍스트필드값(사번)을 int형으로 변환
		showData(logid);	// 해당 질문에 대한 분실질문을 호출
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnOk)) { // 확인 버튼 클릭시 정보 일치 여부 확인
			String strId = txtEmployeeId.getText();
			int intId = Integer.parseInt(strId);
			eDto = dao.getEmployee(intId);
			String toString = String.valueOf(intId);
			
			if ((txtEmployeeId.getText().equals(toString)) && (txtQuestion.getText().equals(eDto.getLostIdQuestion()))
					&& (txtAnswer.getText().equals(eDto.getLostIdAnswer()))) { // 사번/분실질문/분실대답이 모두 일치하면 비밀번호 변경 가능

					createPw(intId);	// 비밀번호 변경 메소드 실행

			} else if (!(txtEmployeeId.getText().equals(toString))
					|| !(txtQuestion.getText().equals(eDto.getLostIdQuestion()))
					|| !(txtAnswer.getText().equals(eDto.getLostIdAnswer()))) { // 사번/분실질문/분실대답 하나라도 일치하지 않으면 관리자문의 메세지
				JOptionPane.showMessageDialog(null, "정보가 일치하지 않습니다 \n 관리자에게 문의하세요");
			}

		} else if (e.getSource().equals(btnCancel)) { // 취소버튼 클릭시 닫기
			dispose();
		}
	}

	private void createPw(int empId) {
		eDto = dao.getEmployee(empId);

		char[] chPw_1 = txtNewPw.getPassword(); // getPassword를 char[]에 담은 후 new String
		String strNewPw = new String(chPw_1);
		char[] chPw_2 = txtVerifyPw.getPassword();
		String strVerPw = new String(chPw_2);

		if ((strNewPw.equals("")) && (strVerPw.equals(""))) { // 두 텍스트필드를 공백으로 두었을 때
			JOptionPane.showMessageDialog(null, "변경할 비밀번호를 입력하세요");

		} else if ((!(strNewPw.length() > 0)) || (!(strVerPw.length() > 0))) { // 하나의 텍스트필드만 공백으로 두었을 때
			JOptionPane.showMessageDialog(null, "변경할 비밀번호를 입력하세요");

		} else if (!(strNewPw.equals(strVerPw))) { // 새비밀번호와 비밀번호확인이 일치하지 않을 때
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다 \n 다시 입력하세요");

		} else if (strNewPw.equals(strVerPw)) { // 비밀번호 변경 가능
			changeData(); // 비밀번호 데이터 변경 메소드 실행
			dispose(); // 변경 후 닫기

		}
	} // createPw

	private void changeData() { // DB로 데이터 전송하는 메소드
		int intId = Integer.parseInt(txtEmployeeId.getText());

		char[] chNewPw = txtNewPw.getPassword();
		String strNewPw = new String(chNewPw);

		int result = dao.setNewPassword(intId, strNewPw); // DAO에 ID와 새로운 PW 값 인자로 전달

		if (result == InJungDao.INSERT_DATA_SUCCESS) { // DB 값 전송 성공 여부
			JOptionPane.showMessageDialog(null, "비밀번호가  변경되었습니다");
		} else if (result == InJungDao.INSERT_DATA_FAILED) {
			JOptionPane.showMessageDialog(null, "비밀번호를 변경할 수 없습니다 \n 관리자에게 문의하세요");
		}

	} // changeData
	
	public void showData(int empId) {	// 전달 받은 사번 값을 인자로 해당 사번에 대한 DB의 분실 질문 호출  메소드 
		eDto = dao.getEmployee(empId);		
			
		if (eDto == null) {		// 없는 사번으로 비밀번호찾기를 시도하면 경고 다이얼로그 호출 
			JOptionPane.showMessageDialog(null,"없는 정보입니다");	
		} else {
			txtQuestion.setText(eDto.getLostIdQuestion());				
		}
		
	}	// showData
	

	@Override
	public void keyPressed(KeyEvent e) { // 엔터 기억키
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			String strId = txtEmployeeId.getText();	
			int id = Integer.parseInt(strId);
			eDto = dao.getEmployee(id);
			
			createPw(id);	// 비밀번호 변경 메소드 실행 
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }
}
