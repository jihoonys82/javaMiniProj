package injung;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/*
 * �������� : 2018.07.08
 * 
 * ������ : �ǹ���
 * 
 * - ���� �����Ӱ� �ٸ� ������ ����(JMenuItem) - 1��
 * - ���� �����Ӱ� �ٸ� ������ ����(JMenuItem) - 2��
 */

public class MainFrame extends JFrame implements ActionListener{ // �׼� ������ ���

	/**
	 * 
	 */
	private static final long serialVersionUID = 7649255430561976296L;


	//��Ʈ �����̳� ������ �ʿ��� ����
	private Container root; // ��Ʈ �����̳�
	
	//ù ��° �ǳ�
	

	//�޴��� ����
	private JMenuBar menuBar;
	
	//�޴��� - File
	private JMenu file;
	private JMenuItem file_LogInOut;
	private JMenuItem file_Import;
	private JMenuItem file_Export;
	private JMenuItem file_Config;
	private JMenuItem file_Close;
	//�޴��� - Record
	private JMenu record;
	private JMenuItem record_NewEmployee;
	private JMenuItem record_NewTeamManage;
	//�޴��� - View
	private JMenu view;
	private JMenuItem view_3View;
	private JMenuItem view_PhotoView;
	private JMenuItem view_WholeView;
	private JMenuItem view_TeamView;
	//�޴��� - Calendar
	private JMenu calendar;
	private JMenuItem calendar_PersonView;
	private JMenuItem calendar_TeamView;
	private JMenuItem calendar_WholeView;
	//�޴��� - Help
	private JMenu help;
	private JMenuItem help_Quiz;
	private JMenuItem help_Help;
	private JMenuItem help_Credit;
	private JMenuItem help_About;
	
	public MainFrame() {
		
		//���� ������ ����
		setTitle("���� ������");
		setBounds(300, 100, 1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//�޴��� ����
		initMenu();
		
		//��Ʈ�����̳� ����
		initRootContainer();

		
		setVisible(true);
		
	}
	
	private void initMenu() {
		
		menuBar = new JMenuBar();
		
		
		//file �޴�
		file = new JMenu("File");
		
		file_LogInOut = new JMenuItem("�α���/�α׾ƿ�(L)");
		file_Import = new JMenuItem("��������...(I)");
		file_Export = new JMenuItem("��������...(E)");
		file_Config = new JMenuItem("ȯ�漳��(O)");
		file_Close = new JMenuItem("����(X)");

		file.add(file_LogInOut);
		file.addSeparator(); //��輱�߰�
		file.add(file_Import);
		file.add(file_Export);
		file.addSeparator(); //��輱�߰�
		file.add(file_Config);
		file.addSeparator(); //��輱�߰�
		file.add(file_Close);
		
		// ActionListener //
		file_LogInOut.addActionListener(this);
		file_Import.addActionListener(this);
		file_Export.addActionListener(this);
		file_Config.addActionListener(this);
		file_Close.addActionListener(this);
		////////////////////
		
		menuBar.add(file);
		
		
		//Record �޴�
		record = new JMenu("Record");
		
		record_NewEmployee = new JMenuItem("�űԵ��(N)");
		record_NewTeamManage = new JMenuItem("��������(M)");
		
		record.add(record_NewEmployee);
		record.addSeparator(); //��輱�߰�
		record.add(record_NewTeamManage);
		
		// ActionListener //
		record_NewEmployee.addActionListener(this);
		record_NewTeamManage.addActionListener(this);
		////////////////////
		
		menuBar.add(record);
		
		
		//View �޴�
		view = new JMenu("View");
		
		view_3View = new JMenuItem("3�� ����(3)");
		view_PhotoView = new JMenuItem("���� ����(P)");
		view_WholeView = new JMenuItem("��ü����Ʈ ����(W)");
		view_TeamView = new JMenuItem("���� ����(T)");
	    
		view.add(view_3View);
		view.add(view_PhotoView);
		view.add(view_WholeView);
		view.add(view_TeamView);
		
		// ActionListener //
		view_3View.addActionListener(this);
		view_PhotoView.addActionListener(this);
		view_WholeView.addActionListener(this);
		view_TeamView.addActionListener(this);
		////////////////////
		
		menuBar.add(view);
		
		
		// Calendar �޴�
		calendar = new JMenu("Calendar");
		
		calendar_PersonView = new JMenuItem("���� ��������(C)");
		calendar_TeamView = new JMenuItem("�� ��������(E)");
		calendar_WholeView = new JMenuItem("��� ��������(I)");
		
		calendar.add(calendar_PersonView);
		calendar.add(calendar_TeamView);
		calendar.addSeparator(); //��輱�߰�
		calendar.add(calendar_WholeView);
		
		// ActionListener //
		calendar_PersonView.addActionListener(this);
		calendar_TeamView.addActionListener(this);
		calendar_WholeView.addActionListener(this);
		////////////////////
		
		menuBar.add(calendar);
		
	
		// Help �޴�
		help = new JMenu("Help");
		
		help_Quiz = new JMenuItem("����!(Q)");
		help_Help = new JMenuItem("����(H)");
		help_Credit = new JMenuItem("ũ����(R)");
		help_About = new JMenuItem("����?�������� ���Ͽ�(A)");
		
		help.add(help_Quiz);
		help.addSeparator(); //��輱�߰�
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
		
		setJMenuBar(menuBar); //�����ӿ� �޴��� �����ϱ�
		
		//���Ű
		file_LogInOut.setMnemonic(KeyEvent.VK_L); 
		file_Import.setMnemonic(KeyEvent.VK_I);
		file_Export.setMnemonic(KeyEvent.VK_E);
		file_Config.setMnemonic(KeyEvent.VK_O);
		file_Close.setMnemonic(KeyEvent.VK_X);
				
		record_NewEmployee.setMnemonic(KeyEvent.VK_N);
		record_NewTeamManage.setMnemonic(KeyEvent.VK_M);
				
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
		
				
		//����Ű
		file_LogInOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.ALT_MASK));
		file_Import.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.ALT_MASK));
		file_Export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.ALT_MASK));
		file_Config.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_MASK));
		file_Close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_MASK));
				
		record_NewEmployee.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.ALT_MASK));
		record_NewTeamManage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.ALT_MASK));
				
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
	
	// JMenuItem ���� ����
	@Override
	public void actionPerformed(ActionEvent e) {

		// file �޴� �̺�Ʈ
		// �α���/�α׾ƿ�
		if(e.getSource() == file_LogInOut) {
//			System.out.println("�α���/�α׾ƿ� ����");

			loginPanel logPan ;
			logPan = new loginPanel(this,"Login Dialog",true,getX()+50,getY()+50);
			logPan.setVisible(true);

//			int logckeck = logPan.isLoginCheck(); // NEED TO FIX !!! UNTIL 2018-07-11 
//			if(logckeck==loginPanel.LOGIN_SUCCESSED) {
////				System.out.println("loginPanel.isLogin() : " + logckeck);
//
//				root.removeAll();
//				
//				root.add(new EmployeeInfoPanel());
//				setTitle("�� ����");
//
//				root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
//				root.repaint(); // �ٽ� �׸���
//			}

		} 
		// ��������
		else if (e.getSource() == file_Import){
			root.removeAll();
//			root.add(comp); // �����̳� �ֱ�
			setTitle("��������");
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ��������
		else if (e.getSource() == file_Export) {
			root.removeAll();
//			root.add(comp); // �����̳� �ֱ�
			setTitle("��������");
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		} 
		// ȯ�漳��
		else if (e.getSource() == file_Config) {
			root.removeAll();
//			root.add(comp); // �����̳� �ֱ�
			setTitle("ȯ�漳��");
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		} 
		// ����
		else if (e.getSource() == file_Close) {
//			System.out.println("���α׷� ����");
			System.exit(0);
		} 

		// Record �޴� �̺�Ʈ
		// �űԵ��
		else if (e.getSource() == record_NewEmployee) {
			root.removeAll();
			
			root.add(new EditEmployeePanel()); // �����̳� �ֱ�
			setTitle("�űԵ��");
//			setBounds(300, 100, 1000, 700);
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ��������
		else if (e.getSource() == record_NewTeamManage) {
			root.removeAll();
			
			root.add(new TeamRecordPanel()); // �����̳� �ֱ�
			setTitle("��������");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}

		// View �޴� �̺�Ʈ
		// 3�� ����
		else if (e.getSource() == view_3View) {
			root.removeAll();
			
			root.add(new ThreeViewPanel()); // �����̳� �ֱ�
			setTitle("3�� ����");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ���� ����
		else if (e.getSource() == view_PhotoView) {
			root.removeAll();
			
			root.add(new photoViewPanel()); // �����̳� �ֱ�
			setTitle("���� ����");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ��ü����Ʈ ����
		else if (e.getSource() == view_WholeView) {
			root.removeAll();
			
			root.add(new WholeView()); // �����̳� �ֱ�
			setTitle("��ü����Ʈ ����");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ���� ����
		else if (e.getSource() == view_TeamView) {
			root.removeAll();
			
			root.add(new TeamViewPanel()); // �����̳� �ֱ�
			setTitle("���� ����");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}

		// Calendar �޴� �̺�Ʈ
		// ���� ��������
		else if (e.getSource() == calendar_PersonView) {
			root.removeAll();
			
//			root.add(comp); // �����̳� �ֱ�
			setTitle("���� ��������");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// �� ��������
		else if (e.getSource() == calendar_TeamView) {
			root.removeAll();
			
//			root.add(comp); // �����̳� �ֱ�
			setTitle("�� ��������");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ��� ��������
		else if (e.getSource() == calendar_WholeView) {
			root.removeAll();
			
//			root.add(comp); // �����̳� �ֱ�
			setTitle("��� ��������");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}

		// Help �޴� �̺�Ʈ
		// ����!
		else if (e.getSource() == help_Quiz) {
			root.removeAll();
			
			root.add(new QuizPanel()); // �����̳� �ֱ�
			setTitle("����!");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ����
		else if (e.getSource() == help_Help) {
			root.removeAll();
			
//			root.add(comp); // �����̳� �ֱ�
			setTitle("����");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ũ����
		else if (e.getSource() == help_Credit) {
			root.removeAll();
			
//			root.add(comp); // �����̳� �ֱ�
			setTitle("ũ����");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ����?�������� ���Ͽ�
		else if (e.getSource() == help_About) {
			root.removeAll();
			
//			root.add(comp); // �����̳� �ֱ�
			setTitle("����?�������� ���Ͽ�");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}

	} // actionPerformed end
	
	public static void main(String[] args) {
		new MainFrame();
		
	}
			
}
