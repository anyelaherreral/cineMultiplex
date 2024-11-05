package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Pedido; // Asegúrate de importar la clase Pedido
import co.edu.ufps.repositories.ClienteRepository;
import co.edu.ufps.repositories.PedidoRepository; // Asegúrate de importar el repositorio de Pedido

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository; // Inyectar el repositorio de pedidos

    /**
     * Lista todos los clientes en el sistema.
     * 
     * @return Lista de todos los clientes.
     */
    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    /**
     * Crea un nuevo cliente.
     * 
     * @param cliente El cliente a crear.
     * @return El cliente creado.
     */
    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Obtiene un cliente por su ID.
     * 
     * @param id ID del cliente.
     * @return El cliente, si está presente.
     */
    public Optional<Cliente> getById(Integer id) {
        return clienteRepository.findById(id);
    }

    /**
     * Actualiza un cliente existente con los detalles proporcionados.
     * 
     * @param id            ID del cliente a actualizar.
     * @param clienteDetails Detalles actualizados del cliente.
     * @return El cliente actualizado, si está presente.
     */
    public Optional<Cliente> update(Integer id, Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (!optionalCliente.isPresent()) {
            return Optional.empty();
        }

        Cliente cliente = optionalCliente.get();
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setEmail(clienteDetails.getEmail());

        return Optional.of(clienteRepository.save(cliente));
    }

    /**
     * Elimina un cliente por su ID.
     * 
     * @param id ID del cliente a eliminar.
     * @return true si el cliente fue eliminado, false si no existe.
     */
    public boolean delete(Integer id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }

    /**
     * Obtiene todos los pedidos de un cliente por su ID.
     * 
     * @param clienteId ID del cliente.
     * @return Lista de pedidos del cliente, si el cliente existe.
     */
    public List<Pedido> getPedidosByClienteId(Integer clienteId) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        return clienteOpt.map(Cliente::getPedidos).orElse(null);
    }

    /**
     * Busca clientes por nombre.
     * 
     * @param nombre Nombre del cliente a buscar.
     * @return Lista de clientes que coinciden con el nombre.
     */
    public List<Cliente> findByName(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Agrega un pedido a un cliente existente.
     * 
     * @param clienteId ID del cliente.
     * @param pedido    El pedido a agregar al cliente.
     * @return El cliente actualizado con el pedido agregado, si el cliente existe.
     */
    public Cliente addPedidoToCliente(Integer clienteId, Pedido pedido) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            pedido.setCliente(cliente); // Asocia el cliente al pedido
            pedidoRepository.save(pedido); // Guarda el pedido en la base de datos
            cliente.getPedidos().add(pedido); // Agrega el pedido a la lista del cliente
            return clienteRepository.save(cliente); // Guarda el cliente actualizado
        }
        return null; // Devuelve null si el cliente no existe
    }
}
