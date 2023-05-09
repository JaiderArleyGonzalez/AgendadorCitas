package edu.eci.cvds;
import org.primefaces.model.DefaultScheduleEvent;


public class Evento extends DefaultScheduleEvent{
    private String casoAsiloTurista;
    private String negocioEUU;
    private String nombre;
    private String apellido;
    private int numeroTelefono;
    private String correoElectronico;
    private String descripcionUsuario;
    private String estadoCita;
    
    
    public void setCasoAsiloTurista(String respuesta){
        casoAsiloTurista = respuesta;
    }
    public void setNegocioEUU(String respuesta){
        negocioEUU = respuesta;
    }
    public String getNegocioEUU(){
        return negocioEUU;
    }
    public String getCasoAsiloTurista(){
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
