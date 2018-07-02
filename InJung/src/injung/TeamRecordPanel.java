package injung;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


public class TeamRecordPanel extends JPanel{

	//»ó¼ö
	private static final int PANEL1_Y=0;
	private static final int PANEL1_HEIGHT=50;
	private static final int PANEL1_WIDTH=980;

	private static final int PANEL2_Y=60;
	private static final int PANEL2_HEIGHT=300;
	private static final int PANEL2_WIDTH=980;

	private static final int PANEL3_Y=370;
	private static final int PANEL3_HEIGHT=155;
	private static final int PANEL3_WIDTH=660;
	
	private static final int PANEL4_X=670;
	private static final int PANEL4_Y=370;
	private static final int PANEL4_HEIGHT=155;
	private static final int PANEL4_WIDTH=310;
			
	private static final int PANEL_X=0;
	
	
	//Ã¹¹øÂ° ÆÇ³Ú
	private JPanel teamRecord_Pane1;
    private JLabel teamRecord_Pane1_Label1;
    private JLabel teamRecord_Pane1_Label2;
    
    //µÎ¹øÂ° ÆÇ³Ú
    private JPanel teamRecord_Pane2;
    private JList<String> teamRecord_Pane2_List1;
    private JList<String> teamRecord_Pane2_List2;
    //µÎ¹øÂ° - ½ºÅ©·Ñ ÆÇ³Ú
    private JScrollPane teamRecord_Pane2_Scroll1;
    private JScrollPane teamRecord_Pane2_Scroll2;
    
    //¼¼¹øÂ° ÆÇ³Ú
    private JPanel teamRecord_Pane3;
    private JLabel teamRecord_Pane3_Label1;
    private JLabel teamRecord_Pane3_Label2;
    private JLabel teamRecord_Pane3_Label3;
    private JTextField teamRecord_Pane3_TextField1;
    private JTextField teamRecord_Pane3_TextField2;
    private JTextField teamRecord_Pane3_TextField3;
    
    //³×¹øÂ° ÆÇ³Ú
    private JPanel teamRecord_Pane4;
    private JButton teamRecord_Pane4_Button1;
    private JButton teamRecord_Pane4_Button2;
    
    
    public TeamRecordPanel() {
	
    	setLayout(null);
    	
    	//ÆÇ³Ú1
    	
    	//ÆÇ³Ú1 - ¼³Á¤
    	teamRecord_Pane1 = new JPanel();
    	teamRecord_Pane1.setBounds(PANEL_X, PANEL1_Y, PANEL1_WIDTH, PANEL1_HEIGHT);
    	teamRecord_Pane1.setLayout(null);
    	teamRecord_Pane1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	//ÆÇ³Ú1 - ÄÁÆ÷³ÍÆ®
    	teamRecord_Pane1_Label1 = new JLabel("ºÎ ¼­");
    	teamRecord_Pane1_Label1.setFont(teamRecord_Pane1_Label1.getFont().deriveFont(20.0f));
    	teamRecord_Pane1_Label1.setBounds(new Rectangle(PANEL_X, PANEL1_Y, 300, PANEL1_HEIGHT));
    	teamRecord_Pane1_Label1.setHorizontalAlignment(JLabel.CENTER);
    	teamRecord_Pane1_Label1.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    	teamRecord_Pane1_Label2 = new JLabel("³» ¿ë");
    	teamRecord_Pane1_Label2.setFont(teamRecord_Pane1_Label2.getFont().deriveFont(20.0f));
    	teamRecord_Pane1_Label2.setBounds(new Rectangle(300, PANEL1_Y, 680, PANEL1_HEIGHT));
    	teamRecord_Pane1_Label2.setHorizontalAlignment(JLabel.CENTER);
    	teamRecord_Pane1_Label2.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    	teamRecord_Pane1.add(teamRecord_Pane1_Label1);
    	teamRecord_Pane1.add(teamRecord_Pane1_Label2);
    	
    	add(teamRecord_Pane1);
    	
    	//ÆÇ³Ú2
    	//ÆÇ³Ú2 - ¼³Á¤

    	teamRecord_Pane2 = new JPanel();
    	teamRecord_Pane2.setBounds(PANEL_X, PANEL2_Y, PANEL2_WIDTH, PANEL2_HEIGHT);
    	teamRecord_Pane2.setLayout(null);
    	teamRecord_Pane2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	//ÆÇ³Ú2 - ÄÄÆ÷³ÍÆ®
    	teamRecord_Pane2_List1 = new JList<>();
    	teamRecord_Pane2_List1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    	teamRecord_Pane2_List2 = new JList<>();
    	teamRecord_Pane2_List2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	
    	//ÆÇ³Ú2 - ½ºÅ©·Ñ ÆÇ³Ú
    	teamRecord_Pane2_Scroll1 = new JScrollPane(teamRecord_Pane2_List1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	teamRecord_Pane2_Scroll1.setBounds(5, 5, 295, 290);
    	teamRecord_Pane2_Scroll1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	
    	teamRecord_Pane2_Scroll2 = new JScrollPane(teamRecord_Pane2_List2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	teamRecord_Pane2_Scroll2.setBounds(302, 5, 675, 290);
    	teamRecord_Pane2_Scroll2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    	
    	teamRecord_Pane2.add(teamRecord_Pane2_Scroll1);
    	teamRecord_Pane2.add(teamRecord_Pane2_Scroll2);
    	add(teamRecord_Pane2);
    	
    		
    	//ÆÇ³Ú3
    	//ÆÇ³Ú3 - ¼³Á¤
    	
    	teamRecord_Pane3 = new JPanel();
    	teamRecord_Pane3.setBounds(PANEL_X, PANEL3_Y, PANEL3_WIDTH, PANEL3_HEIGHT);
    	teamRecord_Pane3.setLayout(null);
    	teamRecord_Pane3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    	
    	
    	//ÆÇ³Ú 3 - ÄÄÆ÷³ÍÆ®
    	
    	teamRecord_Pane3_Label1 = new JLabel("ºÎ ¼­");
    	teamRecord_Pane3_Label1.setFont(teamRecord_Pane3_Label1.getFont().deriveFont(18.0f));
    	teamRecord_Pane3_Label1.setBounds(new Rectangle(5, 5, 140, 45));
    	teamRecord_Pane3_Label1.setHorizontalAlignment(JLabel.CENTER);
    	teamRecord_Pane3_Label1.setBorder(BorderFactory.createLineBorder(Color.BLUE));   	
    	
    	teamRecord_Pane3_Label2 = new JLabel("Role");
    	teamRecord_Pane3_Label2.setFont(teamRecord_Pane3_Label2.getFont().deriveFont(18.0f));
    	teamRecord_Pane3_Label2.setBounds(new Rectangle(5, 55, 140, 45));
    	teamRecord_Pane3_Label2.setHorizontalAlignment(JLabel.CENTER);
    	teamRecord_Pane3_Label2.setBorder(BorderFactory.createLineBorder(Color.BLUE));   	
    	
    	teamRecord_Pane3_Label3 = new JLabel("Leader");
       	teamRecord_Pane3_Label3.setFont(teamRecord_Pane3_Label3.getFont().deriveFont(18.0f));
    	teamRecord_Pane3_Label3.setBounds(new Rectangle(5, 105, 140, 45));
    	teamRecord_Pane3_Label3.setHorizontalAlignment(JLabel.CENTER);
    	teamRecord_Pane3_Label3.setBorder(BorderFactory.createLineBorder(Color.BLUE));   	
    	
    	teamRecord_Pane3_TextField1 = new JTextField();
    	teamRecord_Pane3_TextField1.setBounds(150, 5, 500, 45);
       	teamRecord_Pane3_TextField1.setFont(teamRecord_Pane3_Label1.getFont());
		teamRecord_Pane3_TextField1.setEditable(true);
		teamRecord_Pane3_TextField1.setBackground(Color.white);
		teamRecord_Pane3_TextField1.setForeground(Color.BLACK);
    	
    	teamRecord_Pane3_TextField2 = new JTextField();
    	teamRecord_Pane3_TextField2.setBounds(150, 55, 500, 45);
       	teamRecord_Pane3_TextField2.setFont(teamRecord_Pane3_Label2.getFont());
		teamRecord_Pane3_TextField2.setEditable(true);
		teamRecord_Pane3_TextField2.setBackground(Color.white);
		teamRecord_Pane3_TextField2.setForeground(Color.BLACK);
    	
    	teamRecord_Pane3_TextField3 = new JTextField();
    	teamRecord_Pane3_TextField3.setBounds(150, 105, 500, 45);
       	teamRecord_Pane3_TextField3.setFont(teamRecord_Pane3_Label3.getFont());
		teamRecord_Pane3_TextField3.setEditable(true);
		teamRecord_Pane3_TextField3.setBackground(Color.white);
		teamRecord_Pane3_TextField3.setForeground(Color.BLACK);
    	

    	
    	teamRecord_Pane3.add(teamRecord_Pane3_Label1);
    	teamRecord_Pane3.add(teamRecord_Pane3_Label2);
    	teamRecord_Pane3.add(teamRecord_Pane3_Label3);
    	teamRecord_Pane3.add(teamRecord_Pane3_TextField1);
    	teamRecord_Pane3.add(teamRecord_Pane3_TextField2);
    	teamRecord_Pane3.add(teamRecord_Pane3_TextField3);


    	add(teamRecord_Pane3);
    	
    	//ÆÇ³Ú4
    	//ÆÇ³Ú4 - ¼³Á¤
       	teamRecord_Pane4 = new JPanel();
    	teamRecord_Pane4.setBounds(PANEL4_X, PANEL4_Y, PANEL4_WIDTH, PANEL4_HEIGHT);
    	teamRecord_Pane4.setLayout(null);
    	teamRecord_Pane4.setBorder(BorderFactory.createLineBorder(Color.BLUE));  	
    	
    	
    	//ÆÇ³Ú4 - ÄÄÆ÷³ÍÆ®
    	teamRecord_Pane4_Button1 = new JButton("»ý¼º/¼öÁ¤");
    	teamRecord_Pane4_Button1.setBounds(5, 5, 150, 70);
    	teamRecord_Pane4_Button2 = new JButton("Ãë¼Ò");
    	teamRecord_Pane4_Button2.setBounds(5, 80, 150, 70);

    	teamRecord_Pane4.add(teamRecord_Pane4_Button1);
    	teamRecord_Pane4.add(teamRecord_Pane4_Button2);
    	
    	add(teamRecord_Pane4);
   	
    	
	}
	
	
}
