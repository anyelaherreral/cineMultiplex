package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Pedido; 
import co.edu.ufps.repositories.ClienteRepository;
import co.edu.ufps.repositories.PedidoRepository;  

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;  

   
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


    public Cliente addPedidoToCliente(Integer clienteId, Pedido pedido) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            pedido.setCliente(cliente);  
            pedidoRepository.save(pedido);  
            cliente.getPedidos().add(pedido);  
            return clienteRepository.save(cliente); 
        }
        return null;  
    }
    
    public Optional<Cliente> getByDocumento(String documento) {
        return clienteRepository.findByDocumento(documento);
    }
}
