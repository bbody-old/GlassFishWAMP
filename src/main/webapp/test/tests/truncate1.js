asyncTest( "PubSub: Vanilla subscribe and publish", function() {
	var wsuri = "ws://localhost:8080/GlassFishWAMP/wamp";
	var truncate_topic = "http://example.com/truncate";

	expect(2);
	ab.connect(wsuri,

	  // WAMP session was established
	  function (session) {
         ok( true, "Successfully started" );
	     session.subscribe(truncate_topic, function onEvent(topic, event) {
		      equal(event, "Hello");

		    });

	    	session.publish(truncate_topic, "Hello");


	  },

	  // WAMP session is gone
	  function (code, reason) {
	     console.log(code);
	     console.log(reason);
	  },
	  {skipSubprotocolCheck:true, skipSubprotocolAnnounce:true} // Important! Play rejects all subprotocols...
	);

	start();
});