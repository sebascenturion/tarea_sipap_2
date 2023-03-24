package py.edu.ucom.is2.proyectocamel.routes.tarea2;

import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.tarea1.TransferRequestType;

@Component
public class FiltroTransferencia {
	public static boolean validar(TransferRequestType request) {
		if (request.getMonto()<20000000) {
			return true;
		}else {
			return false;
		}
	}

}