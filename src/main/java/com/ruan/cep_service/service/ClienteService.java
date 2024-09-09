package com.ruan.cep_service.service;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.domain.cliente.ClienteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRespository clienteRespository;

    @Autowired
    public ClienteService(ClienteRespository clienteRespository) {
        this.clienteRespository = clienteRespository;
    }

    public List<Cliente> findAllClientes() {
        return clienteRespository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRespository.save(cliente);

    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRespository.findById(id);
    }

    public void deletarCliente(Long id) {
        clienteRespository.deleteById(id);
    }


}
