import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A class to display an image in a JFrame
 * @author joel
 *
 */
public class ImageFrame extends JFrame{

    private BufferedImage image;
    
    public ImageFrame(BufferedImage image){
	JLabel picLabel = new JLabel(new ImageIcon(image));
	add(picLabel);
	this.pack();
    }
    
    
    
}
