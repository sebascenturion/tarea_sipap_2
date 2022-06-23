package test;

public interface IObserver {
	public String nombre = "";
    public void notify(String message);
}