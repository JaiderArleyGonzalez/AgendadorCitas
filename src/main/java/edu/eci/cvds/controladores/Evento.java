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
    
    public void setColor(){
        if (estadoCita.equals("Programada")){
            setBackgroundColor("#339CFF");
            setBorderColor("#339CFF");
        } if(estadoCita.equals("En proceso")){
            setBackgroundColor("#FFF933");
            setBorderColor("#FFF933");
        } if(estadoCita.equals("Atendida")){
            setBackgroundColor("#39FF33");
            setBorderColor("#39FF33");
        }
    }
    
}
