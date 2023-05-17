package edu.eci.cvds.controladores;

import java.io.Serializable;
import java.time.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.schedule.*;
import org.primefaces.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.*;
import org.springframework.stereotype.*;

import edu.eci.cvds.modelo.Cita;
import edu.eci.cvds.repositorios.CitaRepositorio;
import edu.eci.cvds.servicios.CitaServicio;
import lombok.Data;


@Data
@Component

public class Schedule implements Serializable {
    
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    
    @Autowired
    private final CitaRepositorio citaRepositorio;

    @Autowired
    private Evento event;
    
    @Autowired
    private CitaServicio citaServicio;

    @Autowired
    private Correo correo;

    private boolean slotEventOverlap = true;
    private boolean showWeekNumbers = false;
    private boolean showHeader = true;
    private boolean draggable = true;
    private boolean resizable = true;
    private boolean selectable = false;
    private boolean showWeekends = true;
    private boolean tooltip = true;
    private boolean allDaySlot = true;
    private boolean rtl = false;
    private double aspectRatio = Double.MIN_VALUE;
    private String leftHeaderTemplate = "prev,next today";
    private String centerHeaderTemplate = "title";
    private String rightHeaderTemplate = "dayGridMonth,timeGridWeek,timeGridDay,listYear";
    private String nextDayThreshold = "09:00:00";
    private String weekNumberCalculation = "local";
    private String weekNumberCalculator = "date.getTime()";
    private String displayEventEnd;
    private String timeFormat;
    private String slotDuration = "00:30:00";
    private String slotLabelInterval;
    private String slotLabelFormat;
    private String scrollTime = "06:00:00";
    private String minTime = "04:00:00";
    private String maxTime = "20:00:00";
    private String locale = "en";
    private String serverTimeZone = ZoneId.systemDefault().toString();
    private String timeZone = "";
    private String clientTimeZone = "local";
    private String columnHeaderFormat = "";
    private String view = "timeGridWeek";
    private String height = "auto";
    private String extenderCode = "// Write your code here or select an example from above";
    private String selectedExtenderExample = "";

    
    public Schedule(CitaRepositorio citaRepositorio){    
        this.citaRepositorio = citaRepositorio;
    }
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        
    }
    @Bean
    private CommandLineRunner addEventsFromDataBase(CitaServicio citaServicio) throws Exception{
        
        return args -> {
        List<Cita> citas = citaServicio.getAllCita();
        event = new Evento();
        for(int i = 0; i < citas.size(); i++){
            event.setTitle(citas.get(i).getNombre() +" "+citas.get(i).getApellido());
            event.setStartDate((citas.get(i).getId()));
            event.setEndDate(citas.get(i).getEndDate());
            event.setCasoAsiloTurista(citas.get(i).getCasoAsiloTurista());
            event.setDescripcionUsuario(citas.get(i).getDescripcionUsuario());
            event.setDescription(citas.get(i).getDescripcion()); 
            event.setNegocioEEUU(citas.get(i).getNegocioEEUU());
            event.setNombre(citas.get(i).getNombre());
            event.setApellido(citas.get(i).getApellido());
            event.setNumeroTelefono(citas.get(i).getNumeroTelefono());
            event.setEstadoCita(citas.get(i).getEstadoCita());
            event.setColor();
            eventModel.addEvent(event);
            event = new Evento();
        }
        };
    }

    
    public void addEvent() {

        if (!citaRepositorio.existsById( event.getStartDate() )) {
            event.setTitle(event.getNombre()+" "+event.getApellido());
            eventModel.addEvent(event);
            citaServicio.addCita(new Cita(  event.getStartDate(), 
                                            "descripcion",
                                            event.getCasoAsiloTurista(),
                                            event.getNegocioEEUU(),
                                            event.getNombre(),
                                            event.getApellido(),
                                            event.getNumeroTelefono(),
                                            event.getCorreoElectronico(),
                                            event.getDescripcionUsuario(),
                                            "Programada",
                                            event.getEndDate()                            
                                        ));
            event.setEstadoCita("Programada");
            event.setColor();
            sendEmailToHals(false);
            sendEmailToUser(false, event.getCorreoElectronico());
            
        }
        else {
            eventModel.updateEvent(event);
            citaServicio.updateCita(new Cita(  event.getStartDate(), 
                                                "descripcion",
                                                event.getCasoAsiloTurista(),
                                                event.getNegocioEEUU(),
                                                event.getNombre(),
                                                event.getApellido(),
                                                event.getNumeroTelefono(),
                                                event.getCorreoElectronico(),
                                                event.getDescripcionUsuario(),
                                                event.getEstadoCita(),
                                                event.getEndDate()                            
                                        ));
            event.setEstadoCita(event.getEstadoCita());    
            event.setColor();
            sendEmailToHals(true);
            sendEmailToUser(true, event.getCorreoElectronico());
        }
        
        event = new Evento();
        
    }
    public void sendEmailToHals(boolean modified){
        correo.addSubject(modified,
                        true, 
                        event.getNombre(), 
                        event.getApellido(), 
                        event.getStartDate());
        correo.addContent(modified,
                        true, 
                        event.getNombre(), 
                        event.getApellido(),  
                        event.getStartDate(), 
                        event.getDescripcionUsuario(),
                        event.getCasoAsiloTurista(),
                        event.getNegocioEEUU());
        correo.createEmail();
        correo.sendEmail();
    }
    public void sendEmailToUser(boolean modified, String email){
        correo.addSubject(modified,
                            false, 
                            event.getNombre(), 
                            event.getApellido(), 
                            event.getStartDate());
        correo.addContent(modified,
                        false, 
                        event.getNombre(), 
                        event.getApellido(),  
                        event.getStartDate(), 
                        event.getDescripcionUsuario(),
                        event.getCasoAsiloTurista(),
                        event.getNegocioEEUU());
        correo.createEmail(email);
        correo.sendEmail();
    }

    public void onEventSelect(SelectEvent<Evento> selectEvent) {
        event = selectEvent.getObject();
        
        
    }

    public void onViewChange(SelectEvent<String> selectEvent) {
        view = selectEvent.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "View Changed", "View:" + view);
        addMessage(message);
    }

    public void onDateSelect(SelectEvent<LocalDateTime> selectEvent) {
        Evento evento = new Evento();
        evento.setStartDate(selectEvent.getObject());
        evento.setEndDate(selectEvent.getObject().plusHours(1));
        event = evento;
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
                "Delta:" + event.getDeltaAsDuration());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
                "Start-Delta:" + event.getDeltaStartAsDuration() + ", End-Delta: " + event.getDeltaEndAsDuration());

        addMessage(message);
    }

    public void onRangeSelect(ScheduleRangeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Range selected",
                "Start-Date:" + event.getStartDate() + ", End-Date: " + event.getEndDate());

        addMessage(message);
    }

    public void onEventDelete() {
        String eventId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventId");
        if (event != null) {
            ScheduleEvent<?> event = eventModel.getEvent(eventId);
            eventModel.deleteEvent(event);
        }
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public double getAspectRatio() {
        return aspectRatio == 0 ? Double.MIN_VALUE : aspectRatio;
    }
    
    
}