import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;


public class EmailClient {
    
    private EmailConnection emailConnection;

    public EmailClient() throws MessagingException{
	emailConnection = new EmailConnection("harrypop3@gmail.com", "KVHu4yHy");
	
    }
    
    public void printMessages() throws MessagingException{
	for(Message m : emailConnection){
	    System.out.println(m.getFrom()[0] +" " + m.getSubject());
	}
    }
    
    public void fillListModel(DefaultListModel<Message> messageList)
    {
	messageList.clear();
	for(Message m : emailConnection)
	{
	    System.out.println(m.getClass());
	    messageList.addElement(m);
	}
    }
    
    public EmailConnection getEmailConnection() {
        return emailConnection;
    }

}
