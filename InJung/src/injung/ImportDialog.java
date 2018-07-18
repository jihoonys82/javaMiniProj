package injung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import injung.model.EmployeeDto;
import injung.model.InJungDao;
import injung.model.TeamDto;

public class ImportDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = -735159468639716345L;

	private JPanel importPanel;
	
	private JLabel lblGuide = new JLabel("���� �Ǵ� ������ ������ �� �������� ��ư�� �����ּ���.");
	
	private JLabel lblEmployee = new JLabel("Employee ������ �������� (EmployeeBackup.dat)");
	private JTextField txtEmp;
	private JButton btnEmpChoose = new JButton("���� ����");
	private JButton btnEmpSubmit = new JButton("��������");
	
	private JLabel lblTeam = new JLabel("Team ������ �������� (TeamBackup.dat)");
	private JTextField txtTeam;
	private JButton btnTeamChoose = new JButton("���� ����");
	private JButton btnTeamSubmit = new JButton("��������");
	
	private JLabel lblPhoto = new JLabel("���� ������ �������� (Photo Folder)");
	private JTextField txtPhoto;
	private JButton btnPhotoChoose = new JButton("���� ����");
	private JButton btnPhotoSubmit = new JButton("��������");
	
	
	private JFileChooser empChooser;
	private JFileChooser teamChooser;
	private JFileChooser photoChooser;
	
	private File dir = new File(PropertiesLoad.getProperties().getProperty("LOCATION"));
	private File dirPhoto = new File(dir, "Photo/");
	private File empBackup = new File(dir, "EmployeeBackup.dat");
	private File teamBackup = new File(dir, "TeamBackup.dat");
	
	private InJungDao dao = InJungDao.getInstance();
	private EmployeeDto eDto;
	private ArrayList<EmployeeDto> eDtos;
	private TeamDto tDto;
	private ArrayList<TeamDto> tDtos;
	
	public ImportDialog() {
		setTitle("��������");
		setModal(true);
		
		importPanel = new JPanel();
		getContentPane().add(importPanel, BorderLayout.CENTER);
		importPanel.setLayout(null);
		
		lblGuide.setBounds(12, 10, 410, 15);
		importPanel.add(lblGuide);
		
		lblEmployee.setBounds(12, 57, 288, 15);
		importPanel.add(lblEmployee);
		
		txtEmp = new JTextField(empBackup.getAbsolutePath());
		txtEmp.setBounds(22, 82, 200, 21);
		importPanel.add(txtEmp);
		txtEmp.setColumns(10);
		
		btnEmpChoose.setBounds(222, 81, 101, 23);
		btnEmpChoose.addActionListener(this);
		importPanel.add(btnEmpChoose);
		
		btnEmpSubmit.setBounds(325, 81, 97, 23);
		btnEmpSubmit.addActionListener(this);
		importPanel.add(btnEmpSubmit);
		
		lblTeam.setBounds(12, 125, 260, 15);
		importPanel.add(lblTeam);
		
		txtTeam = new JTextField(teamBackup.getAbsolutePath());
		txtTeam.setBounds(22, 150, 200, 21);
		importPanel.add(txtTeam);
		txtTeam.setColumns(10);
		
		btnTeamChoose.setBounds(222, 149, 101, 23);
		btnTeamChoose.addActionListener(this);
		importPanel.add(btnTeamChoose);
		
		btnTeamSubmit.setBounds(325, 149, 97, 23);
		btnTeamSubmit.addActionListener(this);
		importPanel.add(btnTeamSubmit);
		
		lblPhoto.setBounds(12, 192, 260, 15);
		importPanel.add(lblPhoto);
		
		txtPhoto = new JTextField(dirPhoto.getAbsolutePath());
		txtPhoto.setColumns(10);
		txtPhoto.setBounds(22, 217, 200, 21);
		importPanel.add(txtPhoto);
		
		btnPhotoChoose.setBounds(222, 216, 101, 23);
		btnPhotoChoose.addActionListener(this);
		importPanel.add(btnPhotoChoose);
		
		btnPhotoSubmit.setBounds(325, 216, 97, 23);
		btnPhotoSubmit.addActionListener(this);
		importPanel.add(btnPhotoSubmit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnEmpChoose)) {
			empChooser = new JFileChooser(empBackup);
			empChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat File", "dat");
			empChooser.setFileFilter(filter);
			
			int rtn = empChooser.showOpenDialog(this);
			if(rtn==JFileChooser.APPROVE_OPTION) {
				txtEmp.setText(empChooser.getSelectedFile().getAbsolutePath());
			}
		}
		if(e.getSource().equals(btnTeamChoose)) {
			teamChooser = new JFileChooser(teamBackup);
			teamChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat File", "dat");
			teamChooser.setFileFilter(filter);
			
			int rtn = teamChooser.showOpenDialog(this);
			if(rtn==JFileChooser.APPROVE_OPTION) {
				txtTeam.setText(teamChooser.getSelectedFile().getAbsolutePath());
			}
		}
		if(e.getSource().equals(btnPhotoChoose)) {
			photoChooser = new JFileChooser(dirPhoto);
			photoChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int rtn = photoChooser.showOpenDialog(this);
			if(rtn==JFileChooser.APPROVE_OPTION) {
				txtPhoto.setText(photoChooser.getSelectedFile().getAbsolutePath());
			}
		}
		if(e.getSource().equals(btnEmpSubmit)) {
			int rtn = importEmp();
			
			if(rtn!=0) {
				JOptionPane.showMessageDialog(this, "Employee �������⿡ �����߽��ϴ�.", "Success", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, "Employee �������⿡ �����߽��ϴ�.", "Fail", JOptionPane.DEFAULT_OPTION);
			}
		}
		if(e.getSource().equals(btnTeamSubmit)) {
			int rtn = importTeam();
			
			if(rtn!=0) {
				JOptionPane.showMessageDialog(this, "Team �������⿡ �����߽��ϴ�.", "Success", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, "Team �������⿡ �����߽��ϴ�.", "Fail", JOptionPane.DEFAULT_OPTION);
			}
		}
		if(e.getSource().equals(btnPhotoSubmit)) {
			int rtn = importPhoto();
			
			if(rtn!=0) {
				JOptionPane.showMessageDialog(this, "Photo �������⿡ �����߽��ϴ�.", "Success", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, "Photo �������⿡ �����߽��ϴ�.", "Fail", JOptionPane.DEFAULT_OPTION);
			}
		}
		
	}
	
	private int importEmp() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int importTeam() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int importPhoto() {
		// TODO Auto-generated method stub
		return 0;
	}
}