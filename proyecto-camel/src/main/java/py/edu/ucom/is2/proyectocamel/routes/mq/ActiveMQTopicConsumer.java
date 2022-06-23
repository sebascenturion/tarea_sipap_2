package py.edu.ucom.is2.proyectocamel.routes.mq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQTopicConsumer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:topic:whatsappMQ")
		.to("log:is2log");
		
	}

}