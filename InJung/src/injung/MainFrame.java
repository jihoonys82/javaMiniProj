package injung;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/* 
 * 수정일자 : 2018.07.17
 * 
 * 수정자 : 권미현
 * 
 *  - redirect 메소드 기능 추가 : EditEmployeePanel
 *  - 이달의 생일 기능 관련 메소드, 변수 등 삭제 
 *  	→ BirthdayPanel 클래스로 분리
 *  	→ 이달의 생일 기능을 BirthdayPanel 생성자로 부르기
 *  - 다이어로그 정리
 *  
 */

public class MainFrame extends JFrame implements ActionListener{ // 액션 리스너 상속

	private static final long serialVersionUID = 7649255430561976296L;
	
	// Login 
	private static int id = 0; // 아이디 받아올 값
	private static boolean login = false; // 로그인 체크

	// -------------------------------------------------------//

	//루트 컨테이너 구성에 필요한 변수
	private static Container root; // 루트 컨테이너
	

	//메뉴바 설정
	private JMenuBar menuBar;
	
	//메뉴바 - File
	private JMenu file;
	private JMenuItem file_LogInOut;
	private JMenuItem file_Import;
	private JMenuItem file_Export;
	private JMenuItem file_Config;
	private JMenuItem file_Close;
	//메뉴바 - Record
	private JMenu record;
	private JMenuItem record_NewEmployee;
	private JMenuItem record_NewTeamManage;
	//메뉴바 - View
	private JMenu view;
	private JMenuItem view_MyView;
	private JMenuItem view_3View;
	private JMenuItem view_PhotoView;
	private JMenuItem view_WholeView;
	private JMenuItem view_TeamView;
	//메뉴바 - Calendar
	private JMenu calendar;
	private JMenuItem calendar_PersonView;
	private JMenuItem calendar_TeamView;
	private JMenuItem calendar_WholeView;
	//메뉴바 - Help
	private JMenu help;
	private JMenuItem help_Quiz;
	private JMenuItem help_Help;
	private JMenuItem help_Credit;
	private JMenuItem help_About;
	
	//help메뉴 다이얼로그
	private JDialog dialog;
		
	public MainFrame() {
		
		//메인 프레임 설정
		setTitle("메인 프레임");
		
//		setBounds(300, 100, 1000, 650);
		
		setSize(1000, 650);
		
		//모니터 화면 중앙에 배치하기
		Dimension frameSize = getSize(); // 프레임 사이즈
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
		setLocation((screenSize.width - frameSize.width)/2, 
				(screenSize.height - frameSize.height)/2); // 화면 중앙
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//메뉴바 설정
		initMenu();
		
		//루트컨테이너 설정
		initRootContainer();
		
		setVisible(true);
			
	}
	
	private void initMenu() {
		
		menuBar = new JMenuBar();
		
		
		//file 메뉴
		file = new JMenu("File");
		
		file_LogInOut = new JMenuItem("로그인/로그아웃(L)");
		file_Import = new JMenuItem("가져오기...(I)");
		file_Export = new JMenuItem("내보내기...(E)");
		file_Config = new JMenuItem("환경설정(O)");
		file_Close = new JMenuItem("종료(X)");

		file.add(file_LogInOut);
		file.addSeparator(); //경계선추가
		file.add(file_Import);
		file.add(file_Export);
		file.addSeparator(); //경계선추가
		file.add(file_Config);
		file.addSeparator(); //경계선추가
		file.add(file_Close);
		
		// ActionListener //
		file_LogInOut.addActionListener(this);
		file_Import.addActionListener(this);
		file_Export.addActionListener(this);
		file_Config.addActionListener(this);
		file_Close.addActionListener(this);
		////////////////////
		
		menuBar.add(file);
		
		
		//Record 메뉴
		record = new JMenu("Record");
		
		record_NewEmployee = new JMenuItem("신규등록(N)");
		record_NewTeamManage = new JMenuItem("조직관리(M)");
		
		record.add(record_NewEmployee);
		record.addSeparator(); //경계선추가
		record.add(record_NewTeamManage);
		
		// ActionListener //
		record_NewEmployee.addActionListener(this);
		record_NewTeamManage.addActionListener(this);
		////////////////////
		
		menuBar.add(record);
//		record_NewEmployee.setEnabled(false);
		
		
		//View 메뉴
		view = new JMenu("View");
		
		view_MyView = new JMenuItem("내 정보 보기(1)");
		view_3View = new JMenuItem("3명 보기(3)");
		view_PhotoView = new JMenuItem("사진 보기(P)");
		view_WholeView = new JMenuItem("전체리스트 보기(W)");
		view_TeamView = new JMenuItem("팀별 보기(T)");
	    
		view.add(view_MyView);
		view.add(view_3View);
		view.add(view_PhotoView);
		view.add(view_WholeView);
		view.add(view_TeamView);
		
		// ActionListener //
		view_MyView.addActionListener(this);
		view_3View.addActionListener(this);
		view_PhotoView.addActionListener(this);
		view_WholeView.addActionListener(this);
		view_TeamView.addActionListener(this);
		////////////////////
		
		menuBar.add(view);
		view_MyView.setEnabled(false);
		
		
		// Calendar 메뉴
		calendar = new JMenu("Calendar");
		
		calendar_PersonView = new JMenuItem("개인 일정보기(C)");
		calendar_TeamView = new JMenuItem("팀 일정보기(E)");
		calendar_WholeView = new JMenuItem("모든 일정보기(I)");
		
		calendar.add(calendar_PersonView);
		calendar.add(calendar_TeamView);
		calendar.addSeparator(); //경계선추가
		calendar.add(calendar_WholeView);
		
		// ActionListener //
		calendar_PersonView.addActionListener(this);
		calendar_TeamView.addActionListener(this);
		calendar_WholeView.addActionListener(this);
		////////////////////
		
		menuBar.add(calendar);
		
	
		// Help 메뉴
		help = new JMenu("Help");
		
		help_Quiz = new JMenuItem("퀴즈!(Q)");
		help_Help = new JMenuItem("도움말(H)");
		help_Credit = new JMenuItem("크레딧(R)");
		help_About = new JMenuItem("인정?어인정에 대하여(A)");
		
		help.add(help_Quiz);
		help.addSeparator(); //경계선추가
		help.add(help_Help);
		help.add(help_Credit);
		help.add(help_About);
		
		// ActionListener //
		help_Quiz.addActionListener(this);
		help_Help.addActionListener(this);
		help_Credit.addActionListener(this);
		help_About.addActionListener(this);
		////////////////////
		
		menuBar.add(help);
		
		setJMenuBar(menuBar); //프레임에 메뉴바 설정하기
		
		//기억키
		file_LogInOut.setMnemonic(KeyEvent.VK_L); 
		file_Import.setMnemonic(KeyEvent.VK_I);
		file_Export.setMnemonic(KeyEvent.VK_E);
		file_Config.setMnemonic(KeyEvent.VK_O);
		file_Close.setMnemonic(KeyEvent.VK_X);
				
		record_NewEmployee.setMnemonic(KeyEvent.VK_N);
		record_NewTeamManage.setMnemonic(KeyEvent.VK_M);
				
		view_MyView.setMnemonic(KeyEvent.VK_1);
		view_3View.setMnemonic(KeyEvent.VK_3);
		view_PhotoView.setMnemonic(KeyEvent.VK_P);
		view_WholeView.setMnemonic(KeyEvent.VK_W);
		view_TeamView.setMnemonic(KeyEvent.VK_T);

		calendar_PersonView.setMnemonic(KeyEvent.VK_C);
		calendar_TeamView.setMnemonic(KeyEvent.VK_E);
		calendar_WholeView.setMnemonic(KeyEvent.VK_I);
		
		help_Quiz.setMnemonic(KeyEvent.VK_Q);
		help_Help.setMnemonic(KeyEvent.VK_H);
		help_Credit.setMnemonic(KeyEvent.VK_R);
		help_About.setMnemonic(KeyEvent.VK_A);	
		
				
		//가속키
		file_LogInOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.ALT_MASK));
		file_Import.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.ALT_MASK));
		file_Export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.ALT_MASK));
		file_Config.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_MASK));
		file_Close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_MASK));
				
		record_NewEmployee.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.ALT_MASK));
		record_NewTeamManage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.ALT_MASK));
				
		view_MyView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.ALT_MASK));
		view_3View.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.ALT_MASK));
		view_PhotoView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_MASK));
		view_WholeView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.ALT_MASK));
		view_TeamView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_MASK));
				
		calendar_PersonView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.ALT_MASK));
		calendar_TeamView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.ALT_MASK));
		calendar_WholeView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.ALT_MASK));
		
		help_Quiz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_MASK));
		help_Help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.ALT_MASK));
		help_Credit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.ALT_MASK));
		help_About.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_MASK));
	
		
	}
	
	
	private void initRootContainer() {	
		
		root = getContentPane();
		
	}
	
	
	// JMenuItem 동작 설정
	@Override
	public void actionPerformed(ActionEvent e) {

		// file 메뉴 이벤트
		// 로그인/로그아웃
		if(e.getSource() == file_LogInOut) {

			if(login == false) {

				dialog = new LoginPanel(this,"Login Dialog",true);
				
				dialog.setVisible(true);

				id = ((LoginPanel) dialog).getTxtEmployeeId(); // LoginPanel에서 받아온 ID 값

				int logckeck = ((LoginPanel) dialog).isLoginCheck(id);
				if(logckeck==LoginPanel.LOGIN_SUCCESSED) {

					root.removeAll();
					
					root.add(new EmployeeInfoPanel(id));
					root.add(new BirthdayPanel(), BorderLayout.SOUTH); // 이달의 생일
					setTitle("내 정보");

					root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
					root.repaint(); // 다시 그리기

					view_MyView.setEnabled(true); // 내 정보 보기 MenuItem 활성화
					login = true;
					
				}
				
			} else if (login == true) {
				
				int logOut = logOut();
				
				if (logOut == 0) { // 확인 버튼
					root.removeAll();
					
					root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
					root.repaint(); // 다시 그리기
					
					view_MyView.setEnabled(false); // 내 정보 보기 MenuItem 비활성화
					login = false;

				} else if (logOut == 1) { // 취소 버튼
					
				}
				
			}

		} 
		// 가져오기
		else if (e.getSource() == file_Import){
			root.removeAll();
//			root.add(comp); // 컨테이너 넣기
			setTitle("가져오기");
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 내보내기
		else if (e.getSource() == file_Export) {
			
			dialog = new ExportDialog();
			dialog.setBounds(450, 250, 450, 300);
			
//			dialog.setSize(450, 300);
			
			dialog.setVisible(true);
			
		} 
		// 환경설정
		else if (e.getSource() == file_Config) {
			root.removeAll();
			
			root.add(new PropertyPanel()); // 컨테이너 넣기
			setTitle("환경설정");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		} 
		// 종료
		else if (e.getSource() == file_Close) {
			System.exit(0);
		} 

		// Record 메뉴 이벤트
		// 신규등록
		if (e.getSource() == record_NewEmployee) {
			root.removeAll();
			
			root.add(new EditEmployeePanel()); // 컨테이너 넣기
			setTitle("신규등록");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 조직관리
		else if (e.getSource() == record_NewTeamManage) {
			root.removeAll();
			
			root.add(new TeamRecordPanel()); // 컨테이너 넣기
			setTitle("조직관리");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}

		// View 메뉴 이벤트
		// 내 정보 보기
		if (e.getSource() == view_MyView) {
			root.removeAll();
			
			root.add(new EmployeeInfoPanel(id)); // 컨테이너 넣기
			root.add(new BirthdayPanel(), BorderLayout.SOUTH);; // 이달의 생일
			setTitle("내 정보 보기");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 3명 보기
		else if (e.getSource() == view_3View) {
			root.removeAll();
			
			root.add(new ThreeViewPanel()); // 컨테이너 넣기
			setTitle("3명 보기");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 사진 보기
		else if (e.getSource() == view_PhotoView) {
			root.removeAll();
			
			root.add(new PhotoViewPanel()); // 컨테이너 넣기
			setTitle("사진 보기");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 전체리스트 보기
		else if (e.getSource() == view_WholeView) {
			root.removeAll();
			
			root.add(new WholeView()); // 컨테이너 넣기
			setTitle("전체리스트 보기");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 팀별 보기
		else if (e.getSource() == view_TeamView) {
			root.removeAll();
			
			root.add(new TeamViewPanel()); // 컨테이너 넣기
			setTitle("팀별 보기");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}

		// Calendar 메뉴 이벤트
		// 개인 일정보기
		if (e.getSource() == calendar_PersonView) {
			root.removeAll();
			
//			root.add(comp); // 컨테이너 넣기
			setTitle("개인 일정보기");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 팀 일정보기
		else if (e.getSource() == calendar_TeamView) {
			root.removeAll();
			
//			root.add(comp); // 컨테이너 넣기
			setTitle("팀 일정보기");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 모든 일정보기
		else if (e.getSource() == calendar_WholeView) {
			root.removeAll();
			
//			root.add(comp); // 컨테이너 넣기
			setTitle("모든 일정보기");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}

		// Help 메뉴 이벤트
		// 퀴즈!
		if (e.getSource() == help_Quiz) {
			root.removeAll();
			
			root.add(new QuizPanel()); // 컨테이너 넣기
			setTitle("퀴즈!");
			
			root.validate(); // 컴포넌트 검증 (메모리 상태 확인) - 메모리 확실하게
			root.repaint(); // 다시 그리기
		}
		// 도움말
		else if (e.getSource() == help_Help) {
			dialog = new HelpDialog(
					this, 
					"도움말",
					true, 
					getX()+250,
					getY()+100);
				
			dialog.setVisible(true);
		}
		// 크레딧
		else if (e.getSource() == help_Credit) {
			
		}
		// 인정?어인정에 대하여
		else if (e.getSource() == help_About) {
			dialog = new AboutInjungDialog(
				this, 
				"인정?어인정에 대하여",
				true, 
				getX()+300,
				getY()+100);
			
			dialog.setVisible(true);
			
		}

	} // actionPerformed end
	
	
	// 로그인 후 로그아웃 시도할시 나타날 Dialog
	private int logOut() {
		
		Object[] options = {"확인", "취소"};
 		int yesNo = JOptionPane.showOptionDialog(
				root, 
				"현재 로그인 상태입니다. 로그아웃 하시겠습니까?", 
				"",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, 
				options,
				options[0]);
 		
 		if (yesNo == 0) {
 			JOptionPane.showMessageDialog(root,
 					"로그아웃 되었습니다.",
 					"",
 					JOptionPane.INFORMATION_MESSAGE);
 		}
 		
 		return yesNo;
 		
	}
	
	
	public static void redirect(String dest, int param) { // (연결할 panel 메시지, panel 에 받아온 id 값)
		
		if(dest.equals("EmployeeInfoPanel") && param!=0) { // && param!=0 : 빈 사원 정보를 불려오지 못하도록 막기
			
			root.removeAll();
			
			root.add(new EmployeeInfoPanel(param));
			root.add(new BirthdayPanel(), BorderLayout.SOUTH);; // 이달의 생일
			
			root.validate();
			root.repaint();
			
		} else if(dest.equals("EditEmployeePanel")) { // id값 받아와서 해당 id값의 개인정보 수정하는 페이지로 넘기기
			
			root.removeAll();
			
			root.add(new EditEmployeePanel(param));
			
			root.validate();
			root.repaint();
			
		}
			
	}
	
	public static void main(String[] args) {
		new MainFrame();
		
	}
			
}
