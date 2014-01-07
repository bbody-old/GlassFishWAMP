/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.wamplay.controllers;

import org.codehaus.jackson.JsonNode;
import ws.wamplay.annotations.URIPrefix;
import ws.wamplay.annotations.onRPC;

@URIPrefix("http://example.com/simple/calc#")
public class TestRPCController extends WAMPlayContoller {

        @onRPC("meaningOfLife")
        public static String getMeaningOfLife(String sessionID) {
                return "Meaning of life is: 42";
        }
        
        @onRPC("add")
        public static String add(String sessionID, JsonNode[] args) {
            if ((args[0].isNumber()) && (args[1].isNumber())){
                return new Integer(args[0].getIntValue()+ args[1].getIntValue()).toString();
            } else {
                throw new IllegalArgumentException("Needs two numbers!");
                //return "0";
            }
        }
}