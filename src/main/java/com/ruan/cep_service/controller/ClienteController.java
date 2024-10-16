package com.ruan.cep_service.controller;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.domain.cliente.ClienteDTO;
import com.ruan.cep_service.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        return clienteService.findAllClientes();
    }

    @GetMapping("/{cpfcpnj}")
    public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable String cpfcpnj) {
        Optional<ClienteDTO> cliente = clienteService.buscarClientePorId(cpfcpnj);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transient
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody Cliente cliente) {
        ClienteDTO savedCliente = clienteService.salvarCliente(cliente);
        return ResponseEntity.ok(savedCliente);
    }

    @DeleteMapping("/{cpfcnpj}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpfcnpj) {
        if (clienteService.buscarClientePorId(cpfcnpj).isPresent()) {
            clienteService.deletarClientePorCpfCnpj(cpfcnpj);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletarCliente(@PathVariable String id) {
//        if (clienteService.buscarClientePorId(id).isPresent()) {
//            clienteService.deletarCliente(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
