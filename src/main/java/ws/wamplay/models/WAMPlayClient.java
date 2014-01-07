package ws.wamplay.models;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.websocket.Session;
import org.codehaus.jackson.JsonNode;


public class WAMPlayClient {
	//static ALogger log = Logger.of(WAMPlayClient.class);
	final Set<String> topics;
	final Map<String, String> prefixes;
	final String ID;
	final Session out;
	JsonNode lastSent;

	public WAMPlayClient(Session out) {
		this.out = out;
		topics = new HashSet<String>();
		prefixes = new HashMap<String, String>();
		ID = UUID.randomUUID().toString();
	}

	public void send(JsonNode response) {
		// Just for testing.
		if (out == null) {
			lastSent = response;
			return;
		}

		try {
                    //System.out.println(response.toString());
			out.getBasicRemote().sendText(response.toString());
		} catch (Exception e) {
			//log.error("Cannot send, client dead!");
                    System.out.println("Cannot send, client dead!");
		}
	}
        
        public void send(String response) {

		try {
                    //System.out.println(new Gson().toJson(response).toString());
			out.getBasicRemote().sendText(response);
		} catch (Exception e) {
			//log.error("Cannot send, client dead!");
                    System.out.println("Cannot send, client dead!");
		}
	}

	public void setPrefix(String prefix, String URI) {
		prefixes.put(prefix, URI);
	}

	private String convertCURIEToURI(String curie) {
		// TODO
		// if (prefixes.containsKey(prefix)){
		// return prefixes.get(prefix);
		// }
		return curie;
	}

	public void subscribe(String topic) {
		topics.add(convertCURIEToURI(topic));
	}

	public boolean isSubscribed(String topic) {
		return topics.contains(convertCURIEToURI(topic));
	}

	public void unsubscribe(String topic) {
		topics.remove(convertCURIEToURI(topic));
	}

	public String getSessionID() {
		return this.ID;
	}

	public void kill() throws IOException {
		if (out != null) {
			out.close();
		}
	}

	public JsonNode lastMessage() {
		return lastSent;
	}
}
