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
import core.Plan;
import core.RoomNum;
import core.User;
import core.UserException;
import databaseException.exceedSchedule;
import databaseException.noSuchHotel;
import databaseException.nomoreRoom;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JSpinner;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.BorderLayout;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Graphics;

public class GUI_order_confirm {

	private JFrame frame;
	private JTable table;
	private JTable hotel_info;
	private Order current_order = GUI_hotelpage.getOrdr();
	private Plan current_plan ;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public GUI_order_confirm() {
		if (GUI_order_manage.isFrom_record()) {
			System.out.println("isfrom");
			current_order = GUI_order_manage.getChosen_order();
		}
		current_plan = current_order.getPlan();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		try {
			final BufferedImage backgroundImage = ImageIO.read(new File("images/8.png"));
			frame.setContentPane(new JPanel(new BorderLayout()) {
				@Override
				public void paintComponent(Graphics g) {
					g.drawImage(backgroundImage, 0, 0, null);
				}
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
		Date in = java.sql.Date.valueOf(current_plan.getCheckInOutDate().getCheckin());
		modelin.setValue(in);
		JDatePanelImpl datePanelin = new JDatePanelImpl(modelin, p);
		JDatePickerImpl datePickerin = new JDatePickerImpl(datePanelin, new DateComponentFormatter());
		datePickerin.setBackground(Color.BLACK);
		datePickerin.setForeground(Color.WHITE);
		datePickerin.getJFormattedTextField().setText(in.toString());
		datePickerin.setSize(130, 38);
		datePickerin.setLocation(664, 93);

		UtilDateModel modelout = new UtilDateModel();
		Date out = java.sql.Date.valueOf(current_plan.getCheckInOutDate().getCheckout());
		modelout.setValue(out);
		JDatePanelImpl datePanelout = new JDatePanelImpl(modelout, p);
		JDatePickerImpl datePickerout = new JDatePickerImpl(datePanelout, new DateComponentFormatter());
		datePickerout.setBackground(Color.BLACK);
		datePickerout.setForeground(Color.WHITE);
		datePickerout.getJFormattedTextField().setText(out.toString());
		datePickerout.setSize(130, 38);
		datePickerout.setLocation(896, 93);

		frame.getContentPane().add(datePickerin);
		frame.getContentPane().add(datePickerout);

		JLabel roomnum = new JLabel("\u55AE\u4EBA\u623F");
		roomnum.setForeground(Color.WHITE);
		roomnum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		roomnum.setBounds(577, 15, 62, 27);
		frame.getContentPane().add(roomnum);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setForeground(Color.WHITE);
		spinner_1.setBackground(Color.BLACK);
		spinner_1.setValue(current_plan.getRoomNum().getSingleNum());
		spinner_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_1.setBounds(634, 12, 85, 33);
		frame.getContentPane().add(spinner_1);

		JSpinner spinner_4 = new JSpinner();
		spinner_4.setForeground(Color.WHITE);
		spinner_4.setBackground(Color.BLACK);
		spinner_4.setValue(current_plan.getRoomNum().getQuadNum());
		spinner_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_4.setBounds(941, 10, 85, 33);
		frame.getContentPane().add(spinner_4);

		JLabel label_7 = new JLabel("\u96D9\u4EBA\u623F");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_7.setBounds(731, 16, 63, 27);
		frame.getContentPane().add(label_7);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setForeground(Color.WHITE);
		spinner_2.setBackground(Color.BLACK);
		spinner_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		spinner_2.setValue(current_plan.getRoomNum().getDoubleNum());
		spinner_2.setBounds(782, 12, 85, 33);
		frame.getContentPane().add(spinner_2);

		JLabel label_9 = new JLabel("\u56DB\u4EBA\u623F");
		label_9.setForeground(Color.WHITE);
		label_9.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_9.setBounds(879, 16, 54, 27);
		frame.getContentPane().add(label_9);

		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.setBackground(Color.BLACK);
		confirm.setForeground(Color.WHITE);
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Date datein = (Date) (datePickerin.getModel().getValue());
					Date dateout = (Date) datePickerout.getModel().getValue();
					System.out.println(datein.toString());
					LocalDate selectedDatein = datein.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate selectedDateout = dateout.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					RoomNum rn = new RoomNum((Integer) spinner_1.getValue(), (Integer) spinner_2.getValue(),
							(Integer) spinner_4.getValue());
					CheckInOutDate ckio = new CheckInOutDate(selectedDatein, selectedDateout);
					if(!current_plan.getCheckInOutDate().contain(ckio)) {
						JOptionPane.showMessageDialog(null, "只能縮短日期，訂單未更改", "error:", JOptionPane.INFORMATION_MESSAGE);
					}else {
						current_plan.setCheckInOutDate(ckio);	
					}
					current_plan.setRoomNum(rn);
									
					if (GUI_order_manage.isFrom_record()) {
						try {
							System.out.println("yes");
							current_order.editOrder(current_plan);
							frame.dispose();
							GUI_user fre = new GUI_user();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						}
						GUI_order_manage.setFrom_record(false);
					} else {
						try {
							current_plan.toOrder().confirm();
							System.out.println(current_order);
							GUI_search.getFrame().dispose();
							GUI_hotelpage.getFrame().dispose();
							frame.dispose();
							GUI_user fre = new GUI_user();
						} catch (noSuchHotel e1) {
							JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						} catch (exceedSchedule e1) {
							JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						} catch (nomoreRoom e1) {
							JOptionPane.showMessageDialog(null, "剩餘房間數量不足!", "error:", JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						}
					}
				} catch (UserException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirm.setBounds(896, 613, 130, 29);
		frame.getContentPane().add(confirm);

		JButton button_user = new JButton("\u4F7F\u7528\u8005");
		button_user.setBackground(Color.BLACK);
		button_user.setForeground(Color.WHITE);
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
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setBounds(577, 186, 62, 27);
		frame.getContentPane().add(label);

		JLabel lblNewLabel = new JLabel(((Long) current_plan.calTotalPrice()).toString());
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(622, 186, 144, 27);
		frame.getContentPane().add(lblNewLabel);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 15, 520, 140);
		frame.getContentPane().add(scrollPane_2);

		JTable order_info = new JTable();
		order_info.setFont(new Font("新細明體", Font.PLAIN, 20));
		order_info.setBounds(10, 10, 542, 218);
		frame.getContentPane().add(order_info);

		String[][] inf = { { "用戶", User.getUser().getname() }, { "訂單號", ((Long) current_order.getId()).toString() },
				{ "飯店號", ((Integer) current_plan.getHotel().getId()).toString() },
				{ "星級", ((Integer) current_order.getPlan().getHotel().getStar()).toString() },
				{ "地址", current_order.getPlan().getHotel().getAddress() },
				{ "原日期", current_plan.getCheckInOutDate().toString() },
				{ "原訂房", current_plan.getRoomNum().toString() } };
		String[] infhead = { "訂單資訊", "" };
		hotel_info = new JTable(new DefaultTableModel(inf, infhead));
		hotel_info.setForeground(Color.WHITE);
		hotel_info.setBackground(Color.BLACK);
		scrollPane_2.setViewportView(hotel_info);
		hotel_info.setPreferredScrollableViewportSize(new Dimension(63, 63));

		JLabel label_1 = new JLabel("\u5165\u4F4F\u65E5\u671F:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_1.setBounds(577, 93, 77, 27);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("\u9000\u623F\u65E5\u671F:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_2.setBounds(804, 93, 77, 27);
		frame.getContentPane().add(label_2);

		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
