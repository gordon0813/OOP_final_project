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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.time.LocalDate;
import java.util.ArrayList;
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
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class GUI_order_manage {

	private JFrame frame;
	private JTable table;
	private static Order chosen_order;

	public static Order getChosen_order() {
		return chosen_order;
	}

	private ArrayList<Order> order_record;
	private static boolean from_record = false;

	public static void setFrom_record(boolean from_record) {
		GUI_order_manage.from_record = from_record;
	}

	public static boolean isFrom_record() {
		return from_record;
	}

	// to allow GUI_search to tell apart input from different sources
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

		String[] head = { "已完成訂單" };
		order_record = User.getUser().getOrderList();
		String[][] ph = new String[order_record.size()][1];
		// search record loaded in default
		for (int i = 0; i < order_record.size(); i++) {
			ph[i][0] = order_record.get(i).toString();
		}
		
		JButton remove = new JButton("\u522A\u9664\u8A02\u55AE");
		remove.setBackground(Color.BLACK);
		remove.setForeground(Color.WHITE);
		remove.setEnabled(false);
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					User.getUser().deleteOrder(chosen_order);
					order_record = User.getUser().getOrderList();
					String[][] ph = new String[order_record.size()][1];
					for (int i = 0; i < order_record.size(); i++) {
						ph[i][0] = order_record.get(i).toString();
					}
					table.setModel(new DefaultTableModel(ph, head));
				} catch (UserException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		remove.setFont(new Font("SansSerif", Font.PLAIN, 16));
		remove.setBounds(895, 458, 130, 29);
		frame.getContentPane().add(remove);

		JButton edit = new JButton("\u4FEE\u6539\u8A02\u55AE");
		edit.setBackground(Color.BLACK);
		edit.setForeground(Color.WHITE);
		edit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		edit.setEnabled(false);
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				from_record = true;
				GUI_order_confirm fre = new GUI_order_confirm();
				frame.dispose();
			}
		});
		edit.setBounds(895, 497, 130, 29);
		frame.getContentPane().add(edit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 835, 653);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(ph, head));
		table.setForeground(Color.WHITE);
		table.setBackground(Color.BLACK);
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(835, 65));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable s = (JTable) e.getSource();
				if (order_record.size() == 0) {
					chosen_order = null;
				} else {
					Integer row = s.getSelectedRow();
					chosen_order = order_record.get(row);
					chosen = true;
					remove.setEnabled(true);
					edit.setEnabled(true);
				}

			}
		});

		JButton button_user = new JButton("\u4F7F\u7528\u8005");
		button_user.setBackground(Color.BLACK);
		button_user.setForeground(Color.WHITE);
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

		JButton comment = new JButton("\u65C5\u9928\u8CC7\u8A0A");
		comment.setBackground(Color.BLACK);
		comment.setForeground(Color.WHITE);
		comment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				from_record = true;
				try {
					GUI_hotelpage fre = new GUI_hotelpage();
				} catch (noSuchHotel e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "error:", JOptionPane.INFORMATION_MESSAGE);
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
