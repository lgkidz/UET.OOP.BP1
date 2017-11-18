package app.bp1;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;


public class Quick_test extends JFrame implements ActionListener	 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static BufferedReader br;
	private List<String[]> words = new ArrayList<String[]>();
	private List<String[]> words_to_r = new ArrayList<String[]>();
	private JButton btnStartNow;
	private int current_question = 0;
	private boolean STARTED = false;
	private JRadioButton rdbtnAnswer_1;
	private JRadioButton rdbtnAnswer_2;
	private JRadioButton rdbtnAnswer_3;
	private JRadioButton rdbtnAnswer_4;
	private JLabel lblWhatIsThe;
	private JLabel lblQuestionNumber;
	private JLabel lblX;
	private List<String[]> result = new ArrayList<String[]>();
	private String pre_ans = "";
	private String current_word = "";

	/**
	 * Create the frame.
	 */
	public Quick_test() {
		setResizable(false);
		setTitle("Quick Test");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 475, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnStartNow = new JButton("Start now!");
		btnStartNow.addActionListener(this);
		btnStartNow.setBounds(180, 212, 89, 23);
		contentPane.add(btnStartNow);
		
		lblQuestionNumber = new JLabel("Question :");
		lblQuestionNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuestionNumber.setBounds(367, 21, 63, 23);
		contentPane.add(lblQuestionNumber);
		lblQuestionNumber.setVisible(false);
		
		lblX = new JLabel();
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblX.setBounds(440, 23, 46, 19);
		contentPane.add(lblX);
		lblX.setText(String.valueOf(current_question) + "/5");
		lblX.setVisible(false);
		
		lblWhatIsThe = new JLabel("What is this in language?");
		lblWhatIsThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWhatIsThe.setBounds(7, 21, 350, 23);
		contentPane.add(lblWhatIsThe);
		lblWhatIsThe.setVisible(false);
		
		rdbtnAnswer_1 = new JRadioButton("answer 1");
		rdbtnAnswer_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAnswer_1.setBounds(22, 70, 109, 23);
		contentPane.add(rdbtnAnswer_1);
		rdbtnAnswer_1.setVisible(false);
		
		rdbtnAnswer_3 = new JRadioButton("answer 3");
		rdbtnAnswer_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAnswer_3.setBounds(22, 126, 109, 23);
		contentPane.add(rdbtnAnswer_3);
		rdbtnAnswer_3.setVisible(false);
		
		rdbtnAnswer_2 = new JRadioButton("answer 2");
		rdbtnAnswer_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAnswer_2.setBounds(248, 70, 109, 23);
		contentPane.add(rdbtnAnswer_2);
		rdbtnAnswer_2.setVisible(false);
		
		rdbtnAnswer_4 = new JRadioButton("answer 4");
		rdbtnAnswer_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAnswer_4.setBounds(248, 126, 109, 23);
		contentPane.add(rdbtnAnswer_4);
		rdbtnAnswer_4.setVisible(false);
		
		ButtonGroup answer = new ButtonGroup();
		answer.add(rdbtnAnswer_4);
		answer.add(rdbtnAnswer_1);
		answer.add(rdbtnAnswer_2);
		answer.add(rdbtnAnswer_3);
		
		transfer(new File("learntwords.dat"), words);
		createwords();
	}
	
	//check if the prev is correct
	public void checkPreviousAnswer() {
		String selected = "";
		if(rdbtnAnswer_1.isSelected()) {
			selected = rdbtnAnswer_1.getText();
		}
		else if(rdbtnAnswer_2.isSelected()){
			selected = rdbtnAnswer_2.getText();
		}
		else if(rdbtnAnswer_3.isSelected()){
			selected = rdbtnAnswer_3.getText();
		}
		else if(rdbtnAnswer_4.isSelected()) {
			selected = rdbtnAnswer_4.getText();
		}
		String tmp[] = {current_word,pre_ans,selected};//word,right answer, user choice
		result.add(tmp);
		if(selected.equals(pre_ans)) {
			System.out.println("Right Choice!");
		}
	}
	
	//generate random choices
	public List<String> getQuestionAnswers(String ans, String language) {
		List<String[]> tmp = new ArrayList<String[]>();
		for(int i = 0;i<words_to_r.size();i++) {
			if(ans.equals(words_to_r.get(i)[0]) || ans.equals(words_to_r.get(i)[1])){
				continue;
			}
			else {
				tmp.add(words_to_r.get(i));
			}
		}
		Collections.shuffle(tmp);
		List<String> answers = new ArrayList<String>();
		answers.add(ans);
		for(int i = 0;i<3;i++) {
			answers.add(tmp.get(i)[(language.equals("Vietnamese")?1:0)]);
		}
		Collections.shuffle(answers);
		//for(String x:answers) {
			//System.out.println(x);
		//}
		
		return answers;
	}
	
	//get data for the current question
	public void getQuestion() {
		
		String word = "";
		String ans = "";
		String language = String.valueOf((1 + (int)(Math.random() * 2) - 1 == 0)?"Vietnamese":"English");// 0 = vi, 1 = en
		
		System.out.println(language);
		if(language.equals("Vietnamese")) {
			word = words_to_r.get(current_question)[0];
			ans = words_to_r.get(current_question)[1];
		}
		else if(language.equals("English")) {
			word = words_to_r.get(current_question)[1];
			ans = words_to_r.get(current_question)[0];
		}
		List<String> answers = getQuestionAnswers(ans, language);
		rdbtnAnswer_1.setText(answers.get(0));
		rdbtnAnswer_2.setText(answers.get(1));
		rdbtnAnswer_3.setText(answers.get(2));
		rdbtnAnswer_4.setText(answers.get(3));
		pre_ans = ans;
		current_word = word;
		lblWhatIsThe.setText("What is " + word + " in " + language + " ?");
		lblX.setText(String.valueOf(current_question + 1) + "/5");
		
	}
	
	//get the bloody first question and get the damn whole test to start
	public void initial() {
		lblQuestionNumber.setVisible(true);
		lblX.setVisible(true);
		lblWhatIsThe.setVisible(true);
		rdbtnAnswer_1.setVisible(true);
		rdbtnAnswer_1.setSelected(true);
		rdbtnAnswer_2.setVisible(true);
		rdbtnAnswer_3.setVisible(true);
		rdbtnAnswer_4.setVisible(true);
		btnStartNow.setText("Next");

		getQuestion();
		STARTED = true;
	}
	
	
	//Next question
	public void Next() {
		if(STARTED) {
			checkPreviousAnswer();
		}
		getQuestion();
	}
	
	//read file
	public BufferedReader readFileData(File file) {
		try {
			File in = file;
			br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(in), "UTF8"));
		}catch (Exception e) {
			System.out.println("Cannot read file!");
		}
		return br;
	}
	
	//get 5 words from the list for the test
	public void createwords() {
		int count = 5;
		for(int i = 0;i<count;i++) {
			words_to_r.add(words.get(i));
			//System.out.println(words_to_r.get(i)[0]);
		}
	}
	
	//read data from collection file and write to a list
	public void transfer(File f, List<String[]> words) {
		BufferedReader buff = readFileData(f);
		String s;
		try {
			while((s= buff.readLine())!=null) {
				String parts[] = s.split(" - ");
				words.add(parts);
			}
			buff.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//Collections.sort(words, new oldest_words());
		Collections.shuffle(words);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object x = arg0.getSource();
		
		if(x == btnStartNow) {
			if(!STARTED) { //if haven't started, then start, if started, then next question
				initial();
			}
			else {
				if(current_question == 3) {
					btnStartNow.setText("Finish");		
				}
				if(current_question < 4) {
					current_question++;
					Next();
				}
				else {
					checkPreviousAnswer();
					JOptionPane.showMessageDialog(null, "You have done your test. Let's see your result!");
					this.dispose();
					new Quick_test_result(result).setVisible(true);
				}
			}
		}
		
	}
}
