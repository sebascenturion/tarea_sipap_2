package py.edu.ucom.is2.mqconsumer.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQTopicConsumer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:queue:laboratorio")
		.transform().simple("Hemos leido> ${body}")
		.to("log:is2log");
		
	}

}

//
//from("timer:simple?period=1000")
//.process()
//    .message(m -> m.setHeader("index", index++ % 3))
//.transform()
//    .message(this::randomBody)
//.choice()
//    .when()
//        .body(String.class::isInstance)
//        .log("Got a String body")
//    .when()
//        .body(Integer.class::isInstance)
//        .log("Got an Integer body")
//    .when()
//        .body(Double.class::isInstance)
//        .log("Got a Double body")
//    .otherwise()
//        .log("Other type message");
