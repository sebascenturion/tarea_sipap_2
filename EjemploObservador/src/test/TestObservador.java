package test;

public class TestObservador {
	public static void main(String[] args) {
		// Initialization
		Store tienda = new Store();
		IObserver cliente1 = new ClientePasivo();
		IObserver cliente2 = new CompradorCompulsivo();
		IObserver cliente3 = new CompradorCompulsivo();

		// Adding two customers to the newsletter
		tienda.addSubscriber(cliente1);
		tienda.addSubscriber(cliente2);

		// Notifying customers (observers)
		tienda.notifySubscribers("Zapato X a solo 120.000gs");

		// A customer has decided not to continue following the newsletter
		tienda.removeSubscriber(cliente1);

		// customer2 told customer3 that a sale is going on
		tienda.addSubscriber(cliente3);

		// Notifying the updated list of customers
		tienda.notifySubscribers("Perfume Y 50% desc");
	}
}
