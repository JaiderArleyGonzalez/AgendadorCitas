package edu.eci.cvds.servicios;

import org.springframework.stereotype.Service;

import edu.eci.cvds.modelo.Cita;
import edu.eci.cvds.repositorios.CitaRepositorio;

import java.util.List;
@Service
public class CitaServicio {
    private final CitaRepositorio citaRepositorio;
    public CitaServicio(CitaRepositorio citaRepositorio){
        this.citaRepositorio = citaRepositorio;
    }
    public Cita addCita(Cita citaB){
        return citaRepositorio.save(citaB);
    }
    public Cita getCita (String citaId){
        return citaRepositorio.findById(citaId);
    }
    public List<Cita> getAllCita(){
        return citaRepositorio.findAll();
    }
    public Cita updateCita(Cita citaB){
        if(citaRepositorio.existsById(citaB.getId())){
            return citaRepositorio.save(citaB);
        }
        return null;
    }
    public void deleteCita(String citaId){
        citaRepositorio.deleteById(citaId);
    }
}
