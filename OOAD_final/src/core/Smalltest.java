package core;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
public class Smalltest {

	public static void main(String[] args) throws Exception {
		int task=1;
		if(task==0) {
			// TODO Auto-generated method stub
			// use case 1  create by int
			LocalDate d1= LocalDate.of(2019, 12, 13);
			LocalDate d2= LocalDate.of(2019, 12, 17);
			long days = ChronoUnit.DAYS.between(d1, d2);
			System.out.println("Days between: " + days);
			// use case 2  create by string
			String firstInput="2019-12-13";
			String secondInput="2019-12-17";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//4 char year 2 char month 2 char day
			d1= LocalDate.parse(firstInput, formatter);
	        d2 = LocalDate.parse(secondInput, formatter);
	        days = ChronoUnit.DAYS.between(d1, d2);
	        System.out.println("Days between: " + days);
	        //use case 3   to string
	        String outs=d1.format(formatter);
	        System.out.println(outs);
		}else if(task==1) {
			
			System.out.println("===============check in out===========");
			LocalDate d1= LocalDate.of(2020, 3, 13);
			LocalDate d2= LocalDate.of(2020, 3, 17);
			CheckInOutDate ck1=new CheckInOutDate(d1, d2);
			System.out.println(ck1);
			System.out.println("======================room num=================");
			RoomNum rn=new RoomNum(1,3,1);
			System.out.println(rn);
			System.out.println("=====================hotel======================");
			Hotel h1=Hotel.getHotel(13);//new Hotel(2, 3, "road 1", new Room(1, 1000), new Room(2,200),new Room(4,30));
			Hotel h2=Hotel.getHotel(14);
			System.out.println(h1);
			System.out.println(h2);
			System.out.println("======================plan=======================");
			Plan p1=new Plan(rn.clone(), ck1.clone(), h1);
			System.out.println(p1);
			System.out.println("======================Order======================");
			Order order=new Order(p1, false);
			System.out.println(order);
	
			User.signup("123", "gg");

			
			
			User.login("123", "gg");
		
			Order order1= p1.toOrder();
			order1.confirm();
			System.out.println("==============================user===============================");
			System.out.println(User.getUser().toStringAll());
			order1.deleteSelf();
			System.out.println("==============================search=============================");
			Search_input si=new Search_input(4, 3, 100000/4, 100, 20, ck1, rn, "�x�_��"); //120000/4 price per days
			Plan[] plarr=Hotel.search(si);
			for(Plan p:plarr) {
				System.out.println(p);
			}
			/*
			User u1=User.getUser();
			User.logout();
			Hotel.search(si);
			Hotel.search(si);
			System.out.println(order1);
			*/
			System.out.println("==============================other===============================");
			ArrayList<RoomNum> tmpnum=Hotel.roomset(10,new RoomNum(0,2,0));
			for(RoomNum i:tmpnum ) {
				System.out.println(i);
			}
			
		
			
		}
		
	}

}
