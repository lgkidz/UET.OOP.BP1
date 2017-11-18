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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Search extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnOkay;
	private static BufferedReader br;
	private List<String[]> result = new ArrayList<String[]>();
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public Search(String q) {
		setTitle("Search result");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 365, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblShowingSearchResult = new JLabel("Showing search result for: ");
		lblShowingSearchResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblShowingSearchResult.setBounds(10, 11, 163, 23);
		contentPane.add(lblShowingSearchResult);
		
		JLabel query = new JLabel("New label");
		query.setFont(new Font("Tahoma", Font.PLAIN, 14));
		query.setBounds(183, 13, 156, 21);
		contentPane.add(query);
		query.setText("'"  + q + "'");
		
		btnOkay = new JButton("Thanks!");
		btnOkay.setBounds(250, 277, 89, 23);
		contentPane.add(btnOkay);
		btnOkay.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(10, 51, 329, 215);
		contentPane.add(textArea);
		
		do_the_search(q);
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
	
	//read from those learned words and try to find if there are any matches
	public void do_the_search(String query) {
		BufferedReader buff = readFileData(new File("learntwords.dat"));
		try {
			String line;
			while((line = buff.readLine())!=null) {
				if(line.indexOf(query) >-1) {
					String parts[] = line.split(" - ");
					result.add(parts);
				}
			}
			buff.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(result.size()==0) {
			textArea.append("Sorry, I can't find anything about '" + query + "'");
		}
		else {
			for(String[] x:result) {
				textArea.append(x[0] + " - " + x[1] + "\n");
				//System.out.println(x[0] + " - " + x[1]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object btn = arg0.getSource();
		if(btn == btnOkay) {
			this.dispose();
		}
		
	}
}
