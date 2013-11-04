import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.swing.JList;

public class EmailSearchField extends TextField {

    private MessageList messageList;
    private MessageSearcher messageSearcher;
    private JList<String> jList;
    private List<Message> allMessages;
    
    public EmailSearchField(MessageList messageList, JList<String> jList) {
	this.messageList = messageList;
	
	allMessages = new ArrayList<Message>(messageList.getMessages().size());
	
	for(Message m : messageList.getMessages()){
	    allMessages.add(m);
	}
	
	System.out.println("Message List Length=" + messageList.getSize());
	messageSearcher = new MessageSearcher(messageList.getMessages());
	this.addActionListener(new InputChanged(this));
	this.jList = jList;
    }

    private class InputChanged implements ActionListener {

	public EmailSearchField emailSearchField;

	public InputChanged(EmailSearchField emailSearchField) {
	    this.emailSearchField = emailSearchField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	    jList.setSelectedIndices(new int[0]);
	    
	    if (!emailSearchField.getText().equals("")) {
		System.out.println("Search Field Text " + emailSearchField.getText());
		messageList.addMessages(messageSearcher.searchMessages(emailSearchField.getText()));
	    } else {
		System.out.println("Message List Length=" + messageList.getSize());
		messageList.addMessages(allMessages);
	    }

	}

    }

}
