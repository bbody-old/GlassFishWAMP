package ws.wamplay.controllers.messageHandlers;

import org.codehaus.jackson.JsonNode;
import ws.wamplay.callbacks.PubSubCallback;
import ws.wamplay.controllers.WAMPlayServer;
import ws.wamplay.models.PubSub;
import ws.wamplay.models.WAMPlayClient;


public class SubscribeHandler implements MessageHandler {
	//static ALogger log = Logger.of(SubscribeHandler.class);

	@Override
	public void process(WAMPlayClient senderClient, JsonNode message) {
		String topic = message.get(1).getTextValue();

		PubSubCallback cb = PubSub.getPubSubCallback(topic);

		if (cb == null) {
			//log.error("Topic not found: " + topic);
                    String error = "Topic not found: " + topic;
                    System.out.println(error);
			return;
		}

		boolean sucessful = cb.runSubCallback(senderClient.getSessionID());

		if (!sucessful) {
			//log.debug("Callback for " + topic + " canceled.");
                        String error = "Callback for " + topic + " canceled.";
                    System.out.println(error);
			return;
		}

		if (WAMPlayServer.isTopic(topic)) {
			senderClient.subscribe(topic);
			return;
		}
		//log.error("Client tried to subscribe to nonexistant topic: " + topic);
                String error = "Client tried to subscribe to nonexistant topic: " + topic;
                    System.out.println(error);
	}

}
