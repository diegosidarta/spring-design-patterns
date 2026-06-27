package dio.bootcamp.padrao_projeto_spring.service.impl;

import dio.bootcamp.padrao_projeto_spring.model.Cliente;
import dio.bootcamp.padrao_projeto_spring.model.ClienteRepository;
import dio.bootcamp.padrao_projeto_spring.model.Endereco;
import dio.bootcamp.padrao_projeto_spring.model.EnderecoRepository;
import dio.bootcamp.padrao_projeto_spring.service.ClienteService;
import dio.bootcamp.padrao_projeto_spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @Override
    public void inserir(Cliente cliente) {
        validarCliente(cliente); // Trava de segurança antes de ir pro banco
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        cliente.setId(id); // o id que veio na URL /clientes/{id}
        salvarClienteComCep(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        clienteRepository.deleteById(id);
    }

    public void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O nome do cliente é obrigatório.");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null || cliente.getEndereco().getCep().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CEP do cliente é obrigatório.");
        }
    }
}
