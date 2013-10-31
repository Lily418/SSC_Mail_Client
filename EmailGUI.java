import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;


public class EmailGUI extends JFrame {

    public EmailGUI() throws MessagingException{
	this.setBackground(new Color(200, 0, 0));
	this.setVisible(true);
	
	
	
	NavigationBar navBar = new NavigationBar();
	navBar.setPreferredSize(new Dimension(300, 600));
	this.add(navBar, BorderLayout.WEST);	

	
	EmailDisplay emailDisplay = new EmailDisplay();
	emailDisplay.setPreferredSize(new Dimension(500, 600));
	this.add(emailDisplay, BorderLayout.EAST);
	
	navBar.addListSelectionListener(new MessageSelectedListener(emailDisplay, navBar));
	
	this.pack();
    
    }
    
}
