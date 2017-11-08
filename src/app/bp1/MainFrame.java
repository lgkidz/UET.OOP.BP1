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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;


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
	private JMenuItem mntmEdit;
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
		setResizable(false);
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
		setBounds(100, 100, 650, 450);
		
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
		
		mntmEdit = new JMenuItem("Edit collection...");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "";
				ListSelectionModel collections_model = table_1.getSelectionModel();
				if(!collections_model.isSelectionEmpty()) {
				name = (String) table_1.getValueAt(table_1.getSelectedRow(), 0);
				}
				new Edit_collection(name).setVisible(true);
			}
		});
		mntmEdit.setEnabled(false);
		mnEdit.add(mntmEdit);
		
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
		mntmAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new About_us().setVisible(true);
			}
		});
		mnHelp.add(mntmAboutUs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(301, 11, 323, 368);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(154, 11, 128, 334);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(154, 356, 128, 23);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listCollection(folder);
			}
		});
		
		JButton btnTodaysNewWords = new JButton("Today's new words!");
		btnTodaysNewWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Today_words().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTodaysNewWords.setBounds(15, 22, 129, 23);
		
		JButton btnTakeAQuick = new JButton("Take a quick test!");
		btnTakeAQuick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sorry! This feature is still in development :(");
			}
		});
		btnTakeAQuick.setBounds(15, 56, 129, 23);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"My Collections"
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
					mntmEdit.setEnabled(true);
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
		contentPane.setLayout(null);
		contentPane.add(btnRefresh);
		contentPane.add(btnTakeAQuick);
		contentPane.add(btnTodaysNewWords);
		contentPane.add(scrollPane_1);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Let's Learn English\u00A9 2017  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(10, 365, 112, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnProgress = new JButton("My progress");
		btnProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sorry! This feature is still in development :(");
			}
		});
		btnProgress.setBounds(15, 124, 129, 23);
		contentPane.add(btnProgress);
		
		JButton btnLearnFromThis = new JButton("Learn this collection");
		btnLearnFromThis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sorry! This feature is still in development :(");
			}
		});
		btnLearnFromThis.setBounds(15, 90, 129, 23);
		contentPane.add(btnLearnFromThis);
		
		JButton btnExportCollection = new JButton("Export Collection");
		btnExportCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Sorry! This feature is still in development :(");
			}
		});
		btnExportCollection.setBounds(15, 233, 129, 23);
		contentPane.add(btnExportCollection);
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
					buff.close();
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
