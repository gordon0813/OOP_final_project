package mumi;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.Properties;
import core.*;

public class mailSender {
	final String username = "ilovecene25@gmail.com";
    final String password = "a128509758";
    Properties prop;
    Session session;
    
    public mailSender () {
    	  prop = new Properties();
  		  prop.put("mail.smtp.host", "smtp.gmail.com");
          prop.put("mail.smtp.port", "587");
          prop.put("mail.smtp.auth", "true");
          prop.put("mail.smtp.starttls.enable", "true");
    }
    private Session login() {
    	 Session ss = Session.getInstance(prop,
                 new javax.mail.Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication() {
                         return new PasswordAuthentication(username, password);
                     }
                 });
    	 return ss;
    }
    
    static public String orderTostring (Order o) {
    	LocalDate checkin = o.getPlan().getCheckInOutDate().getCheckin();
    	LocalDate checkout = o.getPlan().getCheckInOutDate().getCheckout();
    	int s_n = o.getPlan().getRoomNum().getSingleNum();
    	int d_n = o.getPlan().getRoomNum().getDoubleNum();
    	int q_n = o.getPlan().getRoomNum().getQuadNum();
    	Hotel hotel = o.getHotel();
    	long orderid = o.getId();
    	String message =  "飯店編號: " + hotel.getId() + "  地址: " + hotel.getAddress() + "\n" 
    					  + "入住日期 : " + checkin + " ~ " + checkout + "\n單人房 " + s_n + " 間     雙人房 " 
    					  + d_n + " 間     四人房 " + q_n + " 間\n" 
    					  + "訂單編號: " + orderid + "\n價格: " + hotel.calPriceOneDay(o.getPlan().getRoomNum())*o.getPlan().getCheckInOutDate().howManyDays();
    	
    	return message;
    }

	public void makeOrder(String mail, Order o) {
        Session session = login();
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail + "," + mail)
            );
            message.setSubject("訂單成立通知");
            message.setText("您的訂單已成立!!\n\n" + orderTostring(o) + "\n\n請記得來喔母咪!!");

            Transport.send(message);

            System.out.println("寄送完成~~");

        } catch (MessagingException e) {
        	System.out.println("寄送失敗QQ");
            e.printStackTrace();
        }
        
    }
	
	public void deleteOrder(String mail, Order o) {
        Session session = login();
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail + "," + mail)
            );
            message.setSubject("訂單取消通知");
            message.setText("您的訂單已取消!!\n\n" + orderTostring(o) + "\n\n請記得不要來喔母咪!!");

            Transport.send(message);

            System.out.println("寄送完成~~");

        } catch (MessagingException e) {
        	System.out.println("寄送失敗QQ");
            e.printStackTrace();
        }
        
    }
	
	public void finishOrder(String mail, Order o) {
        Session session = login();
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail + "," + mail)
            );
            message.setSubject("訂單完成通知");
            message.setText("您的訂單已完成!!\n\n" + o.toString() + "\n\n期待下次光顧喔母咪!!");

            Transport.send(message);

            System.out.println("寄送完成~~");

        } catch (MessagingException e) {
        	System.out.println("寄送失敗QQ");
            e.printStackTrace();
        }
       
    } 
	
	public void editOrder(String mail, Order o) {
        Session session = login();
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail + "," + mail)
            );
            message.setSubject("訂單修改通知");
            message.setText("您的訂單已變更!! 您的新訂單\n\n" + o.toString() + "\n\n不要搞錯喔母咪!!");

            Transport.send(message);

            System.out.println("寄送完成~~");

        } catch (MessagingException e) {
        	System.out.println("寄送失敗QQ");
            e.printStackTrace();
        }
       
    }
    

}
