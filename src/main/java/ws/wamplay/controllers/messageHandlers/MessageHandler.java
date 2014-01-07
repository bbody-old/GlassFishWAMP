package ws.wamplay.controllers.messageHandlers;



import java.util.List;
import org.codehaus.jackson.JsonNode;
import ws.wamplay.models.WAMPlayClient;


public interface MessageHandler {
	public void process(WAMPlayClient client, JsonNode message);
        
        
}
