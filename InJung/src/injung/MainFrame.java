package injung;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//루트 컨테이너 구성에 필요한 변수
	private Container root; // 루트 컨테이너
	
	//첫 번째 판넬
	

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
	
	public MainFrame() {
		
		//메인 프레임 설정
		setTitle("메인 프레임");
		setBounds(300, 100, 1000, 600);
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
		
		file_LogInOut = new JMenuItem("로그인/로그아웃");
		file_Import = new JMenuItem("가져오기...");
		file_Export = new JMenuItem("내보내기...");
		file_Config = new JMenuItem("환경설정");
		file_Close = new JMenuItem("종료");

		file.add(file_LogInOut);
		file.addSeparator(); //경계선추가
		file.add(file_Import);
		file.add(file_Export);
		file.addSeparator(); //경계선추가
		file.add(file_Config);
		file.addSeparator(); //경계선추가
		file.add(file_Close);
		
		menuBar.add(file);
		
		
		//Record 메뉴
		record = new JMenu("Record");
		
		record_NewEmployee = new JMenuItem("신규등록");
		record_NewTeamManage = new JMenuItem("조직관리");
		
		record.add(record_NewEmployee);
		record.addSeparator(); //경계선추가
		record.add(record_NewTeamManage);
		
		menuBar.add(record);
		
		
		//View 메뉴
		view = new JMenu("View");
		
		view_3View = new JMenuItem("3명 보기");
		view_PhotoView = new JMenuItem("사진 보기");
		view_WholeView = new JMenuItem("전체리스트 보기");
		view_TeamView = new JMenuItem("팀별 보기");
	    
		view.add(view_3View);
		view.add(view_PhotoView);
		view.add(view_WholeView);
		view.add(view_TeamView);
		
		
		menuBar.add(view);
		
		
		// Calendar 메뉴
		calendar = new JMenu("Calendar");
		
		calendar_PersonView = new JMenuItem("개인 일정보기");
		calendar_TeamView = new JMenuItem("팀 일정보기");
		calendar_WholeView = new JMenuItem("모든 일정보기");
		
		calendar.add(calendar_PersonView);
		calendar.add(calendar_TeamView);
		calendar.addSeparator(); //경계선추가
		calendar.add(calendar_WholeView);
		
		menuBar.add(calendar);
		
	
		// Help 메뉴
		help = new JMenu("Help");
		
		help_Quiz = new JMenuItem("퀴즈!");
		help_Help = new JMenuItem("도움말");
		help_Credit = new JMenuItem("크레딧");
		help_About = new JMenuItem("인정?어인정에 대하여");
		
		help.add(help_Quiz);
		help.addSeparator(); //경계선추가
		help.add(help_Help);
		help.add(help_Credit);
		help.add(help_About);
		
		menuBar.add(help);
		
		setJMenuBar(menuBar); //프레임에 메뉴바 설정하기
		
		
		
		
	}
	
	private void initRootContainer() {
		
		
	}
	
	
	public static void main(String[] args) {
		new MainFrame();
		
	}
			
	

}
