package edu.eci.cvds.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.modelo.Usuario;
import edu.eci.cvds.repositorios.UsuarioRepositorio;


@Service
public class UsuarioServicio {

    
    private UsuarioRepositorio userRepository;

    @Autowired
    public UsuarioServicio(UsuarioRepositorio usuarioRepository){
        this.userRepository = usuarioRepository;
    }

    public void saveUser(Usuario user) {
        userRepository.save(user);
    }

    public void deleteUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public void updateUser(Usuario user) {
        if(userRepository.existsById(user.getId())){
            userRepository.save(user);
        }
    }

    public boolean login(String userName, String password) {
        Usuario user = userRepository.findByUserNameAndPassword(userName, password);
        return user != null;
    }

    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

}