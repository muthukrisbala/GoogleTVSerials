package google.tv.serials.main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BloggerMail {
   public static  void sendMail(String title, String content) {
	   String to = "muthukris82.0007@blogger.com";
	   String from = "muthukris82@gmail.com";
	   Properties properties = System.getProperties();
	     properties.put("mail.smtp.starttls.enable", "true"); 
	     properties.put("mail.smtp.host", "smtp.gmail.com");
	     properties.put("mail.smtp.port", "587");
	     properties.put("mail.smtp.auth", "true");

	   Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	 protected PasswordAuthentication getPasswordAuthentication() {
	     return new PasswordAuthentication("muthukris82@gmail.com", "Saudia@11");
	 }});

	   try{
		   //String content="<b>Welcome</b>to world of java <iframe frameborder=\"0\" height=\"343\" src=\"http://www.dailymotion.com/embed/video/kR7hqfHkKYQ5z8kYlRL?theme=cappuccino&amp;syndication=117257&amp;foreground=%23E8D9AC&amp;highlight=%23FFF6D9&amp;background=%23493D27&amp;k2ECqTrSJuzTp4gw8N5=0&amp;hideInfos=1\" width=\"560\"></iframe>";
	      MimeMessage message = new MimeMessage(session);
	      message.setFrom(new InternetAddress(from));
	      message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	      message.setSubject(title);
	      message.setContent(content,"text/html" );
	      Transport.send(message);
	      System.out.println("Sent message successfully....");
	   }catch (MessagingException mex) {
	      mex.printStackTrace();
	   }
   }
}
