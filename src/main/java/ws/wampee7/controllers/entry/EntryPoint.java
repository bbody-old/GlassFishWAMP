/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.wampee7.controllers.entry;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import ws.wampee7.controllers.WAMPee7Server;
import ws.wampee7.controllers.examples.TestPubSubController;
import ws.wampee7.controllers.examples.TestRPCController;

/**
 *
 * @author brendonbody
 */
@Startup
@Singleton
public class EntryPoint {
    public EntryPoint(){
        // Add WAMP controllers to WAMP
        WAMPee7Server.addController(new TestPubSubController());
        WAMPee7Server.addController(new TestRPCController());
    }
}
