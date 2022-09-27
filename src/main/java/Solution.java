import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;
import java.io.FileInputStream;

import java.util.Properties;


public class Solution {

    public static void main(String[] args) {


        final Properties properties = new Properties();


        try {
            FileInputStream fis = new FileInputStream("my.properties");
            properties.load(fis);

            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(properties.getProperty("mail.user.name"),
                            properties.getProperty("mail.user.password"));

                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("core.nod@yandex.ru"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("6637573@gmail.com"));

            message.setSubject("Hello2");
            message.setText("I love you!");


            for (int i = 0; i < 5; i++) {

                Transport transport = session.getTransport();
                transport.connect(properties.getProperty("mail.user.name"), properties.getProperty("mail.user.password"));
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                Thread.sleep(2000);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(properties.getProperty("mail.user.name"));
            System.out.println(properties.getProperty("mail.user.password"));

        }
    }
}