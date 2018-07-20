package injung;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class AboutInjungDialog extends JDialog implements ActionListener{
	
	/**
	 * 인정?어인정에 대하여
	 * 최근 작성일 : 7.17
	 * 작성자 : 이현우
	 */
	
	private static final long serialVersionUID = 1L;
	
	//컴포넌트 멤버필드
	JTextArea txtContent = null;
	JPanel btnPane = null;
	JTextArea txtMaker = null;
	JButton btnCancel = null;
	JLabel lblLogo = null;
	
	//생성자
	public AboutInjungDialog(Frame frame,String title,boolean modal,int x,int y) {
		
		//다이얼로그 설정값
		super(frame, title, modal);
		setLocation(x, y);
		setSize(400, 300);
		setLayout(null);
		
		
		//TxtContent 설정
		String txt = "\r\n    프로그램명 : 인정? 어인정\r\n"
					+ "    버전정보 : Injung Release(v1.0)\r\n"
					+ "    소개 : 본 프로그램은 조별 Mini Project\r\n "
					+ "             수행 결과물로 사원의 인사관리와 일정관리의\r\n"
					+ "              기능을 그룹 별로 다양하게 제공합니다\r\n";
		
		txtContent = new JTextArea();
		txtContent.setBounds(0, 0, 400, 130);
		txtContent.setColumns(20);
		txtContent.setRows(10);
		txtContent.setFont(new Font("고딕",Font.BOLD,14));
		txtContent.setEditable(false);
		txtContent.setBackground(new Color(245, 245, 245));
		txtContent.setText(txt);
		
		
		//btnPane 설정
		btnPane = new JPanel();
		btnPane.setLayout(null);
		btnPane.setBounds(0, 130, 400, 150);
		btnPane.setBackground(new Color(245, 245, 245));
		
		//txtMaker 설정
		txtMaker = new JTextArea();
		txt = " 제작 : 답답한남매들 ";
		txtMaker.setFont(new Font("고딕",Font.BOLD,14));
		txtMaker.setBounds(180, 20, 200, 30);
		txtMaker.setEditable(false);
		txtMaker.setBackground(new Color(245, 245, 245));
		txtMaker.setText(txt);
		
		//btnCancel 설정
		btnCancel = new JButton("닫기");		
		btnCancel.setBounds(280, 60, 80, 40);
				
		//lblLogo 설정
		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon("./photo/logo.jpg"));
		lblLogo.setBounds(5, 2, 120, 120);
		lblLogo.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		btnPane.add(btnCancel);
		btnPane.add(lblLogo);
		btnPane.add(txtMaker);

		add(txtContent);
		add(btnPane);
		
		btnCancel.addActionListener(this);		
	}

	//클릭 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		//버튼 닫기
		if(e.getSource()==btnCancel) {
			dispose();
		}
		
	}	
}
