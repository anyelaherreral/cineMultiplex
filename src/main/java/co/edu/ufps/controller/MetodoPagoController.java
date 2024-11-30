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

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.MetodoPago;
import co.edu.ufps.services.AsientoService;
import co.edu.ufps.services.MetodoPagoService;

@RestController
@RequestMapping("/metodosdepago")
public class MetodoPagoController {
	
	@Autowired
	private MetodoPagoService metodoPagoService;

	@GetMapping
	public List<MetodoPago>  list() {
		
		return metodoPagoService.list();
	}

	@PostMapping
	public MetodoPago create(@RequestBody MetodoPago MetodoPago) {
		return metodoPagoService.create(MetodoPago);
	}
	
 
    @PostMapping("/{id}/add_pedidos/{pedidoId}")
	public MetodoPago create(@PathVariable Integer id, @PathVariable Integer pedidoId) {
		MetodoPago nuevaMetodoPago = metodoPagoService.addPedido(id, pedidoId);
		return nuevaMetodoPago;
	}

}
