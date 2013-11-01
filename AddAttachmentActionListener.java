import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;


public class AddAttachmentActionListener implements ActionListener {

    private Component parent;
    private DefaultComboBoxModel<File> attachmentList;
    
    public AddAttachmentActionListener(Component parent, DefaultComboBoxModel<File> attachmentList){
	this.parent = parent;
	this.attachmentList = attachmentList;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	JFileChooser fc = new JFileChooser();
	int returnVal = fc.showOpenDialog(parent);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    attachmentList.addElement(fc.getSelectedFile());
	}
    }
    
}
