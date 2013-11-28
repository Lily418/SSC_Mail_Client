import java.awt.Component;

import javax.mail.Message;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class CheckEmails extends SwingWorker<Void, Void> {

    private MessageList messageList;
    private EmailClient emailClient;
    private Iterable<Message> newMessages;
    private Component parent;
    private boolean firstCheck;

    /**
     * Constructor for Swing Worker to retrieve the e-mail inbox
     * @param messageList Instance of MessageList, a ListModel to fill with the received messages
     * @param emailClient An Email client which contains the IMAP connection
     * @param parent JComponent to be the parent of a JMessageDialog
     * @param firstCheck If this is the first check then the JMessageDialog will not be shown
     */
    public CheckEmails(MessageList messageList, EmailClient emailClient, Component parent, boolean firstCheck) {
	this.messageList = messageList;
	this.emailClient = emailClient;
	this.parent = parent;
	this.firstCheck = firstCheck;
    }

    @Override
    protected Void doInBackground() throws Exception {

	//The email client's connection can be iterated to get the contents of the inbox
	newMessages = emailClient.getEmailConnection();
	return null;

    }

    @Override
    protected void done() {
	if (!firstCheck) {
	    JOptionPane.showMessageDialog(parent, "You have a new e-mail!");
	}

	//Updates the message list with the new messages
	messageList.addMessages(newMessages);
    }

}
