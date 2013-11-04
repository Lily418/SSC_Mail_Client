import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * A panel for adding attachments to an e-mail
 * @author joel
 *
 */
public class AttachmentManager extends JPanel {
    
    private DefaultComboBoxModel<File> attachmentList;
    
    public AttachmentManager(){
	FlowLayout layout = new FlowLayout();
	this.setLayout(layout);
	
	//Adding elements to the attachmentList modifies the contents of the JComboBox
	attachmentList = new DefaultComboBoxModel<File>();
	final JComboBox<File>attachments = new JComboBox<File>(attachmentList);
	
	attachments.setPreferredSize(new Dimension(200, 25));
	
	JButton deleteButton = new JButton("Delete Selected");
	JButton addAttachment = new JButton("Add Attachment");
	
	addAttachment.addActionListener(new AddAttachmentActionListener(this, attachmentList));
	
	deleteButton.addActionListener(new ActionListener() {  
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		attachmentList.removeElementAt(attachments.getSelectedIndex());
	    }
	});
	
	add(attachments);
	add(deleteButton);
	add(addAttachment);
	
    }
    
    /**
     * Gets attachments to add to the email
     * @return An array of file path String representations
     */
    public String[] getAttachments(){
	String[] attachments = new String[attachmentList.getSize()];
	for(int i = 0; i < attachmentList.getSize(); i++){
	    attachments[i] = attachmentList.getElementAt(i).toString();
	}
	return attachments;
    }
    
    public void clearAttachments(){
	attachmentList.removeAllElements();
    }
}
