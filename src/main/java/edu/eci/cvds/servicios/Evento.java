package edu.eci.cvds.servicios;
import org.primefaces.model.DefaultScheduleEvent;

import lombok.Data;

@Data
public class Evento extends DefaultScheduleEvent{
    private String casoAsiloTurista;
    private String negocioEUU;
    private String nombre;
    private String apellido;
    private int numeroTelefono;
    private String correoElectronico;
    private String descripcionUsuario;
    private String estadoCita;
    
}
