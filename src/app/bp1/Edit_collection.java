package app.bp1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Edit_collection extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cname;
	private JTable table;
	private JButton btnLu;
	private static BufferedReader br;
	
	public Edit_collection(String collection_name) {
		setResizable(false);
		
		//System.out.println("Addnew Frame Opened");
		setTitle("Edit Collection");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 574, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Name for Collection's title
		JLabel lblTnBT = new JLabel("Collection's name :");
		
		cname = new JTextField();
		cname.setColumns(10);
		cname.setEnabled(false);
		cname.setText(collection_name);
		// -------
		
		JSeparator separator = new JSeparator();
		
		//Add data to the Collection "Them tu va nghia cua tu"
		JLabel lblThmTV = new JLabel("Add Words and translations");
		
		JScrollPane scrollPane = new JScrollPane();
		
		//Add more row
		JButton btnThmDng = new JButton("Add row");
		btnThmDng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[]{"", ""});
			}
		});
		
		//Save Button
		btnLu = new JButton("Save");
		btnLu.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblThmTV)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTnBT)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cname, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLu, Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addComponent(btnThmDng)))
							.addGap(64))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTnBT, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(cname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(lblThmTV)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(btnThmDng)))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(btnLu)
					.addContainerGap())
		);
		
		//Table to input data
		table = new JTable();
		writetabel();
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	//update changes after clicking save button
	public void actionPerformed(ActionEvent e) {
		
		JButton buttonPressed = (JButton) e.getSource();
		if(buttonPressed == btnLu) {
			String collectionName = cname.getText();

			int rowCount = table.getRowCount();
			ArrayList<String[]> list = new ArrayList<String[]>();
			for(int i = 0;i<rowCount;i++) {
				String word[] = new String[2];
				word[0] = (String) table.getModel().getValueAt(i, 0);
				word[1] = (String) table.getModel().getValueAt(i, 1);
				list.add(word);
			}

			try {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				PrintWriter writer = new PrintWriter("data/" + collectionName + ".dat", "UTF-8");
				for(int i = 0;i<list.size();i++) {
					Date today = Calendar.getInstance().getTime();        
			    	String date = df.format(today);
					String line = list.get(i)[1] + " - " + list.get(i)[0] + " - "+ date;
					writer.println(line);
				}
				writer.close();
				JOptionPane.showMessageDialog(null, "Your change has been saved.");
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Cannot edit file!");
			}
			cname.setText("");
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			this.dispose();
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
	
	//write data from current collection to the table
	public void writetabel() {
		File file = new File("data/" + cname.getText() + ".dat");
		DefaultTableModel tbl = (DefaultTableModel) loadTable();
		BufferedReader buff = readFileData(file);
		String s;
		try {
			while((s= buff.readLine())!=null) {
				String[] parts = s.split(" - ");
				tbl.addRow(new Object[] {parts[0],parts[1]});
			}
			buff.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There is something wrong opening this file");
			e.printStackTrace();
		}
	}
	
	private TableModel loadTable() {
		TableModel dataModel = new DefaultTableModel(loadRowData(),loadColumnNme());
		table.setModel(dataModel);
		return dataModel;
	}
	
	//Load main columnNames
	private Object[] loadColumnNme() {
		
		return new Object[] {"English", "Ti\u1EBFng Vi\u1EC7t"};
	}
	
	//Load mainRow
	private Object[][] loadRowData() {
		return new Object[][] {};
	}
}
