import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;

/**
 * WARNING:Trying to modify the contents of this list using any other methods
 * but addMessages will violate it's integrity
 *
 * This class lets you add Messages to a DefaultListModel but rather than using the toString method of Messages formats the list items differently.
 * 
 * @author joel
 * 
 */
public class MessageList extends DefaultListModel<String> {

    private List<Message> listPositionToMessage = new ArrayList<Message>();

    /**
     * Sets the contents of the DefaultListModel to the Iterable of messages
     * @param messages Iterable of Messages to fill the list with
     */
    public void addMessages(Iterable<Message> messages) {
	try {
	    // Clear previous lists
	    this.clear();
	    listPositionToMessage.clear();

	    for (Message message : messages) {
		//Add the Message object to the List and a string representation of the message to the DefaultListModel
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
    
    public List<Message> getMessages(){
	return listPositionToMessage;
    }

}
