package ec.edu.uce.FabricaOrlando.model;

import ec.edu.uce.FabricaOrlando.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class SubjectClass {
    private List<Observer> observers = new ArrayList<>();

    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notificarObservers(String estado, int progreso) {
        for (Observer observer : observers) {
            observer.notificar(estado, progreso);
        }
    }
}