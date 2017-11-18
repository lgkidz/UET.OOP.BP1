package app.bp1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MainFrame extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final File folder = new File("data"); // Folder chua collections

	private JPanel contentPane;
	private JTable table;
	private static BufferedReader br;
	
	private JMenuItem mntmExit;
	private JMenuItem mntmCollection;
	private JTable table_1;
	private JMenuItem mntmRename;
	private JMenuItem mntmMergeCollection;
	private JMenuItem mntmEdit;
	private JButton btnExportCollection;
	private JButton btnRevision;
	private JButton btnTakeAQuick;
	private JTextField search_field;
	private JButton btnSearch;
	//Open new Collection 
	
	//mo file
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
		setBounds(100, 100, 650, 475);
		
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
		//edit collection button
		mntmEdit.setEnabled(false);
		mnEdit.add(mntmEdit);
		//rename collection button
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
		
		//mergeCollection button
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
		//about us button
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
		scrollPane.setBounds(302, 47, 323, 368);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(154, 47, 128, 334);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(154, 392, 128, 23);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
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
		btnTodaysNewWords.setBounds(15, 61, 129, 23);
		
		btnTakeAQuick = new JButton("Take a quick test!");
		btnTakeAQuick.setEnabled(false);
		btnTakeAQuick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Sorry! This feature is still in development :(");
				new Quick_test().setVisible(true);
			}
		});
		btnTakeAQuick.setBounds(15, 95, 129, 23);
		btnRevision = new JButton("Revision");
		btnRevision.setEnabled(false);
		btnRevision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Revision().setVisible(true);
			}
		});
		btnRevision.setBounds(15, 129, 129, 23);
		contentPane.add(btnRevision);
		
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
			
			//check for changes in collections table then write data to main table
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!collections_model.isSelectionEmpty()) {
					mntmRename.setEnabled(true);
					mntmMergeCollection.setEnabled(true);
					mntmEdit.setEnabled(true);
					btnExportCollection.setEnabled(true);
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
		lblNewLabel.setBounds(10, 401, 112, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnProgress = new JButton("My progress");
		btnProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new My_progress().setVisible(true);
			}
		});
		btnProgress.setBounds(15, 163, 129, 23);
		contentPane.add(btnProgress);
		
		
		
		btnExportCollection = new JButton("Export Collection");
		btnExportCollection.setEnabled(false);
		btnExportCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Sorry! This feature is still in development :(");
				exportCollection();
			}
		});
		btnExportCollection.setBounds(15, 326, 129, 23);
		contentPane.add(btnExportCollection);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(536, 11, 89, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(search_field.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter something in the search bar");
				}
				else {
					String query = search_field.getText();
					new Search(query).setVisible(true);
				}
			}
		});
		
		JLabel lblSearchInWhat = new JLabel("Search in what you have learnt :\r\n");
		lblSearchInWhat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSearchInWhat.setBounds(83, 14, 187, 14);
		contentPane.add(lblSearchInWhat);
		
		search_field = new JTextField();
		search_field.setBounds(280, 12, 246, 20);
		contentPane.add(search_field);
		search_field.setColumns(10);
		
		JButton btnImportCollection = new JButton("Import Collection");
		btnImportCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importCollection();
			}
		});
		btnImportCollection.setBounds(15, 292, 129, 23);
		contentPane.add(btnImportCollection);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object item =  e.getSource();
		if(item == mntmExit) {
			System.exit(0);
		}
	}
	
	//Import collection from excel 2007 or above
	public void importCollection() {
		//JOptionPane.showMessageDialog(null, "Workin on dis!");
		JFileChooser chooser = new JFileChooser();
		int select = -1;
		chooser.setDialogTitle("Import a collection...");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Stylesheet 2007 ( *.xlsx)", "xlsx");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		select  = chooser.showOpenDialog(null);
		
		if(select == JFileChooser.APPROVE_OPTION) {
			//JOptionPane.showMessageDialog(null, "Workin on dis!");
			File file = chooser.getSelectedFile();
			POI_IN(file);
		}
		refresh();
	}
	//read data from excel file
	public void POI_IN(File file) {
		String filename = file.getName().split("\\.")[0];
		System.out.println(filename);
		List<String[]> tmplist = new ArrayList<String[]>();
		try {
		    XSSFWorkbook wb = new XSSFWorkbook(file);
		    XSSFSheet sheet = wb.getSheetAt(0);
		    XSSFRow row;
		    XSSFCell cell;

		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 0; // No of columns
		    int tmp = 0;

		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) cols = tmp;
		        }
		    }
		    
		    for(int r = 0; r < rows; r++) {
		        row = sheet.getRow(r);
		        if(row != null) {
		        	String[] parts = new String[3];
		        	int po = 0;
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell((short)c);
		                if(cell != null) {
		                    parts[po] = cell.getStringCellValue();
		                    po++;
		                    if(po == 2) {
		                    	break;
		                    }
		                }
		            }
		            tmplist.add(parts);
		        }
		    }
		    wb.close();
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		try {
			PrintWriter writer = new PrintWriter("data/" + filename + ".dat", "UTF-8");
			for(int i = 0;i<tmplist.size();i++) {
				String today = df.format(Calendar.getInstance().getTime());
				String line = tmplist.get(i)[1] + " - " + tmplist.get(i)[0] + " - "+ today;
				writer.println(line);
			}
			writer.close();
			JOptionPane.showMessageDialog(null, "New collection " + filename + " is imported!");
		}catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Cannot create file!");
		}
	}
	
	//export collection to a excel 2007 sheet
	public void exportCollection() {
		String collection_name = "";
		ListSelectionModel collections_model = table_1.getSelectionModel();
		if(!collections_model.isSelectionEmpty()) {
			collection_name = (String) table_1.getValueAt(table_1.getSelectedRow(), 0);
		}
		
		BufferedReader buff = readFileData(new File("data/" + collection_name +".dat"));
		String s;
		List<String[]> tmplist = new ArrayList<String[]>();
		try {
			while((s= buff.readLine())!=null) {
				String[] parts = s.split(" - ");
				tmplist.add(parts);
			}
			buff.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JFileChooser chooser = new JFileChooser();
		int select = -1;
		chooser.setDialogTitle("Export this collection as...");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Stylesheet 2007 ( *.xlsx)", "xlsx");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setSelectedFile(new File(collection_name + ".xlsx"));
		select  = chooser.showSaveDialog(null);
		
		if(select == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			String name = file.getAbsolutePath();
			System.out.println(name);
			String parts[] = name.split("\\.");
			
			if(!parts[parts.length - 1].equals(".xlsx")) {
				name = name + ".xlsx";
				file = new File(name);
				System.out.println(name);
			}
			try {
				POI_OUT(file,tmplist);
				JOptionPane.showMessageDialog(null, "Collection exported!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			refresh();
		}
	}
	//write data to excel sheet
	public void POI_OUT(File file,List<String[]> list) throws IOException {
		String excelFileName = file.getAbsolutePath();//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		for (int r=0;r < list.size(); r++ )
		{
			XSSFRow row = sheet.createRow(r);

			XSSFCell cell0 = row.createCell(0);	
			cell0.setCellValue(list.get(r)[0]);
			
			XSSFCell cell1 = row.createCell(1);	
			cell1.setCellValue(list.get(r)[1]);
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		wb.close();
	}
	
	//check if there is any words to review
	public void can_revision() {
		File f = new File("learntwords.dat");
		List<String[]> tmp = new ArrayList<String[]>();
		BufferedReader buff = readFileData(f);
		String s;
		try {
			while((s= buff.readLine())!=null) {
				String parts[] = s.split(" - ");
				tmp.add(parts);
			}
			buff.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(!tmp.isEmpty()) {
			btnRevision.setEnabled(true);
			btnTakeAQuick.setEnabled(true);
		}
	}
	
	//list a collections available
	public void listCollection(final File folder) {
		can_revision();
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
	
	//refresh
	public void refresh() {	
		listCollection(folder);
	}
}
