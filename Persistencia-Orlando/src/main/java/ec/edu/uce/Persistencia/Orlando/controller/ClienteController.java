package ec.edu.uce.Persistencia.Orlando.controller;

import ec.edu.uce.Persistencia.Orlando.model.Cliente;
import ec.edu.uce.Persistencia.Orlando.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<Cliente> iniciarSesion(@RequestBody LoginRequest loginRequest) {
        Cliente cliente = clienteService.autenticarCliente(loginRequest.getNombre(), loginRequest.getContrasena());
        return ResponseEntity.ok(cliente);
    }
}