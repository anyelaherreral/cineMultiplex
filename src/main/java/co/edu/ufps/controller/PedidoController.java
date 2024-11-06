package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.ufps.entities.Pedido;
import co.edu.ufps.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public List<Pedido>  list() {
		
		return pedidoService.list();
	}

	@PostMapping
	public Pedido create(@RequestBody Pedido Pedido) {
		return pedidoService.create(Pedido);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable Integer id) {
		Optional<Pedido> Pedido = pedidoService.getById(id);
		return Pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Integer id, @RequestBody Pedido PedidoDetails) {
		Optional<Pedido> updatedPedido = pedidoService.update(id, PedidoDetails);
		return updatedPedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = pedidoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
