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
                System.out.println("Let's rock");
		List<Object> welcome = new Welcome(client.getSessionID()).toList();
                ObjectMapper mapper = new ObjectMapper();
                System.out.println("LA:" + mapper.valueToTree(welcome).toString());
		client.send(printList(welcome));
	}
        
        private String printList(List<Object> ls){
            String s = "[";
            s += ls.get(0).toString() + ", ";
            s += "\"" + ls.get(1).toString() + "\", ";
            s += ls.get(2).toString() + ", ";
            s += "\"" + ls.get(3).toString() + "\"]";
            return s;
        }
        
        
}