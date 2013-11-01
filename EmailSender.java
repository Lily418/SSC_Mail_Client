import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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

    public void sendMessage(String to, String[] cc, String subject, String[] attachments, String text) throws AddressException, MessagingException {

	// Setup message headers
	MimeMessage message = new MimeMessage(session);
	message.setFrom(new InternetAddress(username));
	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

	String emails = "";
	for (String email : cc) {
	    emails += email + ",";
	}

	emails = emails.substring(0, emails.length() - 1);

	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emails));

	message.setSubject(subject);

	// Create message contents
	Multipart multipart = new MimeMultipart();
	BodyPart messageBodyPart = new MimeBodyPart();
	messageBodyPart.setText(text);
	multipart.addBodyPart(messageBodyPart);

	for (String attachment : attachments) {
	    addAttachment(multipart, attachment);
	}

	message.setContent(multipart);

	message.saveChanges();

	// Step 4: Send the message by javax.mail.Transport .
	Transport tr = session.getTransport("smtp"); // Get Transport object
						     // from session
	tr.connect(smtphost, username, password); // We need to connect
	tr.sendMessage(message, message.getAllRecipients()); // Send message
    }

    private static void addAttachment(Multipart multipart, String filename) throws MessagingException {
	DataSource source = new FileDataSource(filename);
	BodyPart messageBodyPart = new MimeBodyPart();
	messageBodyPart.setDataHandler(new DataHandler(source));
	messageBodyPart.setFileName(filename);
	multipart.addBodyPart(messageBodyPart);
    }
}
