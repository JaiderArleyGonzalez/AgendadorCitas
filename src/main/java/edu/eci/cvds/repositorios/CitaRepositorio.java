package edu.eci.cvds.repositorios;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.eci.cvds.modelo.Cita;
@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long>{
    boolean existsById(LocalDateTime citaId);
    Cita findById(LocalDateTime citaId);   
    void deleteById(LocalDateTime citaId);
}
