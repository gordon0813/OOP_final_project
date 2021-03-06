package mumi;

import java.awt.Cursor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.simple.parser.ParseException;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import GUI.GUI_login;
import core.*;
import databaseException.*;
public class mumiLite {
	public Connection conn;
	final long startday;
	final long endday;
	// constructor
	public mumiLite() throws SQLException{
		SQLiteConfig config = new SQLiteConfig();
		config.setReadOnly(true);   
	    config.setSharedCache(true);
	    config.enableRecursiveTriggers(true);
	    startday = localToLong(LocalDate.of(2019,12, 1));
	    endday = localToLong(LocalDate.of(2020, 9, 1));
	   // connect to the database
        conn = DriverManager.getConnection("jdbc:sqlite:database/record.db");    
       
        try {
        	if (todayflag()) {
        		//System.out.println("mumi");
        		System.out.println("check today!!");
        		checkToday();
        	} else {
        		System.out.println("today has been checked!!");
        	}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	} 
	/**
	 * Load data from JSON into database
	 * @throws IOException
	 * @throws ParseException
	 * @throws SQLException
	 */
	public void LoadFromJSON () throws IOException, ParseException, SQLException {
		ArrayList <Hotel> data = readJSON.Loader();
		String sql;
		Statement stmt;
		PreparedStatement pst;
		stmt = conn.createStatement();
		
		sql = "Drop table IF EXISTS Hotel";
		stmt.executeUpdate(sql);
			
		sql = "create table Hotel ( id int, star int, addr string, s_p long, d_p long, q_p long, s_n int, d_n int, q_n int);"; 
		stmt.executeUpdate(sql);
		stmt.close();
		
		for(int i = 0; i < data.size(); i++) {			
			sql = "INSERT INTO Hotel (id,star,addr,s_p,d_p,q_p,s_n,d_n,q_n) VALUES (?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, data.get(i).getId());
			pst.setInt(2, data.get(i).getStar());
			pst.setString(3, data.get(i).getAddress());
			pst.setLong(4, data.get(i).getRoomsingle().getRoomprice());
			pst.setLong(5, data.get(i).getRoomdouble().getRoomprice());
			pst.setLong(6, data.get(i).getRoomquad().getRoomprice());
			pst.setInt(7, data.get(i).getRoomsingle().getRoomsize());
			pst.setInt(8, data.get(i).getRoomdouble().getRoomsize());
			pst.setInt(9, data.get(i).getRoomquad().getRoomsize());
			pst.executeUpdate();
			pst.close();
		}
		
		System.out.println("Table: Hotel updates successfully!!");
		
		return;
	}
	
	/**
	 * @param id hotel id
	 * @return hotel
	 * @throws SQLException
	 * @throws noSuchHotel 
	 */
	public Hotel getHotel (int id) throws SQLException, noSuchHotel {
		if (id > 1499 || id < 0)
			throw new noSuchHotel(id);
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		String sql = "SELECT * FROM Hotel WHERE id = " + id;
		
		rs = stmt.executeQuery(sql);
		Room s = new Room(1,rs.getInt("s_p"));
		Room d = new Room(2,rs.getInt("d_p"));
		Room q = new Room(4,rs.getInt("q_p"));
		Hotel hotel = new Hotel(rs.getInt("id"),rs.getInt("star"),rs.getString("addr"),s,d,q);
		rs.close();
		stmt.close();
		return hotel;
	}
	/**
	 * print table: Hotel
	 * @throws SQLException
	 */
	public void printAllHotel () throws SQLException {
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Hotel");
		while(rs.next()){
			System.out.println("=============================================");
        	System.out.println("ID : " + rs.getInt("id") + "  star : " + rs.getInt("star"));
        	System.out.println("Addr: " + rs.getString("addr"));
        	System.out.println("single # : " + rs.getInt("s_n") + "  price : " + rs.getLong("s_p"));
        	System.out.println("double # : " + rs.getInt("d_n") + "  price : " + rs.getLong("d_p"));
        	System.out.println("quad   # : " + rs.getInt("q_n") + "  price : " + rs.getLong("q_p"));
        }
		System.out.println("=============================================");
		stmt.close();
	    rs.close();
	}
	
	/**
	 * set up the Table:Schedule for each Hotel
	 * @param d1 day start
	 * @param d2 day end
	 * @throws java.text.ParseException
	 * @throws SQLException
	 */
	public void scheduleInit(LocalDate d1, LocalDate d2) throws java.text.ParseException, SQLException {
		long l1 = localToLong(d1), l2 = localToLong(d2);

		int s_n,d_n,q_n;
		String sql;
		Statement stmt;
		ResultSet rs;	
		PreparedStatement pst;
		
		for (int j = 0; j < 1500; j ++) {		
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Hotel WHERE id = " + j);
			
			s_n = rs.getInt("s_n"); d_n = rs.getInt("d_n"); q_n = rs.getInt("q_n");
			
			sql = "Drop table IF EXISTS Schedule" + j;
			stmt.executeUpdate(sql);
			
			sql = "create table Schedule" + j + " (date long, s_n int, d_n int, q_n int);"; 
			stmt.executeUpdate(sql);
			
			for (long i = l1; i < l2;) {
				sql = "INSERT INTO Schedule" + j + " (date,s_n,d_n,q_n) VALUES (?,?,?,?)";
				pst = conn.prepareStatement(sql);
				pst.setLong(1, i);
				pst.setInt(2, s_n);
				pst.setInt(3, d_n);
				pst.setInt(4, q_n);
				pst.executeUpdate();
				pst.close();
				
				i = onedayPass(i);
			}
			System.out.println("Table: Schedule " + j +  " updates successfully!!");
			rs.next();
			stmt.close();
		}
		
	}
	
	/**
	 * @param id hotel id
	 * @param rn # of each type of room that been reserved
	 * @param ck original check in and check out date
	 * @return longest period that can be extended
	 * @throws SQLException
	 * @throws noSuchHotel 
	 * @throws exceedSchedule 
	 */
	public CheckInOutDate extendDate (int id, RoomNum rn, CheckInOutDate ck) throws SQLException, noSuchHotel, exceedSchedule {
		if (id > 1499 || id < 0)
			throw new noSuchHotel(id);
		Statement stmt;
		ResultSet rs;
		
		long start = localToLong(ck.getCheckin());
		long end = localToLong(ck.getCheckout());
		if (start < startday || end >= endday) {
			throw new exceedSchedule(longToLocal(startday).toString(),longToLocal(endday).toString());
		}
		int s_n = rn.getSingleNum(), d_n = rn.getDoubleNum(), q_n = rn.getQuadNum();

		stmt = conn.createStatement();
		// look forward 
		rs = stmt.executeQuery("SELECT * FROM Schedule" + id + " WHERE date <= " + start);
		// since SQLite only has look_forward cursor
		long init_start = start;
		boolean flag = false;
		while (rs.next()) {
			if (s_n <= rs.getInt("s_n") && d_n <= rs.getInt("d_n") && q_n <= rs.getInt("q_n")) {
				if (flag) {
					
				} else {
					start = rs.getLong("date");
					flag = true; 
				}
				
			}
			else {
				start = init_start;
				flag = false;
			}				
		}
		// look toward
		rs = stmt.executeQuery("SELECT * FROM Schedule" + id + " WHERE date >= " + end);
		while (rs.next()) {
			if (s_n <= rs.getInt("s_n") && d_n <= rs.getInt("d_n") && q_n <= rs.getInt("q_n"))
				end = rs.getLong("date");
			else
				break;
		}
		stmt.close();
		rs.close();
		return new CheckInOutDate(longToLocal(start),longToLocal(end).plusDays(1));
	}
	
	/**
	 * @param id hotel id
	 * @param rn # of each type of room that been reserved
	 * @param ck original check in and check out date
	 * @return maximum amounts of room that user can extend
	 * @throws SQLException
	 * @throws noSuchHotel 
	 * @throws exceedSchedule 
	 */
	public RoomNum extendRoom (int id, RoomNum rn, CheckInOutDate ck) throws SQLException, noSuchHotel, exceedSchedule {
		if (id > 1499 || id < 0)
			throw new noSuchHotel(id);
		Statement stmt;
		ResultSet rs;
		long start = localToLong(ck.getCheckin());
		long end = localToLong(ck.getCheckout());
		if (start < startday || end >= endday) {
			throw new exceedSchedule(longToLocal(startday).toString(),longToLocal(endday).toString());
		}
		int s_n = rn.getSingleNum(), d_n = rn.getDoubleNum(), q_n = rn.getQuadNum();
		int s_m,d_m,q_m;
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery("SELECT * FROM Schedule" + id + " WHERE (date >= " + start + ") AND (date <= " + end + ")");
		rs.next();
		s_m = rs.getInt("s_n"); d_m = rs.getInt("d_n"); q_m = rs.getInt("q_n");
		while (rs.next()) {
			if (rs.getInt("s_n") < s_m) 
				s_m = rs.getInt("s_n");
			if (rs.getInt("d_n") < d_m) 
				d_m = rs.getInt("d_n");
			if (rs.getInt("q_n") < q_m) 
				q_m = rs.getInt("q_n");
		}
		s_n += s_m;
		d_n += d_m;
		q_n += q_m;
		
		rs.close();
		stmt.close();
		
		return new RoomNum(s_n,d_n,q_n);
	}
	
	/**
	 * print the schedule of particular hotel 
	 * @param id Hotel id
	 * @throws SQLException
	 * @throws noSuchHotel 
	 */
	public void printSchedule (int id) throws SQLException, noSuchHotel {
		if (id > 1499 || id < 0)
			throw new noSuchHotel(id);
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Schedule" + id);

		while(rs.next()){
			System.out.println("=============================================");
        	System.out.println("date : " + longToLocal(rs.getLong("date")));
        	System.out.println("s: " + rs.getInt("s_n"));
        	System.out.println("d: " + rs.getInt("d_n"));
        	System.out.println("q: " + rs.getInt("q_n"));
        }
		System.out.println("=============================================");
		stmt.close();
	    rs.close();
	}
	
	public void printSchedule (int id,LocalDate start,LocalDate end) throws SQLException, noSuchHotel {
		if (id > 1499 || id < 0)
			throw new noSuchHotel(id);
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Schedule" + id + " WHERE (date >= " + localToLong(start) + ") AND "
															 + "(date < " + localToLong(end) + ")");

		while(rs.next()){
			System.out.println("=============================================");
        	System.out.println("date : " + longToLocal(rs.getLong("date")));
        	System.out.println("s: " + rs.getInt("s_n"));
        	System.out.println("d: " + rs.getInt("d_n"));
        	System.out.println("q: " + rs.getInt("q_n"));
        }
		System.out.println("=============================================");
		stmt.close();
	    rs.close();
	}
	
	/**
	 * modify the schedule
	 * @param hotelid
	 * @param s_n
	 * @param d_n
	 * @param q_n
	 * @param in
	 * @param out
	 * @throws SQLException
	 * @throws exceedSchedule 
	 * @throws noSuchHotel 
	 * @throws nomoreRoom 
	 */
	private void scheduler (int hotelid, int s_n, int d_n, int q_n, long in, long out) throws SQLException, noSuchHotel, exceedSchedule, nomoreRoom {
		Statement stmt;
		PreparedStatement pst;
		ResultSet rs;
		int os_n,od_n,oq_n;
		RoomNum have = extendRoom(hotelid,new RoomNum(0,0,0),new CheckInOutDate(longToLocal(in),longToLocal(out)));
		if (have.getSingleNum() < s_n || have.getDoubleNum() < d_n || have.getQuadNum() < q_n) 
			throw new nomoreRoom(new RoomNum(s_n,d_n,q_n),have);
		stmt = conn.createStatement();
		for (long i = in; i < out;) {
			rs = stmt.executeQuery("SELECT * FROM Schedule" + hotelid + " WHERE date = " + i);
			os_n = rs.getInt("s_n"); od_n = rs.getInt("d_n"); oq_n = rs.getInt("q_n");
			rs.close();
			pst = conn.prepareStatement("UPDATE Schedule" + hotelid + " SET s_n = ? , d_n = ? , q_n = ? WHERE date = " + i);
			pst.setInt(1, os_n - s_n); pst.setInt(2, od_n - d_n); pst.setInt(3, oq_n - q_n);
			pst.executeUpdate();
			pst.close();
			i = onedayPass(i);
		}   
        stmt.close();   
	}
	
	/**
	 * not for user
	 * @param rm
	 * @param ck
	 * @param hotelid
	 * @throws noSuchHotel
	 * @throws exceedSchedule
	 * @throws nomoreRoom
	 * @throws SQLException
	 */
	public void editSchedule (RoomNum rm, CheckInOutDate ck, int hotelid) throws noSuchHotel, exceedSchedule, nomoreRoom, SQLException {
		scheduler(hotelid,rm.getSingleNum(),rm.getDoubleNum(),rm.getQuadNum(),localToLong(ck.getCheckin()),localToLong(ck.getCheckout()));
	}
	
	private void checkToday() throws Exception {
		long today = todayl();
		Statement stmt;
		ResultSet rs;
		String sql = "SELECT * FROM Orders WHERE checkout <= " + today;
		ArrayList<Long> orderid = new ArrayList<Long>();
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			orderid.add(rs.getLong("orderid"));
		}
		//System.out.println(orderid + " " + orderid.size());
		rs.close();
		stmt.close();
		for (int i = 0; i < orderid.size(); i++) {
			System.out.println("day passing, order is complete");
			System.out.println("delete orderid : " + orderid.get(i));
			finishOrder(orderid.get(i));			
		}
	}
	
	/**
	 * set up the Table:Comment for each Hotel
	 * @throws SQLException
	 */
	public void commentInit() throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		stmt = conn.createStatement();
		String sql;
		
		sql = "Drop table IF EXISTS Comment";
		stmt.executeUpdate(sql);
		
		sql = "create table Comment (id int, comment string);";
		stmt.executeUpdate(sql);
		stmt.close();
		
		for (int i = 0; i < 1500; i++) {
			sql = "INSERT INTO Comment (id,comment) VALUES (?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, i);
			pst.setString(2, "");
			pst.executeUpdate();
			pst.close();
		}
		System.out.println("Table: Comment updates successfully!!");
	}
	
	/**
	 * Load all comments of a hotel
	 * @param id Hotel id
	 * @return
	 * @throws SQLException
	 * @throws noSuchHotel 
	 */
	public ArrayList<String> loadComments(int id) throws SQLException, noSuchHotel {
		if (id > 1499 || id < 0)
			throw new noSuchHotel(id);
		Statement stmt;
		ResultSet rs;
		String comment;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Comment WHERE id = " + id);
		comment = rs.getString("comment");
		ArrayList<String> list= new ArrayList<String>(Arrays.asList(comment.split("@")));
		list.remove(0);
		
		stmt.close();
		rs.close();
		return list;
	}
	
	/**
	 * add a comment to a hotel
	 * @param id hotel id
	 * @param c comment
	 * @throws SQLException 
	 * @throws noSuchHotel 
	 */
	public void addComment(int id, String c) throws SQLException, noSuchHotel {
		if (id > 1499 || id < 0)
			throw new noSuchHotel(id);
		Statement stmt;
		ResultSet rs;
		PreparedStatement pst;
		stmt = conn.createStatement();
		String sql,oldComment;
		//
		//System.out.println ("add Comment [" + c + "] to hotel " + id); 
		//
		rs = stmt.executeQuery("SELECT * FROM Comment WHERE id = " + id);
		oldComment = rs.getString("comment") + "@" + c;
		//
		//System.out.println("After adding\nHotel " + id + ": [" + oldComment + "]");
		//
		sql = "UPDATE Comment SET comment = ? WHERE id = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, oldComment);
		pst.setInt(2, id);
		pst.executeUpdate();
		pst.close();
		stmt.close();
		rs.close();
	}
	
	/**
	 * set up the Table:Orderid
	 * @throws SQLException 
	 */
	public void orderidInit() throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		String sql;
		stmt = conn.createStatement();
		
		sql = "Drop table IF EXISTS Orderid";
		stmt.executeUpdate(sql);
		
		sql = "create table Orderid (orderid long) "; 
        stmt.executeUpdate(sql);
        
        sql = "INSERT INTO Orderid (orderid) VALUES (?)";
        pst = conn.prepareStatement(sql); 
		pst.setLong(1, 1);
		pst.executeUpdate();
		
		stmt.close();
		pst.close();
		System.out.println("Table: Orderid updates successfully!!");
	}

	/**
	 * @return return the newest not-used order id
	 * @throws SQLException
	 */
	public long currentOrderid() throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		ResultSet rs;
		String sql;
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Orderid");
		long idnow = rs.getLong("orderid");
		
		sql = "UPDATE Orderid SET orderid = ?";
		pst = conn.prepareStatement(sql);
        pst.setLong(1, idnow+1);
        pst.executeUpdate();
        
        stmt.close();
        pst.close();
        rs.close();
        
        return idnow;
	}
	
	/**
	 * set up Table: Plan
	 * @throws SQLException
	 */
	public void planInit () throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		String sql;
		stmt = conn.createStatement();
		
		sql = "Drop table IF EXISTS 'Plan'";
		stmt.executeUpdate(sql);
		sql = "Drop table IF EXISTS 'Planid'";
		stmt.executeUpdate(sql);
		
		sql = "create table Plan (planid int,s_n int, d_n int, q_n int, checkin long, checkout long, hotelid int, userid string)"; 
        stmt.executeUpdate(sql);
        sql = "create table Planid (planid int)"; 
        stmt.executeUpdate(sql);
        
        sql = "INSERT INTO Planid (planid) VALUES (?)";
        pst = conn.prepareStatement(sql); 
		pst.setInt(1, 1);
		pst.executeUpdate();
        
		pst.close();
        stmt.close();
        System.out.println("Table: Plan updates successfully!!");  
        System.out.println("Table: Planid updates successfully!!");  
	}
	
	/**
	 * @return newest unused plan id
	 * @throws SQLException
	 */
	public int currentPlanid() throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		ResultSet rs;
		String sql;
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Planid");
		int idnow = rs.getInt("planid");
		
		sql = "UPDATE Planid SET planid = ?";
		pst = conn.prepareStatement(sql);
        pst.setInt(1, idnow+1);
        pst.executeUpdate();
        
        stmt.close();
        pst.close();
        rs.close();
        
        return idnow;
	}
	

	
	/**
	 * add plan with user id
	 * @param p Plan
	 * @throws SQLException
	 */
	public int addPlan(Plan p, String username) throws SQLException {
		int newid = currentPlanid();
		Statement stmt;
		PreparedStatement pst;
		String sql;
		int s_n = p.getRoomNum().getSingleNum();
		int d_n = p.getRoomNum().getDoubleNum();
		int q_n = p.getRoomNum().getQuadNum();
		long in = localToLong(p.getCheckInOutDate().getCheckin());
		long out = localToLong(p.getCheckInOutDate().getCheckout());
		int id = p.getHotel().getId();
		
		stmt = conn.createStatement();
		sql = "INSERT INTO Plan (planid,s_n,d_n,q_n,checkin,checkout,hotelid,userid) VALUES (?,?,?,?,?,?,?,?)";
        pst = conn.prepareStatement(sql);
        pst.setInt(1,newid);
		pst.setInt(2,s_n); pst.setInt(3,d_n); pst.setInt(4,q_n);
		pst.setLong(5, in); pst.setLong(6, out);
		pst.setInt(7, id);  pst.setString(8, username); 
        pst.executeUpdate();
        
        pst.close();
        stmt.close();
        return newid;
	}
	
	
	/** 
	 * get Plan by its id
	 * @param planid
	 * @return Plan
	 * @throws Exception 
	 */
	public Plan getPlan (int planid) throws Exception {
		Statement stmt;
		ResultSet rs;

		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Plan WHERE planid = " + planid);
		if (!rs.isBeforeFirst() ) {    
		    throw new noSuchPlan(planid);
		} 
		RoomNum rn = new RoomNum(rs.getInt("s_n"),rs.getInt("d_n"),rs.getInt("q_n"));
		CheckInOutDate ck = new CheckInOutDate(longToLocal(rs.getLong("checkin")),longToLocal(rs.getLong("checkout")));
		Hotel hotel = getHotel(rs.getInt("hotelid"));
		rs.close();
		stmt.close();
		return new Plan(rn,ck,hotel,planid);
		
	}
	
	/**
	 * delete Plan
	 * @param planid
	 * @throws SQLException
	 * @throws noSuchPlan 
	 */
	public void deletePlan (int planid) throws SQLException, noSuchPlan {
		Statement stmt;
		String sql;
		stmt = conn.createStatement();
		sql = "DELETE FROM Plan WHERE planid = " + planid;
		if(stmt.executeUpdate(sql) == 0) {
			throw new noSuchPlan(planid);
		} else {
			System.out.println("successfully delete!!");
		}
		stmt.close();
	}

	/**
	 * set up Table:Orders
	 * @throws SQLException
	 */
	public void orderInit() throws SQLException {
		Statement stmt;
		String sql;
		stmt = conn.createStatement();
		
		sql = "Drop table IF EXISTS Orders";
		stmt.executeUpdate(sql);
		 
		sql = "create table Orders (orderid long,userid string,planid int,checkout long)"; 
        stmt.executeUpdate(sql);

		stmt.close();
		System.out.println("Table: Orders updates successfully!!");
	}
	
	public void printAllOrder () throws Exception {
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Orders");

		while(rs.next()){
			System.out.println("=============================================");
			System.out.println(mailSender.orderTostring(getOrder(rs.getLong("orderid"))));
        }
		System.out.println("=============================================");
		stmt.close();
	    rs.close();
	}
	
	/**
	 * add 1 Order into database
	 * @param o Order
	 * @throws SQLException
	 * @throws exceedSchedule 
	 * @throws noSuchHotel 
	 * @throws nomoreRoom 
	 */
	public long addOrder(Order o, String username) throws SQLException, noSuchHotel, exceedSchedule, nomoreRoom {
		Statement stmt;
		PreparedStatement pst;
		String sql;
		stmt = conn.createStatement();
		long orderid = o.getId();
		Plan plan = o.getPlan();
		int planid = addPlan(plan,"null");
		
		//
		scheduler (plan.getHotel().getId(),
				   plan.getRoomNum().getSingleNum(),plan.getRoomNum().getDoubleNum(),plan.getRoomNum().getQuadNum(),
				   localToLong(plan.getCheckInOutDate().getCheckin()),localToLong(plan.getCheckInOutDate().getCheckout()));
		//
		sql = "INSERT INTO Orders (orderid,userid,planid,checkout) VALUES (?,?,?,?)";
        pst = conn.prepareStatement(sql);
        pst.setLong(1, orderid);
        pst.setString(2, username);
        pst.setInt(3, planid);
        pst.setLong(4, localToLong(plan.getCheckInOutDate().getCheckout()));
        pst.executeUpdate();
        pst.close();
        stmt.close();
        
        String mail = getUsermail(username);
        if (!mail.equals("NO")) {
        	System.out.println("寄送郵件中~~");
        	mailSender mm = new mailSender();
        	mm.makeOrder(mail, o);
        }
        return orderid;
	}
	
	/**
	 * get Order by order id
	 * @param orderid
	 * @return Order
	 * @throws Exception 
	 */
	public Order getOrder(long orderid) throws Exception {
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Orders WHERE orderid = " + orderid);
		if (!rs.isBeforeFirst() ) {    
		    throw new noSuchOrder(orderid);
		} 
		int planid = rs.getInt("planid");
		Plan plan = getPlan(planid);
		rs.close();
		stmt.close();
		return new Order(plan,true,orderid);
	}
	
	/**
	 * get userid from orderid
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public String getUserid(long orderid) throws Exception {
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Orders WHERE orderid = " + orderid);
		if (!rs.isBeforeFirst() ) {    
		    throw new noSuchOrder(orderid);
		} 
		String name = rs.getString("userid");
		rs.close();
		stmt.close();
		
		return name;
	}
	
	/**
	 * delete Order
	 * @param orderid
	 * @throws Exception 
	 */
	public void deleteOrder(long orderid) throws Exception {
		Statement stmt;
		stmt = conn.createStatement();
		Order o = getOrder(orderid);
		String username = getUserid(orderid);
		
		Plan plan = o.getPlan();
		
		String sql = "DELETE FROM Orders WHERE orderid = " + orderid;
		if(stmt.executeUpdate(sql) == 0) {
			throw new noSuchOrder(orderid);
		} 
		
		//
		scheduler (plan.getHotel().getId(),
				   -plan.getRoomNum().getSingleNum(),-plan.getRoomNum().getDoubleNum(),-plan.getRoomNum().getQuadNum(),
				   localToLong(plan.getCheckInOutDate().getCheckin()),localToLong(plan.getCheckInOutDate().getCheckout()));
		//
		
		System.out.println("successfully delete!!");
		
		String mail = getUsermail(username);
        if (!mail.equals("NO")) {
        	System.out.println("寄送郵件中~~");
        	mailSender mm = new mailSender();
        	mm.deleteOrder(mail, o);
        }
        
		stmt.close();
	}
	
	/** after checkout date, the order is finished 
	 * @param orderid
	 * @throws Exception
	 */
	public void finishOrder(long orderid) throws Exception {
		Statement stmt;
		stmt = conn.createStatement();
		Order o = getOrder(orderid);
		String username = getUserid(orderid);
		
		Plan plan = o.getPlan();
		
		String sql = "DELETE FROM Orders WHERE orderid = " + orderid;
		if(stmt.executeUpdate(sql) == 0) {
			throw new noSuchOrder(orderid);
		} 
		
		//
		scheduler (plan.getHotel().getId(),
				   -plan.getRoomNum().getSingleNum(),-plan.getRoomNum().getDoubleNum(),-plan.getRoomNum().getQuadNum(),
				   localToLong(plan.getCheckInOutDate().getCheckin()),localToLong(plan.getCheckInOutDate().getCheckout()));
		//
		
		//System.out.println("訂單已完成!!");
		
		String mail = getUsermail(username);
        if (!mail.equals("NO")) {
        	System.out.println("寄送郵件中~~");
        	mailSender mm = new mailSender();
        	mm.finishOrder(mail, o);
        }
        
		stmt.close();
	}
	
	/**
	 * make a new order with new plan
	 * @param o new order
	 * @throws Exception 
	 */
	public void editOrder(Order n_o, String username) throws Exception {		
		long orderid = n_o.getId();

		//////////// delete order
		Statement stmt;
		stmt = conn.createStatement();
		Order o_o = getOrder(orderid);	
		Plan plan = o_o.getPlan();
		
		String sql = "DELETE FROM Orders WHERE orderid = " + orderid;
		if(stmt.executeUpdate(sql) == 0) {
			throw new noSuchOrder(orderid);
		} 	
		//
		scheduler (plan.getHotel().getId(),
				   -plan.getRoomNum().getSingleNum(),-plan.getRoomNum().getDoubleNum(),-plan.getRoomNum().getQuadNum(),
				   localToLong(plan.getCheckInOutDate().getCheckin()),localToLong(plan.getCheckInOutDate().getCheckout()));
		//////////// add order
		PreparedStatement pst;
		stmt = conn.createStatement();
		plan = n_o.getPlan();
		int planid = addPlan(plan,username);		
		//
		scheduler (plan.getHotel().getId(),
				   plan.getRoomNum().getSingleNum(),plan.getRoomNum().getDoubleNum(),plan.getRoomNum().getQuadNum(),
				   localToLong(plan.getCheckInOutDate().getCheckin()),localToLong(plan.getCheckInOutDate().getCheckout()));
		sql = "INSERT INTO Orders (orderid,userid,planid,checkout) VALUES (?,?,?,?)";
        pst = conn.prepareStatement(sql);
        pst.setLong(1, orderid);
        pst.setString(2, username);
        pst.setInt(3, planid);
        pst.setLong(4, localToLong(plan.getCheckInOutDate().getCheckout()));
        pst.executeUpdate();
        pst.close();
        stmt.close();
        
        String mail = getUsermail(username);
        if (!mail.equals("NO")) {
        	System.out.println("寄送郵件中~~");
        	mailSender mm = new mailSender();
        	mm.editOrder(mail, n_o);
        }
	}
	
	/**
	 * set up Table: User
	 * @throws SQLException
	 */
	public void userInit () throws SQLException {
		Statement stmt;
		String sql;
		stmt = conn.createStatement();
		
		sql = "Drop table IF EXISTS User";
		stmt.executeUpdate(sql);
		 
		sql = "create table User (userid string, password string)"; 
        stmt.executeUpdate(sql);

		stmt.close();
		System.out.println("Table: User updates successfully!!");
	}
	
	/**
	 * add 1 User into database
	 * @param user
	 * @throws SQLException
	 * @throws userExist 
	 * @throws passwordIllegal 
	 */
	public void addUser (String username, String password) throws SQLException, userExist, passwordIllegal {
		PreparedStatement pst;
		String sql;
		
		legalUserData(username,password);
		sql = "INSERT INTO User (userid,password) VALUES (?,?)";
		pst = conn.prepareStatement(sql);
		pst.setString(1, username);
		pst.setString(2,password);
		pst.executeUpdate();
		pst.close();
	}
	
	/**
	 * check the user name and the password is legal
	 * @param username
	 * @param password
	 * @throws SQLException 
	 * @throws userExist 
	 * @throws passwordIllegal 
	 */
	public void legalUserData (String username, String password) throws SQLException, userExist, passwordIllegal {
		Statement stmt;
		ResultSet rs;
		String sql;
		
		if (password.matches("[a-zA-Z0-9]+") == false || password.length() < 6 || password.length() > 15) {
		    throw new passwordIllegal(); 
		}
		
		stmt = conn.createStatement();
		sql = "SELECT * FROM User WHERE userid = '" + username + "'";
		rs = stmt.executeQuery(sql);
		if (rs.isBeforeFirst()) {    
		    throw new userExist(username);
		}
		//System.out.println("ok!");
		stmt.close();
		rs.close();
	}
	
	/**
	 * get User
	 * @param userid
	 * @param password
	 * @return User
	 * @throws Exception 
	 */
	public User getUser(String username,String password) throws Exception {
		Statement stmt;
		ResultSet rs;
		String sql;
		
		
		stmt = conn.createStatement();
		// check whether the info if user is correct
		sql = "SELECT * FROM User WHERE (userid = '" + username + "')";
		rs = stmt.executeQuery(sql);
		if (!rs.isBeforeFirst() ) {    
		    throw new noSuchUser(username);
		}
		if (!rs.getString("password").equals(password)) {
			throw new passwordWrong();
		}
		User user = new User(username,password);
		// get all order 
		sql = "SELECT * FROM Orders WHERE userid = '" + username + "'";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Order order = getOrder(rs.getInt("orderid"));
			user.addOrder(order,false);
		}
		// get all plan
		sql = "SELECT * FROM Plan WHERE userid = '" + username + "'";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Plan plan = getPlan(rs.getInt("planid"));
			user.addpageMark(plan,false);
		}
		// get all search_input
		sql = "SELECT * FROM Search WHERE userid = '" + username + "'";
		rs = stmt.executeQuery(sql);
		int lowstar,highstar,lowprice,highprice,people;
		long checkin,checkout;
		String addr;
		while(rs.next()) {
			lowstar = rs.getInt("lowstar"); highstar = rs.getInt("highstar");
			lowprice = rs.getInt("lowprice"); highprice = rs.getInt("highprice");
			people = rs.getInt("people");
			checkin = rs.getLong("checkin"); checkout = rs.getLong("checkout");
			CheckInOutDate ck = new CheckInOutDate(longToLocal(checkin),longToLocal(checkout));
			RoomNum rn = new RoomNum(rs.getInt("s_n"),rs.getInt("d_n"),rs.getInt("q_n"));
			addr = rs.getString("addr");
			user.addRecord(new Search_input(highstar,lowstar,highprice,lowprice,people,ck,rn,addr),false);
		}
		rs.close();
		stmt.close();
		return user;
	}
	
	/**
	 * change password
	 * @param username
	 * @param old_p old password
	 * @param new_p new password
	 * @throws SQLException
	 * @throws noSuchUser
	 * @throws passwordWrong
	 * @throws passwordIllegal 
	 * @throws userExist 
	 */
	public void editUserpassword(String username, String old_p, String new_p) throws SQLException, noSuchUser, passwordWrong, userExist, passwordIllegal {
		Statement stmt;
		ResultSet rs;
		PreparedStatement pst;
		String sql;
		
		
		stmt = conn.createStatement();
		// check whether the info if user is correct
		sql = "SELECT * FROM User WHERE (userid = '" + username + "')";
		rs = stmt.executeQuery(sql);
		if (!rs.isBeforeFirst() ) {    
		    throw new noSuchUser(username);
		}
		if (!rs.getString("password").equals(old_p)) {
			throw new passwordWrong();
		}
		
		// update the password
		legalUserData("@@@@@@@@@@",new_p);
		sql = "UPDATE User SET password = ? WHERE (userid = '" + username + "')";
		pst = conn.prepareStatement(sql);
		pst.setString(1, new_p);
		pst.executeUpdate();
		System.out.println("密碼更新成功!!");
		stmt.close();
		pst.close();
		rs.close();
	}
	
	/**
	 * print all user
	 * @throws SQLException
	 */
	public void printAllUser () throws SQLException {
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM User");

		while(rs.next()){
			System.out.println("=============================================");
        	System.out.println("User : " + rs.getString("userid"));
        	System.out.println("password: " + rs.getString("password"));
        }
		System.out.println("=============================================");
		stmt.close();
	    rs.close();
	}
	
	/**
	 * set up Table:Search
	 * @throws SQLException
	 */
	public void searchInit () throws SQLException {
		Statement stmt;
		String sql;
		stmt = conn.createStatement();
		
		sql = "Drop table IF EXISTS Search";
		stmt.executeUpdate(sql);
		 
		sql = "create table Search (lowstar int,highstar int,lowprice int,"
								 + "highprice int,people int,checkin long,checkout long,"
								 + "s_n int,d_n int,q_n int,addr string,userid string)"; 
        stmt.executeUpdate(sql);

		stmt.close();
		System.out.println("Table: Search updates successfully!!");
	}
	
	/**
	 * add one search to one user
	 * @param search
	 * @param userid
	 * @throws SQLException
	 */
	public void addSearch(Search_input search,String username) throws SQLException {
		PreparedStatement pst;
		String sql;
		
		sql = "INSERT INTO Search (lowstar,highstar,lowprice," 
				+ "highprice,people,checkin,checkout,"  
				+ "s_n,d_n,q_n,addr,userid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, search.getLowstar()); pst.setInt(2,search.getHighstar());
		pst.setInt(3, search.getLowprice()); pst.setInt(4, search.getHighprice());
		pst.setInt(5, search.getNumofpeople());
		pst.setLong(6, localToLong(search.getCk().getCheckin()));
		pst.setLong(7, localToLong(search.getCk().getCheckout()));
		pst.setInt(8, search.getLowrn().getSingleNum());
		pst.setInt(9, search.getLowrn().getDoubleNum()); 
		pst.setInt(10, search.getLowrn().getQuadNum()); 
		pst.setString(11, search.getAddress());
		pst.setString(12, username);
		pst.executeUpdate();
		
		pst.close();
	}
	
	/**
	 * delete all search of one user
	 * @param userid
	 * @throws SQLException
	 */
	public void deleteSearch (String username) throws SQLException {
		Statement stmt;
		stmt = conn.createStatement();
		
		stmt.executeUpdate("DELETE FROM Search WHERE userid = '" + username + "'");
		stmt.close();
	}
	
	/**
	 * @param d ms from 1970
	 * @return LocalDate
	 */
	private LocalDate longToLocal (long d) {
		Date date = new Date(d);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
	}
	
	/**
	 * @param d LocalDate
	 * @return ms from 1970
	 */
	private long localToLong (LocalDate d) {
		ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = d.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date.getTime();
	}
	
	/**
	 * one day pass
	 * @param d
	 * @return
	 */
	private long onedayPass (long d) {
		LocalDate l = longToLocal(d);
		l = l.plusDays(1);
		return localToLong(l);
	}
	
	/**
	 * @return first day of schedule
	 */
	public LocalDate startDay() {
		return longToLocal(startday);
	}
	
	/**
	 * @return last day of schedule
	 */
	public LocalDate endDay() {
		return longToLocal(endday);
	}
	/**
	 * @return today (local date version)
	 */
	public LocalDate todayL () {
		return LocalDate.now();
	}
	/**
	 * @return today (long version)
	 */
	private long todayl () {
		return localToLong(LocalDate.now());
	}
	
	public void todayfagInit() throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		String sql;
		stmt = conn.createStatement();
		
		sql = "Drop table IF EXISTS Todayflag";
		stmt.executeUpdate(sql);
		
		sql = "create table Todayflag (today long) "; 
        stmt.executeUpdate(sql);
        
        sql = "INSERT INTO Todayflag (today) VALUES (?)";
        pst = conn.prepareStatement(sql); 
		pst.setLong(1, todayl()-1);
		pst.executeUpdate();
		
		stmt.close();
		pst.close();
		System.out.println("Table: Todayflag updates successfully!!");
	}
	
	private boolean todayflag() throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		ResultSet rs;
		String sql;
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Todayflag");
		long day = rs.getLong("today");
		long today = todayl();
		rs.close();
		
		if (today != day) {
			sql = "UPDATE Todayflag SET today = ?";
			pst = conn.prepareStatement(sql);
			pst.setLong(1, today);
			pst.executeUpdate();
			pst.close();
			stmt.close();			
			return true;
		} else {
			stmt.close();
			return false;
		}
	}
	
	/**
	 * set up table: Usermail
	 * @throws SQLException
	 */
	public void usermailInit() throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		String sql;
		stmt = conn.createStatement();
		
		sql = "Drop table IF EXISTS Usermail";
		stmt.executeUpdate(sql);
		
		sql = "create table Usermail (userid String, mail String) "; 
        stmt.executeUpdate(sql);

		stmt.close();
		System.out.println("Table: Usermail updates successfully!!");
	}
	
	/** update mail for user
	 * @param name
	 * @param mail
	 * @throws SQLException
	 */
	public void editUsermail (String name, String mail) throws SQLException {
		Statement stmt;
		PreparedStatement pst;
		ResultSet rs;
		String sql;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Usermail WHERE userid = '" + name + "'");
		
		if (!rs.isBeforeFirst()) {    
			sql = "INSERT INTO Usermail (userid, mail) VALUES (?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, mail);
			pst.executeUpdate();		
			pst.close();
			System.out.println("電子信箱新增成功!!");
		} else {
			sql = "UPDATE Usermail SET mail = ? WHERE (userid = '" + name + "')";
			pst = conn.prepareStatement(sql);
			pst.setString(1, mail);
			pst.executeUpdate();
			pst.close();
			System.out.println("電子信箱更新成功!!");
		}	
		rs.close();
		stmt.close();
	}
	
	/** if user has mail, then return it
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public String getUsermail (String name) throws SQLException {
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Usermail WHERE userid = '" + name + "'");
		if (!rs.isBeforeFirst() ) {    
		    return "NO";
		} 
		String mail = rs.getString("mail");
		
		rs.close();
		stmt.close();
		
		return mail;
	}
	
	
}

