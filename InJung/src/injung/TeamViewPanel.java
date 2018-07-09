package injung;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
 
/**
+ * TemaViewPanel 
+ * employee Information by team 
+ * @since 2018-07-05
+ * @author Changhwan Bae
+ *
+ */
 public class TeamViewPanel extends JPanel implements ActionListener {
	 
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
	
	private JLabel lblTeamName 	  = new JLabel("����");
	
	
	// Text Fields
	private JTextField[] txtName	  = new JTextField[idx];
	private JTextField[] txtTeam	  = new JTextField[idx];
	private JTextField[] txtRole	  = new JTextField[idx];
	private JTextField[] txtMobile	  = new JTextField[idx];
	private JTextField[] txtWorkPhone = new JTextField[idx];
	private JTextField[] txtEmail 	  = new JTextField[idx];
	
	// Button
	private JButton[] btnDetailView = new JButton[idx];
	private JButton  btnPrivView	= new JButton("����");
	private JButton  btnNextView	= new JButton("����");
	
	// ComboBox
	Vector<String> teamList = new Vector<>();
	private JComboBox<String> cbTeam;
		
 	/**
 	 * constructor
 	 */
 	public TeamViewPanel() {
 		
 		setLayout(null);
 		setBounds(10, 5, 990, 580);
 		
 		// set combo box
		comboPane.setBounds(25,5,300,35);
		comboPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Test data
		teamList.add("������");
		teamList.add("����������");
		teamList.add("�ü�������");
		cbTeam = new JComboBox<>(teamList);
		
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
			lblName[i] = new JLabel("�̸�");
			lblName[i].setPreferredSize(new Dimension(60, 20));
			txtName[i] = new JTextField();
			txtName[i].setColumns(15);
			txtName[i].setEditable(false);
			
			// set teamPane
			teamPane[i] = new JPanel();
			teamPane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblTeam[i] = new JLabel("��");
			lblTeam[i].setPreferredSize(new Dimension(60, 20));
			txtTeam[i] = new JTextField();
			txtTeam[i].setColumns(15);
			txtTeam[i].setEditable(false);
			
			// set rolePane
			rolePane[i] = new JPanel();
			rolePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblRole[i] = new JLabel("��å");
			lblRole[i].setPreferredSize(new Dimension(60, 20));
			txtRole[i] = new JTextField();
			txtRole[i].setColumns(15);
			txtRole[i].setEditable(false);
			
			// set mobilePane
			mobilePane[i] = new JPanel();
			mobilePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblMobile[i] = new JLabel("�޴���ȭ");
			lblMobile[i].setPreferredSize(new Dimension(60, 20));
			txtMobile[i] = new JTextField();
			txtMobile[i].setColumns(15);
			txtMobile[i].setEditable(false);
			
			// set workphonePane
			workphonePane[i] = new JPanel();
			workphonePane[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			lblWorkPhone[i] = new JLabel("������ȭ");
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
			btnDetailView[i] = new JButton("�󼼺���");
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
		}
		
		// set pagePane  
		pagePane.setBounds(330,520,200,35);
		pagePane.setLayout(new FlowLayout());
		
		btnPrivView.addActionListener(this);
		btnNextView.addActionListener(this);
		
		pagePane.add(btnPrivView);
		pagePane.add(btnNextView);
							
		add(pagePane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cbTeam)) {
			
		} else if (e.getSource().equals(btnDetailView[0])) {
			
		} else if(e.getSource().equals(btnDetailView[1])) {
			
		} else if(e.getSource().equals(btnDetailView[2])) {
		
		} else if(e.getSource().equals(btnPrivView)) {
			
		} else if(e.getSource().equals(btnNextView)) {
			
		}
		
	}
 }