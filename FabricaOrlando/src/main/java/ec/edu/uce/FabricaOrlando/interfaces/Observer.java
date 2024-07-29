package ec.edu.uce.FabricaOrlando.interfaces;

@FunctionalInterface
public interface Observer {
    void notificar(String estado, int progreso);
}
