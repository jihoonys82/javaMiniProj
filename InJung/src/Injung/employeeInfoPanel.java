package Injung;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


/**
 * Employee Information Panel
 * @since 2018-07-01
 * @author Jihoon Jeong
 *
 */
public class employeeInfoPanel extends JPanel {
	private static final long serialVersionUID = -8738983467475324121L;

	//main panels
	private JPanel photoNamePane 	= new JPanel();
	private JPanel detailInfoPane	= new JPanel();
	private JPanel buttonPane 		= new JPanel();
	
	// sub panels for detailInfoPane
	private JPanel employeeIdPane 	= new JPanel();
	private JPanel molbilePane 		= new JPanel();
	private JPanel workPhonePane 	= new JPanel();
	private JPanel emailPane 		= new JPanel();
	private JPanel locationPane 	= new JPanel();
	private JPanel rolePane 		= new JPanel();
	private JPanel birthPane 		= new JPanel();
	
	// Labels
	private JLabel lblPhoto 	= new JLabel("Photo");
	private JLabel lblTeam 		= new JLabel("Team");
	private JLabel lblLevel 	= new JLabel("Level");
	private JLabel lblName 		= new JLabel("Name");
	private JLabel lblEmployeeId = new JLabel("사번");
	private JLabel lblMobile 	= new JLabel("휴대전화");
	private JLabel lblWorkPhone = new JLabel("업무전화");
	private JLabel lblEmail 	= new JLabel("이메일");
	private JLabel lblLocation 	= new JLabel("업무위치");
	private JLabel lblRole 		= new JLabel("역할");
	private JLabel lblBirth 	= new JLabel("생일");
	
	// TextFields
	private JTextField txtEmployeeId 	= new JTextField();
	private JTextField txtMobile 		= new JTextField();
	private JTextField txtWorkPhone 	= new JTextField();
	private JTextField txtEmail 		= new JTextField();
	private JTextField txtLocation 		= new JTextField();
	private JTextField txtRole 			= new JTextField();
	private JTextField txtBirth 		= new JTextField();
	
	// Buttons
	JButton btnEditInfo 		= new JButton("Edit Info");
	JButton btnPasswordChange 	= new JButton("Password Change");
	
	public employeeInfoPanel() {
		
		// set up main Panel
		setLayout(null);
		
		// Initialize photoNamePane
		initPhotoName();
		
		// Initialize detailInfoPane
		initDetailInfo();
		
		// Initialize buttonPane
		initButton();		
		
		//disable all JTextFields
		disabledTxtFields();
	}

	/**
	 * setup photoNamePane
	 */
	private void initPhotoName() {
		//photoNamePane setting
		photoNamePane.setBounds(12, 10, 200, 300);
		photoNamePane.setLayout(null);
		
		//lblPhoto
		lblPhoto.setIcon(new ImageIcon("./Photo/no_avatar.jpg"));
		lblPhoto.setBounds(24, 10, 150, 200);
		
		//lblTeam
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setBounds(12, 220, 176, 15);
		
		//lblLevel
		lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLevel.setBounds(12, 245, 82, 15);
		
		//lblName
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(106, 245, 82, 15);
		
		//add components
		photoNamePane.add(lblPhoto);
		photoNamePane.add(lblTeam);
		photoNamePane.add(lblLevel);
		photoNamePane.add(lblName);
		
		add(photoNamePane);
		
	}
	
	/**
	 * setup detailInfoPane
	 */
	private void initDetailInfo() {
		//detailInfoPane setting
		detailInfoPane.setBounds(224, 10, 455, 300);
		detailInfoPane.setLayout(null);
		
		//employeeIdPane setting
		employeeIdPane.setBounds(12, 10, 430, 31);
		employeeIdPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblEmployeeId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmployeeId.setPreferredSize(new Dimension(50,12));
		txtEmployeeId.setText("10000");
		txtEmployeeId.setColumns(30);
		
		//mobilePane setting
		molbilePane.setBounds(12, 51, 430, 31);
		molbilePane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblMobile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMobile.setPreferredSize(new Dimension(50, 12));
		txtMobile.setText("010-0000-0000");
		txtMobile.setColumns(30);
		
		//workPhonePane
		workPhonePane.setBounds(12, 92, 430, 31);
		workPhonePane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblWorkPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWorkPhone.setPreferredSize(new Dimension(50, 12));
		txtWorkPhone.setText("02-000-0000");
		txtWorkPhone.setColumns(30);
		
		//emailPane
		emailPane.setBounds(12, 133, 430, 31);
		emailPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setPreferredSize(new Dimension(50, 12));
		txtEmail.setText("tester@ddbros.com");
		txtEmail.setColumns(30);
		
		//locationPane
		locationPane.setBounds(12, 174, 430, 31);
		locationPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblLocation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocation.setPreferredSize(new Dimension(50, 12));
		txtLocation.setText("KH 정보교육원 B강의실");
		txtLocation.setColumns(30);
		
		//rolePane
		rolePane.setBounds(12, 215, 430, 31);
		rolePane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRole.setPreferredSize(new Dimension(50, 12));
		txtRole.setText("\uAC04\uC2DD\uB2F4\uB2F9");
		txtRole.setColumns(30);
		
		//birthPane
		birthPane.setBounds(12, 256, 430, 31);
		birthPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirth.setPreferredSize(new Dimension(50, 12));
		txtBirth.setText("1990.01.01");
		txtBirth.setColumns(30);
		
		//add components
		employeeIdPane.add(lblEmployeeId);
		employeeIdPane.add(txtEmployeeId);
		
		molbilePane.add(lblMobile);
		molbilePane.add(txtMobile);
		
		workPhonePane.add(lblWorkPhone);
		workPhonePane.add(txtWorkPhone);
			
		emailPane.add(lblEmail);
		emailPane.add(txtEmail);
				
		locationPane.add(lblLocation);
		locationPane.add(txtLocation);
				
		rolePane.add(lblRole);
		rolePane.add(txtRole);
				
		birthPane.add(lblBirth);
		birthPane.add(txtBirth);
		
		detailInfoPane.add(employeeIdPane);
		detailInfoPane.add(molbilePane);
		detailInfoPane.add(workPhonePane);
		detailInfoPane.add(emailPane);
		detailInfoPane.add(locationPane);
		detailInfoPane.add(rolePane);
		detailInfoPane.add(birthPane);
		
		add(detailInfoPane);
	}

	/**
	 * setup buttonPane
	 */
	private void initButton() {
		// setup buttonPane
		buttonPane.setBounds(691, 10, 182, 300);
		buttonPane.setLayout(null);
		
		// buttons
		btnEditInfo.setBounds(12, 234, 158, 23);
		btnPasswordChange.setBounds(12, 267, 158, 23);
		
		// add components
		buttonPane.add(btnEditInfo);
		buttonPane.add(btnPasswordChange);
		add(buttonPane);
	}
	
	/**
	 * disabled all textFields
	 * JTextField.setEditable(false)
	 */
	private void disabledTxtFields() {
		// setup Editable
		txtEmployeeId.setEditable(false);
		txtMobile.setEditable(false);
		txtWorkPhone.setEditable(false);
		txtEmail.setEditable(false);
		txtLocation.setEditable(false);
		txtRole.setEditable(false);
		txtBirth.setEditable(false);
		
		// Refresh main panel ( just in case :) )
		validate();
		repaint();
	}
}
