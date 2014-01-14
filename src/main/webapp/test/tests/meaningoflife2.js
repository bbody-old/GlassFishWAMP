asyncTest( "RPC: Extra args meaningOfLife test", function() {
	var wsuri = "ws://localhost:8080/GlassFishWAMP/wamp";
	var molURI = "http://example.com/calc#meaningOfLife";

	ab.connect(wsuri,

	  // WAMP session was established
	  function (session) {
         ok( true, "Successfully started" );
	     // asynchronous RPC, returns promise object
	     session.call(molURI, "nonsense").then(

	        // RPC success callback
	        function (res) {
               console.log(res);
               equal(res, null);
               start();
           },

	        // RPC error callback
	        function (error, desc) {
               ok( true, "Error!" );
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