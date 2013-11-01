import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;

/**
 * WARNING:Trying to modify the contents of this list using any other methods
 * but addMessages will violate it's integrity
 * 
 * @author joel
 * 
 */
public class MessageList extends DefaultListModel<String> {

    private List<Message> listPositionToMessage = new ArrayList<Message>();

    public void addMessages(Iterable<Message> messages) {
	try {
	    // Clear previous lists
	    this.clear();
	    listPositionToMessage.clear();

	    for (Message message : messages) {
		listPositionToMessage.add(message);

		String subject = message.getSubject();
		if (subject == null) {
		    subject = "";
		}

		this.addElement("<html>" + message.getFrom()[0] + " <br /> " + subject + "<br /><br /></html>");
	    }
	} catch (MessagingException e) {
	    System.out.println("Message did not contain expected component");
	    System.out.println(e);
	}
    }

    public Message getMessage(int index) {
	return listPositionToMessage.get(index);
    }

}
