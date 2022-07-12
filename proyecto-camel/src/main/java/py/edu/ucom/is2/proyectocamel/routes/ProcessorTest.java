package py.edu.ucom.is2.proyectocamel.routes;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class ProcessorTest extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		from("timer:mytimer?period=1000")
		.process(new Procesador())
		.transform().constant("mensaje original")
		.process(new Procesador())
		//.transform().constant("mensaje original")
		
		.to("log:mylogger");
	}
}

class Procesador implements Processor {
	Logger logger = LoggerFactory.getLogger(ProcessorTest.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		String body  = exchange.getIn().getBody(String.class);
		logger.info(exchange.toString());
		exchange.getIn().setBody("mensaje modificado mediante procesador");
	}
}