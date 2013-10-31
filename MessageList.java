import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;


/**
 * WARNING:Trying to modify the contents of this list using any other methods but addMessages will violate it's integrity
 * @author joel
 *
 */
public class MessageList extends DefaultListModel<String> {

    private List<Message> listPositionToMessage = new ArrayList<Message>();
    
    public void addMessages(Iterable<Message> messages) throws MessagingException{
	
	//Clear previous lists
	this.clear();
	listPositionToMessage.clear();
	
	for(Message message : messages){
	    listPositionToMessage.add(message);
	    this.addElement("<html>" + message.getFrom()[0] +" <br /> " + message.getSubject() + "<br /><br /></html>");
	}
    }
    
    public Message getMessage(int index){
	return listPositionToMessage.get(index);
    }
    
}
