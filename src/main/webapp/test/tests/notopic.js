asyncTest( "Pubsub: No topic", function() {
	var wsuri = "ws://localhost:8080/GlassFishWAMP/wamp";
	var truncate_topic = "http://example.com/hello";

	
	ab.connect(wsuri,

	  // WAMP session was established
	  function (session) {
         ok( true, "Successfully started" );
	     session.subscribe(truncate_topic, function (topic, event) {
      	ok( false, "Message has appeared" );
      }
);

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