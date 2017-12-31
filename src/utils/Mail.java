package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Mail {
    public void sendMail(String receiver, String body, String subject){
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", 587);

            String filepath = "mail.properties";
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = loader.getResourceAsStream(filepath);
            Properties property = new Properties();
            property.load(is);

            final String user = property.getProperty("mail");
            final String password = property.getProperty("password");
            String url = property.getProperty("url");

            Session s = Session.getInstance(p,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(user, password);
                        }
                    });

            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(user));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
        } catch (MessagingException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
