package test;

public class ClientePasivo implements IObserver {

    @Override
    public void notify(String message) {
        System.out.println("Ok, ya me di por enterado de la promo "+message);
        // Passive customer does not react to the message too much
    }
}
