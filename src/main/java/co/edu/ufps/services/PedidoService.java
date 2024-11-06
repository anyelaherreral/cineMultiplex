package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Cliente;
import co.edu.ufps.entities.Pedido;
import co.edu.ufps.repositories.PedidoRepository;


@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public List<Pedido> list() {
		return pedidoRepository.findAll();
	}
	
	public Pedido create(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
    public Optional<Pedido> getById(Integer id) {
        return pedidoRepository.findById(id);
    }

	// Actualizar un pedido existente
	public Optional<Pedido> update(Integer id, Pedido pedidoDetails) {
		Optional<Pedido> optionalpedido = pedidoRepository.findById(id);
		if (!optionalpedido.isPresent()) {
			return Optional.empty();
		}

		Pedido pedido = optionalpedido.get();

		// Actualiza otros campos según sea necesario
		pedido.setFecha(pedido.getFecha());
		pedido.setTotal(pedido.getTotal());

		return Optional.of(pedidoRepository.save(pedido));
	}

	// Eliminar un pedido por ID
	public boolean delete(Integer id) {
		if (!pedidoRepository.existsById(id)) {
			return false;
		}

		pedidoRepository.deleteById(id);
		return true;
	}
}
