import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sun.mail.util.BASE64DecoderStream;

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

    /**
     * Sets the contents of the Email Display to the message passed
     * @param message
     */
    public void setMessage(Message message){
	if(message != null){
	    textArea.setText(parseMessageContents(message, true));
	}else{
	    textArea.setText("");
	}
	
    }
    
    /**
     * Returns the text contents of the message and opens attached images if openImages set to true
     * 
     * @param message
     *            The message to parse
     */
    public String parseMessageContents(Message message, boolean openImages) {

	String messageContents = "";
	
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
		    } else if(bodyPart.getContentType().contains("APPLICATION/OCTET-STREAM") && bodyPart.getContentType().contains(".jpg")){
			
			if(openImages){
			//If it's an image then the content will be of type BASE64DecoderStream
			BASE64DecoderStream decoder = (BASE64DecoderStream)bodyPart.getContent();
			
			//Which can be read by ImageIO
			BufferedImage image = ImageIO.read(decoder);
			
			//Opens a frame with that image
			ImageFrame imageFrame = new ImageFrame(image);
			imageFrame.setVisible(true);
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
	
	return messageContents;
    }

}
