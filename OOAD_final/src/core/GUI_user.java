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

public class GUI_user {

	private JFrame frame;
	public User usr;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI_user() {
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
	
		JButton search = new JButton("\u67E5\u8A62\u98EF\u5E97");
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				GUI_search fre = new GUI_search();
			}
		});
		search.setFont(new Font("SansSerif", Font.PLAIN, 16));
		search.setBounds(165, 10, 130, 29);
		frame.getContentPane().add(search);
		
		JButton user_data = new JButton("\u57FA\u672C\u8CC7\u6599");
		user_data.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				GUI_search fre = new GUI_search();
			}
		});
		user_data.setFont(new Font("SansSerif", Font.PLAIN, 16));
		user_data.setBounds(10, 10, 130, 29);
		frame.getContentPane().add(user_data);
		
		JButton finished_order = new JButton("\u5DF2\u5B8C\u6210\u8A02\u55AE");
		finished_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				GUI_search fre = new GUI_search();
			}
		});
		finished_order.setFont(new Font("SansSerif", Font.PLAIN, 16));
		finished_order.setBounds(330, 10, 130, 29);
		frame.getContentPane().add(finished_order);
		
		JButton search_history = new JButton("\u67E5\u8A62\u7D00\u9304");
		search_history.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				GUI_search fre = new GUI_search();
			}
		});
		search_history.setFont(new Font("SansSerif", Font.PLAIN, 16));
		search_history.setBounds(498, 10, 130, 29);
		frame.getContentPane().add(search_history);
		
		JButton logout = new JButton("\u767B\u51FA");
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				User.logout();
				GUI_login fre = new GUI_login();
			}
		});
		logout.setFont(new Font("SansSerif", Font.PLAIN, 16));
		logout.setBounds(924, 10, 130, 29);
		frame.getContentPane().add(logout);

		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
