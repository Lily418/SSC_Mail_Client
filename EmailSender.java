import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private String username = "harrypop3@gmail.com";
    private Session session;
    private String smtphost = "smtp.gmail.com";
    private String password = "KVHu4yHy";

    public EmailSender() {

	
	

	Properties props = System.getProperties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", smtphost);
	props.put("mail.smtp.port", "587");
	props.setProperty("mail.user", username);
	props.setProperty("mail.password", password);

	session = Session.getDefaultInstance(props);

    }

    public void sendMessage(String to, String[] cc, String subject, String text) throws AddressException, MessagingException {
	MimeMessage message = new MimeMessage(session);
	message.setFrom(new InternetAddress(username));
	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	
	String emails = "";
	for(String email : cc)
	{
	    emails += email + ",";
	}
	
	emails = emails.substring(0, emails.length() - 1);
	
	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emails));
	
	
	message.setSubject(subject);
	message.setText(text);

	message.saveChanges();

	// Step 4: Send the message by javax.mail.Transport .
	Transport tr = session.getTransport("smtp"); // Get Transport object
						     // from session
	tr.connect(smtphost, username, password); // We need to connect
	tr.sendMessage(message, message.getAllRecipients()); // Send message
    }
}
