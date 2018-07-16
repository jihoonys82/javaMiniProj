package injung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ExportDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = -7669431686412238822L;
	
	private JPanel exportPanel;
	
	private JLabel lblExport;
	private JCheckBox chkEmployee;
	private JCheckBox chkTeam;
	private JCheckBox chkPhoto;
	
	private JLabel lblFileType;
	private ButtonGroup btnGroup;
	private JRadioButton rbDat;
	private JRadioButton rbCsv;
	
	private JLabel lblFolder;
	private JTextField txtPath;
	private JButton btnSelectFolder;
	
	private JButton btnConfirm;
	private JButton btnCancel;
	
	private JFileChooser chooser;
	private File backDir = new File("c:/InjungBackup");
	private JLabel lblWarning;
	
	
	public ExportDialog() {
		setTitle("��������");
		
		exportPanel = new JPanel();
		getContentPane().add(exportPanel, BorderLayout.CENTER);
		exportPanel.setLayout(null);
		
		chkEmployee = new JCheckBox("���");
		chkEmployee.setBounds(32, 42, 115, 23);
		exportPanel.add(chkEmployee);
		
		chkTeam = new JCheckBox("��");
		chkTeam.setBounds(32, 67, 115, 23);
		exportPanel.add(chkTeam);
		
		chkPhoto = new JCheckBox("����");
		chkPhoto.setBounds(32, 92, 115, 23);
		exportPanel.add(chkPhoto);
		
		lblWarning = new JLabel("");
		lblWarning.setBounds(88, 121, 245, 15);
		exportPanel.add(lblWarning);
		
		lblFileType = new JLabel("���� ������ ����ּ���.");
		lblFileType.setBounds(241, 21, 169, 15);
		exportPanel.add(lblFileType);
		
		rbDat = new JRadioButton("��ü���� (.dat)");
		rbDat.setSelected(true);
		rbDat.setBounds(251, 42, 121, 23);
		exportPanel.add(rbDat);
		
		rbCsv = new JRadioButton("�������� (.csv)");
		rbCsv.setBounds(251, 67, 121, 23);
		exportPanel.add(rbCsv);
		
		btnGroup = new ButtonGroup(); // ��ü ���� �߰� : java.lang.NullPointerException
		btnGroup.add(rbDat);
		btnGroup.add(rbCsv);
		
		lblExport = new JLabel("������ ������ �����ϼ���.");
		lblExport.setBounds(22, 21, 169, 15);
		exportPanel.add(lblExport);
		
		txtPath = new JTextField(backDir.getAbsolutePath());
		txtPath.setBounds(35, 182, 245, 21);
		exportPanel.add(txtPath);
		txtPath.setColumns(10);
		
		btnSelectFolder = new JButton("Select Folder...");
		btnSelectFolder.setBounds(280, 181, 130, 23);
		exportPanel.add(btnSelectFolder);
		btnSelectFolder.addActionListener(this);
		
		lblFolder = new JLabel("������ ������ �������ּ���.");
		lblFolder.setBounds(22, 157, 169, 15);
		exportPanel.add(lblFolder);
		
		btnConfirm = new JButton("Ȯ��");
		btnConfirm.setBounds(110, 214, 97, 23);
		exportPanel.add(btnConfirm);
		btnConfirm.addActionListener(this);
		
		btnCancel = new JButton("���");
		btnCancel.setBounds(219, 214, 97, 23);
		exportPanel.add(btnCancel);
		btnConfirm.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals("btnSelectFolder")) {
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setCurrentDirectory(backDir);
			int returnVal = chooser.showOpenDialog(this);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				txtPath.setText(chooser.getSelectedFile().toString());
			} else {
				lblWarning.setText("������ ������ �ٽ� ������ �ּ���.");
			}
		} else if(e.getSource().equals("btnConfirm")) {
			String[] options = { "Ȯ��", "���" };
			int selected = JOptionPane.showOptionDialog(this, 
					txtPath.getText() + "�� ���õ� ������ �����ϴ�. �����ұ��?", 
					"�������� Ȯ��",
					JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, options, options[1]);
			if (selected == 0) { 
				System.out.println("Ȯ�� Ŭ����");
			}
		} else if(e.getSource().equals("btnCancel")) {
			this.removeAll();
			this.dispose();
		}
		
	}
}
