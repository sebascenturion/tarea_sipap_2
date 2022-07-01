package py.edu.ucom.is2.proyectocamel.routes.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;

import py.edu.ucom.is2.proyectocamel.routes.rest.bean.AlumnoService;
import py.edu.ucom.is2.proyectocamel.routes.rest.tipos.AlumnoRequest;
import py.edu.ucom.is2.proyectocamel.routes.rest.tipos.AlumnoResponse;

//@Component
public class JsonRestTest extends RouteBuilder {

	@Autowired
	AlumnoService service;
	
	
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

		rest().path("/api")
			.consumes("application/json")
			.produces("application/json")
		
		.post("/alumnos")
			.type(AlumnoRequest.class) 
	        .outType(AlumnoResponse.class) 
			.to("direct:procesarAltaAlumno");
		
		
//		//Route que va a procesar el post enviar
		from("direct:procesarAltaAlumno")
		.bean(service);
	
	}

}



//.setExchangePattern(ExchangePattern.InOnly) //Importante
//.to("activemq:is2")
//.setExchangePattern(ExchangePattern.InOut) //Importante
//.bean(procesarPeticion);
