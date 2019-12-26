package GUI;
import core.*;
import databaseException.noSuchHotel;

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
import java.util.ArrayList;
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
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class GUI_order_manage {

	private JFrame frame;
	private JTable table;
	private static Order chosen_order;
	public static Order getChosen_order() {
		return chosen_order;
	}
	private  ArrayList<Order> order_record;
	private static boolean from_record = false;
	public static void setFrom_record(boolean from_record) {
		GUI_order_manage.from_record = from_record;
	}

	public static boolean isFrom_record() {
		return from_record;
	}

	//to allow GUI_search to tell apart input from different sources
    private boolean chosen = true;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_order_manage window = new GUI_order_manage();
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
	public GUI_order_manage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");

		/*
		 * RoomNum RNtest = new RoomNum(0, 0, 0); CheckInOutDate CKtest = new
		 * CheckInOutDate(selectedDateout, selectedDateout); Hotel HTtest = new Hotel(0,
		 * 0, null, null, null, null); Plan[] plantest = {new Plan(RNtest, CKtest,
		 * HTtest)};
		 */

		String[] head = { "已完成訂單" };
		order_record = User.getUser().getOrderList();
		String[][] ph = new String[1][order_record.size()];
		//search record loaded in default
		for(int i = 0;i<order_record.size();i++) {
			ph[0][i] = order_record.get(i).toString();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 835, 653);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(ph, head));
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(450, 63));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable s = (JTable) e.getSource();
				if(order_record.size() == 0) {
					chosen_order = null;
				}else {
				Integer row = s.getSelectedRow();
				chosen_order = order_record.get(row);	
				chosen = true;
				}
					
			}
		});
		
		JButton button_user = new JButton("\u4F7F\u7528\u8005");
		button_user.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_user fre = new GUI_user();
				frame.dispose();
			}
		});
		button_user.setFont(new Font("SansSerif", Font.PLAIN, 16));
		button_user.setBounds(895, 595, 130, 29);
		frame.getContentPane().add(button_user);
		
		JButton remove = new JButton("\u522A\u9664\u8A02\u55AE");
		remove.setEnabled(chosen);
		remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					User.getUser().deleteOrder(chosen_order);
				} catch (UserException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		remove.setFont(new Font("SansSerif", Font.PLAIN, 16));
		remove.setBounds(895, 458, 130, 29);
		frame.getContentPane().add(remove);
		
		JButton edit = new JButton("\u4FEE\u6539\u8A02\u55AE");
		edit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		edit.setEnabled(chosen);
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				from_record = true;
				GUI_order_confirm fre = new GUI_order_confirm();
				frame.dispose();
			}
		});
		edit.setBounds(895, 497, 130, 29);
		frame.getContentPane().add(edit);
		
		JButton comment = new JButton("\u65C5\u9928\u8CC7\u8A0A");
		comment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				from_record = true;
				try {
					GUI_hotelpage fre = new GUI_hotelpage();
				} catch (noSuchHotel e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		comment.setFont(new Font("SansSerif", Font.PLAIN, 16));
		comment.setBounds(895, 634, 130, 29);
		frame.getContentPane().add(comment);

	}
}
