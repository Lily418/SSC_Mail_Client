import java.util.List;


public class ParsedMessage {
    private String text;
    private List<OpenImageWorker> imageWorkers;
    
    public ParsedMessage(String text, List<OpenImageWorker> imageWorkers){
	this.text = text;
	this.imageWorkers = imageWorkers;
    }
    
    public String getText() {
	return text;
    }
    
    public List<OpenImageWorker> getImageWorkers() {
	return imageWorkers;
    }
}
