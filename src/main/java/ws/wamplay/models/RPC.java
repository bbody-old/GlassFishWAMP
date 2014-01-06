package ws.wamplay.models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;


import ws.wamplay.annotations.onRPC;
import ws.wamplay.callbacks.RPCCallback;
import ws.wamplay.controllers.WAMPlayContoller;


public class RPC {

	//static ALogger log = Logger.of(RPC.class.getSimpleName());
	static ConcurrentMap<String, RPCCallback> procURIs = new ConcurrentHashMap<String, RPCCallback>();

	public static void addController(String prefix, final WAMPlayContoller controller) {
		for (final Method method : controller.getClass().getMethods()) {
			if (method.isAnnotationPresent(onRPC.class)) {
				String procURI = prefix
						+ method.getAnnotation(onRPC.class).value();
				procURIs.put(procURI, new RPCCallback() {

					@Override
					public JsonNode call(String sessionID, JsonNode... args) throws Throwable {
						try {
                                                    ObjectMapper mapper = new ObjectMapper();
							if (args.length == 0) {
								//log.debug("No RPC arguments!");
                                                            System.out.println("No RPC arguments!");
                                                            
								return mapper.valueToTree(method.invoke(controller, sessionID));
							}
							return mapper.valueToTree(method.invoke(controller, sessionID, args));
						} catch (InvocationTargetException e) {
							throw e.getCause();
						}
					}
				});
			}
		}

	}

	public static void addCallback(String procURI, RPCCallback cb) {
		procURIs.put(procURI, cb);
	}

	public static RPCCallback getCallback(String procURI) {
		return procURIs.get(procURI);
	}

	public static void reset() {
		procURIs.clear();
	}

}
