import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;


/**
 * A JPanel containing a send button, a list of received messages and a search input
 * @author joel
 *
 */
public class NavigationBar extends JPanel {
    
   private MessageList messageList;
   private JList<String> jList;
   
    public NavigationBar(EmailClient emailClient, final String email, final String password){
	
	
	FlowLayout layout = new FlowLayout();
	this.setLayout(layout);
	
	//Send button
	JButton sendButton = new JButton();
	sendButton.setText("Send");
	sendButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ComposeEmail composeEmail = new ComposeEmail(email, password);
	    }
	});
	
	this.add(sendButton);
	
	
	//List of messages
	this.messageList = new MessageList();
	messageList.addMessages(emailClient.getEmailConnection());
	
	this.jList = new JList<String>(messageList);
	jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	this.add(jList);
	
	
	//Email search
	EmailSearchField emailSearchField = new EmailSearchField(messageList, jList);
	emailSearchField.setPreferredSize(new Dimension(200, 25));
	this.add(emailSearchField);
    }
    
    /**
     * Get the message selected by the JList
     * @return
     */
    public Message getSelectedMessage(){
	//selectedIndex == -1 if no selections
	if(jList.getSelectedIndex() != -1){
	return messageList.getMessage(jList.getSelectedIndex());
	}else{
	    return null;
	}
    }
    
    public void addListSelectionListener(ListSelectionListener l){
	jList.addListSelectionListener(l);
    }
}
