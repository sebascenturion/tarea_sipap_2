package test;

public class CompradorCompulsivo implements IObserver {
    @Override
    public void notify(String message) {
        processMessage(message);
    }
    private void processMessage(String message) {
        System.out.println("Voy a endeudarme para comprar "+message+" ahora mismo!");
        // A complex psychologic response to a sale by a shopaholic
    }
}
