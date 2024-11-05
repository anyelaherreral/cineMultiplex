package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.Boleto;
import co.edu.ufps.services.BoletoService;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

	@Autowired
	private BoletoService boletoService;

	/**
	 * Obtiene una lista de todos los boletos.
	 * 
	 * @return Lista de boletos.
	 */
	@GetMapping
	public ResponseEntity<List<Boleto>> getAllBoletos() {
		List<Boleto> boletos = boletoService.list();
		return new ResponseEntity<>(boletos, HttpStatus.OK);
	}

	/**
	 * Crea un nuevo boleto.
	 * 
	 * @param boleto El boleto a crear.
	 * @return El boleto creado.
	 */
	@PostMapping
	public ResponseEntity<Boleto> createBoleto(@RequestBody Boleto boleto) {
		Boleto createdBoleto = boletoService.create(boleto);
		return new ResponseEntity<>(createdBoleto, HttpStatus.CREATED);
	}

	/**
	 * Obtiene un boleto por su ID.
	 * 
	 * @param id ID del boleto.
	 * @return El boleto, si está presente.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Boleto> getBoletoById(@PathVariable Integer id) {
		Optional<Boleto> boleto = boletoService.getById(id);
		return boleto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Actualiza un boleto existente.
	 * 
	 * @param id            ID del boleto a actualizar.
	 * @param boletoDetails Detalles actualizados del boleto.
	 * @return El boleto actualizado, si está presente.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Boleto> updateBoleto(@PathVariable Integer id, @RequestBody Boleto boletoDetails) {
		Optional<Boleto> updatedBoleto = boletoService.update(id, boletoDetails);
		return updatedBoleto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Elimina un boleto por su ID.
	 * 
	 * @param id ID del boleto a eliminar.
	 * @return respuesta con el estado de la eliminación.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBoleto(@PathVariable Integer id) {
		boolean deleted = boletoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	/**
	 * Asocia un asiento a un boleto.
	 * 
	 * @param boletoId  ID del boleto.
	 * @param asientoId ID del asiento a asociar.
	 * @return El boleto actualizado con el asiento asociado, si el boleto existe.
	 */
	@PostMapping("/{boletoId}/asientos/{asientoId}")
	public ResponseEntity<Boleto> addAsientoToBoleto(@PathVariable Integer boletoId, @PathVariable Integer asientoId) {
		Boleto updatedBoleto = boletoService.addAsientoToBoleto(boletoId, asientoId);
		return updatedBoleto != null ? ResponseEntity.ok(updatedBoleto) : ResponseEntity.notFound().build();
	}
}
