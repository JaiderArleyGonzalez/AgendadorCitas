package edu.eci.cvds.modelo;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
@Entity
@Table(name = "CITA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id
    @Column(name="STARTDATE")
    private LocalDateTime id;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CASOASILOTURISTA")
    private String casoAsiloTurista;
    @Column(name = "NEGOCIOEEUU")
    private String negocioEEUU;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "NUMEROTELEFONO")
    private String numeroTelefono;
    @Column(name = "CORREOELECTRONICO")
    private String correoElectronico;
    @Column(name = "DESCRIPCIONUSUARIO")
    private String descripcionUsuario;
    @Column(name = "ESTADOCITA")
    private String estadoCita;
    
    @Column(name="ENDDATE")
    private LocalDateTime endDate;
    @Lob
    @Column(name="FIRMA")
    private String firma;
    @Column(name="TerminosCondiciones")
    private boolean checkBox;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita that = (Cita) o;
        return Objects.equals(id, that.id) && Objects.equals(descripcion, that.descripcion);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }
}
