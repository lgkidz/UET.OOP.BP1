package app.bp1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

class oldest_words implements Comparator<String[]>{
	@Override
    public int compare(String[] w1, String[] w2){
		Date now = new Date();
		Date date1 = null;
		Date date2 = null;
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		try {
			date1 = format.parse(w1[2]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			date2 = format.parse(w2[2]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long d1 = TimeUnit.MILLISECONDS.toMillis(now.getTime() - date1.getTime());
		long d2 = TimeUnit.MILLISECONDS.toMillis(now.getTime() - date2.getTime());
		if(d1 < d2) {
			return 1;
		}
		else if(d1 > d2) {
			return -1;
		}
		/*
		else if(d1 == d2) {
			int w = w1[0].compareTo(w2[0]);
            if(w != 0){
                return w;
            }
		}
		*/
        return 0;
    }
}

public class Revision extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static BufferedReader br;
	private JLabel e_word;
	private JLabel v_word;
	private JButton btnNext;
	private JButton btnPrevious;
	private List<String[]> words = new ArrayList<String[]>();
	private List<String[]> words_to_r = new ArrayList<String[]>();
	private int currentword = -1;
	private boolean learnt = false;
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private JLabel img;

	/**
	 * Create the frame.
	 */
	public Revision() {
		setTitle("Review what you learnt");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 252, 252);
		contentPane.add(panel);
		
		btnNext = new JButton("Start");
		btnNext.setBounds(385, 240, 89, 23);
		contentPane.add(btnNext);
		btnNext.addActionListener(this);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setEnabled(false);
		btnPrevious.setBounds(286, 240, 89, 23);
		contentPane.add(btnPrevious);
		btnPrevious.addActionListener(this);

		
		e_word = new JLabel("");
		e_word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		e_word.setBounds(286, 59, 188, 14);
		contentPane.add(e_word);
		
		v_word = new JLabel("");
		v_word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		v_word.setBounds(286, 124, 188, 14);
		contentPane.add(v_word);
		
		img = new JLabel(new ImageIcon("images/default.png"));
		img.setAlignmentY(0.0f);
		img.setPreferredSize(new Dimension(250, 250));
		panel.add(img);
		
		transfer(new File("learntwords.dat"), words);
		createwords();
	}
	
	//show the current word
	public void learn(int state) {
		try {
			if(state == 1) {
				wordsCount(state);
				String w = words_to_r.get(currentword)[0];
				e_word.setText(w);
				v_word.setText(words_to_r.get(currentword)[1]);
				w = w.substring(0, 1).toUpperCase() + w.substring(1);
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/" + w + ".jpg").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
				img.setIcon(imageIcon);
			}
			else if(state == -1) {
				wordsCount(state);
				String w = words_to_r.get(currentword)[0];
				e_word.setText(w);
				v_word.setText(words_to_r.get(currentword)[1]);
				w = w.substring(0, 1).toUpperCase() + w.substring(1);
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/" + w + ".jpg").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
				img.setIcon(imageIcon);
			}
		}
		catch(Exception e2) {
			if(currentword > -1) {
				updateWordsTime();
				System.out.println("update files");
				learnt = true;
			}
		}
	}
	
	//get 5 words to learn
	public void createwords() {
		int count = 5;
		for(int i = 0;i<count;i++) {
			words_to_r.add(words.get(i));
		}
	}
	
	//as its name
	public void wordsCount(int state) {
		if(state == 1) {
			currentword++;
		}
		else if(state == -1) {
			currentword--;
		}
		if(currentword == words_to_r.size()) {
			JOptionPane.showMessageDialog(null, "You have finished reviewing some words!");
			btnNext.setEnabled(false);
		}
		
		else if(currentword == words_to_r.size()-1) {
			btnNext.setText("Finish");
			if(learnt) {
				btnNext.setEnabled(false);
			}
		}
		else if(currentword == 0) {
			btnPrevious.setEnabled(false);
			btnNext.setText("Next");
		}
		else {
			btnPrevious.setEnabled(true);
			btnNext.setText("Next");
			btnNext.setEnabled(true);
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
	
	//read data from a collection file and write to a list
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
		Collections.sort(words, new oldest_words());
	}
	
	//update the last time the user learn for those 5 words
	public void updateWordsTime() {
    	String today = df.format(Calendar.getInstance().getTime());
    	for(int i = 0;i<words_to_r.size();i++) {
    		words_to_r.get(i)[2] = today;
    	}
    	for(int i = 0;i<words_to_r.size();i++) {
    		for(int j = 0 ;i<words.size();j++) {
    			if(words_to_r.get(i)[0].equals(words.get(j)[0])) {
    				words.get(j)[2] = words_to_r.get(i)[2];
    				break;
    			}
    		}
    	}
			try {
				PrintWriter writer =  new PrintWriter(new File("learntwords.dat"), "UTF-8"); 
				for(int ii = 0;ii<words.size();ii++) {
					String line = words.get(ii)[0] + " - " + words.get(ii)[1] + " - " + words.get(ii)[2];
					writer.println(line);
				}
				writer.close();
			}
			catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Cannot create file!");
			}
			
		}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton) arg0.getSource();
		if(btn == btnNext) {
			learn(1);
		}
		else if(btn == btnPrevious) {
			learn(-1);
		}
		
	}
}
