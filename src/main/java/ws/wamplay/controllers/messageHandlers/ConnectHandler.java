package ws.wamplay.controllers.messageHandlers;

import java.util.List;
import javax.json.Json;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import ws.wamplay.models.WAMPlayClient;
import ws.wamplay.models.messages.Welcome;


public class ConnectHandler implements MessageHandler {
	@Override
	public void process(WAMPlayClient client, JsonNode message) {
		List<Object> welcome = new Welcome(client.getSessionID()).toList();
                ObjectMapper mapper = new ObjectMapper(); 
		client.send(mapper.valueToTree(welcome));
	}
}
