package app.estacionamiento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.estacionamiento.models.ReservaModel;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findBySectorAndFechaHoraReservaAfterAndEstado(String sector, LocalDateTime fechaActual, String estado);
}
