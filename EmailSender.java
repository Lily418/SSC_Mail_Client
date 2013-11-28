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

/**
 * A class to send e-mails with SMTP
 * @author joel
 *
 */
public class EmailSender {

    private String email;
    private Session session;
    private String smtphost = "smtp.gmail.com";
    private String password;

    /**
     * @param email Email to send e-mails from
     * @param password Password for that email account
     */
    public EmailSender(String email, String password) {
	this.password = password;
	this.email = email;
	Properties props = System.getProperties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", smtphost);
	props.put("mail.smtp.port", "587");
	props.setProperty("mail.user", email);
	props.setProperty("mail.password", password);

	session = Session.getDefaultInstance(props);

    }

    /**
     * Method to send an email
     * @param to Email to send to
     * @param cc String array each containing an e-mail to add as CC
     * @param subject Subject line for the email
     * @param attachments File paths to files to add as attachments
     * @param text Plain text body of the email
     * @throws AddressException Thrown if an email address cannot be parsed
     * @throws MessagingException Thrown if the e-mail cannot be sent
     */
    public void sendMessage(String to, String[] cc, String subject, String[] attachments, String text) throws MessagingException {

	//The MimeMessage contains several headers and a Multipart contents
	MimeMessage message = new MimeMessage(session);
	message.setFrom(new InternetAddress(email));
	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

	//Takes the string array and turns it into one string joined with commas
	String emails = "";
	for (String email : cc) {
	    emails += email + ",";
	}
	
	//Removes the extra comma added to the email string
	emails = emails.substring(0, emails.length() - 1);

	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emails));

	message.setSubject(subject);

	//Create message contents
	Multipart multipart = new MimeMultipart();
	
	//Add the text body part
	BodyPart messageBodyPart = new MimeBodyPart();
	messageBodyPart.setText(text);
	multipart.addBodyPart(messageBodyPart);

	//Add the attachments
	for (String attachment : attachments) {
	    addAttachment(multipart, attachment);
	}

	
	message.setContent(multipart);

	//Updates the headers to message
	message.saveChanges();
	
	//Creates swing worker to create the transport and send the email
	SendEmailWorker sendEmailWorker = new SendEmailWorker(session, smtphost, email, password, message);
	sendEmailWorker.execute();
    }

    
    //I read this StackOverflow question to find out how to add attachments
    //http://stackoverflow.com/questions/3177616/how-to-attach-multiple-files-to-an-email-using-javamail
    /**
     * Adds an attachment to a multipart message
     * @param multipart Message to add attachment to
     * @param filename File path
     * @throws MessagingException 
     */
    private void addAttachment(Multipart multipart, String filename) throws MessagingException {
	DataSource source = new FileDataSource(filename);
	BodyPart messageBodyPart = new MimeBodyPart();
	messageBodyPart.setDataHandler(new DataHandler(source));
	messageBodyPart.setFileName(filename);
	multipart.addBodyPart(messageBodyPart);
    }
}
