package edu.eci.cvds.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class Usuario {

    
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IDENTIFICACION")
    private Long id;

    @Column(name = "USUARIO")
    private String userName;

    @Column(name = "CONTRASEÑA")
    private String password;

    @Column(name = "NOMBRE")
    private String firstName;

    @Column(name = "APELLIDO")
    private String lastName;

    @Column(name = "CORREO")
    private String email;


}