package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import core.User;
import core.UserException;
import databaseException.userExist;

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

public class GUI_login {

	private JFrame frame;
	private JTextField username;
	private JTextField password;
	private static String error = "correct";
	public User usr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_login window = new GUI_login();
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
	
		JLabel errormsg = new JLabel("...");
		errormsg.setBounds(10, 188, 316, 20);
		frame.getContentPane().add(errormsg);
		
		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					User.login(username.getText(), password.getText());
					errormsg.setText(error);
					if(error.matches("correct")) {
						GUI_user fre = new GUI_user();
						frame.dispose();
					}						
				} catch (UserException e1) {
					System.out.println("s");
					frame.dispose();
					GUI_login fre = new GUI_login();
					e1.printStackTrace();
				} catch (Exception e1) {
					System.out.println("s2");
					frame.dispose();
					GUI_login fre = new GUI_login();
					e1.printStackTrace();
				}
			}
		});
		confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirm.setBounds(130, 136, 110, 29);
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
				try {
					try {
						boolean register = User.signup(username.getText(), password.getText());
					} catch (userExist e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		register.setFont(new Font("SansSerif", Font.PLAIN, 16));
		register.setBounds(10, 136, 110, 29);
		frame.getContentPane().add(register);
		
		JButton guest = new JButton("\u8A2A\u5BA2\u767B\u5165");
		guest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_search fre = new GUI_search();
				frame.dispose();
			}
		});
		guest.setFont(new Font("SansSerif", Font.PLAIN, 16));
		guest.setBounds(250, 136, 110, 29);
		frame.getContentPane().add(guest);
		

	}

	public static String getError() {
		return error;
	}

	public static void setError(String error) {
		GUI_login.error = error;
	}
}
