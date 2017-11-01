package app.bp1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AddNewCollection extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cname;
	private JTable table;
	private JButton btnLu;
	public AddNewCollection() {
		//SystemUI
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InstantiationException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		//System.out.println("Addnew Frame Opened");
		setTitle("New Collection");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 574, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Name for Collection's title
		JLabel lblTnBT = new JLabel("T\u00EAn b\u1ED9 t\u1EEB :");
		
		cname = new JTextField();
		cname.setColumns(10);
		// -------
		
		JSeparator separator = new JSeparator();
		
		//Add data to the Collection "Them tu va nghia cua tu"
		JLabel lblThmTV = new JLabel("Th\u00EAm t\u1EEB v\u00E0 ngh\u0129a c\u1EE7a t\u1EEB :");
		
		JScrollPane scrollPane = new JScrollPane();
		
		//Add more row
		JButton btnThmDng = new JButton("Th\u00EAm d\u00F2ng");
		btnThmDng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[]{"", ""});
			}
		});
		
		
		//Reset Button
		JButton btnXaHt = new JButton("X\u00F3a h\u1EBFt");
		btnXaHt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cname.setText("");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
			}
		});
		
		//Save Button
		btnLu = new JButton("L\u01B0u l\u1EA1i");
		btnLu.addActionListener(this);
		
		//UI created by Eclipse
		JLabel error = new JLabel("");
		error.setForeground(Color.RED);
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
							.addComponent(lblTnBT, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cname, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(error)
							.addGap(185))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnXaHt)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnLu))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
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
						.addComponent(cname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(error))
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnXaHt)
						.addComponent(btnLu))
					.addContainerGap())
		);
		
		//Table to input data
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Ti\u1EBFng Vi\u1EC7t", "English"
			}
		));
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
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
				PrintWriter writer = new PrintWriter("data/" + collectionName + ".dat", "UTF-8");
				for(int i = 0;i<list.size();i++) {
					String line = list.get(i)[1] + " - " + list.get(i)[0] + " - "+ new Date();
					writer.println(line);
				}
				writer.close();
				JOptionPane.showMessageDialog(null, "New collection " + collectionName + " Created!");
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Cannot create file!");
			}
			cname.setText("");
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			this.dispose();
		}
	}
}
