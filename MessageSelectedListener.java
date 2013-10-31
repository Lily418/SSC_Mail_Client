import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class MessageSelectedListener implements ListSelectionListener{

    private EmailDisplay emailDisplay;
    private NavigationBar navBar;
    
    public MessageSelectedListener(EmailDisplay emailDisplay, NavigationBar navBar){
	this.emailDisplay = emailDisplay;
	this.navBar = navBar;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
	try {
	    emailDisplay.setMessage(navBar.getSelectedMessage());
	} catch (MessagingException | IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
    }


    
}
