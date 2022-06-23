package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Store implements IObservable {
	private List<IObserver> clientes = new ArrayList<>();

	@Override
	public void addSubscriber(IObserver customer) {
		clientes.add(customer);
	}

	@Override
	public void removeSubscriber(IObserver customer) {
		clientes.remove(customer);
	}

	@Override
	public void notifySubscribers(String mensaje) {
		System.out.println("Se va a notificar a los suscriptores de la nueva promo");
		for (IObserver customer : clientes) {
			customer.notify(mensaje);
		}
	}
}
