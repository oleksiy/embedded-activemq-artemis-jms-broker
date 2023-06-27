// Establish a connection to the JMS broker
var connection = new ActiveMQConnectionFactory().createConnection();
connection.start();

// Create a session
var session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

// Specify the JMS topic you want to subscribe to
var topic = session.createTopic("my-queue-1");

// Create a consumer to receive messages from the topic
var consumer = session.createConsumer(topic);

// Set up a message listener
consumer.setMessageListener(function (message) {
    // Handle received messages
    var text = message.getText();
    console.log("Received message: " + text);
});

// Clean up resources when finished
window.onbeforeunload = function () {
    connection.close();
};

$(document).ready(function () {
    $("#send").click(function () {
        /*
        var text = $("#message").val();
        var message = session.createTextMessage(text);
        var producer = session.createProducer(topic);
        producer.send(message);
        console.log("Sent message: " + text);

         */
        $("#receiver").append(consumer.message.getText());
    });
});