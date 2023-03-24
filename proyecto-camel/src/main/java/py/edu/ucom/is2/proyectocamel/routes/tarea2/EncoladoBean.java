package py.edu.ucom.is2.proyectocamel.routes.tarea2;

import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.tarea1.TransferRequestType;

@Component
public class EncoladoBean {
	public TransferResponseType validar(TransferRequestType request) {
		TransferResponseType ret = new TransferResponseType("Mensaje encolado",request.getId_transaccion());
		return ret;
		
	}

}