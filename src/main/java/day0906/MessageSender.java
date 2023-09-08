package day0906;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

public class MessageSender {
    private static final Message message;

    private MessageSender() {
    }

    private static final class MessageSenderHolder {
        private static final MessageSender MESSAGE_SENDER = new MessageSender();
    }

    public static MessageSender getInstance() {
        return MessageSenderHolder.MESSAGE_SENDER;
    }

    static {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.qq.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1075534380@qq.com", "nsardjvomzokjbce");
            }
        });
        message = new MimeMessage(session);
    }


    public void sentMessage(String toEmail, String title, Object msg) {
        // who you are
        try {
            message.setFrom(new InternetAddress("1075534380@qq.com"));
            // send to ...
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            message.setSubject(title);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sent message successfully....");
    }
}
