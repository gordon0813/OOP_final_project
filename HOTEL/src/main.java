import java.util.Scanner;
import java.util.Random;

public class main {
	public static String getRandomString(int length) {
		String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for(int i=0; i < length; i++)
		{
			int number = random.nextInt(62);//0~61
			sf.append(str.charAt(number));
		}
		return sf.toString();
	}
	
	public static void main(String[] args) {
		User[] UserList = null;
		while (true) {
			System.out.println("Please choose:\n (1)Sign in \n (2)Sign up");
			Scanner scanner = new Scanner(System.in);
			int op = scanner.nextInt();
			
			System.out.println("Please enter your Username:");
			String Username = scanner.next();
			while (true) {
				boolean ok = true;
				for (int i = 0; i < UserList.length; i++) 
					if (UserList.equals(Username)) {
						System.out.println("Username already exist.\nPleaseenter another Username:");
						ok = true;
						break;
					}
				if (ok == true) break;
			}
			
			System.out.println("Please enter your Password:");
			String Password = scanner.next();			
			
			String VerifyCode, UserCode;
			do {
				VerifyCode = getRandomString(6);
				System.out.println(VerifyCode);
				UserCode = scanner.next();
			} while (UserCode != VerifyCode);
		}
	}
}
