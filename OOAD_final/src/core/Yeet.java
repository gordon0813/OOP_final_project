package core;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.time.LocalDate;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Yeet {

	private JFrame frame;
	private JTextField lowprice;
	private JTextField highprice;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Yeet window = new Yeet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Yeet() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1080,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Integer[] starin = {1,2,3,4,5};
		JComboBox starlow = new JComboBox(starin);		
		starlow.setBounds(46, 10, 46, 21);
		frame.getContentPane().add(starlow);
		
		JLabel label = new JLabel("\u661F\u7D1A");
		label.setForeground(Color.BLACK);
		label.setBounds(10, 13, 46, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u7E23\u5E02");
		label_1.setBounds(400, 45, 46, 15);
		frame.getContentPane().add(label_1);
		String[] region = {"基隆市","台北市","新北市","桃園縣","新竹市","新竹縣","苗栗縣","台中市 ","彰化縣"
				,"雲林縣","嘉義市","嘉義縣","台南市","韓國","屏東縣","台東縣","花蓮縣","宜蘭縣","澎湖縣","金門縣","連江縣"};
		JComboBox region_box = new JComboBox(region);
		region_box.setBounds(438, 42, 73, 21);
		frame.getContentPane().add(region_box);
		
		JLabel label_2 = new JLabel("\u5165\u4F4F\u65E5\u671F");
		label_2.setBounds(10, 45, 62, 15);
		frame.getContentPane().add(label_2);
	
		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");
		
		UtilDateModel modelin = new UtilDateModel();
		JDatePanelImpl datePanelin = new JDatePanelImpl(modelin, p);
		JDatePickerImpl datePickerin = new JDatePickerImpl(datePanelin, new DateComponentFormatter());
		datePickerin.setSize(130, 38);
		datePickerin.setLocation(63, 42);
		LocalDate selectedDatein = (LocalDate) datePickerin.getModel().getValue();
		
		UtilDateModel modelout = new UtilDateModel();
		JDatePanelImpl datePanelout = new JDatePanelImpl(modelout, p);
		JDatePickerImpl datePickerout = new JDatePickerImpl(datePanelout, new DateComponentFormatter());
		datePickerout.setSize(130, 38);
		datePickerout.setLocation(260, 42);
		LocalDate selectedDateout = (LocalDate) datePickerout.getModel().getValue();
		frame.getContentPane().add(datePickerin);
		frame.getContentPane().add(datePickerout);
				
		JLabel label_3 = new JLabel("\u9000\u623F\u65E5\u671F");
		label_3.setBounds(202, 45, 62, 15);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u4EBA\u6578");
		label_4.setBounds(373, 13, 46, 15);
		frame.getContentPane().add(label_4);
		
		JSpinner guestnum = new JSpinner();
		guestnum.setBounds(409, 10, 46, 22);
		frame.getContentPane().add(guestnum);
		
		JLabel label_5 = new JLabel("\u50F9\u683C");
		label_5.setBounds(168, 13, 46, 15);
		frame.getContentPane().add(label_5);
		
		lowprice = new JTextField();
		lowprice.setText("0");
		lowprice.setBounds(199, 10, 73, 21);
		frame.getContentPane().add(lowprice);
		lowprice.setColumns(10);
		
		JLabel label_6 = new JLabel("~");
		label_6.setBounds(278, 13, 46, 15);
		frame.getContentPane().add(label_6);
		
		highprice = new JTextField();
		highprice.setText("100");
		highprice.setColumns(10);
		highprice.setBounds(292, 10, 73, 21);
		frame.getContentPane().add(highprice);
		
		JLabel roomnum = new JLabel("\u55AE\u4EBA\u623F");
		roomnum.setBounds(465, 13, 46, 15);
		frame.getContentPane().add(roomnum);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(506, 10, 46, 22);
		frame.getContentPane().add(spinner_1);
		
		JLabel label_8 = new JLabel("~");
		label_8.setBounds(102, 13, 46, 15);
		frame.getContentPane().add(label_8);
		
		JComboBox starhigh = new JComboBox(starin);
		starhigh.setBounds(112, 10, 46, 21);
		frame.getContentPane().add(starhigh);
		
		
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(600, 10, 46, 22);
		frame.getContentPane().add(spinner_2);
		
		JLabel label_7 = new JLabel("\u96D9\u4EBA\u623F");
		label_7.setBounds(559, 13, 46, 15);
		frame.getContentPane().add(label_7);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBounds(695, 9, 46, 22);
		frame.getContentPane().add(spinner_4);
		
		JLabel label_9 = new JLabel("\u56DB\u4EBA\u623F");
		label_9.setBounds(654, 12, 46, 15);
		frame.getContentPane().add(label_9);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(465, 90, 46, 15);
		frame.getContentPane().add(lblNewLabel);
		
		
		RoomNum RNtest = new RoomNum(0, 0, 0);
		CheckInOutDate CKtest = new CheckInOutDate(selectedDateout, selectedDateout);
		Hotel HTtest = new Hotel(0, 0, null, null, null, null);
		Plan[] plantest = {new Plan(RNtest, CKtest, HTtest)};
		
		
		String[] head = { "Hotel", "Check in/out date", "Room Plan", "Price" };
		String[][] test = {{"", "", "", ""}};
		for(int i = 0;i<plantest.length;i++) {
			test[i][0] = plantest[i].getHotel().toString();
			test[i][1] = plantest[i].getCheckInOutDate().toString();
			test[i][2] = plantest[i].getRoomNum().toString();
			test[i][3] = ((Long)plantest[i].calTotalPrice()).toString();
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 447, 382);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(test, head));		
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(450, 63));
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.setRowSelectionAllowed(true);
				
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable s = (JTable)e.getSource();
				Integer row = s.getSelectedRow();
				lblNewLabel.setText(row.toString());
			}
		});
		
		JButton searchbutton = new JButton("\u641C\u5C0B");
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		searchbutton.setBounds(654, 41, 87, 23);
		frame.getContentPane().add(searchbutton);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_1.setBounds(10, 477, 217, 118);
		frame.getContentPane().add(table_1);
		
		
		
	//	DefaultTableModel model = (DefaultTableModel) table.getModel();
	
		
		
		Search_input input = new Search_input((Integer)starhigh.getSelectedItem(),(Integer)starlow.getSelectedItem(),Integer.parseInt(highprice.getText()),
				Integer.parseInt(lowprice.getText()),(Integer)guestnum.getValue(),new CheckInOutDate(selectedDatein,selectedDateout),
				new RoomNum((Integer)spinner_1.getValue(),(Integer)spinner_2.getValue(),(Integer)spinner_4.getValue()),region_box.getSelectedObjects().toString());
		}
}
