package edu.eci.cvds.controladores;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;


import edu.eci.cvds.modelo.Usuario;
import edu.eci.cvds.repositorios.UsuarioRepositorio;
import edu.eci.cvds.servicios.UsuarioServicio;
import lombok.Data;

@Component
@Data
@ManagedBean(name = "registro")
@SessionScoped
//@ApplicationScoped
public class Registro implements Serializable {
    private final UsuarioRepositorio usuarioRepository;
    @Autowired
    private UsuarioServicio usuarioServicio;
    private String user;
    private String password;
    public Registro(UsuarioRepositorio usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public String login() {
        Usuario usuario = usuarioRepository.findByUserNameAndPassword(user,password);
        if (usuario == null || !usuario.getPassword().equals(password)) {
            FacesContext.getCurrentInstance().addMessage("@all", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario o la contaseña no son correctos.", null));
            return null;
        } else {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            try {
                externalContext.redirect("../calendario/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace(); // Manejo de errores en caso de que ocurra un problema durante la redirección
            }
            return null; // La redirección se realizará, por lo que no es necesario retornar una cadena de navegación
        }
    }

    @Bean
    public CommandLineRunner currentUser() throws Exception{
        return args -> {
            usuarioServicio.saveUser(new Usuario((long) 1,"Liliana","1234","Liliana","Jones","asd"));
            usuarioServicio.findAll().forEach(System.out::println);
        };
    }
}
