package app.estacionamiento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.estacionamiento.models.ReservaModel;
import app.estacionamiento.services.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController (ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<ReservaModel> crearReserva(@PathVariable Long usuarioId, @RequestBody ReservaModel reserva) {
        try {
            ReservaModel newReserva = reservaService.crearReserva(usuarioId, reserva);
            return ResponseEntity.ok(newReserva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/activas/{sector}")
    public ResponseEntity<List<ReservaModel>> obtenerReservasActivas(@PathVariable String sector){
        return ResponseEntity.ok(reservaService.obtenerReservasActivas(sector));
    }
    @DeleteMapping("/{reservaId}")
    public ResponseEntity<String> cancelarReserva(@PathVariable Long reservaId) {
        try {
            reservaService.cancelarReserva(reservaId);
            return ResponseEntity.ok("Reserva cancelada correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
