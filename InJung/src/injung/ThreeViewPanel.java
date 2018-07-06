package injung;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * ThreeViewPanel 
 * Show 3 employee Inforamtion 
 * @since 2018-07-05
 * @author Changhwan Bae
 *
 */
public class ThreeViewPanel extends JPanel {
	private static final long serialVersionUID = -1740434906725933696L;
	
	// index setting
	private final int idx = 3;
	
	// person Panes 
	private JPanel[] personPane = new JPanel[idx];
	
	// sub panes for personPane - infoPane, buttonPane
	private JPanel[] infoPane = new JPanel[idx];
	private JPanel[] buttonPane = new JPanel[idx];
	
	// sub panes for infoPane 
	private JPanel[] photoPane = new JPanel[idx];
	private JPanel[] personInfoPane = new JPanel[idx];
	
	// sub panes for personInfoPane
	private JPanel[] namePane = new JPanel[idx];
	private JPanel[] teamPane = new JPanel[idx];
	private JPanel[] rolePane = new JPanel[idx];
	private JPanel[] mobilePane = new JPanel[idx];
	private JPanel[] workphonePane = new JPanel[idx];
	private JPanel[] emailPane = new JPanel[idx];

	// Labels 
	private JLabel[] lblName = new JLabel[idx];
	private JLabel[] lblTeam = new JLabel[idx];
	private JLabel[] lblRole = new JLabel[idx];
	private JLabel[] lblMobile = new JLabel[idx];
	private JLabel[] lblWorkPhone = new JLabel[idx];
	private JLabel[] lblEmail = new JLabel[idx];
	private JLabel[] lblPhoto = new JLabel[idx];
	
	// Text Fields
	private JTextField[] txtName = new JTextField[idx];
	private JTextField[] txtTeam = new JTextField[idx];
	private JTextField[] txtRole = new JTextField[idx];
	private JTextField[] txtMobile = new JTextField[idx];
	private JTextField[] txtWorkPhone = new JTextField[idx];
	private JTextField[] txtEmail = new JTextField[idx];
	
	// Button
	private JButton[] btnDetailView = new JButton[idx];
	// TODO: add 2 buttons for priv and next
	
	/**
	 * constructor
	 */
	public ThreeViewPanel() {
		setLayout(null);
		
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
			
			// set teamPane
			teamPane[i] = new JPanel();
			teamPane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblTeam[i] = new JLabel("팀");
			lblTeam[i].setPreferredSize(new Dimension(60, 20));
			txtTeam[i] = new JTextField();
			txtTeam[i].setColumns(15);
			
			// set rolePane
			rolePane[i] = new JPanel();
			rolePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblRole[i] = new JLabel("직책");
			lblRole[i].setPreferredSize(new Dimension(60, 20));
			txtRole[i] = new JTextField();
			txtRole[i].setColumns(15);
			
			// set mobilePane
			mobilePane[i] = new JPanel();
			mobilePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblMobile[i] = new JLabel("휴대전화");
			lblMobile[i].setPreferredSize(new Dimension(60, 20));
			txtMobile[i] = new JTextField();
			txtMobile[i].setColumns(15);
			
			// set workphonePane
			workphonePane[i] = new JPanel();
			workphonePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblWorkPhone[i] = new JLabel("업무전화");
			lblWorkPhone[i].setPreferredSize(new Dimension(60, 20));
			txtWorkPhone[i] = new JTextField();
			txtWorkPhone[i].setColumns(15);
			
			// set emailPane
			emailPane[i] = new JPanel();
			emailPane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblEmail[i] = new JLabel("e-mail");
			lblEmail[i].setPreferredSize(new Dimension(60, 20));
			txtEmail[i] = new JTextField();
			txtEmail[i].setColumns(15);
			
			// set buttonPane
			buttonPane[i] = new JPanel();
			buttonPane[i].setBounds(724, 10, 150, 120);
			
			// set btnDetailView
			btnDetailView[i] = new JButton("상세보기");
			buttonPane[i].setLayout(new BorderLayout(0, 0));
			
			
			// add components 
			
			add(personPane[i]);
			
			
			personPane[i].add(infoPane[i]);
			
			infoPane[i].add(photoPane[i]);
			infoPane[i].add(personInfoPane[i]);
			
			photoPane[i].add(lblPhoto[i]);
			
			personInfoPane[i].add(namePane[i]);
			personInfoPane[i].add(teamPane[i]);
			personInfoPane[i].add(rolePane[i]);
			personInfoPane[i].add(mobilePane[i]);
			personInfoPane[i].add(workphonePane[i]);
			personInfoPane[i].add(emailPane[i]);
			
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
			
			personPane[i].add(buttonPane[i]);
			buttonPane[i].add(btnDetailView[i], BorderLayout.SOUTH);
			
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ThreeViewPanel());
		
		frame.setVisible(true);
	}
}
