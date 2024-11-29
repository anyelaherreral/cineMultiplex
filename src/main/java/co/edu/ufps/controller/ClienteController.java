package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Pedido;
import co.edu.ufps.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.list();
    }


    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente createdCliente = clienteService.create(cliente);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.getById(id);
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> updatedCliente = clienteService.update(id, clienteDetails);
        return updatedCliente.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        boolean isDeleted = clienteService.delete(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @GetMapping("/{clienteId}/pedidos")
    public ResponseEntity<List<Pedido>> getPedidosByClienteId(@PathVariable Integer clienteId) {
        List<Pedido> pedidos = clienteService.getPedidosByClienteId(clienteId);
        return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{clienteId}/pedidos")
    public ResponseEntity<Cliente> addPedidoToCliente(@PathVariable Integer clienteId, @RequestBody Pedido pedido) {
        Cliente updatedCliente = clienteService.addPedidoToCliente(clienteId, pedido);
        return updatedCliente != null ? ResponseEntity.ok(updatedCliente) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/buscar-por-documento")
    public ResponseEntity<Cliente> getClienteByDocumento(@RequestParam String documento) {
        Optional<Cliente> cliente = clienteService.getByDocumento(documento);
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
