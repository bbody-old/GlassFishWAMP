asyncTest("PubSub: Subscribe then unsubscribe and publish", function() {
    var wsuri = "ws://localhost:8080/GlassFishWAMP/wamp";
    var truncate_topic = "http://example.com/truncate";

    expect(4);

    ab.connect(wsuri,
            // WAMP session was established
                    function(session) {
                        var first1 = true;
                        var first2 = true;
                        function topicHandler1(topic, event){
                            console.log("Hello");
                            if (first1){
                                console.log("First message: 1");
                                ok(true, "First message: 1");
                                first1 = false;
                            } else {
                                console.log("Unexpected message received");
                                ok(false, "Unexpected message received");
                                //equal("Hello", event);
                                start();
                            }
                        };
                        ok(true, "Successfully started");
                        session.subscribe(truncate_topic, topicHandler1);
                        session.subscribe(truncate_topic, function(topic, event) {
                            
                            if (first2){
                                console.log("First message: 2");
                                ok(true, "First message: 2");
                                first2 = false;
                            } else {
                                console.log("Second message: 2");
                                ok(true, "Second message: 2");
                                start();
                            }
                        });
                        
                        
                        session.publish(truncate_topic, "Hello");
                        
                        
                        setTimeout(function (){session.unsubscribe(truncate_topic, topicHandler1);
                            setTimeout(function(){
                                session.publish(truncate_topic, "Hello");
                            },1000);
                        }, 1000);

                    },
                    // WAMP session is gone
                            function(code, reason) {
                                console.log(code);
                                console.log(reason);
                            },
                            {skipSubprotocolCheck: true, skipSubprotocolAnnounce: true} // Important! Play rejects all subprotocols...
                    );
                    //equal(messageQueue, 1);
                    
                    //ok( true, "Message received" );
                    //start();
                });