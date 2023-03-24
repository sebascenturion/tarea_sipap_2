package py.edu.ucom.is2.proyectocamel.routes.tarea2;

import java.io.Serializable;

public class TransferResponseType implements Serializable {

	String mensaje;
	Integer idTransaccion;
	

	public TransferResponseType(String mensaje, Integer idTransaccion) {
		super();
		this.mensaje = mensaje;
		this.idTransaccion = idTransaccion;
	}


	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

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
