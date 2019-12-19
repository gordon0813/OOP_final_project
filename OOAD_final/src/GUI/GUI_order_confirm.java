package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import core.UserException;

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

public class GUI_order_confirm {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public GUI_order_confirm() {
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
		
		JButton confirm = new JButton("\u78BA\u5B9A");
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					GUI_hotelpage.getOrdr().confirm();
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
		label.setBounds(577, 92, 62, 27);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel(((Long)GUI_search.getChosen_plan().calTotalPrice()).toString());
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(623, 92, 144, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel order_info = new JLabel(GUI_hotelpage.getOrdr().toString());
		order_info.setFont(new Font("�s�ө���", Font.PLAIN, 20));
		order_info.setBounds(10, 22, 542, 218);
		frame.getContentPane().add(order_info);

		// DefaultTableModel model = (DefaultTableModel) table.getModel();

	}
}
