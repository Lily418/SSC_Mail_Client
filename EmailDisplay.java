import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class EmailDisplay extends JPanel {

    private JTextArea textArea;
    
    public EmailDisplay(){
	this.setBackground(new Color(0, 0, 255));
	
	textArea = new JTextArea();
	textArea.setPreferredSize(new Dimension(500, 600));
	this.add(textArea);
    }
    
    public void setMessage(Message message) throws MessagingException, IOException{
	if(message.getContentType().contains("TEXT/PLAIN")){
	    textArea.setText(message.getContent().toString());
	}
	else
	{
	    textArea.setText("");
	    
	    Multipart multipart = (Multipart) message.getContent();
	    for(int i = 0; i < multipart.getCount(); i++){
		BodyPart bodyPart = multipart.getBodyPart(i);
		if(bodyPart.getContentType().contains("TEXT/PLAIN")){
		    textArea.setText(textArea.getText() + bodyPart.getContent().toString());
		}
		else{
		    System.out.println("Unrecognised content type " + bodyPart.getContentType());
		}
	    }
	}
    }
    
}
