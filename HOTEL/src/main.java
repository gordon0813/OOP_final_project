import java.util.*;
import java.io.*;
import org.json.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FileUtils;
import com.google.gson.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class main {
	public static Hotel HotelList[];
	public static ArrayList<User> UserList;
	public static User user;
	
	public static void main(String[] args) throws IOException {
		//ReadHotelList();
		HotelPreference program = new HotelPreference();
		program.setVisible(true);
	}
	public static void ReadHotelList() throws IOException {
		File file = new File("HotelList");
	    try (Reader reader = new InputStreamReader(main.class.getResourceAsStream("HotelList.json"), "big5")) {
		//try (BufferedReader reader = new BufferedReader(new FileReader(file)) {
	    	Gson gson = new GsonBuilder().create();
	        HotelList = gson.fromJson(reader, Hotel[].class);
	        for (Hotel h : HotelList) {
	        	System.out.println(h);
	        }
	    } 
	}
	public static String getRandomString(int length) {
		String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for(int i = 0; i < length; i++) {
			int number = random.nextInt(62);//0~61
			sf.append(str.charAt(number));
		}
		return sf.toString();
	}
	public static int SignInCheck(String UserID, String Password) { //0->can't find user  -1->wrong password
		for (int i = 0; i < UserList.size(); i++) 
			if (UserList.get(i) != null) {
				if (UserList.get(i).getUserID().equals(UserID) && UserList.get(i).getPassword().equals(Password)) {
					user = UserList.get(i); // Logged in
					return 1;
				} else if (UserList.get(i).getUserID().equals(UserID)) 
					return -1; //wrong password
			}
		return 0;
	}
	public static boolean SignUpCheck(String UserID, String Password, String UserCode) { 
		if (UserList == null) {
			UserList = new ArrayList<User>();
			return true;
		}
		for (int i = 0; i < UserList.size(); i++) 
			if (UserList.get(i) != null && UserList.get(i).getUserID().equals(UserID)) 
				return false;
		return true;
	}
	public static boolean CheckDate(int Year, int Month, int Day) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	    sdf.setLenient(false);
	    try {
	       Date d = sdf.parse(Year + "/" + Month + "/" + Day);
	       System.out.println(d) ;
	    } catch (ParseException e) {        
	    	e.printStackTrace();
	    }
		return true;
	}
	public static long CountDaysBetween(String D1, String D2) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        final LocalDate firstDate = LocalDate.parse(D1, formatter);
        final LocalDate secondDate = LocalDate.parse(D2, formatter);
        final long days = ChronoUnit.DAYS.between(firstDate, secondDate);
        //System.out.println("Days between: " + days);
        return days;
    }
	public static boolean CheckRoomIsAvailable(Room room, long Start, long end) {
		boolean[] DIO = room.getDateIsOccupied();
		for (int i = (int)Start; i < end; i++) 
			if (DIO[i] == true) 
				return false;
		return true;
	}
	public static boolean CheckAllRooms(int HotelID, long start, long end, int sn, int dn, int qn) {
		Hotel hotel = HotelList[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		if (sn > 0) {
			int ok = 0;
			for (Room sr : singleroom) 
				if (CheckRoomIsAvailable(sr, start, end)) 
					ok ++;
			if (ok < sn) 
				return false;
		}
		if (dn > 0) {
			int ok = 0;
			for (Room dr : doubleroom) 
				if (CheckRoomIsAvailable(dr, start, end)) 
					ok ++;
			if (ok < dn) 
				return false;
		}
		if (qn > 0) {
			int ok = 0;
			for (Room qr : doubleroom) 
				if (CheckRoomIsAvailable(qr, start, end)) 
					ok ++;
			if (ok < qn) 
				return false;
		}
		return true;
	}
	public static void BookRooms(int HotelID, String UserID, long start, long end, int sn, int dn, int qn) {
		Hotel hotel = HotelList[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		if (sn > 0) { //single room
			int booked = 0;
			for (Room sr : singleroom) {
				if (CheckRoomIsAvailable(sr, start, end)) {
					for (int i = (int)start; i < end; i++)
						sr.setDateIsOccupied(i);
					booked ++;
				}
				if (booked == sn) break;
			}
		}
		if (dn > 0) { //double room
			int booked = 0;
			for (Room dr : doubleroom) {
				if (CheckRoomIsAvailable(dr, start, end)) {
					for (int i = (int)start; i < end; i++)
						dr.setDateIsOccupied(i);
					booked ++;
				}
				if (booked == dn) break;
			}
		}
		if (qn > 0) { //quad room
			int booked = 0;
			for (Room qr : quadroom) {
				if (CheckRoomIsAvailable(qr, start, end)) {
					for (int i = (int)start; i < end; i++)
						qr.setDateIsOccupied(i);
					booked ++;
				}
				if (booked == qn) break;
			}
		}
	}
	public static ArrayList<AvailableHotelRooms> SearchAvailableHotels(String CID, String COD, int p, int n) { 
		ArrayList<AvailableHotelRooms> AHR = null;
		
		for (Hotel hotel : HotelList) {
			Room[] singleroom = hotel.getSingleRooms();
			Room[] doubleroom = hotel.getDoubleRooms();
			Room[] quadroom = hotel.getQuadRooms();
			
			Date Now = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
		    long start = CountDaysBetween(sdf.format(Now), CID);
			long end = CountDaysBetween(sdf.format(Now), COD);
			
			int available_sr = 0;
			for (Room sr : singleroom) 
				if (CheckRoomIsAvailable(sr, start, end)) 
					available_sr ++;
			int available_dr = 0;
			for (Room dr : doubleroom) 
				if (CheckRoomIsAvailable(dr, start, end)) 
					available_dr ++;
			int available_qr = 0;
			for (Room qr : quadroom) 
				if (CheckRoomIsAvailable(qr, start, end)) 
					available_qr ++;
			/*
			 * solve 1*x + 2*y + 4*z >= p
			 * x >= 0, y >= 0, z >= 0, x + y + z = n
			 */
			boolean first = true;
			for (int x = 0; x <= Math.min(n, available_sr); x++)
				for (int y = 0; y <= Math.min(n, available_dr); y++) 
					for (int z = 0; z <= Math.min(n, available_qr); z++) 
						if (x + y + z == n && 1*x + 2*y + 4*z >= p) {
							if (first) {
								AHR.add(new AvailableHotelRooms(hotel.getID(), hotel.getStar(), new int[3]));
								first = false;
							} else {
								if (x > 0) AHR.get(AHR.size()-1).setAvailabelRoomsNumber(0, x);
								if (y > 0) AHR.get(AHR.size()-1).setAvailabelRoomsNumber(1, y);
								if (z > 0) AHR.get(AHR.size()-1).setAvailabelRoomsNumber(2, z);
							}
						}
		}
		return AHR;
	}
	public static boolean BookHotel(String CID, String COD, int HotelID, int sn, int dn, int qn) { 
		Date Now = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
	    long start = CountDaysBetween(sdf.format(Now), CID);
		long end = CountDaysBetween(sdf.format(Now), COD);
		
		Hotel hotel = HotelList[HotelID];
		
		if (CheckAllRooms(HotelID, start, end, sn, dn, qn)) {
			Order nOrder = new Order(user.getnextOrderID(), user.getUserID(), HotelID, CID, COD, sn, dn, qn);
			hotel.newOrder(nOrder);
			user.newOrder(nOrder);
			BookRooms(HotelID, user.getUserID(), start, end, sn, dn, qn);
			return true;
		}  
		return false;
	}
	public static boolean CheckOrder(int OrderID) {
		ArrayList<Order> orders = user.getOrders();
		return (orders != null && orders.size() <= OrderID);
	}
	public static void ModifyRooms(int OrderID, int type, int number) {//to do
		
	}
	public static void ModifyDate(int OrderID, String CID, String COD) {
		long nDays = CountDaysBetween(CID, COD);
		Date Now = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
	    long nstart = CountDaysBetween(sdf.format(Now), CID);
		long nend = CountDaysBetween(sdf.format(Now), COD);
	   
		
	}
	public static void ModifyOrder() { //to do
		Scanner scanner = new Scanner(System.in);
		//使用者ID, 訂位代號, 取消訂單 / 減少<房型><數量>, … / 變更住宿日期：<入住日期>-<退房日期>
		int OrderID = scanner.nextInt();
		int op = scanner.nextInt();
		
		ArrayList<Order> orders = user.getOrders();
		
		if (op == 1) {
			if (orders.size() <= OrderID) {
				System.out.println("退訂/修改失敗，此訂位代號不存在");
			} else {
				System.out.println("退訂成功，已取消您的訂房紀錄"); //假設任何時間都可以退訂
			}
		} else if (op == 2) {//reduce 
			if (orders.size() <= OrderID) {
				System.out.println("退訂/修改失敗，此訂位代號不存在");
			} else { 
				String type = scanner.next();
				int number = scanner.nextInt();
				Hotel hotel = HotelList[orders.get(OrderID).getHotelID()];
				int sn = orders.get(OrderID).getsn();
				int dn = orders.get(OrderID).getdn();
				int qn = orders.get(OrderID).getqn();
				/*int t = type == "Single"? 0 : (type == "Double"? 1 : 2);
				if (number < demand[t]) {
					ModifyRooms(OrderID, t, number);
					System.out.println("修改成功，已將您的訂房數量變更為");
					
				} else {
					System.out.println("修改失敗，修改數量超過訂房數量");
				}*/
			}
		} else if (op == 3) {//change date
			if (orders.size() <= OrderID) {
				System.out.println("退訂/修改失敗，此訂位代號不存在");
			} else { 
				String CID = scanner.next();
				String COD = scanner.next();
				
			}
		} else {
			System.out.println("您輸入的身分識別碼/訂位代號有誤，請重新輸入");
		}
	}
	
	public static void SearchOrder() {
		Scanner scanner = new Scanner(System.in);
		//使用者ID, 訂位代號
		String UserID = scanner.next();
		int OrderID = scanner.nextInt(); 
		
		ArrayList<Order> orders = user.getOrders();
		if (user.equals(UserID) && orders.size() > OrderID) {
			System.out.println("<" + OrderID + "> :");
			int sn = orders.get(OrderID).getsn();
			int dn = orders.get(OrderID).getdn();
			int qn = orders.get(OrderID).getqn();
			if (sn > 0) 
				System.out.println("<Single> : <" + sn + ">");
			if (dn > 0) 
				System.out.println("<Double> : <" + dn + ">");
			if (qn > 0) 
				System.out.println("<Quad> : <" + qn + ">");
			
			String CID = orders.get(OrderID).getCheckInDate(), COD = orders.get(OrderID).getCheckOutDate();
			System.out.println("<" + CID + ">-<" + COD + ">");
			System.out.println("共入住<" + CountDaysBetween(CID, COD) + ">晚   總價：<" + orders.get(OrderID).getSumPrice()+ ">");
		} else {
			System.out.println("您輸入的身分識別碼/訂位代號有誤，請重新輸入");
		}
	}
	
}
