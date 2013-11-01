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


public class NavigationBar extends JPanel {
    
   private EmailClient emailClient;
   private MessageList messageList;
   private JList<String> jList;
   
    public NavigationBar(EmailClient emailClient){
	
	JButton sendButton = new JButton();
	sendButton.setText("Send ");
	sendButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ComposeEmail composeEmail = new ComposeEmail();
	    }
	});
	this.add(sendButton);
	
	this.emailClient = emailClient;
	this.messageList = new MessageList();
	messageList.addMessages(emailClient.getEmailConnection());
	
	this.jList = new JList<String>(messageList);
	jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	this.add(jList);
    }
    
    public Message getSelectedMessage(){
	return messageList.getMessage(jList.getSelectedIndex());
    }
    
    public void addListSelectionListener(ListSelectionListener l){
	jList.addListSelectionListener(l);
    }
}
