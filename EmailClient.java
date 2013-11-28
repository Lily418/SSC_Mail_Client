import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageCountListener;
import javax.swing.DefaultListModel;

/**
 * For retrieving an Messages from an inbox
 * 
 * @author joel
 */
public class EmailClient {

    private EmailConnection emailConnection;

    /**
     * 
     * @param email
     *            Email Address for account
     * @param password
     *            Password for account
     * @throws MessagingException
     *             Thrown if the credentials cannot be authenticated or if there
     *             is a network issue
     */
    public EmailClient(String email, String password) throws MessagingException {
	emailConnection = new EmailConnection(email, password);

    }

    /**
     * Adds each message from the connected account the the messageList
     * 
     * @param messageList
     *            A MessageList to add the messages to
     */
    public void fillListModel(DefaultListModel<Message> messageList) {
	messageList.clear();
	for (Message m : emailConnection) {
	    messageList.addElement(m);
	}
    }

    public EmailConnection getEmailConnection() {
	return emailConnection;
    }
    
    public boolean newEmails(){
	return emailConnection.newEmails();
    }
    
    public void addMessageCountListener(MessageCountListener messageCountListener){
	emailConnection.addMessageCountListener(messageCountListener);
    }

}
