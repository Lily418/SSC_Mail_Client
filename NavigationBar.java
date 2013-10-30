import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;


public class NavigationBar extends JPanel {
    
   private EmailClient emailClient;
    
    public NavigationBar() throws MessagingException{
	emailClient = new EmailClient();
	MessageList messages = new MessageList();
	messages.addMessages(emailClient.getEmailConnection());
	
	JList<String> jList = new JList<String>(messages);
	jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	this.add(jList);
    }
}
