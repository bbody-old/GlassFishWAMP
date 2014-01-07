/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.wamplay.controllers.examples;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import ws.wamplay.annotations.URIPrefix;
import ws.wamplay.annotations.onPublish;
import ws.wamplay.annotations.onSubscribe;
import ws.wamplay.controllers.WAMPlayContoller;
import ws.wamplay.controllers.WAMPlayContoller;

// Prefix is optional, but helps remove duplicate code.
@URIPrefix("http://example.com/")
public class TestPubSubController extends WAMPlayContoller {
        static int MAX_MESSAGE_LENGTH = 10;
        
        /**
         * Method that truncates an event message before it's published. 
         * @param client WAMP client that sent the event
         * @param event Event to be truncated
         * @return Modified json event, null to halt publish
         */
        @onPublish("truncate")
        public static JsonNode truncatePublish(String sessionID, JsonNode event) {
                if (!event.isTextual()) {
                        return cancel();
                }                
                String message = event.getTextValue();
                if (message.length() > MAX_MESSAGE_LENGTH) {
                        message = message.substring(0, MAX_MESSAGE_LENGTH);
                }
                
                ObjectMapper mapper = new ObjectMapper();
                
                JsonNode rootNode = null;
            try {
                rootNode = mapper.readTree("{\"value\" : \"" + message + "\"}");

                
                //rootNode = mapper.readTree(message);
            } catch (IOException ex) {
                Logger.getLogger(TestPubSubController.class.getName()).log(Level.SEVERE, null, ex);
            }

                                /**
                                 * * read value from key "name" **
                                 */
                                //return rootNode.path("value");
            System.out.println(rootNode.toString());
                return rootNode.path("value");
        }
        
        /**
         * Only one onPublish or onSubscribe annotation is necessary to create a topic.
         * @param subscribingClient
         * @return True if client is allowed to subscribe, false otherwise.
         */
        @onSubscribe("truncate")
        public static boolean capitalSubscribe(String sessionID) {
                return true;
        }
}