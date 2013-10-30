import javax.mail.MessagingException;


public class Main {
    
    public static void main(String[] args) throws MessagingException{
    EmailClient emailClient = new EmailClient();
    emailClient.printMessages();
    }
}
