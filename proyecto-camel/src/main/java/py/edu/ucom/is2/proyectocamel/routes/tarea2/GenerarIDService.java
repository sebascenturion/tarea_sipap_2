package py.edu.ucom.is2.proyectocamel.routes.tarea2;

import java.util.Random;

import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.tarea1.TransferRequestType;

@Component
public class GenerarIDService {
	public TransferRequestType validar(TransferRequestType request) {
		Random random = new Random();
		request.setId_transaccion(random.nextInt(1000));
		return request;

	}

}