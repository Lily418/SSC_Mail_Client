import java.awt.FlowLayout;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class AttachmentManager extends JPanel {
    
    private DefaultComboBoxModel<File> attachmentList;
    
    public AttachmentManager(){
	FlowLayout layout = new FlowLayout();
	this.setLayout(layout);
	
	attachmentList = new DefaultComboBoxModel<File>();
	JComboBox<File>attachments = new JComboBox<File>(attachmentList);
	
	
	JButton deleteButton = new JButton("Delete Selected");
	JButton addAttachment = new JButton("Add Attachment");
	
	addAttachment.addActionListener(new AddAttachmentActionListener(this, attachmentList));
	
	add(attachments);
	add(deleteButton);
	add(addAttachment);
	
    }
    
    public String[] getAttachments(){
	String[] attachments = new String[attachmentList.getSize()];
	for(int i = 0; i < attachmentList.getSize(); i++){
	    attachments[i] = attachmentList.getElementAt(i).toString();
	}
	return attachments;
    }
}
