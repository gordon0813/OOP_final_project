package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import core.CheckInOutDate;
import core.Order;
import core.RoomNum;
import core.User;
import core.UserException;
import databaseException.exceedSchedule;
import databaseException.noSuchHotel;
import databaseException.nomoreRoom;

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
import java.sql.SQLException;
import java.awt.Font;

public class GUI_order_confirm {

	private JFrame frame;
	private JTable table;
	private Order current_order = GUI_hotelpage.getOrdr();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI_order_confirm() {
		if(GUI_order_manage.isFrom_record()) {
			current_order = GUI_order_manage.getChosen_input();
		}
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
		
		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");
		
		UtilDateModel modelin = new UtilDateModel();
		//can't really turn localdate into date...
		modelin.setYear(current_order.getPlan().getCheckInOutDate().getCheckin().getYear());
		modelin.setMonth(current_order.getPlan().getCheckInOutDate().getCheckin().getMonthValue());
		modelin.setDay(current_order.getPlan().getCheckInOutDate().getCheckin().getDayOfMonth());
		JDatePanelImpl datePanelin = new JDatePanelImpl(modelin, p);
		JDatePickerImpl datePickerin = new JDatePickerImpl(datePanelin, new DateComponentFormatter());
		datePickerin.setSize(130, 38);
		datePickerin.setLocation(664, 93);
		

		UtilDateModel modelout = new UtilDateModel();
		modelin.setYear(current_order.getPlan().getCheckInOutDate().getCheckout().getYear());
		modelin.setMonth(current_order.getPlan().getCheckInOutDate().getCheckout().getMonthValue());
		modelin.setDay(current_order.getPlan().getCheckInOutDate().getCheckout().getDayOfMonth());
		JDatePanelImpl datePanelout = new JDatePanelImpl(modelout, p);
		JDatePickerImpl datePickerout = new JDatePickerImpl(datePanelout, new DateComponentFormatter());
		datePickerout.setSize(130, 38);
		datePickerout.setLocation(896, 93);
		
		frame.getContentPane().add(datePickerin);
		frame.getContentPane().add(datePickerout);

		JLabel roomnum = new JLabel("\u55AE\u4EBA\u623F");
		roomnum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		roomnum.setBounds(577, 15, 62, 27);
		frame.getContentPane().add(roomnum);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setValue(current_order.getPlan().getRoomNum().getSingleNum());
		spinner_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_1.setBounds(634, 12, 85, 33);
		frame.getContentPane().add(spinner_1);		

		JSpinner spinner_4 = new JSpinner();
		spinner_4.setValue(current_order.getPlan().getRoomNum().getQuadNum());
		spinner_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_4.setBounds(941, 10, 85, 33);
		frame.getContentPane().add(spinner_4);

		JLabel label_7 = new JLabel("\u96D9\u4EBA\u623F");
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_7.setBounds(731, 16, 63, 27);
		frame.getContentPane().add(label_7);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_2.setValue(current_order.getPlan().getRoomNum().getDoubleNum());
		spinner_2.setBounds(782, 12, 85, 33);
		frame.getContentPane().add(spinner_2);

		JLabel label_9 = new JLabel("\u56DB\u4EBA\u623F");
		label_9.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_9.setBounds(879, 16, 54, 27);
		frame.getContentPane().add(label_9);
		
		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					LocalDate selectedDatein = (LocalDate) datePickerin.getModel().getValue();
					LocalDate selectedDateout = (LocalDate) datePickerout.getModel().getValue();
					RoomNum rn = new RoomNum((Integer) spinner_1.getValue(),
							(Integer) spinner_2.getValue(), (Integer) spinner_4.getValue());
					CheckInOutDate ckio = new CheckInOutDate(selectedDatein, selectedDateout);
					current_order.getPlan().setRoomNum(rn);
					current_order.getPlan().setCheckInOutDate(ckio);
					if(GUI_order_manage.isFrom_record()) {
						User.getUser().editOrder(current_order);
						GUI_order_manage.setFrom_record(false);
					}else{
						try {
							current_order.confirm();
						} catch (noSuchHotel e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (exceedSchedule e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (nomoreRoom e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (UserException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
		JLabel label = new JLabel("\u7E3D\u50F9:");
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setBounds(577, 186, 62, 27);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel(((Long)current_order.getPlan().calTotalPrice()).toString());
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(622, 186, 144, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel order_info = new JLabel(current_order.toString());
		order_info.setFont(new Font("·s²Ó©úÅé", Font.PLAIN, 20));
		order_info.setBounds(10, 10, 542, 218);
		frame.getContentPane().add(order_info);
		
		JLabel label_1 = new JLabel("\u5165\u4F4F\u65E5\u671F:");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_1.setBounds(577, 93, 77, 27);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u9000\u623F\u65E5\u671F:");
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_2.setBounds(804, 93, 77, 27);
		frame.getContentPane().add(label_2);

		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
