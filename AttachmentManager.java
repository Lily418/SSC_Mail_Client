import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	final JComboBox<File>attachments = new JComboBox<File>(attachmentList);
	
	Dimension attachmentsSize = attachments.getSize();
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
    
    public String[] getAttachments(){
	String[] attachments = new String[attachmentList.getSize()];
	for(int i = 0; i < attachmentList.getSize(); i++){
	    attachments[i] = attachmentList.getElementAt(i).toString();
	}
	return attachments;
    }
}
