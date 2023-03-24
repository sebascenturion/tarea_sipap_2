package py.edu.ucom.is2.proyectocamel.routes.tarea1;

import java.io.Serializable;

public class TransferRequestType implements Serializable{
	String cuenta;
	Integer monto;
	String banco_destino;
	String banco_origen;
	Integer id_transaccion;
	
	
	@Override
	public String toString() {
		return "TranferRequestType [cuenta=" + cuenta + ", monto=" + monto + ", banco_destino=" + banco_destino
				+ ", banco_origen=" + banco_origen + "]";
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public Integer getMonto() {
		return monto;
	}
	public void setMonto(Integer monto) {
		this.monto = monto;
	}
	public String getBanco_destino() {
		return banco_destino;
	}
	public void setBanco_destino(String banco_destino) {
		this.banco_destino = banco_destino;
	}
	public String getBanco_origen() {
		return banco_origen;
	}
	public void setBanco_origen(String banco_origen) {
		this.banco_origen = banco_origen;
	}
	
	public Integer getId_transaccion() {
		return id_transaccion;
	}
	public void setId_transaccion(Integer id_transaccion) {
		this.id_transaccion = id_transaccion;
	}

}
