package py.edu.ucom.is2.proyectocamel.routes.filter;

class Filtro5 {
	public boolean divisible(String valor) {
		Integer num = Integer.parseInt(valor);
		boolean esDivisible = num%5==0?true:false;
		return esDivisible;
	}

}