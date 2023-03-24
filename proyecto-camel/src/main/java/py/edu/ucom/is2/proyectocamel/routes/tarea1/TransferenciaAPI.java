package py.edu.ucom.is2.proyectocamel.routes.tarea1;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;

import py.edu.ucom.is2.proyectocamel.routes.tarea2.FiltroTransferencia;

//@Component
public class TransferenciaAPI extends RouteBuilder {

	@Autowired
	TransferenciaService service;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

		rest().path("/api").consumes("application/json").produces("application/json")

				.post("/transferencia").type(TransferRequestType.class).outType(TransferResponseType.class)
				.to("direct:procesarTransferencia");
		// .to("log:loggerNOOK");

		// Route que va a procesar el post enviar
		from("direct:procesarTransferencia").bean(service).filter().method(FiltroTransferencia.class, "validar")
				// camino feliz
				// .choice
				// encolar
				// bean retorno
				.stop()
				// camino de infeliz
				.to("log:loggerNOOK");

//		    .log("${headers}")
//			.setExchangePattern(ExchangePattern.InOnly) //Importante
//		    .to("activemq:test")
//		    .setExchangePattern(ExchangePattern.InOut) //Importante
//		    .transform().simple("Mensaje encolado exitosamente");
		// .bean(procesarPeticion); esto funcionaba pero en el productor no se debe
		// procesar
	}

}
