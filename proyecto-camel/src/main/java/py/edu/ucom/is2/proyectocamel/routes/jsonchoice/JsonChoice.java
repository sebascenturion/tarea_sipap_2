package py.edu.ucom.is2.proyectocamel.routes.jsonchoice;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.tarea1.TransferRequestType;
import py.edu.ucom.is2.proyectocamel.routes.tarea2.FiltroTransferencia;

//@Component
public class JsonChoice extends RouteBuilder {

	
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		
		rest().path("/api")
			.consumes("application/json")
			.produces("application/json")
		
		.post("/transferencia2")
			.type(TransferRequestType.class) 
			.to("direct:procesarTransferencia2");
		
		//Route que va a procesar el post enviar
		from("direct:procesarTransferencia2")
			.filter().method(FiltroTransferencia.class)
				.log("Mensaje con monto valido")
				.log("${body}")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal().json(JsonLibrary.Jackson)
				 .choice()
				    .when().jsonpath("$.[?(@.banco_destino == 'ITAU')]").to("log:MORALES-ITAU-INLOG").endChoice()
				    .otherwise()
				    .to("log:OTROSBANCOS").end()
		        .setExchangePattern(ExchangePattern.InOut)
		        .log("${body}")
		        .unmarshal(new JacksonDataFormat(TransferRequestType.class))
				.stop().end()
			.to("log:loggerNOOK");
		
	}

}




