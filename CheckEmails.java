import javax.mail.Message;
import javax.swing.SwingWorker;

public class CheckEmails extends SwingWorker<Void, Void> {

    private MessageList messageList;
    private EmailClient emailClient;
    private Iterable<Message> newMessages;

    public CheckEmails(MessageList messageList, EmailClient emailClient) {
	this.messageList = messageList;
	this.emailClient = emailClient;
    }

    @Override
    protected Void doInBackground() throws Exception {
	
	newMessages = emailClient.getEmailConnection();
	return null;

    }

    @Override
    protected void done() {
	messageList.addMessages(newMessages);
    }

}
