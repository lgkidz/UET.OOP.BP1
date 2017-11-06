package app.bp1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;


public class MainFrame extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final File folder = new File("data");
	
	private static final int FILE_OPEN = 1;
	private static final int FILE_SAVE = 2;
	private JPanel contentPane;
	private JTable table;
	private static BufferedReader br;
	
	private JMenuItem mntmExit;
	private JMenuItem mntmCollection;
	private JMenuItem mntmOpenCollection;
	private JTable table_1;
	private JMenuItem mntmRename;
	private JMenuItem mntmMergeCollection;
	
	//Open new Collection 
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
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Load Main Table
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
	
	/**
	 * Create the frame.
	 */
	public MainFrame(){
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
		//Basic Frame
		setTitle("Let's learn English! LOOL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 552);
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		//File button
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		
		//New Collection menuItem
		mntmCollection = new JMenuItem("New Collection");
		mntmCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddNewCollection().setVisible(true);
			}
		});
		mntmCollection.setMnemonic(KeyEvent.VK_N);
		mnFile.add(mntmCollection);
		
		//Open Collection menuItem
		mntmOpenCollection = new JMenuItem("Open Collection...");
		mntmOpenCollection.setMnemonic(KeyEvent.VK_O);
		mnFile.add(mntmOpenCollection);
		mntmOpenCollection.addActionListener(this);
		mnFile.addSeparator();
		
		//Exit menuItem
		mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic(KeyEvent.VK_X);
		mnFile.add(mntmExit);
		mntmExit.addActionListener(this);
		
		//Edit Button
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic(KeyEvent.VK_E);
		menuBar.add(mnEdit);
		
		mntmRename = new JMenuItem("Rename...");
		mntmRename.setEnabled(false);
		mnEdit.add(mntmRename);
		mntmRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = "";
				ListSelectionModel collections_model = table_1.getSelectionModel();
				if(!collections_model.isSelectionEmpty()) {
				name = (String) table_1.getValueAt(table_1.getSelectedRow(), 0);
				}
				new Rename_collection(name).setVisible(true);
			}
		});
		
		mntmMergeCollection = new JMenuItem("Merge collections...");
		mntmMergeCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "";
				ListSelectionModel collections_model = table_1.getSelectionModel();
				if(!collections_model.isSelectionEmpty()) {
				name = (String) table_1.getValueAt(table_1.getSelectedRow(), 0);
				}
				new Merge_collection(name).setVisible(true);
			}
		});
		mntmMergeCollection.setEnabled(false);
		mnEdit.add(mntmMergeCollection);
		
		//Help buton
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);
		
		JMenuItem mntmHowToUse = new JMenuItem("How to use");
		mnHelp.add(mntmHowToUse);
		
		JMenuItem mntmAboutUs = new JMenuItem("About us");
		mnHelp.add(mntmAboutUs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listCollection(folder);
			}
		});
		
		//Layout
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(290)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnRefresh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(btnRefresh))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Collections"
			}
		));
		listCollection(folder);
		scrollPane_1.setViewportView(table_1);
		ListSelectionModel collections_model = table_1.getSelectionModel();
		collections_model.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!collections_model.isSelectionEmpty()) {
					mntmRename.setEnabled(true);
					mntmMergeCollection.setEnabled(true);
					String col_name = (String) table_1.getValueAt(table_1.getSelectedRow(), 0);
					DefaultTableModel tbl = (DefaultTableModel) loadTable();
					File file = new File("data/" + col_name + ".dat");
					BufferedReader buff = readFileData(file);
					String s;
					try {
						while((s= buff.readLine())!=null) {
							String[] parts = s.split(" - ");
							tbl.addRow(new Object[] {parts[0],parts[1]});
						}
						buff.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				
			}
		});
		
		//MainTable
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem)e.getSource();
		if(item == mntmExit) {
			System.exit(0);
		}
		else if(item == mntmOpenCollection) {
			openFile("Open a Collection", FILE_OPEN);
		}
	}
	

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
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0);
		for(String col: collections) {
			model.addRow(new Object[]{col});
		}
	}
	
	public void openFile(String title,int type) {
		JFileChooser chooser = new JFileChooser();
		int choose = -1;
		chooser.setDialogTitle(title);
		
		switch(type) {
		case FILE_OPEN:{
			choose = chooser.showOpenDialog(null);
			break;
		}
		case FILE_SAVE:{
			break;
		}
		}
		
		if(choose == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			switch(type) {
			case FILE_OPEN:{
				DefaultTableModel tbl = (DefaultTableModel) loadTable();
				BufferedReader buff = readFileData(file);
				String s;
				try {
					while((s= buff.readLine())!=null) {
						String[] parts = s.split(" - ");
						tbl.addRow(new Object[] {parts[0],parts[1]});
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "There is something wrong opening this file");
					e.printStackTrace();
				}
				break;
			}
			case FILE_SAVE:{
				break;
			}
			}
		}
	}
}
