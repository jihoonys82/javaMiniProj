package injung;

import javax.swing.JDialog;

public class DetailDialog extends JDialog {

	private static final long serialVersionUID = 5858592773353644590L;

	public DetailDialog( ) {
		setBounds(700, 500, 100,100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
	}
	
	public DetailDialog(EmployeeInfoPanel pane) {
		this();
		this.add(pane);
		pane.setVisible(true);
	}
}
