package ec.edu.uce.FabricaOrlando.interfaces;

public interface Observable {

    void notificarTodos();
    void agregarObserver(Observer observer);
}
