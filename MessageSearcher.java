import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.mail.Message;

public class MessageSearcher {

    private TreeMap<String, Set<Message>> messageIndex = new TreeMap<String, Set<Message>>();

    // Email Display used for reading text contents of e-mails
    private EmailDisplay emailDisplay = new EmailDisplay();

    public MessageSearcher(List<Message> messages) {
	for (Message message : messages) {
	    indexMessage(message);
	}
    }

    private void indexMessage(Message m) {
	indexString(emailDisplay.parseMessageContents(m, false), m);
    }

    private void indexString(String s, Message m) {
	for (String word : s.split(" ")) {

	    Set<Message> setOfMessagesContainingWord = messageIndex.get(word);

	    if (setOfMessagesContainingWord == null) {
		setOfMessagesContainingWord = new HashSet<Message>();
		messageIndex.put(word, setOfMessagesContainingWord);
	    }

	    setOfMessagesContainingWord.add(m);

	}
    }

    public Set<Message> searchMessages(String query) {
	Set<Message> messages = new HashSet<Message>();

	for (String word : query.split(" ")) {

	    Set<Message> matchedMessages = messageIndex.get(word);

	    if (matchedMessages != null) {
		for (Message m : matchedMessages) {
		    messages.add(m);
		}
	    }
	}

	return messages;
    }
}
