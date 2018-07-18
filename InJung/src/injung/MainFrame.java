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
 * �������� : 2018.07.17
 * 
 * ������ : �ǹ���
 * 
 *  - redirect �޼ҵ� ��� �߰� : EditEmployeePanel
 *  - �̴��� ���� ��� ���� �޼ҵ�, ���� �� ���� 
 *  	�� BirthdayPanel Ŭ������ �и�
 *  	�� �̴��� ���� ����� BirthdayPanel �����ڷ� �θ���
 *  - ���̾�α� ����
 *  
 */

public class MainFrame extends JFrame implements ActionListener{ // �׼� ������ ���

	private static final long serialVersionUID = 7649255430561976296L;
	
	// Login 
	private static int id = 0; // ���̵� �޾ƿ� ��
	private static boolean login = false; // �α��� üũ

	// -------------------------------------------------------//

	//��Ʈ �����̳� ������ �ʿ��� ����
	private static Container root; // ��Ʈ �����̳�
	

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
	private JMenuItem view_MyView;
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
	
	//help�޴� ���̾�α�
	private JDialog dialog;
		
	public MainFrame() {
		
		//���� ������ ����
		setTitle("���� ������");
		
//		setBounds(300, 100, 1000, 650);
		
		setSize(1000, 650);
		
		//����� ȭ�� �߾ӿ� ��ġ�ϱ�
		Dimension frameSize = getSize(); // ������ ������
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // ����� ������
		setLocation((screenSize.width - frameSize.width)/2, 
				(screenSize.height - frameSize.height)/2); // ȭ�� �߾�
		
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
//		record_NewEmployee.setEnabled(false);
		
		
		//View �޴�
		view = new JMenu("View");
		
		view_MyView = new JMenuItem("�� ���� ����(1)");
		view_3View = new JMenuItem("3�� ����(3)");
		view_PhotoView = new JMenuItem("���� ����(P)");
		view_WholeView = new JMenuItem("��ü����Ʈ ����(W)");
		view_TeamView = new JMenuItem("���� ����(T)");
	    
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
		
				
		//����Ű
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
	
	
	// JMenuItem ���� ����
	@Override
	public void actionPerformed(ActionEvent e) {

		// file �޴� �̺�Ʈ
		// �α���/�α׾ƿ�
		if(e.getSource() == file_LogInOut) {

			if(login == false) {

				dialog = new LoginPanel(this,"Login Dialog",true);
				
				dialog.setVisible(true);

				id = ((LoginPanel) dialog).getTxtEmployeeId(); // LoginPanel���� �޾ƿ� ID ��

				int logckeck = ((LoginPanel) dialog).isLoginCheck(id);
				if(logckeck==LoginPanel.LOGIN_SUCCESSED) {

					root.removeAll();
					
					root.add(new EmployeeInfoPanel(id));
					root.add(new BirthdayPanel(), BorderLayout.SOUTH); // �̴��� ����
					setTitle("�� ����");

					root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
					root.repaint(); // �ٽ� �׸���

					view_MyView.setEnabled(true); // �� ���� ���� MenuItem Ȱ��ȭ
					login = true;
					
				}
				
			} else if (login == true) {
				
				int logOut = logOut();
				
				if (logOut == 0) { // Ȯ�� ��ư
					root.removeAll();
					
					root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
					root.repaint(); // �ٽ� �׸���
					
					view_MyView.setEnabled(false); // �� ���� ���� MenuItem ��Ȱ��ȭ
					login = false;

				} else if (logOut == 1) { // ��� ��ư
					
				}
				
			}

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
			
			dialog = new ExportDialog();
			dialog.setBounds(450, 250, 450, 300);
			
//			dialog.setSize(450, 300);
			
			dialog.setVisible(true);
			
		} 
		// ȯ�漳��
		else if (e.getSource() == file_Config) {
			root.removeAll();
			
			root.add(new PropertyPanel()); // �����̳� �ֱ�
			setTitle("ȯ�漳��");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		} 
		// ����
		else if (e.getSource() == file_Close) {
			System.exit(0);
		} 

		// Record �޴� �̺�Ʈ
		// �űԵ��
		if (e.getSource() == record_NewEmployee) {
			root.removeAll();
			
			root.add(new EditEmployeePanel()); // �����̳� �ֱ�
			setTitle("�űԵ��");
			
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
		// �� ���� ����
		if (e.getSource() == view_MyView) {
			root.removeAll();
			
			root.add(new EmployeeInfoPanel(id)); // �����̳� �ֱ�
			root.add(new BirthdayPanel(), BorderLayout.SOUTH);; // �̴��� ����
			setTitle("�� ���� ����");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
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
			
			root.add(new PhotoViewPanel()); // �����̳� �ֱ�
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
		if (e.getSource() == calendar_PersonView) {
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
		if (e.getSource() == help_Quiz) {
			root.removeAll();
			
			root.add(new QuizPanel()); // �����̳� �ֱ�
			setTitle("����!");
			
			root.validate(); // ������Ʈ ���� (�޸� ���� Ȯ��) - �޸� Ȯ���ϰ�
			root.repaint(); // �ٽ� �׸���
		}
		// ����
		else if (e.getSource() == help_Help) {
			dialog = new HelpDialog(
					this, 
					"����",
					true, 
					getX()+250,
					getY()+100);
				
			dialog.setVisible(true);
		}
		// ũ����
		else if (e.getSource() == help_Credit) {
			
		}
		// ����?�������� ���Ͽ�
		else if (e.getSource() == help_About) {
			dialog = new AboutInjungDialog(
				this, 
				"����?�������� ���Ͽ�",
				true, 
				getX()+300,
				getY()+100);
			
			dialog.setVisible(true);
			
		}

	} // actionPerformed end
	
	
	// �α��� �� �α׾ƿ� �õ��ҽ� ��Ÿ�� Dialog
	private int logOut() {
		
		Object[] options = {"Ȯ��", "���"};
 		int yesNo = JOptionPane.showOptionDialog(
				root, 
				"���� �α��� �����Դϴ�. �α׾ƿ� �Ͻðڽ��ϱ�?", 
				"",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, 
				options,
				options[0]);
 		
 		if (yesNo == 0) {
 			JOptionPane.showMessageDialog(root,
 					"�α׾ƿ� �Ǿ����ϴ�.",
 					"",
 					JOptionPane.INFORMATION_MESSAGE);
 		}
 		
 		return yesNo;
 		
	}
	
	
	public static void redirect(String dest, int param) { // (������ panel �޽���, panel �� �޾ƿ� id ��)
		
		if(dest.equals("EmployeeInfoPanel") && param!=0) { // && param!=0 : �� ��� ������ �ҷ����� ���ϵ��� ����
			
			root.removeAll();
			
			root.add(new EmployeeInfoPanel(param));
			root.add(new BirthdayPanel(), BorderLayout.SOUTH);; // �̴��� ����
			
			root.validate();
			root.repaint();
			
		} else if(dest.equals("EditEmployeePanel")) { // id�� �޾ƿͼ� �ش� id���� �������� �����ϴ� �������� �ѱ��
			
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
