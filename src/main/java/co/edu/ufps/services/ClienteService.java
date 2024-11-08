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

   
    public List<Cliente> list() {
        return clienteRepository.findAll();
    }


    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public Optional<Cliente> getById(Integer id) {
        return clienteRepository.findById(id);
    }

 
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


    public boolean delete(Integer id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }

    public List<Pedido> getPedidosByClienteId(Integer clienteId) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        return clienteOpt.map(Cliente::getPedidos).orElse(null);
    }


//    public List<Cliente> findByName(String nombre) {
//        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
//    }

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
