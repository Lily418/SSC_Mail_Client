import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPFolder;

public class EmailConnection implements Iterable<Message> {

    private Properties prop;
    private Store store = null;
    private IMAPFolder folder = null;

    public EmailConnection(String username, String password) throws MessagingException {

	prop = System.getProperties();
	prop.setProperty("mail.store.protocol", "imaps");
	prop.setProperty("mail.user", username);
	prop.setProperty("mail.password", password);

	Session session = Session.getDefaultInstance(prop);
	store = session.getStore("imaps");
	store.connect("imap.googlemail.com", username, password);

	folder = (IMAPFolder) store.getFolder("inbox");
	folder.open(Folder.READ_WRITE);

    }

    @Override
    public Iterator<Message> iterator() {

	try {
	    return new Iterator<Message>() {
	        Message[] messages = folder.getMessages();
	        int count = 0;
	        
	        @Override
	        public boolean hasNext() {
	    	return count < messages.length;
	        }

	        @Override
	        public Message next() {
	    	return messages[count++];
	        }

	        @Override
	        public void remove() {
	    	throw new UnsupportedOperationException();
	        }
	    };
	} catch (MessagingException e) {
	    System.out.println(e);
	    return null;
	}
    }

}
