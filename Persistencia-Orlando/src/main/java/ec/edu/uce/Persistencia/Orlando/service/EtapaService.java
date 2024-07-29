package ec.edu.uce.Persistencia.Orlando.service;

import ec.edu.uce.Persistencia.Orlando.model.Etapa;
import ec.edu.uce.Persistencia.Orlando.repository.EtapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapaService {

    @Autowired
    private  EtapaRepository etapaRepository;

    public List<Etapa> obtenerTodasLasEtapas() {
        return etapaRepository.findAll();
    }

    public List<Etapa> obtenerEtapasPorProductoId(Long productoId) {
        return etapaRepository.findByProductoId(productoId);
    }
}
