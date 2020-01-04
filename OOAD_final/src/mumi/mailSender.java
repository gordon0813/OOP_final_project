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
    	String message =  "�����s��: " + hotel.getId() + "  �a�}: " + hotel.getAddress() + "\n" 
    					  + "�J���� : " + checkin + " ~ " + checkout + "\n��H�� " + s_n + " ��     ���H�� " 
    					  + d_n + " ��     �|�H�� " + q_n + " ��\n" 
    					  + "�q��s��: " + orderid + "\n����: " + hotel.calPriceOneDay(o.getPlan().getRoomNum())*o.getPlan().getCheckInOutDate().howManyDays();
    	
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
            message.setSubject("�q�榨�߳q��");
            message.setText("�z���q��w����!!\n\n" + orderTostring(o) + "\n\n�аO�o�ӳ���}!!");

            Transport.send(message);

            System.out.println("�H�e����~~");

        } catch (MessagingException e) {
        	System.out.println("�H�e����QQ");
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
            message.setSubject("�q������q��");
            message.setText("�z���q��w����!!\n\n" + orderTostring(o) + "\n\n�аO�o���n�ӳ���}!!");

            Transport.send(message);

            System.out.println("�H�e����~~");

        } catch (MessagingException e) {
        	System.out.println("�H�e����QQ");
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
            message.setSubject("�q�槹���q��");
            message.setText("�z���q��w����!!\n\n" + o.toString() + "\n\n���ݤU�����U����}!!");

            Transport.send(message);

            System.out.println("�H�e����~~");

        } catch (MessagingException e) {
        	System.out.println("�H�e����QQ");
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
            message.setSubject("�q��ק�q��");
            message.setText("�z���q��w�ܧ�!! �z���s�q��\n\n" + o.toString() + "\n\n���n�d������}!!");

            Transport.send(message);

            System.out.println("�H�e����~~");

        } catch (MessagingException e) {
        	System.out.println("�H�e����QQ");
            e.printStackTrace();
        }
       
    }
    

}
