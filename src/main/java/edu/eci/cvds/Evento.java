package edu.eci.cvds;
import org.primefaces.model.DefaultScheduleEvent;


public class Evento extends DefaultScheduleEvent{
    private boolean casoAsiloTurista;
    private boolean negocioEUU;
    private String nombre;
    private String apellido;
    private int numeroTelefono;
    private String correoElectronico;
    private String descripcionUsuario;
    private String estadoCita;
    
    public void setCasoAsiloTurista(boolean respuesta){
        casoAsiloTurista = respuesta;
    }
    public void setNegocioEUU(boolean respuesta){
        negocioEUU = respuesta;
    }
    public boolean getNegocioEUU(){
        return negocioEUU;
    }
    public boolean getCasoAsiloTurista(){
        return casoAsiloTurista;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNumeroTelefono() {
        return numeroTelefono;
    }
    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    public String getDescripcionUsuario() {
        return descripcionUsuario;
    }
    public void setDescripcionUsuario(String descripcionUsuario) {
        this.descripcionUsuario = descripcionUsuario;
    }
    public String getEstadoCita() {
        return estadoCita;
    }
    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }
}
