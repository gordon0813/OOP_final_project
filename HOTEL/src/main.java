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
		databaseUtil.buildConnection();
		databaseUtil.initDatabase();

		ReadHotelList();
		HotelPreference program = new HotelPreference();
		program.setVisible(true);
	}

	public static void ReadHotelList() throws IOException {
		File file = new File("HotelList");
		try (Reader reader = new InputStreamReader(main.class.getResourceAsStream("HotelList.json"), "big5")) {
			// try (BufferedReader reader = new BufferedReader(new FileReader(file)) {
			Gson gson = new GsonBuilder().create();
			HotelList = gson.fromJson(reader, Hotel[].class);
			for (Hotel h : HotelList)
				h.init();
			for (Hotel h : HotelList)
				System.out.println(h);
		} catch (Exception e) {
			System.out.println("cannot find the file");
		}
	}

	public static String getRandomString(int length) {
		String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);// 0~61
			sf.append(str.charAt(number));
		}
		return sf.toString();
	}

	public static int SignInCheck(String UserID, String Password) { // 0->can't find user -1->wrong password
		/*if (UserList == null)
			return 0;
		for (int i = 0; i < UserList.size(); i++)
			if (UserList.get(i) != null) {
				if (UserList.get(i).getUserID().equals(UserID) && UserList.get(i).getPassword().equals(Password)) {
					user = UserList.get(i); // Logged in
					return 1;
				} else if (UserList.get(i).getUserID().equals(UserID))
					return -1; // wrong password
			}
		*/
		user = databaseUtil.getUser(UserID);
		if (user == null) return 0;
		else if (Password != user.getPassword()) return -1;
		return 1;
	}

	public static boolean SignUpCheck(String UserID, String Password, String UserCode) {
		/*if (UserList == null) {
			UserList = new ArrayList<User>();
			return true;
		} 
		for (int i = 0; i < UserList.size(); i++)
			if (UserList.get(i) != null && UserList.get(i).getUserID().equals(UserID))
				return false;
		*/
		
		return databaseUtil.getUser(UserID) == null;
	}

	public static boolean CheckDate(int Year, int Month, int Day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setLenient(false);
		try {
			Date d = sdf.parse(Year + "/" + Month + "/" + Day);
			System.out.println(d);
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
		// System.out.println("Days between: " + days);
		return days;
	}

	public static boolean CheckRoomIsAvailable(Room room, long Start, long end) {
		boolean[] DIO = room.getDateIsOccupied();
		for (int i = (int) Start; i < end; i++)
			if (DIO[i] == true) 
				return false;
		return true;
	}

	public static boolean CheckAllRooms(int HotelID, long start, long end, int sn, int dn, int qn) {
		Hotel hotel = HotelList[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();

		System.out.println(singleroom);
		if (sn > 0) {
			if (singleroom == null)
				return false;
			int ok = 0;
			for (Room sr : singleroom)
				if (CheckRoomIsAvailable(sr, start, end))
					ok++;
			if (ok < sn)
				return false;
		}
		if (dn > 0) {
			int ok = 0;
			for (Room dr : doubleroom)
				if (CheckRoomIsAvailable(dr, start, end))
					ok++;
			if (ok < dn)
				return false;
		}
		if (qn > 0) {
			int ok = 0;
			for (Room qr : quadroom)
				if (CheckRoomIsAvailable(qr, start, end))
					ok++;
			if (ok < qn)
				return false;
		}
		return true;
	}

	public static ArrayList<ArrayList<Integer>> Reserve(int HotelID, String UserID, long start, long end, int sn, int dn, int qn) {
		Hotel hotel = HotelList[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		ArrayList<ArrayList<Integer>> RoomNumbers = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 3; i++)
			RoomNumbers.add(new ArrayList<Integer> ());
		
		if (sn > 0) { // single room
			int booked = 0;
			for (int i = 0; i < singleroom.length; i++) {
				if (CheckRoomIsAvailable(singleroom[i], start, end)) {
					for (int t = (int)start; t < end; t++)
						singleroom[i].setDateIsOccupied(t);
					RoomNumbers.get(0).add(i);
					booked++;
				}
				if (booked == sn)
					break;
			}
		}
		if (dn > 0) { // double room
			int booked = 0;
			for (int i = 0; i < doubleroom.length; i++) {
				if (CheckRoomIsAvailable(doubleroom[i], start, end)) {
					for (int t = (int)start; t < end; t++)
						doubleroom[i].setDateIsOccupied(t);
					RoomNumbers.get(1).add(i);
					booked++;
				}
				if (booked == dn)
					break;
			}
		}
		if (qn > 0) { // quad room
			int booked = 0;
			for (int i = 0; i < quadroom.length; i++) {
				if (CheckRoomIsAvailable(quadroom[i], start, end)) {
					for (int t = (int) start; t < end; t++)
						quadroom[i].setDateIsOccupied(t);
					RoomNumbers.get(2).add(i);
					booked++;
				}
				if (booked == qn)
					break;
			}
		}
		return RoomNumbers;
	}

	public static ArrayList<AvailableHotelRooms> SearchAvailableHotels(String CID, String COD, int p, int n) {
		ArrayList<AvailableHotelRooms> AHR = new ArrayList<AvailableHotelRooms>();

		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = CountDaysBetween(sdf.format(Now), CID);
		long end = CountDaysBetween(sdf.format(Now), COD);

		for (Hotel hotel : HotelList) {
			Room[] singleroom = hotel.getSingleRooms();
			Room[] doubleroom = hotel.getDoubleRooms();
			Room[] quadroom = hotel.getQuadRooms();

			int available_sr = 0;
			if (singleroom != null) {
				for (Room sr : singleroom)
					if (CheckRoomIsAvailable(sr, start, end))
						available_sr++;
			}
			int available_dr = 0;
			if (doubleroom != null) {
				for (Room dr : doubleroom)
					if (CheckRoomIsAvailable(dr, start, end))
						available_dr++;
			}
			int available_qr = 0;
			if (quadroom != null) {
				for (Room qr : quadroom)
					if (CheckRoomIsAvailable(qr, start, end))
						available_qr++;
			}
			/*
			 * solve 1*x + 2*y + 4*z >= p x >= 0, y >= 0, z >= 0, x + y + z = n
			 */
			boolean first = true;
			for (int x = 0; x <= Math.min(n, available_sr); x++)
				for (int y = 0; y <= Math.min(n, available_dr); y++)
					for (int z = 0; z <= Math.min(n, available_qr); z++)
						if (x + y + z == n && 1 * x + 2 * y + 4 * z >= p)
							AHR.add(new AvailableHotelRooms(hotel.getID(), hotel.getStar(), hotel.getLocality(),
									hotel.getAddress(), x, y, z));
		}
		return AHR;
	}

	public static Order BookHotel(String CID, String COD, int HotelID, int sn, int dn, int qn) {
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = CountDaysBetween(sdf.format(Now), CID);
		long end = CountDaysBetween(sdf.format(Now), COD);

		if (CheckAllRooms(HotelID, start, end, sn, dn, qn)) {
			ArrayList<ArrayList<Integer>> re = Reserve(HotelID, user.getUserID(), start, end, sn, dn, qn);
			Order nOrder = new Order(user.getnextOrderID(), user.getUserID(), HotelID, CID, COD, re.get(0), re.get(1), re.get(2));
			databaseUtil.insertOrder(nOrder);
			//hotel.newOrder(nOrder);
			//user.newOrder(nOrder);
			return nOrder;
		}
		return null;
	}

	public static Order CheckOrder(int OrderID) {
		//return user.getOrders().get(OrderID);
		return databaseUtil.getOrderByOrderID(OrderID);
	}

	public static ArrayList<AvailableHotelRooms> SearchByStar(ArrayList<AvailableHotelRooms> AHR, int Star) {
		ArrayList<AvailableHotelRooms> nAHR = new ArrayList<AvailableHotelRooms>();
		for (AvailableHotelRooms ahr : AHR)
			if (ahr.getHotelStar() == Star)
				nAHR.add(ahr);
		return nAHR;
	}

	public static int CountSumPrice(AvailableHotelRooms x) {
		return Hotel.getSingleRoomPrice() * x.getSingle() 
			 + Hotel.getDoubleRoomPrice() * x.getDouble()
		     + Hotel.getQuadRoomPrice() * x.getQuad();
	}

	public static ArrayList<AvailableHotelRooms> SortByPrice(ArrayList<AvailableHotelRooms> AHR, int op) {
		Collections.sort(AHR, new Comparator<AvailableHotelRooms>() {
			public int compare(AvailableHotelRooms a, AvailableHotelRooms b) {
				return (op == 1 ? (CountSumPrice(a) - CountSumPrice(b)) : (CountSumPrice(b) - CountSumPrice(a)));
			}
		});
		return AHR;
	}

	public static void CancelOrder(int OrderID) {
		databaseUtil.deleteOrder(OrderID);
	}
	
	public static void ChangeRooms(int OrderID, int nsn, int ndn, int nqn) { 
		Order order = databaseUtil.getOrderByOrderID(OrderID);
		Hotel hotel = HotelList[order.getHotelID()];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = CountDaysBetween(sdf.format(Now), order.getCheckInDate());
		long end = CountDaysBetween(sdf.format(Now), order.getCheckOutDate());
		
		int sn = order.getSnum().size();
		if (nsn < sn) {
			ArrayList<Integer> Snum = order.getSnum();
			for (int i = 0; i < sn; i++) 
				for (int t = (int)start; t < end; t++) 
					singleroom[Snum.get(i)].setDateIsNotOccupied(t);
		} 
		int dn = order.getDnum().size();
		if (ndn < dn) {
			ArrayList<Integer> Dnum = order.getDnum();
			for (int i = 0; i < dn; i++) 
				for (int t = (int)start; t < end; t++) 
					doubleroom[Dnum.get(i)].setDateIsNotOccupied(t);
		} 
		int qn = order.getQnum().size();
		if (nqn < qn) {
			ArrayList<Integer> Qnum = order.getQnum();
			for (int i = 0; i < sn; i++) 
				for (int t = (int)start; t < end; t++) 
					quadroom[Qnum.get(i)].setDateIsNotOccupied(t);
		}
	}
	
	public static boolean CheckDateforReviseDate(int OrderID, String nCID, String nCOD) {
		Order order = user.getOrders().get(OrderID);
		long Days = CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate());
		long D = CountDaysBetween(nCID, nCOD);
		
		return D > 0 && D < Days && CountDaysBetween(order.getCheckInDate(), nCID) >= 0;
	}

	public static void ModifyDate(int OrderID, String nCID, String nCOD) {// to do
		Order order = databaseUtil.getOrderByOrderID(OrderID);
		Hotel hotel = HotelList[order.getHotelID()];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = CountDaysBetween(sdf.format(Now), order.getCheckInDate());
		long end = CountDaysBetween(sdf.format(Now), order.getCheckOutDate());
		
		long nstart = CountDaysBetween(sdf.format(Now), nCID);
		long nend = CountDaysBetween(sdf.format(Now), nCOD);
		
		ArrayList<Integer> Snum = order.getSnum();
		if (Snum.size() > 0) {
			for (int i = 0; i < Snum.size(); i++) 
				for (int t = (int)start; t < end; t++) 
					if (nstart <= t && t < nend) 
						singleroom[Snum.get(i)].setDateIsNotOccupied(t);
		}
		ArrayList<Integer> Dnum = order.getDnum();
		if (Dnum.size() > 0) {
			for (int i = 0; i < Dnum.size(); i++) 
				for (int t = (int)start; t < end; t++) 
					if (nstart <= t && t < nend) 
						doubleroom[Dnum.get(i)].setDateIsNotOccupied(t);
		}
		ArrayList<Integer> Qnum = order.getQnum();
		if (Qnum.size() > 0) {
			for (int i = 0; i < Qnum.size(); i++) 
				for (int t = (int)start; t < end; t++) 
					if (nstart <= t && t < nend) 
						quadroom[Qnum.get(i)].setDateIsNotOccupied(t);
		}
	}
}
