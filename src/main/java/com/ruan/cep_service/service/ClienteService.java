package com.ruan.cep_service.service;

import com.ruan.cep_service.domain.cliente.Cliente;
import com.ruan.cep_service.domain.cliente.ClienteDTO;
import com.ruan.cep_service.domain.cliente.ClienteRepository;
import com.ruan.cep_service.domain.endereco.Endereco;
import com.ruan.cep_service.domain.endereco.EnderecoDTO;
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

    public List<ClienteDTO> findAllClientes() {
        // ClienteD cliente = clienteRepository.findAll();

        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(this::converterParaClienteDTO)
                .toList();
    }

    public ClienteDTO salvarCliente(Cliente cliente) {
        // Salva o cliente no banco de dados
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Retorna o ClienteDTO a partir do cliente salvo
        return converterParaClienteDTO(clienteSalvo);
    }

    public Optional<ClienteDTO> buscarClientePorId(String cpfcnpj) {
        Optional<Cliente> optionalCliente = clienteRepository.findByCpfcnpj(cpfcnpj);
        return optionalCliente.map(this::converterParaClienteDTO);
    }


    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public void atualizarCliente(Long id, Cliente clienteAtualizado) {
        // Implementação da atualização do cliente
    }

    private ClienteDTO converterParaClienteDTO(Cliente cliente) {
        // Usa o método auxiliar para converter o Endereco de forma segura
        EnderecoDTO enderecoDTO = converterParaEnderecoDTO(cliente.getEndereco());

        // Converte o cliente para ClienteDTO, incluindo o EnderecoDTO
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCpfcnpj(),
                cliente.getTipo(),
                enderecoDTO
        );
    }

    private EnderecoDTO converterParaEnderecoDTO(Endereco endereco) {
        return Optional.ofNullable(endereco)
                .map(e -> new EnderecoDTO(
                        e.getCep(),
                        e.getLogradouro(),
                        e.getCidade(),
                        e.getUf(),
                        e.getComplemento(),
                        e.getBairro(),
                        e.getCep()
                ))
                // Retorna um EnderecoDTO com valores vazios ou nulos
                .orElseGet(() -> new EnderecoDTO(
                        "",    // Cep vazio ou padrão
                        "",    // Logradouro vazio ou padrão
                        "",    // Cidade vazia ou padrão
                        "",    // UF vazio ou padrão
                        "",    // Complemento vazio ou padrão
                        "",    // Bairro vazio ou padrão
                        ""     // Cep vazio ou padrão
                ));
    }

}
