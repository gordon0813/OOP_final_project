package GUI;

import core.*;
import databaseException.noSuchHotel;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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

public class GUI_record {

	private JFrame frame;
	private JTable table;
	private static Plan chosen_plan;
	private static Search_input chosen_input;
	private static int index_plan;
	private static int index_input;

	public static Search_input getChosen_input() {
		return chosen_input;
	}

	private static ArrayList<Search_input> search_record;
	private static ArrayList<Plan> bkmark;
	private int mode = 0;
	// 0 for search, 1 for bookmark
	private static boolean from_record = false;

	public static void setFrom_record(boolean from_record) {
		GUI_record.from_record = from_record;
	}

	public static boolean isFrom_record() {
		return from_record;
	}

	// to allow GUI_search to tell apart input from different sources
	public static Plan getChosen_plan() {
		return chosen_plan;
	}

	private boolean chosen = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_record window = new GUI_record();
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
	public GUI_record() {
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

		String[] head = { "¬ö¿ý" };
		search_record = User.getUser().getRecord();
		bkmark = User.getUser().getPageMark();
		String[][] ph = new String[search_record.size()][1];
		String[][] rc = new String[bkmark.size()][1];
		// search record loaded in default
		for (int i = 0; i < search_record.size(); i++) {
			ph[i][0] = search_record.get(i).toString();
		}
		for (int i = 0; i < bkmark.size(); i++) {
			rc[i][0] = bkmark.get(i).toString();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 835, 602);
		frame.getContentPane().add(scrollPane);
		table = new JTable(new DefaultTableModel(ph, head));
		table.setBackground(Color.BLACK);
		table.setForeground(Color.WHITE);

		JButton recordbutton = new JButton("\u641C\u5C0B\u7D00\u9304");
		recordbutton.setForeground(Color.WHITE);
		recordbutton.setBackground(Color.BLACK);
		recordbutton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		recordbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 0;
				table.setModel(new DefaultTableModel(ph, head));
			}
		});

		recordbutton.setBounds(10, 10, 167, 29);
		frame.getContentPane().add(recordbutton);
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(450, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable s = (JTable) e.getSource();
				if (mode == 0) {
					if (search_record.size() == 0) {
						chosen_input = null;
					} else {
						Integer row = s.getSelectedRow();
						chosen_input = search_record.get(row);
						index_input = row;
						chosen = true;
					}
				} else {
					if (bkmark == null) {
						chosen_plan = null;
					} else {
						Integer row = s.getSelectedRow();
						chosen_plan = bkmark.get(row);
						index_plan = row;
						chosen = true;
					}
				}				
			}
		});

		JButton button_user = new JButton("\u4F7F\u7528\u8005");
		button_user.setForeground(Color.WHITE);
		button_user.setBackground(Color.BLACK);
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

		JButton bookmarkbutton = new JButton("\u66F8\u7C64");
		bookmarkbutton.setForeground(Color.WHITE);
		bookmarkbutton.setBackground(Color.BLACK);
		bookmarkbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 1;
				table.setModel(new DefaultTableModel(rc, head));
			}
		});
		bookmarkbutton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		bookmarkbutton.setBounds(206, 10, 167, 29);
		frame.getContentPane().add(bookmarkbutton);

		JButton confirm = new JButton("\u524D\u5F80");
		confirm.setForeground(Color.WHITE);
		confirm.setBackground(Color.BLACK);
		confirm.setEnabled(chosen);
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				from_record = true;
				if (mode == 0) {
					GUI_search fre = new GUI_search();
					frame.dispose();
				} else {
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
			}
		});
		confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirm.setBounds(895, 634, 130, 29);
		frame.getContentPane().add(confirm);

		JButton remove = new JButton("\u522A\u9664");
		remove.setForeground(Color.WHITE);
		remove.setBackground(Color.BLACK);
		remove.setEnabled(chosen);
		remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(mode == 0) {
					User.getUser().getRecord().remove(index_input);
					search_record = User.getUser().getRecord();					
					String[][] ph = new String[search_record.size()][1];					
					for (int i = 0; i < search_record.size(); i++) {
						ph[i][0] = search_record.get(i).toString();
					}
					table.setModel(new DefaultTableModel(ph, head));
				}					
				else {
					User.getUser().getPageMark().remove(index_plan);
					bkmark = User.getUser().getPageMark();
					String[][] rc = new String[bkmark.size()][1];
					for (int i = 0; i < bkmark.size(); i++) {
						rc[i][0] = bkmark.get(i).toString();
					}
					table.setModel(new DefaultTableModel(rc, head));
				}	
				
			}
		});
		remove.setFont(new Font("SansSerif", Font.PLAIN, 16));
		remove.setBounds(895, 458, 130, 29);
		frame.getContentPane().add(remove);

	}
}
