package injung;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import injung.model.EmployeeDto;
import injung.model.InJungDao;
import injung.model.TeamDto;

/**
 * Employee Add and Edit Panel
 * @since 2018-07-01
 * @author Jihoon Jeong
 *
 */
public class EditEmployeePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -8685746161617403122L;
	
		//main panels
		private JPanel photoPane 	= new JPanel();
		private JPanel editInfoPane	= new JPanel();
		private JPanel buttonPane 	= new JPanel();
		
		// sub panels for editInfoPane
		private JPanel employeeIdPane 	= new JPanel();
		private JPanel passwordPane		= new JPanel();
		private JPanel pwConfirmPane	= new JPanel();
		private JPanel pwLostQeustionPane	= new JPanel();
		private JPanel pwLostAnswerPane	= new JPanel();
		private JPanel namePane			= new JPanel();
		private JPanel birthPane 		= new JPanel();
		private JPanel teamPane			= new JPanel();
		private JPanel levelPane		= new JPanel();
		private JPanel rolePane 		= new JPanel();
		private JPanel mobilePane 		= new JPanel();
		private JPanel workPhonePane 	= new JPanel();
		private JPanel emailPane 		= new JPanel();
		private JPanel locationPane 	= new JPanel();

		// Labels
		private JLabel lblPhoto 	= new JLabel("Photo");
		private JLabel lblEmployeeId= new JLabel("사번");
		private JLabel lblPassword	= new JLabel("패스워드");
		private JLabel lblPwConfirm	= new JLabel("패스워드 확인");
		private JLabel lblLostQuestion	= new JLabel("분실 확인 질문");
		private JLabel lblLostAnswer= new JLabel("분실 확인 답변");
		private JLabel lblName 		= new JLabel("이름");
		private JLabel lblBirth 	= new JLabel("생일");
		private JLabel lblTeam 		= new JLabel("팀");
		private JLabel lblLevel 	= new JLabel("직급");
		private JLabel lblRole 		= new JLabel("역할");
		private JLabel lblMobile 	= new JLabel("휴대전화");
		private JLabel lblWorkPhone = new JLabel("업무전화");
		private JLabel lblEmail 	= new JLabel("이메일");
		private JLabel lblLocation 	= new JLabel("업무위치");
		
		
		// TextFields
		private JTextField txtEmployeeId 	= new JTextField();
		private JPasswordField txtPassword  = new JPasswordField();
		private JPasswordField txtPwConfirm = new JPasswordField();
		private JTextField txtLostQuestion	= new JTextField();
		private JTextField txtLostAnswer	= new JTextField();
		private JTextField txtName			= new JTextField();
		private JTextField txtRole 			= new JTextField();
		private JTextField txtLocation 		= new JTextField();
		private JFormattedTextField txtBirth	 = new JFormattedTextField();
		private JFormattedTextField txtMobile 	 = new JFormattedTextField();
		private JFormattedTextField txtWorkPhone = new JFormattedTextField();
		private JFormattedTextField txtEmail 	 = new JFormattedTextField();
		
		private MaskFormatter birthFormat 		= null;
		private MaskFormatter mobileFormat 		= null;
		private MaskFormatter workPhoneFormat 	= null;
		private MaskFormatter emailFormat 		= null;
		
		private JTextArea txtWarning		= new JTextArea("모든 정보를 빠짐없이 기입해 주세요.");
		
		// ComboBoxes 
		private JComboBox<String> cbTeam 	= new JComboBox<>();
		private JComboBox<String> cbLevel	= new JComboBox<>();
		
		// FileChooser(for photo upload)
		private JFileChooser fileChooser = new JFileChooser();
		private File photoFile;
		
		// Buttons
		private JButton btnPhotoUpload	= new JButton("사진 업로드...");
		private JButton btnConfirm		= new JButton("확인");
		private JButton btnCancel 		= new JButton("취소");
		
		// DTOs
		private InJungDao dao = InJungDao.getInstance();
		private EmployeeDto eDto; 
		private ArrayList<TeamDto> tDtos;
		private final String[] level = {"인턴", "사원", "대리", "과장", "차장", "부장", "이사", "대표이사"};
		
		/**
		 * Edit Employ Panel for new employee
		 */
		public EditEmployeePanel() {
			
			// set up main Panel
			setLayout(null);
			
			// Initialize photoPane
			initPhoto();
			
			// Initialize detailInfoPane
			initEditInfo();
			
			// Initialize buttonPane
			initButton();	
			
			// Initialize cbTeam, cbLevel
			initCheckBoxes();
		}

		public EditEmployeePanel(int empId) {
			// set up Panel
			this();
			
			displayEmpInfo(empId);
			
		}

		/**
		 * setup photoNamePane
		 */
		private void initPhoto() {
			//photoNamePane setting
			photoPane.setBounds(12, 5, 200, 520);
			photoPane.setLayout(null);
			lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
			
			//lblPhoto
			lblPhoto.setIcon(new ImageIcon("./Photo/no_avatar.jpg"));
			lblPhoto.setBounds(24, 10, 150, 200);
			
			//btnPhotoUpload
			btnPhotoUpload.setBounds(12, 220, 176, 23);
			btnPhotoUpload.addActionListener(this);
			
			//add components
			photoPane.add(lblPhoto);
			photoPane.add(btnPhotoUpload);
			
			add(photoPane);
		}
		
		/**
		 * setup editInfoPane
		 */
		private void initEditInfo() {
			//detailInfoPane setting
			editInfoPane.setBounds(224, 5, 455, 520);
			editInfoPane.setLayout(null);
			
			//employeeIdPane
			employeeIdPane.setBounds(12, 10, 430, 31);
			employeeIdPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblEmployeeId.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEmployeeId.setPreferredSize(new Dimension(100,12));
			txtEmployeeId.setColumns(25);
			txtEmployeeId.setEditable(false);
			
			//passwordPane
			passwordPane.setBounds(12, 46, 430, 31);
			passwordPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setPreferredSize(new Dimension(100, 12));
			txtPassword.setColumns(25);
			
			//pwConfirmPane
			pwConfirmPane.setBounds(12, 82, 430, 31);
			pwConfirmPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblPwConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPwConfirm.setPreferredSize(new Dimension(100, 12));
			txtPwConfirm.setColumns(25);
			
			//pwLostQuestionPane
			pwLostQeustionPane.setBounds(12, 118, 430, 31);
			pwLostQeustionPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblLostQuestion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLostQuestion.setPreferredSize(new Dimension(100, 12));
			txtLostQuestion.setColumns(25);
			
			//pwLostAnswerPane
			pwLostAnswerPane.setBounds(12, 154, 430, 31);
			pwLostAnswerPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblLostAnswer.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLostAnswer.setPreferredSize(new Dimension(100, 12));
			txtLostAnswer.setColumns(25);
			
			//namePane
			namePane.setBounds(12, 190, 430, 31);
			namePane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblName.setPreferredSize(new Dimension(100, 12));
			txtName.setColumns(25);
			
			//birthPane
			birthPane.setBounds(12, 226, 430, 31);
			birthPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblBirth.setHorizontalAlignment(SwingConstants.RIGHT);
			lblBirth.setPreferredSize(new Dimension(100, 12));
			txtBirth.setColumns(25);
			
			//teamPane
			teamPane.setBounds(12, 262, 430, 31);
			teamPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblTeam.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTeam.setPreferredSize(new Dimension(100, 12));
			cbTeam.setBounds(97, 297, 250, 15); 
			cbTeam.addItem("");
			
			//levelPane
			levelPane.setBounds(12, 298, 430, 31);
			levelPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblLevel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLevel.setPreferredSize(new Dimension(100, 12));
			cbLevel.setBounds(97, 338, 250, 15); 
			cbLevel.addItem("");
			
			//rolePane
			rolePane.setBounds(12, 334, 430, 31);
			rolePane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRole.setPreferredSize(new Dimension(100, 12));
			txtRole.setColumns(25);
			
			//mobilePane
			mobilePane.setBounds(12, 370, 430, 31);
			mobilePane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblMobile.setHorizontalAlignment(SwingConstants.RIGHT);
			lblMobile.setPreferredSize(new Dimension(100, 12));
			txtMobile.setColumns(25);
			
			//workPhonePane
			workPhonePane.setBounds(12, 406, 430, 31);
			workPhonePane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblWorkPhone.setHorizontalAlignment(SwingConstants.RIGHT);
			lblWorkPhone.setPreferredSize(new Dimension(100, 12));
			txtWorkPhone.setColumns(25);
			
			//emailPane
			emailPane.setBounds(12, 441, 430, 31);
			emailPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEmail.setPreferredSize(new Dimension(100, 12));
			txtEmail.setColumns(25);
			
			//locationPane
			locationPane.setBounds(12, 477, 430, 31);
			locationPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lblLocation.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLocation.setPreferredSize(new Dimension(100, 12));
			txtLocation.setColumns(25);
			
			// set MaskFormatter
			try {
				birthFormat = new MaskFormatter("####.##.##"); //yy.mm.dd
				mobileFormat = new MaskFormatter("###-####-####");
				workPhoneFormat = new MaskFormatter("##-####-####");
				emailFormat = new MaskFormatter("?@?");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtBirth.setValue(birthFormat);
			txtMobile.setValue(mobileFormat);
			txtWorkPhone.setValue(workPhoneFormat);
			txtEmail.setValue(emailFormat);
			
			//add components
			employeeIdPane.add(lblEmployeeId);
			employeeIdPane.add(txtEmployeeId);
			
			passwordPane.add(lblPassword);
			passwordPane.add(txtPassword);
			
			pwConfirmPane.add(lblPwConfirm);
			pwConfirmPane.add(txtPwConfirm);
			
			pwLostQeustionPane.add(lblLostQuestion);
			pwLostQeustionPane.add(txtLostQuestion);
			
			pwLostAnswerPane.add(lblLostAnswer);
			pwLostAnswerPane.add(txtLostAnswer);

			namePane.add(lblName);
			namePane.add(txtName);

			birthPane.add(lblBirth);
			birthPane.add(txtBirth);

			teamPane.add(lblTeam);
			teamPane.add(cbTeam);

			levelPane.add(lblLevel);
			levelPane.add(cbLevel); 
			
			rolePane.add(lblRole);
			rolePane.add(txtRole);
			
			mobilePane.add(lblMobile);
			mobilePane.add(txtMobile);
				
			workPhonePane.add(lblWorkPhone);
			workPhonePane.add(txtWorkPhone);
					
			emailPane.add(lblEmail);
			emailPane.add(txtEmail);
					
			locationPane.add(lblLocation);
			locationPane.add(txtLocation);
			
			editInfoPane.add(employeeIdPane);
			editInfoPane.add(passwordPane);
			editInfoPane.add(pwConfirmPane);
			editInfoPane.add(pwLostQeustionPane);
			editInfoPane.add(pwLostAnswerPane);
			editInfoPane.add(namePane);
			editInfoPane.add(birthPane);
			editInfoPane.add(teamPane);
			editInfoPane.add(levelPane);
			editInfoPane.add(rolePane);
			editInfoPane.add(mobilePane);
			editInfoPane.add(workPhonePane);
			editInfoPane.add(emailPane);
			editInfoPane.add(locationPane);
			
			add(editInfoPane);
		}

		/**
		 * setup buttonPane
		 */
		private void initButton() {
			// setup buttonPane
			buttonPane.setBounds(691, 5, 277, 520);
			buttonPane.setLayout(null);
			
			// txtWarning 
			txtWarning.setBounds(12, 10, 253, 350);
			txtWarning.setLineWrap(true);
			txtWarning.setWrapStyleWord(true);
			txtWarning.setEditable(false);
			txtWarning.append("모든 필드는 필수 항목입니다.");
			
			// buttons
			btnConfirm.setBounds(12, 454, 158, 23);
			btnCancel.setBounds(12, 487, 158, 23);
			
			btnConfirm.addActionListener(this);
			btnCancel.addActionListener(this);
			
			// add components
			buttonPane.add(txtWarning);
			buttonPane.add(btnConfirm);
			buttonPane.add(btnCancel);
			add(buttonPane);
		}
		
		/*
		 * setup Check boxes
		 */
		private void initCheckBoxes() {
			//set Team
			tDtos = dao.getAllTeam();
			for(TeamDto dto : tDtos) {
				cbTeam.addItem(dto.getTeamName());
			}
			//set Level
			for(String lv: level) {
				cbLevel.addItem(lv);
			}
		}
		
		/**
		 * Get Data from DAO and Set to panel
		 * @param empId
		 */
		private void displayEmpInfo(int empId) {
			eDto = dao.getEmployee(empId);
			
			txtEmployeeId.setText(((Integer)eDto.getEmployeeId()).toString());
			txtPassword.setText("");
			txtPwConfirm.setText("");
			txtLostQuestion.setText(eDto.getLostIdQuestion());
			txtLostAnswer.setText(eDto.getLostIdAnswer());
			txtName.setText(eDto.getName());
			txtBirth.setText(eDto.getBirth());
			txtRole.setText(eDto.getRole());
			txtMobile.setText(eDto.getMobile());
			txtWorkPhone.setText(eDto.getWorkPhone());
			txtEmail.setText(eDto.geteMail());
			txtLocation.setText(eDto.getLocation());
			
			cbTeam.setSelectedItem(eDto.getTeam());
			cbLevel.setSelectedItem(eDto.getLevel());
			
			lblPhoto.setText(eDto.getPhoto());
			lblPhoto.setIcon(new ImageIcon("./Photo/"+eDto.getPhoto()));
		}
		
		/**
		 * Clear All textField
		 */
		private void claearField() {
			txtEmployeeId.setText("");
			txtPassword.setText("");
			txtPwConfirm.setText("");
			txtLostQuestion.setText("");
			txtLostAnswer.setText("");
			txtName.setText("");
			txtBirth.setText("");
			txtRole.setText("");
			txtMobile.setText("");
			txtWorkPhone.setText("");
			txtEmail.setText("");
			txtLocation.setText("");
			lblPhoto.setIcon(new ImageIcon("./Photo/no_avatar.jpg"));
		}
	
		/**
		 * Declare Button Actions
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnConfirm)) {
				
				//Check PW
				if(txtPassword.getPassword().toString()!=txtPwConfirm.getPassword().toString() || 
						txtPassword.getPassword().toString()!=eDto.getPassword()) {
					JOptionPane.showMessageDialog(
							btnConfirm, 
							"패스워드가 일치하지 않습니다.", 
							"패스워드 확인", 
							JOptionPane.ERROR_MESSAGE
					);
				} else {
					// field data validation 
					int valid  = fieldValidation();
					
					if(valid==0 && txtEmployeeId.getText().trim()=="") {
						// Create (If empID is NOT exist)
						
					} else if(valid==0 && txtEmployeeId.getText().trim()!="") {
						// Update (If empId is exist)	
					}
					 
					// TODO: Photo Upload function
				}
			} else if(e.getSource().equals(btnCancel)) {
				String[] options = {"확인", "취소"}; 
				int selected =JOptionPane.showOptionDialog(
						buttonPane, 
						"모든 필드의 내용이 지워집니다. 진행하시겠습니까?", 
						"Confirm", 
						JOptionPane.OK_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, 
						null, options, options[1]
				);
				if(selected==0) claearField();
			} else if(e.getSource().equals(btnPhotoUpload)) {
				fileChooser.setFileFilter(new FileNameExtensionFilter("ImageFiles", ImageIO.getReaderFileSuffixes()));
				int returnVal = fileChooser.showOpenDialog(EditEmployeePanel.this);
				
				if(returnVal==JFileChooser.APPROVE_OPTION) {
					photoFile = fileChooser.getSelectedFile();
					lblPhoto.setIcon(new ImageIcon(photoFile.getPath()));
				}
			}	
		}

		/**
		 * Field Validation 
		 * @return If all field are valid return 0. <br> The figure means the number of invalid items 
		 */
		private int fieldValidation() {
			int valid = 0; 
			
			if(lblPhoto.getText()=="Photo") {
				txtWarning.append("사진이 없습니다.");
				valid++;
			}
			if(txtName.getText().length()<3 || txtName.getText().length()>20) {
				txtWarning.append("이름이 너무 짧거나 깁니다. 2~10자 내로 입력하세요.");
				valid++;
			}
			if(txtPassword.getPassword().length<6) {
				txtWarning.append("패스워드는 반드시 6자 이상이어야 합니다.");
				valid++;
			}
			if(txtLostQuestion.getText().trim()=="" || txtLostAnswer.getText().trim()=="" ) {
				txtWarning.append("패스워드 분실 질문과 대답을 입력해 주세요.");
				valid++;
			}			
			return valid;
		}
}
