var messageQueue = [];
var times = 0;
var timeout = 3000;

asyncTest( "PubSub: Subscribe then unsubscribe and publish", function() {
	var wsuri = "ws://localhost:8080/GlassFishWAMP/wamp";
	var truncate_topic = "http://example.com/truncate";
	
	
	var binder = function onEvent(topic, event) {
	  	messageQueue.push(event);
	  	times = times + 1;
		console.log("H: " + messageQueue);
		ok( true, "Message received" );
		//equal("Hello", event);
		session.unsubscribe(truncate_topic);
	  	session.publish("Hello");
	};

	ab.connect(wsuri,

	  // WAMP session was established
	  function (session) {
	  	ok( true, "Successfully started" );
	  	/*
	  	function tt(times, session){
	  		console.log("Hello");
	  		setTimeout(function (times, session){
	  		session.subscribe(truncate_topic, binder);

	  		setTimeout(function (times, session){
	  			session.publish(truncate_topic, "Hello");
	  			setTimeout(function (times, session){
	  				session.unsubscribe(truncate_topic);
	  				setTimeout(function (times, session){
	  					session.publish(truncate_topic, "Hello");
	  				}(times,session),timeout);
	  			}(times,session),timeout);
	  		}(times,session), timeout);
	  	}(times,session), timeout);
	  	};
	  	tt(times, session);*/
	  	console.log("Subbing");
	  	session.subscribe(truncate_topic, function (topic, event){
		  	messageQueue.push(event);
		  	times = times + 1;
			
			ok( false, "Message received" );
			console.log("H: " + messageQueue);
			//equal("Hello", event);
			
			
		});

		//session.publish(truncate_topic, "Hello"); 
		
			session.unsubscribe(truncate_topic);
		
		/*s*/
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
	//equal(messageQueue, 1);
});