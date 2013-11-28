import java.awt.Dimension;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A Panel to display an e-mail message
 * 
 * @author joel
 * 
 */
public class EmailDisplay extends JPanel {

    private JTextArea textArea;

    public EmailDisplay() {
	textArea = new JTextArea();
	textArea.setPreferredSize(new Dimension(500, 600));
	this.add(textArea);
    }
    
    
    public void setTextArea(String text){
	textArea.setText(text);
    }
    
    /**
     * Returns the text contents of the message and opens attached images if openImages set to true
     * 
     * @param message
     *            The message to parse
     */
    public ParsedMessage parseMessageContents(Message message, boolean openImages) {

	String messageContents = "";
	List<OpenImageWorker> imageWorkers = new LinkedList<>();
	
	try {
	    if (message.getContentType().contains("TEXT/PLAIN")) {
		messageContents = (message.getContent().toString());
	    } else {
		//Message type is multipart
		Multipart multipart = (Multipart) message.getContent();
		
		
		for (int i = 0; i < multipart.getCount(); i++) {
		    //For each part of the message if it's plain text then add that to the text area otherwise log an unrecongized part.
		    BodyPart bodyPart = multipart.getBodyPart(i);
		    if (bodyPart.getContentType().contains("TEXT/PLAIN")) {
			messageContents += bodyPart.getContent().toString();
		    } else if(bodyPart.getContentType().contains("IMAGE/JPEG")){
			
			if(openImages){
			    OpenImageWorker openImageWorker = new OpenImageWorker(bodyPart.getContent());
			    imageWorkers.add(openImageWorker);
			}
			
			
		    }
		    else {
			System.out.println("Unrecognised content type " + bodyPart.getContentType());
		    }
		}
	    }
	} catch (MessagingException | IOException e) {
	    messageContents = (e.toString());
	}
	
	return new ParsedMessage(messageContents, imageWorkers);
    }

}
