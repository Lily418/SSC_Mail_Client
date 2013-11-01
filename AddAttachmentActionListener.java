import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;

/**
 * Listens for when the 'Add' button is pressed and when it is opens a file chooser and adds the selected file to the attachments list
 * @author joel
 *
 */
public class AddAttachmentActionListener implements ActionListener {

    private Component parent;
    private DefaultComboBoxModel<File> attachmentList;
    
    /**
     * @param parent The parent of the opened FileChooser
     * @param attachmentList A DefaultComboBoxModel to add the selected file to
     */
    public AddAttachmentActionListener(Component parent, DefaultComboBoxModel<File> attachmentList){
	this.parent = parent;
	this.attachmentList = attachmentList;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	JFileChooser fc = new JFileChooser();
	int returnVal = fc.showOpenDialog(parent);
	
	//showOpenDialog will return FileChooser.APPROVE_OPTION if the user presses the 'ok' button. If they cancel it will return something else so there's nothing to do.
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    attachmentList.addElement(fc.getSelectedFile());
	}
    }
    
}
