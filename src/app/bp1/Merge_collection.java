package app.bp1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

class ComboItem{
    private String key;
    private String value;

    public ComboItem(String key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString(){
        return key;
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }
}

public class Merge_collection extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private final File folder = new File("data");
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<Object> comboBox;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JTextField new_name;
	private JLabel lblNewName;
	private static BufferedReader br;
	private JCheckBox dont_keep;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Merge_collection(String collection_name) {
		setResizable(false);
		setTitle("Merge 2 collections");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 383, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblThisCollection = new JLabel("This collection :");
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setText(collection_name);
		
		JLabel lblNewLabel = new JLabel("Merge with :");
		
		JSeparator separator = new JSeparator();
		
		comboBox = new JComboBox<Object>();
		
		dont_keep = new JCheckBox("Delete old collections");
		listCollection(folder);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(this);
		
		new_name = new JTextField();
		new_name.setColumns(10);
		
		lblNewName = new JLabel("New Name :");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnConfirm)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnCancel))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblThisCollection)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewName))
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(dont_keep))
								.addComponent(new_name, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThisCollection)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dont_keep))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(new_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewName))
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnConfirm)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	//list all available collections for user to select
	public void listCollection(final File folder) {
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
		for(String x:collections) {
			comboBox.addItem(new ComboItem(x,x));
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
	
	//transfer collection's data from file to a list
	public void transfer(File f, ArrayList<String> List) {
		BufferedReader buff = readFileData(f);
		String s;
		try {
			while((s= buff.readLine())!=null) {
				List.add(s);
			}
			buff.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//merge 2 selected collection
	public void merge(File f1, File f2,String f) {
		ArrayList<String> List = new ArrayList<String>();
		transfer(f1, List);
		transfer(f2,List);
		try {
			PrintWriter writer = new PrintWriter(f, "UTF-8");
			for(int i = 0;i<List.size();i++) {
				String line = List.get(i);
				writer.println(line);
			}
			writer.close();
			JOptionPane.showMessageDialog(null, "New collection Created!");
		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Cannot create file!");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton buttonpressed = (JButton) arg0.getSource();
		if(buttonpressed == btnCancel) {
			this.dispose();
		}
		else if(buttonpressed == btnConfirm) {
			Object item = comboBox.getSelectedItem();
			String collection_selected = ((ComboItem)item).getValue();
			if(collection_selected.equals(textField.getText())) {
				JOptionPane.showMessageDialog(null, "You cannot merge this collection with itself!");
			}
			else {
				if(new_name.getText().equals("")) {//if user doesn't choose a name
					JOptionPane.showMessageDialog(null, "Please give your new collection a name.");
				}
				else {
					String folder = "data/";
					String f1_name = folder + textField.getText() + ".dat";
					String f2_name =  folder + collection_selected +  ".dat";
					String final_name =  folder + new_name.getText() + ".dat";
					if(final_name.equals(f1_name) || final_name.equals(f2_name)){//if the name is already exist
						JOptionPane.showMessageDialog(null, "Please give your new collection a different name.");
					}
					else {
						merge(new File(f1_name), new File(f2_name), final_name);
						
						if(dont_keep.isSelected()) {//if user chooses not to keep the old collections
							File f1 = new File(f1_name);
							f1.delete();
							File f2 = new File(f2_name);
							f2.delete();
						}
						this.dispose();
					}
				}
			}
		}
	}
}
