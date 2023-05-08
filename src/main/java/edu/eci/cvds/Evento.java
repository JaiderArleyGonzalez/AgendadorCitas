package edu.eci.cvds;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.schedule.ScheduleEntryMoveEvent;
import org.primefaces.event.schedule.ScheduleEntryResizeEvent;
import org.primefaces.event.schedule.ScheduleRangeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleDisplayMode;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

public class Evento extends DefaultScheduleEvent{
    private boolean casoAsiloTurista;
    private boolean negocioEUU;
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
    
    
}
