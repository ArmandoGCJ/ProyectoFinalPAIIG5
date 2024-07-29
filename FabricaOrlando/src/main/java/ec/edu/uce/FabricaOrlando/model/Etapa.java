package ec.edu.uce.FabricaOrlando.model;

import java.util.concurrent.TimeUnit;

public class Etapa extends SubjectClass {

    private Long id;
    private String descripcion;
    private int duracion;

    public Etapa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }


    public void ejecutarEtapa() throws InterruptedException {
        for (int i = 0; i <= 100; i += 10) {
            TimeUnit.SECONDS.sleep(duracion / 10); // Simula el tiempo de procesamiento
            notificarObservers(descripcion, i); // Notifica el progreso a los observadores
        }
    }
}
