package app.bp1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			date2 = format.parse(w2[2]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
        return 0;
    }



}

public class Today_words extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<String[]> words = new ArrayList<String[]>();
	private final File folder = new File("data");
	private static BufferedReader br;
	/**
	 * Create the frame.
	 */
	public Today_words() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		loadData();
	}
	
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
	
	public List<String> listCollection(final File folder) {
		List<String> collections = new ArrayList<String>();
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
	
	public void loadData() {
		List<String> collections = listCollection(folder);
		for(int i = 0;i<collections.size();i++) {
			transfer(new File("data/" + collections.get(i)), words);
		}
		Collections.sort(words, new diffrent_in_time_compare());
		for(int i = 0;i<words.size();i++) {
			System.out.println(words.get(i)[0] + " - " + words.get(i)[1] + " - " + words.get(i)[2]);
		}
	}
}
