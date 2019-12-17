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
import java.awt.Font;

public class GUI_hotelpage {

	private JFrame frame;
	private JTextField lowprice;
	private JTextField highprice;
	private JTable table;

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

		Integer[] starin = { 1, 2, 3, 4, 5 };
		JComboBox starlow = new JComboBox(starin);
		starlow.setFont(new Font("SansSerif", Font.PLAIN, 16));
		starlow.setBounds(48, 10, 54, 38);
		frame.getContentPane().add(starlow);

		JLabel label = new JLabel("\u661F\u7D1A                  ~");
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setForeground(Color.BLACK);
		label.setBounds(10, 21, 121, 15);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u7E23\u5E02");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_1.setBounds(438, 93, 46, 35);
		frame.getContentPane().add(label_1);
		String[] region = { "基隆市", "台北市", "新北市", "桃園縣", "新竹市", "新竹縣", "苗栗縣", "台中市 ", "彰化縣", "雲林縣", "嘉義市", "嘉義縣", "台南市",
				"韓國", "屏東縣", "台東縣", "花蓮縣", "宜蘭縣", "澎湖縣", "金門縣", "連江縣" };
		JComboBox region_box = new JComboBox(region);
		region_box.setFont(new Font("SansSerif", Font.PLAIN, 16));
		region_box.setBounds(480, 90, 85, 38);
		frame.getContentPane().add(region_box);

		JLabel label_2 = new JLabel("\u5165\u4F4F\u65E5\u671F");
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_2.setBounds(10, 93, 81, 35);
		frame.getContentPane().add(label_2);

		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");

		UtilDateModel modelin = new UtilDateModel();
		JDatePanelImpl datePanelin = new JDatePanelImpl(modelin, p);
		JDatePickerImpl datePickerin = new JDatePickerImpl(datePanelin, new DateComponentFormatter());
		datePickerin.setSize(130, 38);
		datePickerin.setLocation(86, 93);
		LocalDate selectedDatein = (LocalDate) datePickerin.getModel().getValue();

		UtilDateModel modelout = new UtilDateModel();
		JDatePanelImpl datePanelout = new JDatePanelImpl(modelout, p);
		JDatePickerImpl datePickerout = new JDatePickerImpl(datePanelout, new DateComponentFormatter());
		datePickerout.setSize(130, 38);
		datePickerout.setLocation(296, 93);
		LocalDate selectedDateout = (LocalDate) datePickerout.getModel().getValue();
		frame.getContentPane().add(datePickerin);
		frame.getContentPane().add(datePickerout);

		JLabel label_3 = new JLabel("\u9000\u623F\u65E5\u671F");
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_3.setBounds(222, 93, 81, 35);
		frame.getContentPane().add(label_3);

		JLabel label_4 = new JLabel("\u4EBA\u6578");
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_4.setBounds(438, 22, 46, 15);
		frame.getContentPane().add(label_4);

		JSpinner guestnum = new JSpinner();
		guestnum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		guestnum.setBounds(480, 10, 85, 33);
		frame.getContentPane().add(guestnum);

		JLabel label_5 = new JLabel("\u50F9\u683C                        ~");
		label_5.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_5.setBounds(204, 21, 148, 15);
		frame.getContentPane().add(label_5);

		lowprice = new JTextField();
		lowprice.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lowprice.setText("0");
		lowprice.setBounds(246, 11, 81, 36);
		frame.getContentPane().add(lowprice);
		lowprice.setColumns(10);

		highprice = new JTextField();
		highprice.setFont(new Font("SansSerif", Font.PLAIN, 16));
		highprice.setText("100");
		highprice.setColumns(10);
		highprice.setBounds(352, 10, 81, 38);
		frame.getContentPane().add(highprice);

		JLabel roomnum = new JLabel("\u55AE\u4EBA\u623F");
		roomnum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		roomnum.setBounds(577, 15, 62, 27);
		frame.getContentPane().add(roomnum);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_1.setBounds(634, 12, 85, 33);
		frame.getContentPane().add(spinner_1);

		JComboBox starhigh = new JComboBox(starin);
		starhigh.setFont(new Font("SansSerif", Font.PLAIN, 16));
		starhigh.setBounds(131, 10, 62, 38);
		frame.getContentPane().add(starhigh);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_2.setBounds(941, 10, 85, 33);
		frame.getContentPane().add(spinner_2);

		JLabel label_7 = new JLabel("\u96D9\u4EBA\u623F");
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_7.setBounds(731, 16, 63, 27);
		frame.getContentPane().add(label_7);

		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBounds(782, 12, 85, 33);
		frame.getContentPane().add(spinner_4);

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

		String[] head = { "Hotel", "Check in/out date", "Room Plan", "Price" };
		String[][] ph = {};
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 835, 511);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(ph, head));
		JButton searchbutton = new JButton("\u641C\u5C0B");
		searchbutton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search_input input = new Search_input((Integer) starhigh.getSelectedItem(),
						(Integer) starlow.getSelectedItem(), Integer.parseInt(highprice.getText()),
						Integer.parseInt(lowprice.getText()), (Integer) guestnum.getValue(),
						new CheckInOutDate(selectedDatein, selectedDateout), new RoomNum((Integer) spinner_1.getValue(),
								(Integer) spinner_2.getValue(), (Integer) spinner_4.getValue()),
						region_box.getSelectedObjects().toString());
				Plan[] plan_input = Hotel.search(input);
				String[][] test = {};
				for (int i = 0; i < plan_input.length; i++) {
					test[i][0] = plan_input[i].getHotel().toString();
					test[i][1] = plan_input[i].getCheckInOutDate().toString();
					test[i][2] = plan_input[i].getRoomNum().toString();
					test[i][3] = ((Long) plan_input[i].calTotalPrice()).toString();
				}
				table.setModel(new DefaultTableModel(test,head));
			}
		});

		searchbutton.setBounds(598, 96, 99, 29);
		frame.getContentPane().add(searchbutton);
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(450, 63));
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.setRowSelectionAllowed(true);
		
		JButton byprice = new JButton("\u4EE5\u50F9\u683C\u6392\u5217");
		byprice.setFont(new Font("SansSerif", Font.PLAIN, 16));
		byprice.setBounds(896, 184, 130, 29);
		frame.getContentPane().add(byprice);
		
		JButton bystar = new JButton("\u4EE5\u661F\u7D1A\u6392\u5217");
		bystar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		bystar.setBounds(896, 258, 130, 29);
		frame.getContentPane().add(bystar);
		
		JButton byhotel = new JButton("\u4EE5\u98EF\u5E97\u6392\u5217");
		byhotel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		byhotel.setBounds(896, 337, 130, 29);
		frame.getContentPane().add(byhotel);
		
		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirm.setBounds(896, 613, 130, 29);
		frame.getContentPane().add(confirm);
		
		JButton button_user = new JButton("\u4F7F\u7528\u8005");
		button_user.setFont(new Font("SansSerif", Font.PLAIN, 16));
		button_user.setBounds(896, 572, 130, 29);
		frame.getContentPane().add(button_user);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable s = (JTable) e.getSource();
				Integer row = s.getSelectedRow();
			}
		});

		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
