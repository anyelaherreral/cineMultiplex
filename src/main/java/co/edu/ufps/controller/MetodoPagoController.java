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
	
	
//	
//	 /**
//     * Endpoint para obtener un método de pago por su descripción.
//     * @param descripcion Descripción del método de pago.
//     * @return ResponseEntity con el MetodoPago encontrado o código 404 si no existe.
//     */
//    @GetMapping("/{descripcion}")
//    public ResponseEntity<MetodoPago> getByDescripcion(@PathVariable String descripcion) {
//        Optional<MetodoPago> metodoPago = metodoPagoService.getByDescripcion(descripcion);
//        return metodoPago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//    
//    /**
//     * Endpoint para actualizar un método de pago basado en su descripción.
//     * @param descripcion Descripción actual del método de pago a actualizar.
//     * @param metodoPagoDetails Objeto MetodoPago con los nuevos datos.
//     * @return ResponseEntity con el MetodoPago actualizado o código 404 si no existe.
//     */
//    @PutMapping("/{descripcion}")
//    public ResponseEntity<MetodoPago> update(@PathVariable String descripcion, @RequestBody MetodoPago metodoPagoDetails) {
//        Optional<MetodoPago> updatedMetodoPago = metodoPagoService.update(descripcion, metodoPagoDetails);
//        return updatedMetodoPago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    /**
//     * Endpoint para eliminar un método de pago basado en su descripción.
//     * @param descripcion Descripción del método de pago a eliminar.
//     * @return ResponseEntity con código 204 si se eliminó o 404 si no se encontró.
//     */
//    @DeleteMapping("/{descripcion}")
//    public ResponseEntity<Void> deleteByDescripcion(@PathVariable String descripcion) {
//        boolean deleted = metodoPagoService.deleteByDescripcion(descripcion);
//        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//    }
//    
    @PostMapping("/{id}/add_pedidos/{pedidoId}")
	public MetodoPago create(@PathVariable Integer id, @PathVariable Integer pedidoId) {
		MetodoPago nuevaMetodoPago = metodoPagoService.addPedido(id, pedidoId);
		return nuevaMetodoPago;
	}

}
