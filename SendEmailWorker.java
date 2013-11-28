import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.swing.SwingWorker;

public class SendEmailWorker extends SwingWorker<Boolean, Void> {

    private MimeMessage message;
    private Session session;
    private String smtphost;
    private String email;
    private String password;
    
/**
 * Constructor for Email Worker to send an email
 * @param session The SMTP session
 * @param smtphost Address of the SMTP host
 * @param email E-mail of sender
 * @param password Password of sender
 * @param message MimeMessage to send
 */
    public SendEmailWorker(Session session, String smtphost, String email, String password, MimeMessage message) {
	this.message = message;
	this.session = session;
	this.smtphost = smtphost;
	this.email = email;
	this.password = password;
    }

    @Override
    protected Boolean doInBackground() throws Exception {

	//Creates a transport, connects and sends a message
	try {
	    Transport tr = session.getTransport("smtp");
	    tr.connect(smtphost, email, password);
	    tr.sendMessage(message, message.getAllRecipients());
	    return true;
	} catch (MessagingException e) {
	    return false;
	}

    }

}
