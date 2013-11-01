import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;


public class SendEmail implements ActionListener {

    private EmailSender emailSender;
    private ComposeEmail composeEmail;
    
    public SendEmail(ComposeEmail composeEmail){
	emailSender = new EmailSender();
	this.composeEmail = composeEmail;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	try {
	    emailSender.sendMessage(composeEmail.getTo(), composeEmail.getCC(), composeEmail.getSubject(), composeEmail.getEmailContents());
	} catch (MessagingException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }

}
