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

    /**
     * Lista todos los clientes.
     * 
     * @return Lista de clientes.
     */
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.list();
    }

    /**
     * Crea un nuevo cliente.
     * 
     * @param cliente El cliente a crear.
     * @return El cliente creado.
     */
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente createdCliente = clienteService.create(cliente);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    /**
     * Obtiene un cliente por su ID.
     * 
     * @param id ID del cliente.
     * @return El cliente, si está presente.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.getById(id);
        return cliente.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza un cliente existente.
     * 
     * @param id ID del cliente a actualizar.
     * @param clienteDetails Detalles actualizados del cliente.
     * @return El cliente actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> updatedCliente = clienteService.update(id, clienteDetails);
        return updatedCliente.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Elimina un cliente por su ID.
     * 
     * @param id ID del cliente a eliminar.
     * @return ResponseEntity indicando si se eliminó.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        boolean isDeleted = clienteService.delete(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Obtiene todos los pedidos de un cliente por su ID.
     * 
     * @param clienteId ID del cliente.
     * @return Lista de pedidos del cliente.
     */
    @GetMapping("/{clienteId}/pedidos")
    public ResponseEntity<List<Pedido>> getPedidosByClienteId(@PathVariable Integer clienteId) {
        List<Pedido> pedidos = clienteService.getPedidosByClienteId(clienteId);
        return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.notFound().build();
    }

    /**
     * Busca clientes por nombre.
     * 
     * @param nombre Nombre del cliente a buscar.
     * @return Lista de clientes que coinciden con el nombre.
     */
    @GetMapping("/buscar")
    public List<Cliente> findClientesByName(@RequestParam String nombre) {
        return clienteService.findByName(nombre);
    }

    /**
     * Agrega un pedido a un cliente existente.
     * 
     * @param clienteId ID del cliente.
     * @param pedido    El pedido a agregar.
     * @return El cliente actualizado con el pedido agregado.
     */
    @PostMapping("/{clienteId}/pedidos")
    public ResponseEntity<Cliente> addPedidoToCliente(@PathVariable Integer clienteId, @RequestBody Pedido pedido) {
        Cliente updatedCliente = clienteService.addPedidoToCliente(clienteId, pedido);
        return updatedCliente != null ? ResponseEntity.ok(updatedCliente) : ResponseEntity.notFound().build();
    }
}
