package edu.eci.cvds.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.modelo.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    boolean existsById(Long id);
    
    void deleteByUserName(String userName);
    Usuario findByUserNameAndPassword(String userName, String password);
}