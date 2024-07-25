package com.dio.design_patters.controller;

import com.dio.design_patters.model.Cliente;
import com.dio.design_patters.model.Endereco;
import com.dio.design_patters.service.ClienteService;
import com.dio.design_patters.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ViaCepService viaCepService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Endereco> buscarEnderecoPorCep(@PathVariable("cep") String cep) {
        try {
            Endereco endereco = viaCepService.consultarCep(cep);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            // Em caso de erro, como CEP inválido ou problemas com o serviço
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}