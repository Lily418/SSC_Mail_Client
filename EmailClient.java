import javax.mail.Message;
import javax.mail.MessagingException;


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
}
