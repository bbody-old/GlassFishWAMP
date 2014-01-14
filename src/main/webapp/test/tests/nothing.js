asyncTest( "RPC: Non existant method", function() {
	var wsuri = "ws://localhost:8080/GlassFishWAMP/wamp";
	var molURI = "http://example.com/calc#nothing";

	ab.connect(wsuri,

	  // WAMP session was established
	  function (session) {
         ok( true, "Successfully started" );
	     // asynchronous RPC, returns promise object
	     session.call(molURI).then(

	        // RPC success callback
	        function (res) {
               console.log("Args: ", res);
               //equal(res, "wrong number of arguments");
               
               //start();
               ok(false, "not exepected...");
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