package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
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
 
	public Optional<Pedido> update(Integer id, Pedido pedidoDetails) {
		Optional<Pedido> optionalpedido = pedidoRepository.findById(id);
		if (!optionalpedido.isPresent()) {
			return Optional.empty();
		}

		Pedido pedido = optionalpedido.get();
 
		pedido.setFecha(pedidoDetails.getFecha());
		pedido.setTotal(pedidoDetails.getTotal());

		return Optional.of(pedidoRepository.save(pedido));
	}
 
	public boolean delete(Integer id) {
		if (!pedidoRepository.existsById(id)) {
			return false;
		}

		pedidoRepository.deleteById(id);
		return true;
	}
}
