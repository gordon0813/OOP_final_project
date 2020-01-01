package mumi;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
            message.setText("您的訂單已成立!!\n\n" + o.toString() + "\n\n請記得來喔母咪!!");

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
            message.setText("您的訂單已取消!!\n\n" + o.toString() + "\n\n請記得不要來喔母咪!!");

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

}
