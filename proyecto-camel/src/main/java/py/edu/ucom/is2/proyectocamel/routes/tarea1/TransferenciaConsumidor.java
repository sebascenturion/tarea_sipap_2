package py.edu.ucom.is2.proyectocamel.routes.tarea1;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaConsumidor extends RouteBuilder{
	@Autowired
	ProcesarTransferencia procesarPeticion;
	
	@Override
	public void configure() throws Exception {
		from("activemq:test")
		.log("Mensaje recibido ${body}")
		.unmarshal(new JacksonDataFormat(TransferRequestType.class))
		.to("log:is2log")
		.bean(procesarPeticion)
		.to("log:is2log");
	}
}
