import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Listens for when a JList selection is changed and sets the email display contents to the selected message
 * @author joel
 */
public class MessageSelectedListener implements ListSelectionListener {

    private EmailDisplay emailDisplay;
    private NavigationBar navBar;

    public MessageSelectedListener(EmailDisplay emailDisplay, NavigationBar navBar) {
	this.emailDisplay = emailDisplay;
	this.navBar = navBar;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
	if(!e.getValueIsAdjusting())
	{
	emailDisplay.setMessage(navBar.getSelectedMessage());
	}

    }

}
