package py.edu.ucom.is2.proyectocamel.routes.pipelines;



import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.helper.Bean1;
import py.edu.ucom.is2.proyectocamel.helper.Bean2;

//@Component
public class PipeLineTest extends RouteBuilder {

	@Autowired
	Bean1 bean1;
	
	@Autowired
	Bean2 bean2;

	@Override
	public void configure() throws Exception {
		// timer (componente endpoint)
		// transformaciones
		// logger
		from("timer:mytimer?period=5000")
		.log("${body}")
		.pipeline()
			.to("bean:bean1")
	        .to("bean:bean2")
			.to("log:mylogger")
			.to("file:archivos/ouput/?fileName=salida.txt&amp;fileExist=Append");
	}
}



