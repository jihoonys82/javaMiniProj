package injung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	
	private JLabel lblGuide = new JLabel("파일 또는 폴더를 선택한 후 내보내기 버튼을 눌러주세요.");
	
	private JLabel lblEmployee = new JLabel("Employee 데이터 가져오기 (EmployeeBackup.dat)");
	private JTextField txtEmp;
	private JButton btnEmpChoose = new JButton("파일 선택");
	private JButton btnEmpSubmit = new JButton("가져오기");
	
	private JLabel lblTeam = new JLabel("Team 데이터 가져오기 (TeamBackup.dat)");
	private JTextField txtTeam;
	private JButton btnTeamChoose = new JButton("파일 선택");
	private JButton btnTeamSubmit = new JButton("가져오기");
	
	private JLabel lblPhoto = new JLabel("사진 데이터 가져오기 (Photo Folder)");
	private JTextField txtPhoto;
	private JButton btnPhotoChoose = new JButton("폴더 선택");
	private JButton btnPhotoSubmit = new JButton("가져오기");
	
	
	private JFileChooser empChooser;
	private JFileChooser teamChooser;
	private JFileChooser photoChooser;
	
	private File dir = new File(PropertiesLoad.getProperties().getProperty("LOCATION"));
	private File dirPhoto = new File(dir, "Photo");
	private File empBackup = new File(dir, "EmployeeBackup.dat");
	private File teamBackup = new File(dir, "TeamBackup.dat");
	
	private InJungDao dao = InJungDao.getInstance();
	private ArrayList<EmployeeDto> eDtos;
	private ArrayList<TeamDto> tDtos;
	
	public ImportDialog() {
		setTitle("가져오기");
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
				JOptionPane.showMessageDialog(this, "Employee 가져오기에 성공했습니다.", "Success", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, "Employee 가져오기에 실패했습니다.", "Fail", JOptionPane.DEFAULT_OPTION);
			}
		}
		if(e.getSource().equals(btnTeamSubmit)) {
			int rtn = importTeam();
			
			if(rtn!=0) {
				JOptionPane.showMessageDialog(this, "Team 가져오기에 성공했습니다.", "Success", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, "Team 가져오기에 실패했습니다.", "Fail", JOptionPane.DEFAULT_OPTION);
			}
		}
		if(e.getSource().equals(btnPhotoSubmit)) {
			int rtn = importPhoto();
			
			if(rtn!=0) {
				JOptionPane.showMessageDialog(this, "Photo 가져오기에 성공했습니다.", "Success", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, "Photo 가져오기에 실패했습니다.", "Fail", JOptionPane.DEFAULT_OPTION);
			}
		}
		
	}
	
	private int importEmp() {
		int rtn = 0;
		empBackup = new File(txtEmp.getText());
		
		if(!empBackup.exists()) return 0; 
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null; 
		
		eDtos = new ArrayList<>();
		
		try {
			fis = new FileInputStream(empBackup);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
			eDtos = (ArrayList<EmployeeDto>) ois.readObject();
			
			for(EmployeeDto e : eDtos) {
				if(!dao.isExist(e.getEmployeeId())) {
					dao.insertEmployee(e);
					rtn++;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ois!=null) ois.close();
				if(bis!=null) bis.close();
				if(fis!=null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return rtn;
	}

	private int importTeam() {
		int rtn = 0;
		teamBackup = new File(txtTeam.getText());
		
		if(!teamBackup.exists()) return 0; 
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null; 
		
		eDtos = new ArrayList<>();
		
		try {
			fis = new FileInputStream(teamBackup);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
			tDtos = (ArrayList<TeamDto>) ois.readObject();
			
			for(TeamDto t : tDtos) {
				if(!dao.isExist(t.getTeamName())) {
					dao.insertTeam(t);
					rtn++;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ois!=null) ois.close();
				if(bis!=null) bis.close();
				if(fis!=null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return rtn;
	}

	private int importPhoto() {
		int rtn = 0; 
		dirPhoto = new File(txtPhoto.getText());
		String[] imgExtension = {"jpg", "jpeg", "png", "bmp", "gif", "tiff"};
		File[] photoFiles = dirPhoto.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				for(String img : imgExtension) {
					if( name.endsWith("."+img)) {
						return true;
					}
				}
				return false;
			}
		});
		
		for(File photo : photoFiles) {
			FileSender sender = new FileSender(photo);
			sender.start();
			rtn ++;
		}
		
		return rtn;
	}
}
