package com.ruan.cep_service.service;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

//    public List<ClienteDTO> listarTodos() {
//        return clienteRepository.findAll().stream().map(ClienteDTO::new).toList();
//    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarClientePorId(String cpfcnpj) {
        return clienteRepository.findByCpfcnpj(cpfcnpj);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }


    public void atualizarCliente(Long id, Cliente clienteAtualizado) {
    }
}
