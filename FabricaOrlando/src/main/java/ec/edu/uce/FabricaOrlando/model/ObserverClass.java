package ec.edu.uce.FabricaOrlando.model;

import ec.edu.uce.FabricaOrlando.interfaces.Observer;

import javax.swing.*;

public class ObserverClass implements Observer {
    private final JProgressBar progressBar;
    private final JLabel statusLabel;

    public ObserverClass(JProgressBar progressBar, JLabel statusLabel) {
        this.progressBar = progressBar;
        this.statusLabel = statusLabel;
    }

    @Override
    public void notificar(String estado, int progreso) {
        SwingUtilities.invokeLater(() -> {
            if (progressBar.getValue() < progreso) { // Evitar retroceder el progreso
                progressBar.setValue(progreso);
            }
            statusLabel.setText("Estado: " + estado);
        });
    }
}