import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import com.sun.mail.util.BASE64DecoderStream;


public class OpenImageWorker extends SwingWorker<Void, Void> {

    private Object content;
    
    public OpenImageWorker(Object content){
	this.content = content;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
	//If it's an image then the content will be of type BASE64DecoderStream
	BASE64DecoderStream decoder = (BASE64DecoderStream)content;
	
	//Which can be read by ImageIO
	BufferedImage image = ImageIO.read(decoder);
	
	//Opens a frame with that image
	ImageFrame imageFrame = new ImageFrame(image);
	imageFrame.setVisible(true);
	return null;
    }

}
