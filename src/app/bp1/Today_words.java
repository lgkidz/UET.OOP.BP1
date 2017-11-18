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
import javax.swing.border.MatteBorder;

class diffrent_in_time_compare implements Comparator<String[]>{
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
		long d1 = TimeUnit.MILLISECONDS.toHours(now.getTime() - date1.getTime());
		long d2 = TimeUnit.MILLISECONDS.toHours(now.getTime() - date2.getTime());
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

public class Today_words extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private List<String> collections = new ArrayList<String>();
	private List<String[]> learnt_words = new ArrayList<String[]>();
	private List<String[]> words = new ArrayList<String[]>();
	private List<String[]> words_to_learn = new ArrayList<String[]>();
	private File learntwords = new File("learntwords.dat");
	
	private final File folder = new File("data");
	private static BufferedReader br;
	private JButton btnNext;
	private JLabel e_word;
	private JLabel v_word;
	private static int currentword = -1;
	private static boolean learnt = false;
	private JButton btnPrevious;
	private JLabel img;
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Today_words() throws ParseException {
		setResizable(false);
		setTitle("Today's new words");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 11, 252, 252);
		contentPane.add(panel);
		
		btnNext = new JButton("Start");
		btnNext.setBounds(358, 237, 76, 23);
		contentPane.add(btnNext);
		btnNext.addActionListener(this);
		
		e_word = new JLabel("");
		e_word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		e_word.setBounds(270, 69, 154, 14);
		contentPane.add(e_word);
		
		v_word = new JLabel("");
		v_word.setFont(new Font("Tahoma", Font.PLAIN, 15));
		v_word.setBounds(270, 124, 154, 14);
		contentPane.add(v_word);
		
		img = new JLabel(new ImageIcon("images/default.png"));
		img.setAlignmentY(0.0f);
		img.setPreferredSize(new Dimension(250, 250));
		panel.add(img);
		
		//e_word.setText(words_to_learn.get(0)[0]);
		//v_word.setText(words_to_learn.get(0)[1]);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setEnabled(false);
		btnPrevious.setBounds(275, 237, 73, 23);
		contentPane.add(btnPrevious);
		btnPrevious.addActionListener(this);
		
		loadData();
		createWords();
		checklastlearndate();
	}
	
	//check the last time user learn new words
	public void checklastlearndate() throws ParseException {
		List<String[]> lastdate = new ArrayList<String[]>();
		transfer(new File("lastlearndate.date"), lastdate);
		Date today = Calendar.getInstance().getTime();
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date thatday = format.parse(lastdate.get(0)[0]);
		long days = TimeUnit.MILLISECONDS.toDays(today.getTime() - thatday.getTime());
		System.out.println(days);
		if(days == 0) {
			learnt = true;
		}
	}
	
	//check if user has learned today
	public void checklearnt() {
		if(learnt && currentword == -1) {
			JOptionPane.showMessageDialog(null, "You have finished learing today's new words!");
			this.dispose();
		}
	}
	
	//show the word
	public void learn(int state) {
		try {
			if(state == 1) {
				wordsCount(state);
				String w = words_to_learn.get(currentword)[0];
				e_word.setText(w);
				v_word.setText(words_to_learn.get(currentword)[1]);
				w = w.substring(0, 1).toUpperCase() + w.substring(1);
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/" + w + ".jpg").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
				img.setIcon(imageIcon);
			}
			else if(state == -1) {
				wordsCount(state);
				String w = words_to_learn.get(currentword)[0];
				e_word.setText(w);
				v_word.setText(words_to_learn.get(currentword)[1]);
				w = w.substring(0, 1).toUpperCase() + w.substring(1);
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/" + w + ".jpg").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
				img.setIcon(imageIcon);
			}
		}
		catch(Exception e2) {
			if(currentword > -1) {
				if(!learnt) {
					updateLearntWords();
					updateWordsTime();
					updatelastlearndate();
				}
				System.out.println("update files");
				learnt = true;
			}
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
		if(currentword == words_to_learn.size()) {
			JOptionPane.showMessageDialog(null, "You have finished learing today's new words!");
			btnNext.setEnabled(false);
		}
		
		else if(currentword == words_to_learn.size()-1) {
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
	
	//check if the word is learned or not
	public boolean this_word_is_learnt(String word, List<String[]> list) {
		for(int i = 0;i<list.size();i++) {
			if(list.get(i)[0].equals(word)) {
				return true;
			}
		}
		return false;
	}
	
	//create 5 words to learn
	public void createWords() {
		transfer(learntwords, learnt_words);
		int count = 5;
		int po = 0;
		while(count != 0) {
			System.out.println(po);
			try {
				String[] word = words.get(po);
				if(!this_word_is_learnt(word[0], learnt_words)) {
					words_to_learn.add(word);
					po++;
					count--;
				}
				else {
					po++;
				}
			}catch (Exception e) {
				break;
			}	
		}
		if(count == 10) {
			JOptionPane.showMessageDialog(null, "You have learnt all new words!");
			
			learnt = true;
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
	}
	
	//list all available collections
	public List<String> listCollection(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listCollection(fileEntry);
			}
			else {
				String name = fileEntry.getName();
				collections.add(name);
			}
		}
		return collections;
	}
	
	//load words from all available collections to a list
	public void loadData() {
		List<String> collections = listCollection(folder);
		for(int i = 0;i<collections.size();i++) {
			transfer(new File("data/" + collections.get(i)), words);
		}
		Collections.sort(words, new diffrent_in_time_compare());
		for(int i = 0;i<words.size();i++) {
		}
	}
	
	//update time for 5 words just learn
	public void updateWordsTime() {
		int left = words_to_learn.size();
		for(int i = 0;i<collections.size();i++) {
			List<String[]> tmp = new ArrayList<String[]>();
			transfer(new File(folder + "/" +collections.get(i)), tmp);
			for(int j = 0;j<words_to_learn.size();j++) {
				if(left<=0) {
					break;
				}
				for(int k = 0;k<tmp.size();k++) {
					if(words_to_learn.get(j)[0].equals(tmp.get(k)[0])) {
						Date today = Calendar.getInstance().getTime();
				    	String date = df.format(today);
						tmp.get(k)[2] = date;
						left--;
						break;
					}
				}
			}
			try {
				PrintWriter writer =  new PrintWriter(folder + "/" +collections.get(i), "UTF-8"); 
				for(int ii = 0;ii<tmp.size();ii++) {
					String line = tmp.get(ii)[0] + " - " + tmp.get(ii)[1] + " - " + tmp.get(ii)[2];
					writer.println(line);
				}
				writer.close();
			}
			catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Cannot create file!");
			}
			
		}
	}
	
	//update the last day user learned
	public void updatelastlearndate() {
    	String today = df.format(Calendar.getInstance().getTime());
    	List<String[]> tmp = new ArrayList<String[]>();
    	transfer(new File("lastlearndate.date"), tmp);
    	tmp.get(0)[0] = today;
		try {
			PrintWriter writer =  new PrintWriter(new File("lastlearndate.date"), "UTF-8"); 
			for(int i = 0;i<tmp.size();i++) {
				writer.println(tmp.get(0)[0]);
			}
			writer.close();
		}
		catch (Exception e1) {
		JOptionPane.showMessageDialog(null, "Cannot create file!");
		}
	}
	
	//list those 5 words as learned
	public void updateLearntWords() {
		Date today = Calendar.getInstance().getTime();
    	String date = df.format(today);
    	for(int i = 0;i<words_to_learn.size();i++) {
    		words_to_learn.get(i)[2] = date;
    	}
    	List<String[]> tmp = new ArrayList<String[]>();
    	transfer(learntwords, tmp);
    	for(int i = 0;i<words_to_learn.size();i++) {
    		tmp.add(words_to_learn.get(i));
    	}
		try {
			PrintWriter writer =  new PrintWriter(learntwords, "UTF-8"); 
			for(int i = 0;i<tmp.size();i++) {
				String line = tmp.get(i)[0] + " - " + tmp.get(i)[1] + " - " + tmp.get(i)[2];
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
			checklearnt();
			learn(1);
		}
		else if(btn == btnPrevious) {
			learn(-1);
		}
	}
}
