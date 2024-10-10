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

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> findAllClientes() {
        // ClienteD cliente = clienteRepository.findAll();

        // Aqui você pode filtrar para retornar apenas os clientes ativos
        return clienteRepository.findByAtivoTrue()
                .stream()
                .map(this::converterParaClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO salvarCliente(Cliente cliente) {
        // Salva o cliente no banco de dados
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Retorna o ClienteDTO a partir do cliente salvo
        return converterParaClienteDTO(clienteSalvo);
    }

    public Optional<ClienteDTO> buscarClientePorId(String cpfcnpj) {
        Optional<Cliente> optionalCliente = clienteRepository.findByCpfcnpjAndAtivoTrue(cpfcnpj);
        return optionalCliente.map(this::converterParaClienteDTO);
    }


    public void atualizarCliente(String cpfcnpj, Cliente clienteAtualizado) {
        // Buscar o cliente existente no banco de dados pelo ID
        Optional<Cliente> clienteExistenteOptional = clienteRepository.findByCpfcnpjAndAtivoTrue(cpfcnpj);

        if (clienteExistenteOptional.isPresent()) {
            Cliente clienteExistente = clienteExistenteOptional.get();

            // Atualizar os dados do cliente
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setCpfcnpj(clienteAtualizado.getCpfcnpj());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setTelefone(clienteAtualizado.getTelefone());
            clienteExistente.setTipo(clienteAtualizado.getTipo());

            // Atualizar endereço
            if (clienteAtualizado.getEndereco() != null) {
                Endereco enderecoExistente = clienteExistente.getEndereco();
                Endereco enderecoAtualizado = clienteAtualizado.getEndereco();

                if (enderecoExistente == null) {
                    clienteExistente.setEndereco(new Endereco()); // Inicia um novo endereço se necessário
                    enderecoExistente = clienteExistente.getEndereco();
                }

                enderecoExistente.setCep(enderecoAtualizado.getCep());
                enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
                enderecoExistente.setBairro(enderecoAtualizado.getBairro());
                enderecoExistente.setCidade(enderecoAtualizado.getCidade());
                enderecoExistente.setUf(enderecoAtualizado.getUf());
                enderecoExistente.setNumero(enderecoAtualizado.getNumero());
                enderecoExistente.setComplemento(enderecoAtualizado.getComplemento());
            }

            // Salvar o cliente atualizado no banco de dados
            clienteRepository.save(clienteExistente);
        } else {
            throw new RuntimeException("Cliente com CPF/CNPJ " + cpfcnpj + " não encontrado.");
        }
    }



    public void deletarClientePorCpfCnpj(String cpfcnpj) {
        Optional<Cliente> clienteOpt = clienteRepository.findByCpfcnpjAndAtivoTrue(cpfcnpj);
        clienteOpt.ifPresent(cliente -> {
            cliente.setAtivo(false);
            clienteRepository.save(cliente);
        });
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
                        e.getBairro(),
                        e.getCidade(),
                        e.getUf(),
                        e.getNumero(),
                        e.getComplemento()

                ))
                // Retorna um EnderecoDTO com valores vazios ou nulos
                .orElseGet(() -> new EnderecoDTO(
                        "",    // Cep vazio ou padrão
                        "",    // Logradouro vazio ou padrão
                        "",    // Cidade vazia ou padrão
                        "",    // UF vazio ou padrão
                        "",    // Complemento vazio ou padrão
                        "",    // Bairro vazio ou padrão
                        ""// Cep vazio ou padrão
                ));
    }

}
