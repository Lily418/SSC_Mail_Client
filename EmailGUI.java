import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EmailGUI extends JFrame {

    public EmailGUI() {
	
	NavigationBar navBar;
	try {
	    this.setBackground(new Color(200, 0, 0));
		
	    navBar = new NavigationBar(new EmailClient());
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
