import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;

/**
 * An action listener to listen to when a button is pressed,send the email and
 * clear the email input
 * 
 * @author joel
 * 
 */
public class SendEmail implements ActionListener {

    private EmailSender emailSender;
    private ComposeEmail composeEmail;

    /**
     * 
     * @param composeEmail
     *            Email Input to take the contents of
     * @param email
     *            Email of the sender
     * @param password
     *            Password of the sender
     */
    public SendEmail(ComposeEmail composeEmail, String email, String password) {
	emailSender = new EmailSender(email, password);
	this.composeEmail = composeEmail;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	try {
	    emailSender.sendMessage(composeEmail.getTo(), composeEmail.getCC(), composeEmail.getSubject(), composeEmail.getAttachments(), composeEmail.getEmailContents());
	    composeEmail.setVisible(false);
	    composeEmail.dispose();
	} catch (MessagingException e1) {
	    JOptionPane.showMessageDialog(composeEmail, e1);
	}

    }

}
