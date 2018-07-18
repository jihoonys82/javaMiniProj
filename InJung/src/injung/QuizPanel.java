package injung;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import injung.model.EmployeeDto;
import injung.model.InJungDao;

/**
 * Quiz Panel
 * 
 * @since 2018-07-01
 * @author Jihoon Jeong
 *
 */
public class QuizPanel extends JPanel implements ActionListener {
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
	private JButton[] btnPhoto = new JButton[3];
	
	// scorePane components
	private JLabel lblNotice = new JLabel("Notice");
	private JLabel lblHint = new JLabel("남은 힌트 :");
	private JLabel lblCorrect = new JLabel("맞은 개수 :");
	private JLabel lblWrong = new JLabel("틀린 개수 :");

	// buttonPane components
	private JButton btnHint = new JButton("힌트");
	private JButton btnRetry = new JButton("다시하기");
	private JButton btnEnd = new JButton("끝내기");
	private JTextField txtHint = new JTextField();
	private JTextField txtCorrect = new JTextField();
	private JTextField txtWrong = new JTextField();
	
	private final int idx = 3;
	private final int max_hint =3;
	private int hint;
	private int wrong;
	private int correct;
	private int answerIdx;
	private int hintIdx;
	
	private ArrayList<EmployeeDto> dtos;
	
	private File dir = new File(PropertiesLoad.getProperties().getProperty("PHOTOPATH"));
	private File file;
	
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
		
		initQuiz();
		
	}

	private void initQuiz() {
		hint = max_hint; 
		correct = 0;
		wrong = 0;
		
		txtHint.setText(((Integer)hint).toString());
		txtCorrect.setText(((Integer)correct).toString());
		txtWrong.setText(((Integer)wrong).toString());
		lblNotice.setText("");
		setQuizData();
	}

	private void setQuizData() {
		//Button Enable
		for(JButton btn : btnPhoto) {
			btn.setEnabled(true);
		}
		
		//get Quiz data 
		InJungDao dao = InJungDao.getInstance();
		dtos = dao.getQuizData();
		Random rand = new Random();
		answerIdx = rand.nextInt(idx);
		int hintIdx = rand.nextInt(idx-1);
		if(hintIdx == answerIdx) {
			hintIdx = (answerIdx==0) ? hintIdx++ : hintIdx--; 
		}
		
		// load images
		for(int i=0;i<idx;i++) {
			file = new File(dir,dtos.get(i).getPhoto());
			if (!file.exists()) {
				FileReceiver request = new FileReceiver(file, null);
				request.start();
				System.out.println(file.length() + " receive");
			}
			btnPhoto[i].setIcon(new ImageIcon(file.getPath()));
		}
		lblName.setText(dtos.get(answerIdx).getName());
		
		validate();
		repaint();
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
		for(int i=0;i<btnPhoto.length;i++) {
			btnPhoto[i] = new JButton();
			btnPhoto[i].addActionListener(this);
		}
		photoPane.setLayout(new GridLayout(0, 3, 20, 5));
		photoPane.setBounds(50, 90, 590, 230);

		// component of quizPane - 3. lblNotice
		lblNotice.setBounds(174, 333, 357, 72);
		lblNotice.setHorizontalAlignment(SwingConstants.CENTER);

		// sub Panel of quizPane - 4. scorePane
		scorePane.setLayout(null);
		scorePane.setBounds(174, 402, 357, 95);
		scorePane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblHint.setBounds(90, 13, 73, 15);
		lblCorrect.setBounds(90, 38, 73, 15);
		lblWrong.setBounds(90, 63, 73, 15);
		txtHint.setBounds(160, 10, 116, 21);
		txtHint.setColumns(10);
		txtHint.setEditable(false);
		txtCorrect.setBounds(160, 35, 116, 21);
		txtCorrect.setColumns(10);
		txtCorrect.setEditable(false);
		txtWrong.setBounds(160, 60, 116, 21);
		txtWrong.setColumns(10);
		txtWrong.setEditable(false);
		
		// add components
		introPane.add(lblWho);
		introPane.add(lblName);
		introPane.add(lblWho2);

		photoPane.add(btnPhoto[0]);
		photoPane.add(btnPhoto[1]);
		photoPane.add(btnPhoto[2]);

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
		
		//actions 
		btnHint.addActionListener(this);
		btnRetry.addActionListener(this);
		btnEnd.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnHint)) {
			if(hint>0) {
				btnPhoto[hintIdx].setEnabled(false);
				hint--;
				txtHint.setText(((Integer)hint).toString());
				lblNotice.setText("힌트를 사용하셨습니다. 두 분중에 선택해주세요.");				
			} else {
				lblNotice.setText("힌트를 모두 다 사용하셨습니다.");
			}
			
		} else if(e.getSource().equals(btnRetry)) {
			lblNotice.setText("");
			initQuiz();
			
		} else if(e.getSource().equals(btnEnd)) {
			for(JButton btn : btnPhoto) {
				btn.setEnabled(false);
			}
			showResults();
		} else if(e.getSource().equals(btnPhoto[0])) {
			if(answerIdx == 0) {
				lblNotice.setText("정답입니다!");
				correct++;
				txtCorrect.setText(((Integer)correct).toString());
			} else {
				lblNotice.setText("틀렸습니다. 선택한 분은 " + dtos.get(0).getName() +" 입니다.");
				wrong++;
				txtWrong.setText(((Integer)wrong).toString());
			}
			
			if(correct+wrong == 10) {
				showResults();
			} else {
				setQuizData();				
			}
		} else if(e.getSource().equals(btnPhoto[1])) {
			if(answerIdx == 1) {
				lblNotice.setText("정답입니다!");
				correct++;
				txtCorrect.setText(((Integer)correct).toString());
			} else {
				lblNotice.setText("틀렸습니다. 선택한 분은 " + dtos.get(1).getName() +" 입니다.");
				wrong++;
				txtWrong.setText(((Integer)wrong).toString());
			}
			
			if(correct+wrong == 10) {
				showResults();
			} else {
				setQuizData();				
			}			
		} else if(e.getSource().equals(btnPhoto[2])) {
			if(answerIdx == 2) {
				lblNotice.setText("정답입니다!");
				correct++;
				txtCorrect.setText(((Integer)correct).toString());
			} else {
				lblNotice.setText("틀렸습니다. 선택한 분은 " + dtos.get(2).getName() +" 입니다.");
				wrong++;
				txtWrong.setText(((Integer)wrong).toString());
			}
			
			if(correct+wrong == 10) {
				showResults();
			} else {
				setQuizData();				
			}
		}
		
	}

	private void showResults() {
		lblNotice.setText("퀴즈를 다시 도전하려면 '다시하기'를 눌러주세요.");
		String[] options = { "다시하기", "그만하기" };
		int selected = JOptionPane.showOptionDialog(this, 
				"=====결과===== \n"
				+ "푼 문제 개수 : " + (correct+wrong)
				+ "\n  맞은 개수 : " + correct
				+ "\n사용한 힌트 : " + (max_hint-hint),
				
				"Confirm",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (selected == 0) initQuiz();
	}

}
