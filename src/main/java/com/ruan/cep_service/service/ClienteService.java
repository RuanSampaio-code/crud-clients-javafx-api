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
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    // Injeção de dependência do ClienteRepository
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Retorna todos os clientes ativos no banco de dados
    public List<ClienteDTO> findAllClientes() {
        // Filtra e mapeia os clientes ativos para ClienteDTO
        return clienteRepository.findByAtivoTrue()
                .stream()
                .map(this::converterParaClienteDTO)
                .collect(Collectors.toList());
    }

    // Salva um novo cliente e retorna seu DTO
    public ClienteDTO salvarCliente(Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return converterParaClienteDTO(clienteSalvo);
    }

    // Busca um cliente pelo CPF/CNPJ, retornando um Optional de ClienteDTO se encontrado
    public Optional<ClienteDTO> buscarClientePorId(String cpfcnpj) {
        Optional<Cliente> optionalCliente = clienteRepository.findByCpfcnpjAndAtivoTrue(cpfcnpj);
        return optionalCliente.map(this::converterParaClienteDTO);
    }

    // Atualiza os dados de um cliente existente
    public void atualizarCliente(String cpfcnpj, Cliente clienteAtualizado) {
        // Busca o cliente existente no banco de dados
        Optional<Cliente> clienteExistenteOptional = clienteRepository.findByCpfcnpjAndAtivoTrue(cpfcnpj);

        if (clienteExistenteOptional.isPresent()) {
            Cliente clienteExistente = clienteExistenteOptional.get();

            // Atualiza os campos principais do cliente
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setCpfcnpj(clienteAtualizado.getCpfcnpj());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setTelefone(clienteAtualizado.getTelefone());
            clienteExistente.setTipo(clienteAtualizado.getTipo());

            // Atualiza o endereço do cliente, se fornecido
            if (clienteAtualizado.getEndereco() != null) {
                Endereco enderecoExistente = clienteExistente.getEndereco();
                Endereco enderecoAtualizado = clienteAtualizado.getEndereco();

                // Se o cliente não tem um endereço, cria um novo
                if (enderecoExistente == null) {
                    clienteExistente.setEndereco(new Endereco());
                    enderecoExistente = clienteExistente.getEndereco();
                }

                // Atualiza os campos do endereço
                enderecoExistente.setCep(enderecoAtualizado.getCep());
                enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
                enderecoExistente.setBairro(enderecoAtualizado.getBairro());
                enderecoExistente.setCidade(enderecoAtualizado.getCidade());
                enderecoExistente.setUf(enderecoAtualizado.getUf());
                enderecoExistente.setNumero(enderecoAtualizado.getNumero());
                enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
            }

            // Salva o cliente atualizado
            clienteRepository.save(clienteExistente);
        } else {
            throw new RuntimeException("Cliente com CPF/CNPJ " + cpfcnpj + " não encontrado.");
        }
    }

    // Marca o cliente como inativo para exclusão lógica
    public void deletarClientePorCpfCnpj(String cpfcnpj) {
        Optional<Cliente> clienteOpt = clienteRepository.findByCpfcnpjAndAtivoTrue(cpfcnpj);
        clienteOpt.ifPresent(cliente -> {
            cliente.setAtivo(false);
            clienteRepository.save(cliente);
        });
    }

    // Converte Cliente para ClienteDTO, incluindo endereço
    private ClienteDTO converterParaClienteDTO(Cliente cliente) {
        EnderecoDTO enderecoDTO = converterParaEnderecoDTO(cliente.getEndereco());
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

    // Converte Endereco para EnderecoDTO, retornando um DTO vazio se o endereço for nulo
    private EnderecoDTO converterParaEnderecoDTO(Endereco endereco) {
        return Optional.ofNullable(endereco)
                .map(e -> new EnderecoDTO(
                        e.getCep(),
                        e.getLogradouro(),
                        e.getBairro(),
                        e.getCidade(),
                        e.getUf(),
                        e.getNumero(),
                        e.getComplemento()
                ))
                // Retorna um EnderecoDTO com valores padrão
                .orElseGet(() -> new EnderecoDTO(
                        "", // CEP padrão
                        "", // Logradouro padrão
                        "", // Bairro padrão
                        "", // Cidade padrão
                        "", // UF padrão
                        "", // Número padrão
                        ""  // Complemento padrão
                ));
    }
}
