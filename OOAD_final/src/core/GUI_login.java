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

public class GUI_login {

	private JFrame frame;
	private JTextField username;
	private JTextField password;
	public User usr;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI_login() {
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
	
		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User.login(Integer.parseInt(username.getText()), password.getText());
			}
		});
		confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirm.setBounds(196, 136, 130, 29);
		frame.getContentPane().add(confirm);
		
		JLabel label = new JLabel("\u7528\u6236\u540D");
		label.setFont(new Font("新細明體", Font.PLAIN, 16));
		label.setBounds(10, 22, 78, 20);
		frame.getContentPane().add(label);
		
		username = new JTextField();
		username.setFont(new Font("新細明體", Font.PLAIN, 16));
		username.setBounds(77, 12, 249, 33);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u78BC");
		label_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		label_1.setBounds(10, 77, 78, 20);
		frame.getContentPane().add(label_1);
		
		password = new JTextField();
		password.setFont(new Font("新細明體", Font.PLAIN, 16));
		password.setColumns(10);
		password.setBounds(77, 71, 249, 33);
		frame.getContentPane().add(password);
		
		JButton register = new JButton("\u8A3B\u518A");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean register = User.signup(Integer.parseInt(username.getText()), password.getText());
			}
		});
		register.setFont(new Font("SansSerif", Font.PLAIN, 16));
		register.setBounds(37, 136, 130, 29);
		frame.getContentPane().add(register);

		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
