import java.util.List;

import javax.mail.Message;
import javax.swing.SwingWorker;

public class DisplayMessageWorker extends SwingWorker<Void, Void> {

    private NavigationBar navBar;
    private EmailDisplay emailDisplay;
    private ParsedMessage parsedMessage;
    private List<OpenImageWorker> imageWorkers;

    public DisplayMessageWorker(NavigationBar navBar, EmailDisplay emailDisplay) {
	this.navBar = navBar;
	this.emailDisplay = emailDisplay;
    }

    @Override
    protected Void doInBackground() throws Exception {
	Message selectedMessage = navBar.getSelectedMessage();
	parsedMessage = emailDisplay.parseMessageContents(selectedMessage, true);
	imageWorkers = parsedMessage.getImageWorkers();
	return null;
    }

    @Override
    protected void done() {
	// Parsed message will be null if the worker is cancelled because
	// another message is selected
	if (parsedMessage != null) {
	    emailDisplay.setTextArea(parsedMessage.getText());

	    for (OpenImageWorker imageWorker : imageWorkers) {
		imageWorker.execute();
	    }
	}

    }

    /**
     * Cancels the ImageOpening workers that this worker spawned
     */
    public void cancelChildWorkers() {
	//Will be null if worker interupted because another message selected
	if (imageWorkers != null) {
	    for (OpenImageWorker worker : imageWorkers) {
		worker.cancel(true);
	    }
	}
    }

}
