package app.bp1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class Rename_collection extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField oldn_name;
	private JTextField new_name;
	private JButton btnconfirm;
	private JButton btnCancel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Rename_collection(String collection_name) {
		setResizable(false);
		setTitle("Rename this collection");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 366, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Old name :");
		
		oldn_name = new JTextField();
		oldn_name.setColumns(10);
		oldn_name.setEditable(false);
		oldn_name.setText(collection_name);
		
		JLabel lblNewName = new JLabel("New Name:");
		
		new_name = new JTextField();
		new_name.setColumns(10);
		
		btnconfirm = new JButton("Confirm");
		btnconfirm.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewName))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(new_name, 197, 197, 197)
						.addComponent(oldn_name, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(188, Short.MAX_VALUE)
					.addComponent(btnconfirm)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(oldn_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewName)
						.addComponent(new_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnconfirm)
						.addComponent(btnCancel)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	//get the new name and rename, no big deal
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton) arg0.getSource();
		if(btn == btnconfirm) {
			String name = oldn_name.getText() + ".dat";
			String newname =  new_name.getText() + ".dat";
			//System.out.println(name + " , " + newname);
			if(newname.equals(".dat")) {
				JOptionPane.showMessageDialog(null, "Please enter a new name!");
			}
			else {
				File x1 = new File("data/" + name);
				//System.out.println(name + " , " + newname);
		    	x1.renameTo(new File("data/" + newname));
		    	this.dispose();
			}
		}
		else if(btn == btnCancel) {
			this.dispose();
		}
	}
}
