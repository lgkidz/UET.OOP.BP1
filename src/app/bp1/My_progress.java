package app.bp1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class My_progress extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	
	private final File folder = new File("data");
	private static BufferedReader br;
	private JLabel n_o_w;
	private JLabel n_o_c;
	private JLabel last_time;
	private JLabel goal;
	private JButton btnOkay;
	private JLabel percents;
	/**
	 * Create the frame.
	 */
	public My_progress() {
		setResizable(false);
		setTitle("My progress");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouHaveLearnt = new JLabel("You have learnt :");
		lblYouHaveLearnt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYouHaveLearnt.setBounds(10, 83, 118, 14);
		contentPane.add(lblYouHaveLearnt);
		
		n_o_w = new JLabel("");
		n_o_w.setFont(new Font("Tahoma", Font.PLAIN, 15));
		n_o_w.setBounds(155, 83, 40, 14);
		contentPane.add(n_o_w);
		
		JLabel lblWords = new JLabel("words");
		lblWords.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWords.setBounds(205, 83, 46, 14);
		contentPane.add(lblWords);
		
		JLabel lblYouHave = new JLabel("You have");
		lblYouHave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYouHave.setBounds(10, 36, 118, 14);
		contentPane.add(lblYouHave);
		
		JLabel lblCollections = new JLabel("collections");
		lblCollections.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCollections.setBounds(205, 38, 70, 14);
		contentPane.add(lblCollections);
		
		n_o_c = new JLabel("");
		n_o_c.setFont(new Font("Tahoma", Font.PLAIN, 15));
		n_o_c.setBounds(155, 36, 40, 14);
		contentPane.add(n_o_c);
		
		JLabel lblLastTimeYou = new JLabel("Last time you study :");
		lblLastTimeYou.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastTimeYou.setBounds(10, 164, 150, 26);
		contentPane.add(lblLastTimeYou);
		
		last_time = new JLabel("");
		last_time.setFont(new Font("Tahoma", Font.PLAIN, 15));
		last_time.setBounds(205, 165, 169, 24);
		contentPane.add(last_time);
		
		btnOkay = new JButton("Okay");
		btnOkay.setBounds(285, 287, 89, 23);
		contentPane.add(btnOkay);
		btnOkay.addActionListener(this);
		
		JLabel lblCurrentGoal = new JLabel("Current goal : learn");
		lblCurrentGoal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrentGoal.setBounds(10, 122, 125, 24);
		contentPane.add(lblCurrentGoal);
		
		JLabel label = new JLabel("words");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(205, 129, 46, 14);
		contentPane.add(label);
		
		goal = new JLabel("");
		goal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		goal.setBounds(155, 129, 40, 14);
		contentPane.add(goal);
		
		JLabel lblMmddyyyyHhmmss = new JLabel("MM/dd/yyyy HH:mm:ss");
		lblMmddyyyyHhmmss.setBounds(215, 189, 125, 14);
		contentPane.add(lblMmddyyyyHhmmss);
		
		JLabel lblYourAverageTest = new JLabel("Your average test score :");
		lblYourAverageTest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYourAverageTest.setBounds(10, 230, 185, 26);
		contentPane.add(lblYourAverageTest);
		
		percents = new JLabel("words");
		percents.setFont(new Font("Tahoma", Font.PLAIN, 15));
		percents.setBounds(205, 236, 169, 14);
		contentPane.add(percents);
		
		intialize();
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
	
	public void intialize() {
		
		//load number of collections, number of words learned
		BufferedReader buff = readFileData(new File("learntwords.dat"));
		int noc = listCollection(folder);
		n_o_c.setText(String.valueOf(noc));
		String lasttime = "";
		int now = 0;
		try {
			while((buff.readLine())!=null) {
				now++;
			}
			buff.close();
			n_o_w.setText(String.valueOf(now));
			goal.setText(String.valueOf(now + 10));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//load the last time user study
		buff = readFileData(new File("lastlearndate.date"));
		try {
			String s;
			while((s =buff.readLine())!=null) {
				lasttime = s;
			}
			buff.close();
			last_time.setText(lasttime);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//load data about user tests
		buff = readFileData(new File("test_log.dat"));
		try {
			float times = Float.parseFloat(buff.readLine());
			float rights = Float.parseFloat(buff.readLine());
			float percent = (times!=0)?rights/(5 * times) * 100:0;
			DecimalFormat df = new DecimalFormat("#.##");
			percents.setText(df.format(percent) +" %");
			buff.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//list all available collections
	public int listCollection(final File folder) {
		ArrayList<String> collections = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listCollection(fileEntry);
			}
			else {
				String name = fileEntry.getName().split("\\.")[0];
				collections.add(name);
			}
		}
		return collections.size();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton) arg0.getSource();
		if(btn == btnOkay) {
			this.dispose();
		}
		
	}
}
