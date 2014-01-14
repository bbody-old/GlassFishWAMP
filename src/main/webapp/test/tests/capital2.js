asyncTest( "RPC: No capital test", function() {
	var wsuri = "ws://localhost:8080/GlassFishWAMP/wamp";
	var molURI = "http://example.com/calc#capital";

	ab.connect(wsuri,

	  // WAMP session was established
	  function (session) {
         ok( true, "Successfully started" );
	     // asynchronous RPC, returns promise object
	     session.call(molURI).then(

	        // RPC success callback
	        function (res) {
               //console.log(res);
           },

	        // RPC error callback
	        function (error, desc) {
               ok( true, "Did not start!" );
               start();
	        }
	     );
	  },

	  // WAMP session is gone
	  function (code, reason) {
	     console.log(code);
	     console.log(reason);
	  },
	  {skipSubprotocolCheck:true, skipSubprotocolAnnounce:true} // Important! Play rejects all subprotocols...
	);
	
});