package injung;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class ThreeViewPanel extends JPanel {
	private static final long serialVersionUID = -1740434906725933696L;
	private JTextField txtName;

	// declare memberField
	
	
	/**
	 * constructor
	 */
	public ThreeViewPanel() {
		setLayout(null);
		
		JPanel personPane = new JPanel();
//		JPanel[] personPane = new JPanel[3];
		
//		for(int i=0;i<personPane.length;i++) {
//			personPane[i] = new JPanel();
//			personPane[i].setBounds(12,100+ i*200,936,180);
//		}
//		personPane[0] = new JPanel();
//		personPane[0].setBounds(12,100,936,180);
		
		personPane.setBounds(12, 100, 936, 180);
		add(personPane);
		personPane.setLayout(null);
		
		JPanel infoPane = new JPanel();
		infoPane.setBounds(12, 10, 700, 160);
		personPane.add(infoPane);
		infoPane.setLayout(null);
		
		JPanel photoPane = new JPanel();
		photoPane.setBounds(0, 0, 150, 160);
		infoPane.add(photoPane);
		
		JLabel lblPhoto = new JLabel("Photo");
		photoPane.add(lblPhoto);
		
		JPanel personInfoPane = new JPanel();
		personInfoPane.setBounds(149, 10, 539, 140);
		infoPane.add(personInfoPane);
		personInfoPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel namePane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) namePane.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		personInfoPane.add(namePane);
		
		JLabel lblName = new JLabel("ÀÌ¸§");
		namePane.add(lblName);
		
		txtName = new JTextField();
		namePane.add(txtName);
		txtName.setColumns(18);
		
		JPanel teamPane = new JPanel();
		personInfoPane.add(teamPane);
		
		JPanel levelPane = new JPanel();
		personInfoPane.add(levelPane);
		
		JPanel panel_3 = new JPanel();
		personInfoPane.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		personInfoPane.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		personInfoPane.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		personInfoPane.add(panel_6);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(724, 10, 200, 160);
		personPane.add(buttonPane);
		buttonPane.setLayout(new BorderLayout(0, 0));
		
		JButton btnDetailView = new JButton("\uC0C1\uC138\uBCF4\uAE30");
		buttonPane.add(btnDetailView, BorderLayout.SOUTH);
		//Panel setting 
		
		
		
		
		
	}
}
