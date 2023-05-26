package edu.eci.cvds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import edu.eci.cvds.repositorios.CitaRepositorio;
import edu.eci.cvds.servicios.CitaServicio;


@RunWith(MockitoJUnitRunner.class)
public class CitaServicioTets {
    
    @Mock
    private CitaRepositorio citaRepositorio;

    @InjectMocks
    private CitaServicio citaServicio;


    

}
