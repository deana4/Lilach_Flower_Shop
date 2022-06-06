package il.server;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendEmail {
    private static Session session;
    private final static String username = "lilach7746";
    private final static String password = "pgnfszsewczixhjr";

    public static void sendTo(String email, String titel, String text){
        try {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username + "@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(titel);
            message.setText(text);

            Transport.send(message);

            System.out.println("send Email ("+titel+") to: "+email);

        } catch (MessagingException e) {
            System.out.println("Error: dont send email");
            e.printStackTrace();
        }
    }
}




