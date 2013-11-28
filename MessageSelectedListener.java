import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Listens for when a JList selection is changed and sets the email display contents to the selected message
 * @author joel
 */
public class MessageSelectedListener implements ListSelectionListener {

    private EmailDisplay emailDisplay;
    private NavigationBar navBar;
    private DisplayMessageWorker displayMessageWorker = null;
    
    public MessageSelectedListener(EmailDisplay emailDisplay, NavigationBar navBar) {
	this.emailDisplay = emailDisplay;
	this.navBar = navBar;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
	if(!e.getValueIsAdjusting())
	{
	    if(displayMessageWorker != null){
		
		//Cancels opening any images related with the email
		displayMessageWorker.cancelChildWorkers();
				
		displayMessageWorker.cancel(true);
		
		
	    }
	    
	    displayMessageWorker = new DisplayMessageWorker(navBar, emailDisplay);
	    displayMessageWorker.execute();
	}

    }

}
