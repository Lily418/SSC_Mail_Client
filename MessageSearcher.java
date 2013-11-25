import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.Message;

/**
 * A class for searching Messages
 * @author joel
 *
 */
public class MessageSearcher {

    //This maps a word to the set of messages the word appears in
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
	//For each word in the string
	for (String word : s.split(" ")) {

	    
	    Set<Message> setOfMessagesContainingWord = messageIndex.get(word);

	    //If word has not been found in any previous string then the set will be null so we need to create it
	    if (setOfMessagesContainingWord == null) {
		setOfMessagesContainingWord = new HashSet<Message>();
		messageIndex.put(word, setOfMessagesContainingWord);
	    }

	    setOfMessagesContainingWord.add(m);

	}
    }

    /**
     * @param query Words to search for should be separated by spaces
     * @return set of all messages containing any word in the query
     */
    public Set<Message> searchMessages(String query) {
	
	Set<Message> messages = new HashSet<Message>();

	//For each word in the query
	for (String word : query.split(" ")) {

	    //Gets the Set of messages that contain word
	    Set<Message> matchedMessages = messageIndex.get(word);

	    //matchedMessages will be null if there's not messages containing that word
	    if (matchedMessages != null) {
		for (Message m : matchedMessages) {
		    messages.add(m);
		}
	    }
	}

	return messages;
    }
}
