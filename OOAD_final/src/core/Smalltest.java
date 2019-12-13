package core;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
public class Smalltest {

	public static void main(String[] args) {
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
			Room r1=new Room(2,1387);
			int []a=new int[100];
			RoomNum rn=new RoomNum(1,3,1);
			System.out.println(rn.totalPeople());
			
		}
		
	}

}
