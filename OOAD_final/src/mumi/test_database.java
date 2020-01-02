package mumi;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import core.*;
import databaseException.noSuchHotel;
import databaseException.userExist;

import org.json.simple.parser.ParseException;

public class test_database {

	public static void main(String[] args) throws Exception {
		// connect to the database
		mumiLite Lite = new mumiLite();
//=================================================================		
		// initialize table:Hotel
		boolean reset_Hotel = false;
		if (reset_Hotel) {
			Lite.LoadFromJSON();
		}
		// show all hotel
		//Lite.printAllHotel();
//=================================================================
		// initialize table:Schedule
		boolean reset_Schedule = false;
		
		if (reset_Schedule) {	
			Lite.scheduleInit(LocalDate.of(2019, 12, 1), LocalDate.of(2020, 9, 1));
		}
		// show schedule
		//Lite.printSchedule(5);		
//=================================================================	
		// initialize table:Comment
		boolean reset_Comment = false;
		if (reset_Comment) {
			Lite.commentInit();
		}
		// debug comment
		// add comment to hotel 1
		/*
		for (int i = 0; i < 5; i++)
			Lite.addComment(1, "mumi is good");
		System.out.println(Lite.loadComments(1));
		*/
		// end
//=================================================================		
		//initialize table:Orderid
		boolean reset_orderid = false;
		if (reset_orderid) {
			Lite.orderidInit();
		}
		// debug orderid
		/*
		for (int i = 0; i < 20; i++) {
			System.out.println(Lite.currentOrderid());
		}
		*/
		// end
//=================================================================	
		//initialize table:Plan
		boolean reset_plan = false;
		if (reset_plan) {
			Lite.planInit();
		}
		// debug Plan
		// test plan id
		/*
		for (int i = 0; i < 20; i++) {
			System.out.println(Lite.currentPlanid());
		}
		*/
		// test add Plan
		/*
		LocalDate d1 = LocalDate.of(2020, 6, 13);
		LocalDate d2 = LocalDate.of(2020, 6, 15);
		Plan a = new Plan (new RoomNum(2,2,3),new CheckInOutDate(d1,d2),Lite.getHotel(6));
		d1 = LocalDate.of(2020, 1, 6);
		d2 = LocalDate.of(2020, 1, 10);
		Plan b = new Plan (new RoomNum(1,10,2),new CheckInOutDate(d1,d2),Lite.getHotel(7));
		
		int pid1 = Lite.addPlan(a, 20);
		int pid2 = Lite.addPlan(b, 20);
		
		// test get plan
		Plan p1 = Lite.getPlan(pid1);
		Plan p2 = Lite.getPlan(pid2);
		System.out.println(p1); System.out.println(p2);
		// test delete plan
		try {
			Lite.deletePlan(p1.getId());
			Lite.deletePlan(p2.getId());
			Lite.deletePlan(p1.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Lite.deletePlan(666);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		*/
		// end
//=================================================================	
		//initialize table:Orders
		boolean reset_order = false;
		if (reset_order) {
			Lite.orderInit();
		}
		// debug order
		// test addOrder
		/*
		LocalDate d1 = LocalDate.of(2020, 2, 3);
		LocalDate d2 = LocalDate.of(2020, 2, 6);
		Plan p1 = new Plan (new RoomNum(2,2,3),new CheckInOutDate(d1,d2),Lite.getHotel(22));
		d1 = LocalDate.of(2020, 1, 6);
		d2 = LocalDate.of(2020, 1, 10);
		Plan p2 = new Plan (new RoomNum(1,10,2),new CheckInOutDate(d1,d2),Lite.getHotel(33));
		
		Order o1 = new Order(p1,true,Lite.currentOrderid()), o2 = new Order(p2,true,Lite.currentOrderid());
		long oid1 = Lite.addOrder(o1, 123); 
		long oid2 = Lite.addOrder(o2, 456);
		*/
		// test get order
		/*
		o1 = Lite.getOrder(oid1);
		o2 = Lite.getOrder(oid2);
		System.out.println(o1); System.out.println(o2);
		*/
		// test delete
		/*
		try {
			Lite.deleteOrder(oid1);
			Lite.deleteOrder(oid2);
			Lite.deleteOrder(oid1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		*/
		// end
//=================================================================
		//initialize table:User
		boolean reset_user = false;
		if (reset_user) {
			Lite.userInit();
		}
//=================================================================
		// test date and localdate
		/*
		LocalDate d = LocalDate.of(1999, 5, 12);
		System.out.println(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(d.toString());
		System.out.println(date);
		Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
		System.out.println(localDate);
		*/
		/*
		LocalDate d = LocalDate.of(1999, 5, 12);
		long l = Lite.localToLong(d);
		System.out.println(d);
		System.out.println(l);
		d = d.plusDays(1);
		l = Lite.localToLong(d);
		System.out.println(d);
		System.out.println(l);
		*/
//=================================================================
		// test extendRoom
		/*
		for (int i = 0; i < 1500; i++) {
			RoomNum room = new RoomNum(0,0,0);
			CheckInOutDate time = new CheckInOutDate(LocalDate.of(2020, 1, 3),LocalDate.of(2020, 1, 6));
			room = Lite.extendRoom(i, room, time);
			System.out.println(room);
		}
		*/
//=================================================================
		// test extendDate
		/*
		int id = 0;
		RoomNum room = new RoomNum(1,1,1);
		CheckInOutDate time = new CheckInOutDate(LocalDate.of(2020, 1, 3),LocalDate.of(2026, 1, 6));
		try {
			time = Lite.extendDate(id, room, time);
			System.out.println(time);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		*/
		
		
//=================================================================		
		// test exception
		// no such hotel
		/*
		try{
			Lite.getHotel(-1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try{
			Lite.getHotel(1500);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		*/
//=================================================================
		//initialize table:Search
		boolean reset_search = false;
		if (reset_search) {
			Lite.searchInit();;
		}	
//=================================================================
		
		//Lite.printAllUser();
		/*
		try {
			Lite.addUser("123", "ggo");
		} catch (userExist e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}*/
		/*
		try {
			User mumi = Lite.getUser("ss", "234");
			System.out.println(mumi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
//=================================================================
		// test scheduler and order
		/*
		try {
			Lite.addUser("mumi2", "dadala");
		} catch (userExist e) {
			e.printStackTrace();
		}
		*/
		//Lite.printSchedule(10,LocalDate.of(2019, 12, 10),LocalDate.of(2019, 12, 16));
		
		//User user = Lite.getUser("mumi2", "dada");
		//Lite.addUsermail("mumi2", "ilovecene27@gmail.com");
		//System.out.println(user);
		/*
		Hotel hotel = Lite.getHotel(88);
		CheckInOutDate ck = new CheckInOutDate(LocalDate.of(2020,3, 19),LocalDate.of(2020, 3, 21));
		RoomNum rm = new RoomNum(3,2,1);
		Plan p = new Plan(rm,ck,hotel,Lite.currentPlanid());
		Order o = new Order(p,true);
		System.out.println(o.getId());
		Lite.addOrder(o,"mumi2");
		*/
		
		 //Lite.deleteOrder(117);
		 
		//Lite.printSchedule(10,LocalDate.of(2019, 12, 10),LocalDate.of(2019, 12, 16));
		
		//System.out.println(Lite.currentOrderid());
		//User user = Lite.getUser("mumi", "dadala");
		/*
		Order o = Lite.getOrder(31);
		System.out.println(o);*/
		//Lite.deleteOrder(28);
//=================================================================
		// test add search
		/*
		CheckInOutDate ck = new CheckInOutDate(LocalDate.of(2020, 1, 3),LocalDate.of(2020, 1, 6));
		RoomNum rm = new RoomNum(3,2,1);
		Search_input ser = new Search_input(5,3,10000,1000,3,ck,rm,"母咪路四段");
		Lite.addSearch(ser, "mumi人");
		*/
		/*
		try {
			Lite.addUser("mumi人", "mumimumi");
		} catch (userExist e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		*/
		/*
		try {
			User mumi = Lite.getUser("mumi人", "mumimumi");
			System.out.println(mumi.toStringAll());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
//===================================================================
		/*
		try {
			Lite.legalUserData("mumi", "mumimumuum動");
			//Lite.legalUserData("mumi", "       ");
			//Lite.legalUserData("mumi", "心動動a心動動");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		*/
//=================================================================
		// set up table: Todayflag
		boolean reset_todayflag = false;
		if (reset_todayflag) {
			Lite.todayfagInit();
		}
//=================================================================
		// test mail
		//mailSender mm = new mailSender();
		
		//mm.makeOrder("ilovecene27@gmail.com",o);
//=================================================================
		// set up table: Usermail
		boolean reset_usermail = false;
		if (reset_usermail) {
			Lite.usermailInit();
		}
//=================================================================	
		// test edit password
		//Lite.printAllUser();
		//User user = Lite.getUser("z", "zzzzzzz");
		//Lite.editUsermail("z", "ilovecene27@gmail.com");
		//Lite.editUserpassword("mumi2", "dadala","shindondon");
		/*
		user = Lite.getUser("mumi2", "dadala");
		user = Lite.getUser("mumi2", "shindondon");
		*/
//=================================================================
		// test order to string form
		//System.out.println(Lite.currentOrderid());
		//System.out.println(Lite.getOrder(121));
//=================================================================
		// test print all order
		Lite.printAllOrder();
	}

	
	
}
