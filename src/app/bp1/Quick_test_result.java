package app.bp1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Quick_test_result extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BufferedReader br;
	private JPanel contentPane;
	private JLabel c1;
	private JLabel c2;
	private JLabel c3;
	private JLabel c4;
	private JLabel c5;
	private JLabel u1;
	private JLabel u2;
	private JLabel u3;
	private JLabel u4;
	private JLabel u5;
	private JLabel t1;
	private JLabel t2;
	private JLabel t3;
	private JLabel t4;
	private JLabel t5;
	private JLabel comment_field;
	private JButton btnNewButton;
	private JLabel score;


	/**
	 * Create the frame.
	 */
	public Quick_test_result(List<String[]> result) {
		setTitle("Your result");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		score = new JLabel("Score : x/5");
		score.setFont(new Font("Tahoma", Font.PLAIN, 19));
		score.setBounds(10, 37, 131, 35);
		contentPane.add(score);
		
		JLabel lblQuest = new JLabel("Quest");
		lblQuest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuest.setBounds(64, 104, 45, 19);
		contentPane.add(lblQuest);
		
		JLabel lblYourAnswer = new JLabel("Your answer");
		lblYourAnswer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYourAnswer.setBounds(171, 104, 93, 19);
		contentPane.add(lblYourAnswer);
		
		JLabel lblItShouldBe = new JLabel("It should be");
		lblItShouldBe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblItShouldBe.setBounds(333, 104, 93, 19);
		contentPane.add(lblItShouldBe);
		
		JLabel label = new JLabel("#1");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 138, 19, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("#1");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(10, 179, 19, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("#1");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(10, 223, 19, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("#1");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(10, 269, 19, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("#1");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(10, 315, 19, 14);
		contentPane.add(label_4);
		
		c1 = new JLabel("c1");
		c1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c1.setBounds(51, 138, 98, 19);
		contentPane.add(c1);
		
		c2 = new JLabel("c1");
		c2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c2.setBounds(51, 179, 98, 19);
		contentPane.add(c2);
		
		c3 = new JLabel("c1");
		c3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c3.setBounds(51, 223, 90, 19);
		contentPane.add(c3);
		
		c4 = new JLabel("c1");
		c4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c4.setBounds(51, 269, 90, 19);
		contentPane.add(c4);
		
		c5 = new JLabel("c1");
		c5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c5.setBounds(51, 315, 90, 19);
		contentPane.add(c5);
		
		u1 = new JLabel("c1");
		u1.setForeground(Color.GREEN);
		u1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		u1.setBounds(171, 140, 98, 17);
		contentPane.add(u1);
		
		u2 = new JLabel("c1");
		u2.setForeground(Color.GREEN);
		u2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		u2.setBounds(171, 179, 98, 19);
		contentPane.add(u2);
		
		u3 = new JLabel("c1");
		u3.setForeground(Color.GREEN);
		u3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		u3.setBounds(171, 223, 98, 19);
		contentPane.add(u3);
		
		u4 = new JLabel("c1");
		u4.setForeground(Color.GREEN);
		u4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		u4.setBounds(171, 269, 98, 19);
		contentPane.add(u4);
		
		u5 = new JLabel("c1");
		u5.setForeground(Color.GREEN);
		u5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		u5.setBounds(171, 315, 98, 19);
		contentPane.add(u5);
		
		t1 = new JLabel("c1");
		t1.setForeground(Color.GREEN);
		t1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		t1.setBounds(333, 140, 98, 19);
		contentPane.add(t1);
		
		t2 = new JLabel("c1");
		t2.setForeground(Color.GREEN);
		t2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		t2.setBounds(333, 181, 98, 17);
		contentPane.add(t2);
		
		t3 = new JLabel("c1");
		t3.setForeground(Color.GREEN);
		t3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		t3.setBounds(333, 223, 98, 19);
		contentPane.add(t3);
		
		t4 = new JLabel("c1");
		t4.setForeground(Color.GREEN);
		t4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		t4.setBounds(333, 269, 98, 19);
		contentPane.add(t4);
		
		t5 = new JLabel("c1");
		t5.setForeground(Color.GREEN);
		t5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		t5.setBounds(333, 315, 98, 19);
		contentPane.add(t5);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(158, 358, 150, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		comment_field = new JLabel("comment");
		comment_field.setFont(new Font("Tahoma", Font.ITALIC, 12));
		comment_field.setBounds(183, 46, 291, 19);
		contentPane.add(comment_field);
		
		loadResult(result);
		
	}
	
	public void loadResult(List<String[]> result) {
		int right = 0;  //the variable's name is 'right' due to lack of English vocabulary during writing this code
		for(int i = 0;i<result.size();i++) {
			if(result.get(i)[1].equals(result.get(i)[2])) { //count how many answer is correct
				right++;
			}
		}
		
		updateTestLog(right);
		
		score.setText("Score: " + right+"/5");
		
		if(right == 0) {
			comment_field.setText("How did you even get that terrible Score??");
			btnNewButton.setText("Unbelievable! ");
		}
		else if(right == 1) {
			comment_field.setText("Your score looks bad!");
			btnNewButton.setText("This is sad! ");
		}
		else if(right == 2) {
			comment_field.setText("You should study more!");
			btnNewButton.setText("a bit bad! ");
		}
		else if(right == 3) {
			comment_field.setText("Keep improving yourself!");
			btnNewButton.setText("Okay");
		}
		else if(right == 4) {
			comment_field.setText("Almost!!!!");
			btnNewButton.setText("looks good! ");
		}
		else if(right == 5) {
			comment_field.setText("PERFECT!!!!! OMG");
			btnNewButton.setText("Awesome!");
		}
		
		c1.setText(result.get(0)[0]);
		c2.setText(result.get(1)[0]);
		c3.setText(result.get(2)[0]);
		c4.setText(result.get(3)[0]);
		c5.setText(result.get(4)[0]);
		
		t1.setText(result.get(0)[1]);
		t2.setText(result.get(1)[1]);
		t3.setText(result.get(2)[1]);
		t4.setText(result.get(3)[1]);
		t5.setText(result.get(4)[1]);
		
		u1.setText(result.get(0)[2]);
		u2.setText(result.get(1)[2]);
		u3.setText(result.get(2)[2]);
		u4.setText(result.get(3)[2]);
		u5.setText(result.get(4)[2]);
		
		if(!result.get(0)[1].equals(result.get(0)[2])) {
			u1.setForeground(Color.red);
		}
		if(!result.get(1)[1].equals(result.get(1)[2])) {
			u2.setForeground(Color.red);
		}
		if(!result.get(2)[1].equals(result.get(2)[2])) {
			u3.setForeground(Color.red);
		}
		if(!result.get(3)[1].equals(result.get(3)[2])) {
			u4.setForeground(Color.red);
		}
		if(!result.get(4)[1].equals(result.get(4)[2])) {
			u5.setForeground(Color.red);
		}
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
	
	//read, edit, write back to the log file
	public void updateTestLog(int right) {
		int times_tried = 0;
		int rights = 0;
		BufferedReader buff = readFileData(new File("test_log.dat"));
		
		try {
			String s = "0";
			s = buff.readLine();
			times_tried = Integer.parseInt(s);
			s = buff.readLine();
			rights = Integer.parseInt(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(times_tried + " " + rights);
		times_tried++;
		rights = rights + right;
			try {
				PrintWriter writer =  new PrintWriter(new File("test_log.dat"), "UTF-8"); 
				writer.println(times_tried);
				writer.println(rights);
				writer.close();
			}
			catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Cannot create file!");
			}
			
		}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object btn = arg0.getSource();
		if(btn == btnNewButton) {
			this.dispose();
		}
	}
}
