package app.estacionamiento.services;

import org.springframework.stereotype.Service;
import app.estacionamiento.models.ReservaModel;
import app.estacionamiento.repositories.EspacioRepository;
import app.estacionamiento.repositories.ReservaRepository;
import app.estacionamiento.repositories.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final EspacioRepository espacioRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService (ReservaRepository reservaRepository,
                           EspacioRepository espacioRepository,
                           UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.espacioRepository = espacioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ReservaModel crearReserva(Long usuarioId, ReservaModel reserva) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
        // Validar espacios disponibles en el sector solicitado
        long espaciosDisponibles = espacioRepository.countBySectorAndOcupadoFalse(reserva.getSector());
        if (espaciosDisponibles <= 0) {
            throw new IllegalArgumentException("No hay espacios disponibles en el sector solicitado.");
        }
        reserva.setUsuario(usuarioRepository.findById(usuarioId).orElseThrow());
        reserva.setEstado("ACTIVA");
        reserva.setFechaHoraReserva(LocalDateTime.now());

        return reservaRepository.save(reserva);
    }

    public List<ReservaModel> obtenerReservasActivas(String sector) {
        String s = sector;
        return reservaRepository.findBySectorAndFechaHoraReservaAfterAndEstado(s,LocalDateTime.now(),"ACTIVA");
    }
    public void cancelarReserva(Long reservaId) {
        ReservaModel reserva = reservaRepository.findById(reservaId)
                .orElseThrow(()-> new IllegalArgumentException("Reserva no encontrada."));
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }


}
