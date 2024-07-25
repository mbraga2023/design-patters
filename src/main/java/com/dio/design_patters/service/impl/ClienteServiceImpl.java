package com.dio.design_patters.service.impl;

import com.dio.design_patters.model.Cliente;
import com.dio.design_patters.model.ClienteRepository;
import com.dio.design_patters.model.Endereco;
import com.dio.design_patters.model.EnderecoRepository;
import com.dio.design_patters.service.ClienteService;
import com.dio.design_patters.service.ViaCepService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
        try {
            return clienteRepository.findAll();
        } catch (Exception e) {
            // Log the exception and rethrow or handle it appropriately
            // logger.error("Failed to fetch all clients", e);
            throw new RuntimeException("Failed to fetch all clients", e);
        }
    }

    @Override
    public Cliente buscarPorId(Long id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                return cliente.get();
            } else {
                throw new RuntimeException("Client not found with id: " + id);
            }
        } catch (Exception e) {
            // Log the exception and rethrow or handle it appropriately
            // logger.error("Failed to fetch client by id: " + id, e);
            throw new RuntimeException("Failed to fetch client by id: " + id, e);
        }
    }

    @Override
    @Transactional
    public void inserir(Cliente cliente) {
        try {
            salvarClienteComCep(cliente);
        } catch (Exception e) {
            // Log the exception and rethrow or handle it appropriately
            // logger.error("Failed to insert client", e);
            throw new RuntimeException("Failed to insert client", e);
        }
    }

    @Override
    @Transactional
    public void atualizar(Long id, Cliente cliente) {
        try {
            Optional<Cliente> clienteBd = clienteRepository.findById(id);
            if (clienteBd.isPresent()) {
                salvarClienteComCep(cliente);
            } else {
                throw new RuntimeException("Client not found with id: " + id);
            }
        } catch (Exception e) {
            // Log the exception and rethrow or handle it appropriately
            // logger.error("Failed to update client with id: " + id, e);
            throw new RuntimeException("Failed to update client with id: " + id, e);
        }
    }

    @Override
    public void deletar(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Handle case where the ID does not exist
            // logger.warn("Client with id: " + id + " not found for deletion", e);
            throw new RuntimeException("Client with id: " + id + " not found for deletion", e);
        } catch (Exception e) {
            // Log the exception and rethrow or handle it appropriately
            // logger.error("Failed to delete client with id: " + id, e);
            throw new RuntimeException("Failed to delete client with id: " + id, e);
        }
    }

    private void salvarClienteComCep(Cliente cliente) {
        try {
            String cep = cliente.getEndereco().getCep();
            Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                Endereco novoEndereco = viaCepService.consultarCep(cep);
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });
            cliente.setEndereco(endereco);
            clienteRepository.save(cliente);
        } catch (Exception e) {
            // Log the exception and rethrow or handle it appropriately
            // logger.error("Failed to save client with CEP: " + cliente.getEndereco().getCep(), e);
            throw new RuntimeException("Failed to save client with CEP: " + cliente.getEndereco().getCep(), e);
        }
    }
}