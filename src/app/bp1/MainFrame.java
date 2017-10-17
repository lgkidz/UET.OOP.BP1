package app.bp1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private static BufferedReader br;
	
	
	public BufferedReader readDefaultData() {
		try {
			File in = new File("beginner.dat");
			br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(in), "UTF8"));
		}catch (Exception e) {
			System.out.println("Cannot read file!");
		}
		return br;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private TableModel loadTable() {
		TableModel dataModel = new DefaultTableModel(loadRowData(),loadColumnNme());
		table.setModel(dataModel);
		return dataModel;
	}
	
	private Object[] loadColumnNme() {
		
		return new Object[] {"English", "Ti\u1EBFng Vi\u1EC7t"};
	}
	
	private Object[][] loadRowData() {
		return new Object[][] {};
	}
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Let's learn English! LOOL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnLoadData = new JButton("Load data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JButton btn = (JButton)arg0.getSource();
				if(btn == btnLoadData) {
					DefaultTableModel tbl = (DefaultTableModel) loadTable();
					BufferedReader buff = readDefaultData();
					String s;
					try {
						while((s= buff.readLine())!=null) {
							String[] parts = s.split("\\-");
							tbl.addRow(new Object[] {parts[0],parts[1]});
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnLoadData)
					.addPreferredGap(ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(76)
					.addComponent(btnLoadData)
					.addContainerGap(397, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
