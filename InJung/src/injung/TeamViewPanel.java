
package injung;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import injung.model.EmployeeDto;
import injung.model.InJungDao;
import injung.model.TeamDto;
 
/**
+ * TemaViewPanel 
+ * employee Information by team 
+ * @since 2018-07-05
+ * @author Changhwan Bae
+ *
+ */
 public class TeamViewPanel extends JPanel implements ActionListener, ItemListener {
	 
 	private static final long serialVersionUID = -1740434906725933696L;

	// index setting
	private final int idx = 3;
	
	// person Panes 
	private JPanel[] personPane = new JPanel[idx];
	
	// sub panes for personPane - comboPane, infoPane, buttonPane
	private JPanel comboPane 		= new JPanel();
	private JPanel[] infoPane   = new JPanel[idx];
	private JPanel[] buttonPane = new JPanel[idx];
	
	// sub panes for infoPane 
	private JPanel[] photoPane	    = new JPanel[idx];
	private JPanel[] personInfoPane = new JPanel[idx];
	
	// sub panes for personInfoPane
	private JPanel[] namePane 		= new JPanel[idx];
	private JPanel[] teamPane	    = new JPanel[idx];
	private JPanel[] rolePane 		= new JPanel[idx];
	private JPanel[] mobilePane 	= new JPanel[idx];
	private JPanel[] workphonePane  = new JPanel[idx];
	private JPanel[] emailPane 		= new JPanel[idx];
	
	// sub panes for priv,next button
	private JPanel pagePane 		= new JPanel();
	
	// declare memberField
	// Labels 
	private JLabel[] lblName 	  = new JLabel[idx];
	private JLabel[] lblTeam 	  = new JLabel[idx];
	private JLabel[] lblRole   	  = new JLabel[idx];
	private JLabel[] lblMobile	  = new JLabel[idx];
	private JLabel[] lblWorkPhone = new JLabel[idx];
	private JLabel[] lblEmail	  = new JLabel[idx];
	private JLabel[] lblPhoto	  = new JLabel[idx];
	
	private JLabel lblTeamName 	  = new JLabel("팀명");
	
	
	// Text Fields
	private JTextField[] txtName	  = new JTextField[idx];
	private JTextField[] txtTeam	  = new JTextField[idx];
	private JTextField[] txtRole	  = new JTextField[idx];
	private JTextField[] txtMobile	  = new JTextField[idx];
	private JTextField[] txtWorkPhone = new JTextField[idx];
	private JTextField[] txtEmail 	  = new JTextField[idx];
	
	// Button
	private JButton[] btnDetailView = new JButton[idx];
	private JButton  btnPrivView	= new JButton("이전");
	private JButton  btnNextView	= new JButton("다음");
	private JLabel lblCurIdx = new JLabel("00");
	private JLabel lblMaxIdx = new JLabel("/00"); 
	
	// ComboBox
	private JComboBox<String> cbTeam;
	private String selectTeam;
	
	// Page indxing
	private InJungDao dao = InJungDao.getInstance();
	private TeamDto Tdto; 
	private ArrayList<TeamDto> Tdtos; 
	private EmployeeDto dto; 
	private ArrayList<EmployeeDto> dtos; 
	private int CountPage;
	private int Max_Page;
	private int[] lastEmpIds;
	
	// Files
//	private String photoPath = "./Photo/";
	private File dir = new File("./Photo");
	private File file;
	
		
 	/**
 	 * constructor
 	 */
 	public TeamViewPanel() {
 		ViewPanel();
	}

	private void ViewPanel() {
 		setLayout(null);
 		setBounds(10, 5, 990, 580);
 		
 		// set combo box
		comboPane.setBounds(25,5,300,35);
		comboPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Test data
		cbTeam = new JComboBox<>();
		cbTeam.addItem("");
		
		Tdtos = dao.getAllTeam();
		for(int i=0; i<Tdtos.size() ; i++) {
			Tdto = Tdtos.get(i);
			cbTeam.addItem(Tdto.getTeamName());
		}
		
		cbTeam.addItemListener(this);
		
		comboPane.add(lblTeamName);
		comboPane.add(cbTeam);
 		
		add(comboPane);
 		
		for(int i=0;i<idx;i++) {
			//set personPane 
			personPane[i] = new JPanel();
			personPane[i].setBounds(12,40 + i*160, 936, 160);
			personPane[i].setLayout(null);		
			
			//set infoPane
			infoPane[i] = new JPanel();
			infoPane[i].setBounds(12, 10, 700, 140);
			infoPane[i].setLayout(null);
			infoPane[i].setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			
			// set photoPane
			photoPane[i] = new JPanel();
			photoPane[i].setBounds(12, 10, 140, 120);
			lblPhoto[i] = new JLabel("Photo");
			
			// set personInfoPane
			personInfoPane[i] = new JPanel();
			personInfoPane[i].setBounds(149, 5, 539, 130);
			personInfoPane[i].setLayout(new GridLayout(0, 2, 0, 0));
			
			// set namePane
			namePane[i] = new JPanel();
			namePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblName[i] = new JLabel("이름");
			lblName[i].setPreferredSize(new Dimension(60, 20));
			txtName[i] = new JTextField();
			txtName[i].setColumns(15);
			txtName[i].setEditable(false);
			
			// set teamPane
			teamPane[i] = new JPanel();
			teamPane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblTeam[i] = new JLabel("팀");
			lblTeam[i].setPreferredSize(new Dimension(60, 20));
			txtTeam[i] = new JTextField();
			txtTeam[i].setColumns(15);
			txtTeam[i].setEditable(false);
			
			// set rolePane
			rolePane[i] = new JPanel();
			rolePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblRole[i] = new JLabel("직책");
			lblRole[i].setPreferredSize(new Dimension(60, 20));
			txtRole[i] = new JTextField();
			txtRole[i].setColumns(15);
			txtRole[i].setEditable(false);
			
			// set mobilePane
			mobilePane[i] = new JPanel();
			mobilePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblMobile[i] = new JLabel("휴대전화");
			lblMobile[i].setPreferredSize(new Dimension(60, 20));
			txtMobile[i] = new JTextField();
			txtMobile[i].setColumns(15);
			txtMobile[i].setEditable(false);
			
			// set workphonePane
			workphonePane[i] = new JPanel();
			workphonePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblWorkPhone[i] = new JLabel("업무전화");
			lblWorkPhone[i].setPreferredSize(new Dimension(60, 20));
			txtWorkPhone[i] = new JTextField();
			txtWorkPhone[i].setColumns(15);
			txtWorkPhone[i].setEditable(false);
			
			// set emailPane
			emailPane[i] = new JPanel();
			emailPane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblEmail[i] = new JLabel("e-mail");
			lblEmail[i].setPreferredSize(new Dimension(60, 20));
			txtEmail[i] = new JTextField();
			txtEmail[i].setColumns(15);
			txtEmail[i].setEditable(false);
			
			// set buttonPane
			buttonPane[i] = new JPanel();
			buttonPane[i].setBounds(724, 10, 150, 120);
			
			// set btnDetailView
			btnDetailView[i] = new JButton("상세보기");
			buttonPane[i].setLayout(new BorderLayout(0, 0));
		
			// add components 
			
			// add personPane
			add(personPane[i]);
			
			// add subPanes for personPane infoPane, photoPane, personInfoPane
			personPane[i].add(infoPane[i]);
			infoPane[i].add(photoPane[i]);
			infoPane[i].add(personInfoPane[i]);
			
			photoPane[i].add(lblPhoto[i]);
			
			// add subPanes for personInfoPane
			personInfoPane[i].add(namePane[i]);
			personInfoPane[i].add(teamPane[i]);
			personInfoPane[i].add(rolePane[i]);
			personInfoPane[i].add(mobilePane[i]);
			personInfoPane[i].add(workphonePane[i]);
			personInfoPane[i].add(emailPane[i]);
			
			// add lbl, txt for subPanes
			namePane[i].add(lblName[i]);
			namePane[i].add(txtName[i]);
			teamPane[i].add(lblTeam[i]);
			teamPane[i].add(txtTeam[i]);
			rolePane[i].add(lblRole[i]);
			rolePane[i].add(txtRole[i]);
			mobilePane[i].add(lblMobile[i]);
			mobilePane[i].add(txtMobile[i]);
			workphonePane[i].add(lblWorkPhone[i]);
			workphonePane[i].add(txtWorkPhone[i]);
			emailPane[i].add(lblEmail[i]);
			emailPane[i].add(txtEmail[i]);
			
			// add subPane(button)
			personPane[i].add(buttonPane[i]);
			
			// add buttons for subPane(button)
			buttonPane[i].add(btnDetailView[i], BorderLayout.SOUTH);
			btnDetailView[i].addActionListener(this);
			
		}
		
		// set pagePane  
		pagePane.setBounds(330,520,200,35);
		pagePane.setLayout(new FlowLayout());
		
		btnPrivView.addActionListener(this);
		btnNextView.addActionListener(this);
		
		pagePane.add(btnPrivView);
		pagePane.add(lblCurIdx);
		pagePane.add(lblMaxIdx);
		pagePane.add(btnNextView);
							
		add(pagePane);
 	}

 	private void setTeamPageIndex(String team) {
		int TempNum = dao.countEmployee(team);
		if(TempNum%idx==0) {
			Max_Page = TempNum/idx;
		} else {
			Max_Page = (TempNum/idx)+1;
		}
		CountPage = 1;
		lblCurIdx.setText( ((Integer)CountPage).toString() );
		lblMaxIdx.setText( "/" + ((Integer)Max_Page).toString() );
		
		lastEmpIds = new int[Max_Page];
		lastEmpIds[0] = 1;
	}
 	 
 	private void setTeamPageLoad(String team, int lastEmpId) {
 		if(lastEmpId>0) {
 			
			dtos = dao.getTeamEmployee(team, lastEmpId, idx);
		
			System.out.println("dtos.size:"+dtos.size() + ", CountPage:"+CountPage + ", MaxPage:" + Max_Page);
			for(int i=0; i<dtos.size() ; i++) {
				dto = dtos.get(i);
				txtName[i].setText(dto.getName());
				txtTeam[i].setText(dto.getTeam());
				txtRole[i].setText(dto.getRole());
				txtMobile[i].setText(dto.getMobile());
				txtWorkPhone[i].setText(dto.getWorkPhone());
				txtEmail[i].setText(dto.geteMail());
				
				file = new File(dir, dto.getPhoto());
				if (!file.exists()) {
					FileReceiver request = new FileReceiver(file, lblPhoto[i]);
					request.start();
					System.out.println(file.length() + " receive");
				} else {
					lblPhoto[i].setIcon(new ImageIcon(file.toString()));
				}
			}
			if(CountPage<Max_Page)
				lastEmpIds[CountPage] = dtos.get(idx-1).getEmployeeId();
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnDetailView[0])) {
			if(dtos.get(0).getName()!="-") {
				JDialog dialog = new DetailDialog(new EmployeeInfoPanel(dtos.get(0).getEmployeeId(), true));
				dialog.setLocationRelativeTo(this);
				dialog.setSize(700, 400);
				dialog.setVisible(true);
			}
			
		} else if(e.getSource().equals(btnDetailView[1])) {
			if(dtos.get(1).getName()!="-") {
				JDialog dialog = new DetailDialog(new EmployeeInfoPanel(dtos.get(1).getEmployeeId(), true));
				dialog.setLocationRelativeTo(this);
				dialog.setSize(700, 400);
				dialog.setVisible(true);
			}
			
		} else if(e.getSource().equals(btnDetailView[2])) {
			if(dtos.get(2).getName()!="-") {
				JDialog dialog = new DetailDialog(new EmployeeInfoPanel(dtos.get(2).getEmployeeId(), true));
				dialog.setLocationRelativeTo(this);
				dialog.setSize(700, 400);
				dialog.setVisible(true);
			}
		
		} else if(e.getSource().equals(btnPrivView)) {
			if(CountPage>1) {
				CountPage--;
				setTeamPageLoad(selectTeam, lastEmpIds[CountPage-1]);
				lblCurIdx.setText(((Integer)CountPage).toString());
				
				this.validate();
				this.repaint();
			}
		} else if(e.getSource().equals(btnNextView)) {
			if(CountPage<Max_Page) {
				CountPage++;
				setTeamPageLoad(selectTeam, lastEmpIds[CountPage-1]);
				lblCurIdx.setText(((Integer)CountPage).toString());
				
				this.validate();
				this.repaint();
				}
			}
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		System.out.println(1);
		selectTeam = cbTeam.getSelectedItem().toString();
		
		// 페이지 설정
		setTeamPageIndex(selectTeam);
		
		// 페이지 세팅
		setTeamPageLoad(selectTeam, 1);
		
	}

//	public static void main(String[] args) {
//	JFrame frame = new JFrame();
//	frame.setBounds(10, 10, 1000, 600);
//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	frame.add(new TeamViewPanel());
//		
//	frame.setVisible(true);
//	}
}
 