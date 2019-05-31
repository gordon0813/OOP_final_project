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
	public static User[] UserList = new User[100];
	public static User user;
	
	public static void main(String[] args) throws IOException {
		ReadHotelList();
		
		System.out.println("Please choose:\n (1)Sign in \n (2)Sign up");
		Scanner scanner = new Scanner(System.in);
		int op = scanner.nextInt();
		UserCertification(op);
		
		while (true) {
			JSONObject Hotels[] = new JSONObject[1500];
			
			System.out.println("Choose what you want:\n (1)Search for available hotels\n (2)Modify/Cancel your order\n"
					+ " (3)Check your order\n (4)Search your order");
			
			op = scanner.nextInt();
			if (op == 1) {//查詢可訂旅館
				SearchAvailableHotels();
			} else if (op == 2) {//住宿預訂
				BookHotel();
			} else if (op == 3) {//退訂/減少房間數量/縮短住宿天數
				ModifyOrder();
			} else if (op == 4) {//查詢訂房
				SearchOrder();
			} 
			
		}
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
	
	public static void UserCertification(int op) {
		String UserID = null, Password = null;
		Scanner scanner = new Scanner(System.in);
		if (op == 1) { //sign in
			while (true) {
				System.out.println("Please enter your UserID:");
				UserID = scanner.next();
				System.out.println("Please enter your Password:");
				Password = scanner.next();	
				
				int ok = 0;
				for (int i = 0; i < UserList.length; i++) {
					if (UserList[i] != null) {
						if (UserList[i].getUserID().equals(UserID) && UserList[i].getPassword().equals(Password)) {
							ok = 1;
							user = UserList[i]; // Logged in
							break;
						} else if (UserList[i].getUserID().equals(UserID)) {
							System.out.println("Wrong password. Please try again.");
							ok = -1;
							break;
						}
					}
				}
				if (ok == 1) break;
				else if (ok == 0) System.out.println("User doesn't exist. Please try again.");
			}
		} 
		else if (op == 2) { //sign up
			while (true) {
				System.out.println("Please enter your UserID:");
				UserID = scanner.next();
				System.out.println("Please enter your Password:");
				Password = scanner.next();		
				
				boolean exist = false;
				for (int i = 0; i < UserList.length; i++) {
					if (UserList[i] != null && UserList[i].getUserID().equals(UserID)) {
						exist = true;
						break;
					}
				}
				if (exist == false) break;
				else System.out.println("User has already been existed. Please try another UserID.");
			}
			String VerifyCode, UserCode; //驗證碼
			do {
				VerifyCode = getRandomString(6);
				System.out.println(VerifyCode);
				UserCode = scanner.next();
			} while (!UserCode.equals(VerifyCode));
			
			user = new User(UserID, Password);
			UserList[UserList.length] = user;
		}
		
		
		System.out.println("Logged in.");
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
	
	public static boolean CheckAllRooms(int HotelID, int[] demand, long start, long end) {
		Hotel hotel = HotelList[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		if (demand[0] > 0) {
			int ok = 0;
			for (Room sr : singleroom) {
				if (CheckRoomIsAvailable(sr, start, end)) {
					ok ++;
				}
			}
			if (ok < demand[0]) {
				return false;
			}
		}
		if (demand[1] > 0) {
			int ok = 0;
			for (Room dr : doubleroom) {
				if (CheckRoomIsAvailable(dr, start, end)) {
					ok ++;
				}
			}
			if (ok < demand[1]) {
				return false;
			}
		}
		if (demand[2] > 0) {
			int ok = 0;
			for (Room qr : doubleroom) {
				if (CheckRoomIsAvailable(qr, start, end)) {
					ok ++;
				}
			}
			if (ok < demand[2]) {
				return false;
			}
		}
		return true;
	}
	
	public static void BookRooms(int HotelID, String userID, int[] demand, long start, long end) {
		Hotel hotel = HotelList[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		if (demand[0] > 0) { //single room
			int booked = 0;
			for (Room sr : singleroom) {
				if (CheckRoomIsAvailable(sr, start, end)) {
					for (int i = (int)start; i < end; i++)
					sr.setDateIsOccupied(i);
					booked ++;
				}
				if (booked == demand[0]) break;
			}
		}
		
		if (demand[1] > 0) { //double room
			int booked = 0;
			for (Room dr : doubleroom) {
				if (CheckRoomIsAvailable(dr, start, end)) {
					for (int i = (int)start; i < end; i++)
					dr.setDateIsOccupied(i);
					booked ++;
				}
				if (booked == demand[1]) break;
			}
		}
		if (demand[2] > 0) { //quad room
			int booked = 0;
			for (Room qr : quadroom) {
				if (CheckRoomIsAvailable(qr, start, end)) {
					for (int i = (int)start; i < end; i++)
					qr.setDateIsOccupied(i);
					booked ++;
				}
				if (booked == demand[2]) break;
			}
		}
	}
	
	public static void SearchAvailableHotels() { 
		Scanner scanner = new Scanner(System.in);
		//入住日期, 退房日期, 入住人數, 客房數量
		int y1 = 2019, m1 = 5, d1 = 31, y2 = 2019, m2 = 6, d2 = 1;
		int people = 0;
		int roomnum = 0; 
		
		boolean tag = false;
		while (!tag) {
			System.out.println("y1 m1 d1");
			y1 = scanner.nextInt(); m1 = scanner.nextInt(); d1 = scanner.nextInt();
			System.out.println("y2 m2 d2");
			y2 = scanner.nextInt(); m2 = scanner.nextInt(); d2 = scanner.nextInt();
			people = scanner.nextInt();
			roomnum = scanner.nextInt();
			//assume roomnum <= people 
			
			if (CheckDate(y1, m1, d1) && CheckDate(y2, m2, d2)) 
				tag = true;
			else System.out.println("Please try again.");
		}
		boolean matched = false;
		for (Hotel hotel : HotelList) {
			Room[] singleroom = hotel.getSingleRooms();
			Room[] doubleroom = hotel.getDoubleRooms();
			Room[] quadroom = hotel.getQuadRooms();
			int n = roomnum, p = people;
			
			String CID = Integer.toString(y1) + "/" + Integer.toString(m1) + "/" + Integer.toString(d1);
			String COD = Integer.toString(y2) + "/" + Integer.toString(m2) + "/" + Integer.toString(d2);
			Date Now = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
		    long start = CountDaysBetween(sdf.format(Now), CID);
			long end = CountDaysBetween(sdf.format(Now), COD);
			
			int available_sr = 0;
			for (Room sr : singleroom) {
				boolean[] DIO = sr.getDateIsOccupied();
				if (CheckRoomIsAvailable(sr, start, end)) 
					available_sr ++;
			}
			int available_dr = 0;
			for (Room dr : doubleroom) {
				boolean[] DIO = dr.getDateIsOccupied();
				if (CheckRoomIsAvailable(dr, start, end)) 
					available_dr ++;
			}
			int available_qr = 0;
			for (Room qr : quadroom) {
				boolean[] DIO = qr.getDateIsOccupied();
				if (CheckRoomIsAvailable(qr, start, end)) 
					available_qr ++;
			}
			
			/*
			 * solve 1*x + 2*y + 4*z >= p
			 * x >= 0, y >= 0, z >= 0, x + y + z = n
			 */
			boolean first = true;
			for (int x = 0; x <= Math.min(n, available_sr); x++)
				for (int y = 0; y <= Math.min(n, available_dr); y++) 
					for (int z = 0; z <= Math.min(n, available_qr); z++) 
						if (x + y + z == n && 1*x + 2*y + 4*z >= p) {
							matched = true;
							if (first) {
								System.out.println("<" + hotel.getID() + "><" + hotel.getStar() + "> :");
								first = false;
							}
							if (x > 0) System.out.println("<Single> : <" + x + ">");
							if (y > 0) System.out.println("<Double> : <" + y + ">");
							if (z > 0) System.out.println("<Quad> : <" + z + ">");
							System.out.println("總價 : <" + singleroom[0].getPrice()*x + doubleroom[0].getPrice()*y + quadroom[0].getPrice()*z + ">");
						}
		}
		if (!matched) System.out.println("No matched Hotels.");
	}
	
	public static void BookHotel() { 
		Scanner scanner = new Scanner(System.in);
		//使用者ID, 旅館編號, 入住日期, 退房日期, 入住房型, 房間數量
		String UserID = null;
		int HotelID = 0;
		int y1 = 2019, m1 = 5, d1 = 31, y2 = 2019, m2 = 6, d2 = 1;
		int demand[] = new int[3];
		
		boolean tag = false;
		while (!tag) {
			System.out.println("UserID HotelID");
			UserID = scanner.next(); 
			HotelID = scanner.nextInt();
			System.out.println("y1 m1 d1");
			y1 = scanner.nextInt(); m1 = scanner.nextInt(); d1 = scanner.nextInt();
			System.out.println("y2 m2 d2");
			y2 = scanner.nextInt(); m2 = scanner.nextInt(); d2 = scanner.nextInt();
			for (int i = 0; i < 3; i++) {
				String type = scanner.next(); 
				demand[i] = scanner.nextInt();// 無->0
			}
			
			if (CheckDate(y1, m1, d1) && CheckDate(y2, m2, d2)) 
				tag = true;
			else System.out.println("Please try again.");
		}
		
		String CID = Integer.toString(y1) + "/" + Integer.toString(m1) + "/" + Integer.toString(d1);
		String COD = Integer.toString(y2) + "/" + Integer.toString(m2) + "/" + Integer.toString(d2);
		Date Now = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
	    long start = CountDaysBetween(sdf.format(Now), CID);
		long end = CountDaysBetween(sdf.format(Now), COD);
		
		Hotel hotel = HotelList[HotelID];
		
		if (CheckAllRooms(HotelID, demand, start, end)) {
			Order nOrder = new Order(user.getnextOrderID(), UserID, HotelID, CID, COD, demand);
			hotel.newOrder(nOrder);
			user.newOrder(nOrder);
			BookRooms(HotelID, UserID, demand, start, end);
			System.out.println("<" + (user.getnextOrderID()-1) + ">     <" + UserID + ">");
			System.out.println("<" + HotelID + "> :");
			
			
		} else {
			System.out.println("失敗 房間數量不足/房間已售罄");
		}
	}
	
	public static void ModifyRooms(int OrderID, int type, int number) {//to do
		
	}
	public static void ModifyDate() {//to do
		
	}
	public static void ModifyOrder() { //to do
		Scanner scanner = new Scanner(System.in);
		//使用者ID, 訂位代號, 取消訂單 / 減少<房型><數量>, … / 變更住宿日期：<入住日期>-<退房日期>
		String UserID = scanner.next();
		int OrderID = scanner.nextInt();
		int op = scanner.nextInt();
		
		ArrayList<Order> orders = user.getOrders();
		if (user.equals(UserID)) {
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
					int[] demand = orders.get(OrderID).getDemand();
					int t = type == "Single"? 0 : (type == "Double"? 1 : 2);
					if (number < demand[t]) {
						ModifyRooms(OrderID, t, number);
						System.out.println("修改成功，已將您的訂房數量變更為");
						
					} else {
						System.out.println("修改失敗，修改數量超過訂房數量");
					}
				}
			} else if (op == 3) {//change date
				if (orders.size() <= OrderID) {
					System.out.println("退訂/修改失敗，此訂位代號不存在");
				} else { 
					String CID = scanner.next();
					String COD = scanner.next();
					
				}
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
			int[] demand = orders.get(OrderID).getDemand();
			if (demand[0] > 0) 
				System.out.println("<Single> : <" + demand[0] + ">");
			if (demand[1] > 0) 
				System.out.println("<Double> : <" + demand[1] + ">");
			if (demand[2] > 0) 
				System.out.println("<Quad> : <" + demand[2] + ">");
			
			String CID = orders.get(OrderID).getCheckInDate(), COD = orders.get(OrderID).getCheckOutDate();
			System.out.println("<" + CID + ">-<" + COD + ">");
			System.out.println("共入住<" + CountDaysBetween(CID, COD) + ">晚   總價：<" + orders.get(OrderID).getSumPrice()+ ">");
		} else {
			System.out.println("您輸入的身分識別碼/訂位代號有誤，請重新輸入");
		}
	}
	
}
