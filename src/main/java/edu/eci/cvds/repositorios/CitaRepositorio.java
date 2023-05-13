package edu.eci.cvds.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.modelo.Cita;
@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long>{
    boolean existsById(String citaId);
    Cita findById(String citaId);   
    void deleteById(String citaId);
}
