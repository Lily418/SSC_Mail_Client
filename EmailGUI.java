import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A GUI for an Email Client
 * @author joel
 *
 */
public class EmailGUI extends JFrame {

    public EmailGUI() {
	
	NavigationBar navBar;
	try {		
	    navBar = new NavigationBar(new EmailClient("harrypop3@gmail.com", "8j4fECpb"), "harrypop3@gmail.com", "8j4fECpb", this);
	    navBar.setPreferredSize(new Dimension(300, 600));
	    this.add(navBar, BorderLayout.WEST);

	    EmailDisplay emailDisplay = new EmailDisplay();
	    emailDisplay.setPreferredSize(new Dimension(500, 600));
	    this.add(emailDisplay, BorderLayout.EAST);

	    navBar.addListSelectionListener(new MessageSelectedListener(emailDisplay, navBar));
	    this.setVisible(true);
	    this.pack();
	} catch (MessagingException e) {
	    JOptionPane.showMessageDialog(this,"Could not connect to Mail Server \n" + e);
	}
	
	

    }

}
