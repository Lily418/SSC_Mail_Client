import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.swing.JList;

/**
 * A text field that alters edits a JList to show the result of a Message Search
 * @author joel
 *
 */
public class EmailSearchField extends TextField {

    private MessageList messageList;
    private MessageSearcher messageSearcher;
    private JList<String> jList;
    
    //A list of all the messages so that when no search is made the list can be updated with all the e-mails again
    private List<Message> allMessages;
    
    /**
     * 
     * @param messageList A list of message to sea
     * @param jList A J List to update with results of the search query
     */
    public EmailSearchField(MessageList messageList, JList<String> jList) {
	this.messageList = messageList;
	
	//Creating a shallow clone of the message list
	allMessages = new ArrayList<Message>(messageList.getMessages().size());
	for(Message m : messageList.getMessages()){
	    allMessages.add(m);
	}
	
	messageSearcher = new MessageSearcher(messageList.getMessages());
	
	//Will fire when input changed
	this.addActionListener(new InputChanged(this));
	this.jList = jList;
    }

    /**
     * This nested class is the action listener that calls the search and update methods
     */
    private class InputChanged implements ActionListener {

	public EmailSearchField emailSearchField;

	public InputChanged(EmailSearchField emailSearchField) {
	    this.emailSearchField = emailSearchField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	    jList.setSelectedIndices(new int[0]);
	    
	    if (!emailSearchField.getText().equals("")) {
		messageList.addMessages(messageSearcher.searchMessages(emailSearchField.getText()));
	    } else {
		messageList.addMessages(allMessages);
	    }

	}

    }

}
