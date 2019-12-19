package GUI;
import core.*;
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
import java.awt.Font;

public class GUI_hotelpage {

	private JFrame frame;
	private JTable table;
	private static Order ordr;
    public static Order getOrdr() {
		return ordr;
	}

	public static void setOrdr(Order ordr) {
		GUI_hotelpage.ordr = ordr;
	}
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI_hotelpage() {
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
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel roomnum = new JLabel("\u55AE\u4EBA\u623F");
		roomnum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		roomnum.setBounds(577, 15, 62, 27);
		frame.getContentPane().add(roomnum);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setValue(GUI_search.getChosen_plan().getRoomNum().getSingleNum());
		spinner_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_1.setBounds(634, 12, 85, 33);
		frame.getContentPane().add(spinner_1);		

		JSpinner spinner_4 = new JSpinner();
		spinner_4.setValue(GUI_search.getChosen_plan().getRoomNum().getQuadNum());
		spinner_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_4.setBounds(941, 10, 85, 33);
		frame.getContentPane().add(spinner_4);

		JLabel label_7 = new JLabel("\u96D9\u4EBA\u623F");
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_7.setBounds(731, 16, 63, 27);
		frame.getContentPane().add(label_7);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setValue(GUI_search.getChosen_plan().getRoomNum().getDoubleNum());
		spinner_2.setBounds(782, 12, 85, 33);
		frame.getContentPane().add(spinner_2);

		JLabel label_9 = new JLabel("\u56DB\u4EBA\u623F");
		label_9.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_9.setBounds(879, 16, 54, 27);
		frame.getContentPane().add(label_9);
		/*
		 * RoomNum RNtest = new RoomNum(0, 0, 0); CheckInOutDate CKtest = new
		 * CheckInOutDate(selectedDateout, selectedDateout); Hotel HTtest = new Hotel(0,
		 * 0, null, null, null, null); Plan[] plantest = {new Plan(RNtest, CKtest,
		 * HTtest)};
		 */
		String[] head = { "µû½×" };
		String[] place = GUI_search.getChosen_plan().getHotel().loadcomments();
		String[][] ph = new String[1][place.length];
		for(int i = 0;i<place.length;i++) {
			ph[0][i] = place[i];
		}
//		String[][] test = { {"a","b","c"} ,{"d"}   };
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 348, 835, 315);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(ph, head));
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(450, 63));
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.setRowSelectionAllowed(true);
		
		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RoomNum rn = new RoomNum((Integer) spinner_1.getValue(),
						(Integer) spinner_2.getValue(), (Integer) spinner_4.getValue());
				GUI_search.getChosen_plan().setRoomNum(rn);
			}
		});
		confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirm.setBounds(896, 613, 130, 29);
		frame.getContentPane().add(confirm);
		
		JButton button_user = new JButton("\u4F7F\u7528\u8005");
		button_user.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_user fre = new GUI_user();
			}
		});
		button_user.setFont(new Font("SansSerif", Font.PLAIN, 16));
		button_user.setBounds(896, 572, 130, 29);
		frame.getContentPane().add(button_user);
		
		JLabel hotel_info = new JLabel(GUI_search.getChosen_plan().getHotel().toString());
		hotel_info.setFont(new Font("SansSerif", Font.PLAIN, 20));
		hotel_info.setBounds(10, 12, 538, 200);
		frame.getContentPane().add(hotel_info);
		
		JLabel label = new JLabel("\u7E3D\u50F9:");
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setBounds(577, 92, 62, 27);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel(((Long)GUI_search.getChosen_plan().calTotalPrice()).toString());
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(623, 92, 144, 27);
		frame.getContentPane().add(lblNewLabel);

		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
