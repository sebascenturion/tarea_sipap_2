package py.edu.ucom.is2.proyectocamel.routes.tarea1;

import java.io.Serializable;

public class TransferResponseType implements Serializable{
	

	String mensaje;

	public TransferResponseType(String msg) {
		mensaje = msg;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
