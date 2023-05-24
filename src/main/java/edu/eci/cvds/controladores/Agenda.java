package edu.eci.cvds.controladores;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class Agenda {
    @Autowired
    private Evento eventoUsuario;
    @Autowired
    private Evento eventoAdministrador;
    @Autowired
    private Schedule calendario;
    private boolean administracion;

    public void agendarCitaAdministrador(){     
        calendario.setEvent(eventoAdministrador);
        calendario.addEvent();
        eventoAdministrador = new Evento();
    }
    public void agendarCitaUsuario(){
        calendario.setEvent(eventoUsuario);
        calendario.addEvent();
        eventoUsuario = new Evento();
    }
    public void onEventSelect(SelectEvent<Evento> selectEvent) {
        eventoAdministrador = selectEvent.getObject();
    }
    
    
}
