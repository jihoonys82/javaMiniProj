package injung;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;

/**
 * Quiz Panel
 * 
 * @since 2018-07-01
 * @author Jihoon Jeong
 *
 */
public class QuizPanel extends JPanel {
	private static final long serialVersionUID = 4157611301322840656L;

	private JPanel quizPane = new JPanel();
	private JPanel buttonPane = new JPanel();

	// sub panels of quizPane
	private JPanel introPane = new JPanel();
	private JPanel photoPane = new JPanel();
	private JPanel scorePane = new JPanel();

	// introPane components
	private JLabel lblWho = new JLabel("다음 중 ");
	private JLabel lblName = new JLabel("ㅇㅇㅇ");
	private JLabel lblWho2 = new JLabel("님을 찾아주세요. ");

	// photoPane components
	private JButton btnPhoho1 = new JButton("Phoho1");
	private JButton btnPhoto2 = new JButton("Photo2");
	private JButton btnPhoto3 = new JButton("Photo3");

	// scorePane components
	private JLabel lblNotice = new JLabel("Notice");
	private JLabel lblHint = new JLabel("남은 힌트 :");
	private JLabel lblCorrect = new JLabel("맞은 개수 :");
	private JLabel lblWrong = new JLabel("틀린 갯수 :");

	// buttonPane components
	private JButton btnHint = new JButton("힌트");
	private JButton btnRetry = new JButton("다시하기");
	private JButton btnEnd = new JButton("끝내기");
	private JTextField txtHint = new JTextField();
	private JTextField txtCorrect = new JTextField();
	private JTextField txtWrong = new JTextField();

	/**
	 * setup quizPanel
	 */
	public QuizPanel() {
		// set quizPanel
		setLayout(null);

		// initialize quizPane
		initQuizPane();

		// initialize buttonPane
		initButtonPane();

	}

	/**
	 * setup quizPane quizPane is sub pane of quizPanel
	 */
	private void initQuizPane() {
		// set quizPane (CAUTION!! NOT quizPanel!)
		quizPane.setLayout(null);
		quizPane.setBounds(12, 10, 700, 560);

		// sub Panel of quizPane - 1. introPane
		FlowLayout fl_introPane = (FlowLayout) introPane.getLayout();
		fl_introPane.setAlignOnBaseline(true);
		introPane.setBounds(12, 30, 304, 45);

		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("굴림", Font.PLAIN, 20));

		// sub Panel of quizPane - 2. photoPane
		photoPane.setLayout(new GridLayout(0, 3, 20, 5));
		photoPane.setBounds(50, 90, 590, 230);

		// component of quizPane - 3. lblNotice
		lblNotice.setBounds(174, 333, 357, 72);
		lblNotice.setHorizontalAlignment(SwingConstants.CENTER);

		// sub Panel of quizPane - 4. scorePane
		scorePane.setLayout(null);
		scorePane.setBounds(174, 415, 357, 86);
		scorePane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblHint.setBounds(30, 10, 73, 15);
		lblCorrect.setBounds(30, 35, 73, 15);
		lblWrong.setBounds(30, 60, 73, 15);
		txtHint.setBounds(100, 7, 116, 21);
		txtHint.setColumns(10);
		txtHint.setEditable(false);
		txtCorrect.setBounds(100, 32, 116, 21);
		txtCorrect.setColumns(10);
		txtCorrect.setEditable(false);
		txtWrong.setBounds(100, 57, 116, 21);
		txtWrong.setColumns(10);
		txtWrong.setEditable(false);

		// add components
		introPane.add(lblWho);
		introPane.add(lblName);
		introPane.add(lblWho2);

		photoPane.add(btnPhoho1);
		photoPane.add(btnPhoto2);
		photoPane.add(btnPhoto3);

		quizPane.add(lblNotice);

		scorePane.add(lblHint);
		scorePane.add(lblCorrect);
		scorePane.add(lblWrong);
		scorePane.add(txtHint);
		scorePane.add(txtCorrect);
		scorePane.add(txtWrong);

		quizPane.add(introPane);
		quizPane.add(photoPane);
		quizPane.add(scorePane);

		add(quizPane);
	}

	/**
	 * setup buttonPane
	 */
	private void initButtonPane() {
		// set buttonPane
		buttonPane.setBounds(724, 10, 244, 560);
		buttonPane.setLayout(null);

		btnHint.setBounds(12, 461, 160, 23);
		btnRetry.setBounds(12, 494, 160, 23);
		btnEnd.setBounds(12, 527, 160, 23);

		// add components
		buttonPane.add(btnHint);
		buttonPane.add(btnRetry);
		buttonPane.add(btnEnd);

		add(buttonPane);
	}

}
