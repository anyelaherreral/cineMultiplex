package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.entities.Asiento;
import co.edu.ufps.entities.Boleto; 
import co.edu.ufps.services.BoletoService;
import co.edu.ufps.services.CategoriaBoletoService;
import co.edu.ufps.services.FuncionService;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private FuncionService funcionService;
	
	@Autowired
	private CategoriaBoletoService categoriaBoletoService;

	@GetMapping
	public ResponseEntity<List<Boleto>> getAllBoletos() {
		List<Boleto> boletos = boletoService.list();
		return new ResponseEntity<>(boletos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Boleto> createBoleto(@RequestBody Boleto boleto) {
		Boleto createdBoleto = boletoService.create(boleto);
		return new ResponseEntity<>(createdBoleto, HttpStatus.CREATED);
	}
	
	@GetMapping("/funcion/{funcionId}/asientos")
	public ResponseEntity<List<Asiento>> getAsientosByFuncion(@PathVariable Integer funcionId) {
	    List<Asiento> asientos = boletoService.getAsientosByFuncionId(funcionId);
	    if (asientos.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(asientos);
	}


	@GetMapping("/{id}")
	public ResponseEntity<Boleto> getBoletoById(@PathVariable Integer id) {
		Optional<Boleto> boleto = boletoService.getById(id);
		return boleto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Boleto> updateBoleto(@PathVariable Integer id, @RequestBody Boleto boletoDetails) {
		Optional<Boleto> updatedBoleto = boletoService.update(id, boletoDetails);
		return updatedBoleto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBoleto(@PathVariable Integer id) {
		boolean deleted = boletoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	@PutMapping("/{boletoId}/asignar-asiento")
    public ResponseEntity<Boleto> asignarAsiento(
            @PathVariable Integer boletoId,
            @RequestParam Integer asientoId,
            @RequestParam Integer funcionId) {

        try {
            Boleto boletoActualizado = boletoService.addAsientoToBoleto(boletoId, asientoId, funcionId);
            return ResponseEntity.ok(boletoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);  
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);  
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  
        }
    }
	
	@PostMapping("/ListaBoletos")
	public ResponseEntity<List<Boleto>> createBoletos(@RequestBody List<Boleto> boletos) {
	    try {
	         
	        List<Boleto> boletosCreados = boletoService.createBoletos(boletos);
	         
	        return new ResponseEntity<>(boletosCreados, HttpStatus.CREATED);
	    } catch (Exception e) {
	        
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
