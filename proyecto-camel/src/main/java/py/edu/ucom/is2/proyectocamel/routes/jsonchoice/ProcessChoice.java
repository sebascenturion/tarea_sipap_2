package py.edu.ucom.is2.proyectocamel.routes.jsonchoice;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.ProcessorTest;
import py.edu.ucom.is2.proyectocamel.routes.tarea1.TransferRequestType;

@Component
public class ProcessChoice extends RouteBuilder {

	@Override
	public void configure() throws Exception {		
		//Route que va a procesar el post enviar
		from("activemq:MORALES-ITAU-IN")
		.choice()
          .when().jsonpath("$.[?(@.banco_destino == 'ITAU')]").log("PROCESAMOS WHEN JSON").to("log:MORALES-ITAU-JSONPATH").endChoice()		
		.otherwise()
         	.log("entramos en opcion otros")
         	.end()
        .to("log:FIN");	
	}
}

class AgregarHeaderProcessor implements Processor {
	Logger logger = LoggerFactory.getLogger(ProcessorTest.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		TransferRequestType request = (TransferRequestType)exchange.getIn().getBody();
		
		Map<String, Object> headers  = exchange.getIn().getHeaders();
		headers.put("banco_origen", request.getBanco_origen());
		headers.put("header_cualquier_cosa", "hola  mundo");
		exchange.getIn().setHeaders(headers);
	}
}

//.unmarshal(new JacksonDataFormat(TransferRequestType.class))
//.process( new AgregarHeaderProcessor())
//Validar Filter si cumple logica de fecha
//Crear objeto tipo respuesta (TransferResponseType)
//.bean()//entra request sale response
//a partir de aca el mensaje sera del tipo RESPONSE class
//.choice()
//.when(header("banco_origen").contains("FAMILIAR"))
//    .log("entramos en opcion familiar")
//    .to("activemq:morales-familiar-OUT")
//.otherwise()
//	.log("entramos en opcion otros")
//.end()
