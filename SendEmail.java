import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 * An action listener to listen to when a button is pressed,send the email and clear the email input
 * @author joel
 *
 */
public class SendEmail implements ActionListener {

    private EmailSender emailSender;
    private ComposeEmail composeEmail;
    private volatile boolean sendingEmail = false;

    /**
     * 
     * @param composeEmail Email Input to take the contents of
     * @param email Email of the sender
     * @param password Password of the sender
     */
    public SendEmail(ComposeEmail composeEmail, String email, String password) {
	emailSender = new EmailSender(email, password);
	this.composeEmail = composeEmail;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	

	try {
	    //Stops pressing the button multiple times sending the e-mail multiple times.
	    if(!sendingEmail){
		sendingEmail = true;
		emailSender.sendMessage(composeEmail.getTo(), composeEmail.getCC(), composeEmail.getSubject(), composeEmail.getAttachments(), composeEmail.getEmailContents());
		composeEmail.clearInput();
		JOptionPane.showMessageDialog(composeEmail,"Message Sent");
		
	    }
	} catch (MessagingException e1) {
	    // Thrown if the email cannot be sent
	    e1.printStackTrace();
	} finally {
	    sendingEmail = false;
	}
    }

}
