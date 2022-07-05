package py.edu.ucom.is2.proyectocamel.routes.choice;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class ChoiceRestTest extends RouteBuilder {

	
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

		rest().path("/api")
			.consumes("application/json")
			.produces("application/json")
		
		.post("/mensaje")
			.to("direct:procesarChoice");
		
		
		//Route que va a procesar el post enviar
		from("direct:procesarChoice")
			 .choice()
		        .when(header("origen").contains("IS1")).to("log:is1Logger").endChoice()
		        .when(header("origen").contains("IS2")).to("log:is2sLogger").endChoice()
		        .otherwise()
		            .transform().constant("El valor enviado no ex valido");
	
		
	}

}

