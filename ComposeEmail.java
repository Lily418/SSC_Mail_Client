import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ComposeEmail extends JFrame {

    private JTextField toField;
    private JTextField ccField;
    private JTextField subjectField;
    private JTextArea emailArea;
    
    public ComposeEmail(){
	setVisible(true);
	
	FlowLayout layout = new FlowLayout();
	this.setLayout(layout);

	JLabel toLabel = new JLabel("To:");
	JLabel ccLabel = new JLabel("cc:");
	JLabel subjectLabel = new JLabel("Subject:");
	toField = new JTextField(45);
	ccField = new JTextField(45);
	subjectField = new JTextField(45);
	emailArea = new JTextArea();
	emailArea.setPreferredSize(new Dimension(500, 300));
	JButton sendButton = new JButton();
	sendButton.setText("Send");
	
	this.setPreferredSize(new Dimension(550, 500));
	
	this.add(toLabel);
	this.add(toField);
	this.add(ccLabel);
	this.add(ccField);
	this.add(subjectLabel);
	this.add(subjectField);
	this.add(emailArea);
	this.add(sendButton);
	
	sendButton.addActionListener(new SendEmail(this));
	
	this.pack();
    }
    
    public String getTo(){
	return toField.getText();
    }
    
    public String getSubject(){
	return subjectField.getText();
    }
    
    public String getEmailContents(){
	return emailArea.getText();
    }
    
    public String[] getCC(){
	return ccField.getText().split(",");
    }
    
    public void clearInput(){
	toField.setText("");
	ccField.setText("");
	subjectField.setText("");
	emailArea.setText("");
    }
   
    
}
