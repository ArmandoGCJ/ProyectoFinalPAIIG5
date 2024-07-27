package ec.edu.uce.Persistencia.Orlando.service;

import ec.edu.uce.Persistencia.Orlando.model.Etapa;
import ec.edu.uce.Persistencia.Orlando.repository.EtapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapaService {
    private final EtapaRepository etapaRepository;

    @Autowired
    public EtapaService(EtapaRepository etapaRepository) {
        this.etapaRepository = etapaRepository;
    }

    public Etapa guardarEtapa(Etapa etapa) {
        return etapaRepository.save(etapa);
    }

/*    public List<Etapa> obtenerTodasLasEtapas() {
        return etapaRepository.findAll();
    }*/
}
