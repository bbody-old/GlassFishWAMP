/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.wampee7.controllers.examples;

import org.codehaus.jackson.JsonNode;
import ws.wampee7.annotations.URIPrefix;
import ws.wampee7.annotations.onRPC;
import ws.wampee7.controllers.WAMPee7Contoller;

@URIPrefix("http://example.com/calc#")
public class TestRPCController extends WAMPee7Contoller {

        @onRPC("meaningOfLife")
        public static String getMeaningOfLife(String sessionID) {
                return "Meaning of life is: 42";
        }

        @onRPC("capital")
        public static String add(String sessionID, JsonNode[] args) {
                if (!args[0].isTextual() || args[0].isNumber()) {
                        throw new IllegalArgumentException("Argument is not a word!");
                }
                String ans = args[0].getTextValue().toUpperCase();
                return ans;
        }
}