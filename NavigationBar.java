import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

/**
 * A JPanel containing a send button, a list of received messages and a search
 * input
 * 
 * @author joel
 * 
 */
public class NavigationBar extends JPanel {

    private MessageList messageList;
    private JList<String> jList;

    public NavigationBar(final EmailClient emailClient, final String email, final String password, final EmailGUI emailGUI) {

	FlowLayout layout = new FlowLayout();
	this.setLayout(layout);

	// Send button
	JButton sendButton = new JButton();
	sendButton.setText("Send");
	sendButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ComposeEmail composeEmail = new ComposeEmail(email, password);
	    }
	});

	this.add(sendButton);

	// List of messages
	this.messageList = new MessageList();
	this.jList = new JList<String>(messageList);
	jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane jScrollPane = new JScrollPane(jList);

	this.add(jScrollPane);

	//Performs the initial check for emails
	CheckEmails checkEmails = new CheckEmails(messageList, emailClient, emailGUI, true);
	checkEmails.execute();

	//Adds a listener for when new messages are added to the IMAP folder
	emailClient.addMessageCountListener(new MessageCountListener() {

	    @Override
	    public void messagesRemoved(MessageCountEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void messagesAdded(MessageCountEvent arg0) {
		//When the message count has changed it creates a Swing Worker to get the new emails
		CheckEmails checkEmails = new CheckEmails(messageList, emailClient,  emailGUI, false);
		checkEmails.execute();
	    }
	});


    }

    /**
     * Get the message selected by the JList
     * 
     * @return
     */
    public Message getSelectedMessage() {
	// selectedIndex == -1 if no selections
	if (jList.getSelectedIndex() != -1) {
	    return messageList.getMessage(jList.getSelectedIndex());
	} else {
	    return null;
	}
    }

    public void addListSelectionListener(ListSelectionListener l) {
	jList.addListSelectionListener(l);
    }
}
