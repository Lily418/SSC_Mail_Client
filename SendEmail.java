import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

public class SendEmail implements ActionListener {

    private EmailSender emailSender;
    private ComposeEmail composeEmail;
    private volatile boolean sendingEmail = false;

    public SendEmail(ComposeEmail composeEmail) {
	emailSender = new EmailSender();
	this.composeEmail = composeEmail;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	

	try {
	    if(!sendingEmail){
		sendingEmail = true;
		emailSender.sendMessage(composeEmail.getTo(), composeEmail.getCC(), composeEmail.getSubject(), composeEmail.getEmailContents());
		composeEmail.clearInput();
		JOptionPane.showMessageDialog(composeEmail,"Message Sent");
		
	    }
	} catch (MessagingException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	} finally {
	    sendingEmail = false;
	}
    }

}
