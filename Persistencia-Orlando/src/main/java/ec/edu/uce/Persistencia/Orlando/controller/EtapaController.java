package ec.edu.uce.Persistencia.Orlando.controller;

import ec.edu.uce.Persistencia.Orlando.model.Cliente;
import ec.edu.uce.Persistencia.Orlando.model.Etapa;
import ec.edu.uce.Persistencia.Orlando.model.Producto;
import ec.edu.uce.Persistencia.Orlando.service.EtapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etapas")
public class EtapaController {

    @Autowired
    private EtapaService etapaService;


    @GetMapping("/all")
    public ResponseEntity<List<Etapa>> obtenerTodasEtapas() {
        List<Etapa> productos = etapaService.obtenerTodasLasEtapas();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Etapa>> obtenerEtapasPorProductoId(@PathVariable Long productoId) {
        List<Etapa> etapas = etapaService.obtenerEtapasPorProductoId(productoId);
        return ResponseEntity.ok(etapas);
    }

}
