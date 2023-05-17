package edu.eci.cvds.controladores;
import org.primefaces.model.DefaultScheduleEvent;
import org.springframework.stereotype.Component;

import lombok.*;


@Data
@Component

public class Evento extends DefaultScheduleEvent{
    private String casoAsiloTurista;
    private String negocioEEUU;
    private String nombre;
    private String apellido;
    private String numeroTelefono;
    private String correoElectronico;
    private String descripcionUsuario;
    private String estadoCita;
    private Boolean checkBox;
    
}
