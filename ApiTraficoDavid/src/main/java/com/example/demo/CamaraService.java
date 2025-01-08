package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.CamaraDTO;
import com.example.demo.modelo.CamaraRepositorio;
import com.example.demo.modelo.Incidencia;
import com.example.demo.modelo.IncidenciaDTO;
import com.example.demo.modelo.IncidenciaRepositorio;
import com.example.demo.modelo.IncidenciasResponse;

@Service
public class CamaraService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CamaraRepositorio camaraRepositorio;
    
    @Autowired
    private IncidenciaRepositorio incidenciaRepositorio;

    private final String API_URL = "https://api.euskadi.eus/traffic/v1.0/cameras/byLocation/43.21167/-2.719359/20000";

    

public void cargarDatosDesdeApi() {
    try {
    	camaraRepositorio.deleteAll();
    
        CamaraDTO response = restTemplate.getForObject(API_URL, CamaraDTO.class);
        
        //Lo filtro para que no inserte si tiene un campo vacio
        
        if (response != null && response.getCameras() != null && !response.getCameras().isEmpty()) {
            response.getCameras().stream()
                .filter(camara -> camara != null && camara.getUrlImage() != null)
                .forEach(camaraRepositorio::save);
            System.out.println("Datos cargados correctamente.");
        } else {
            System.out.println("No hay datos para cargar.");
        }
    } catch (Exception e) {
        System.err.println("Error al cargar datos desde la API: " + e.getMessage());
    }
}


public void cargarDatosDesdeApiIncidencias() {
	
	try {
        SSLUtils.disableSslVerification();
        LocalDate now = LocalDate.now();
        String baseUrl = "https://api.euskadi.eus/traffic/v1.0/incidences/byDate/" + now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
        String url = baseUrl;

        incidenciaRepositorio.deleteAll();
        tareaProgramada();
        IncidenciasResponse incidencesResponse = restTemplate.getForObject(url, IncidenciasResponse.class);
        int totalPages = incidencesResponse.getTotalPages();

        for (int i = 1; i <= totalPages; i++) {
            url = baseUrl + "?_page=" + i;
            incidencesResponse = restTemplate.getForObject(url, IncidenciasResponse.class);
            IncidenciaDTO[] listaIncidencias = incidencesResponse.getIncidences();

            if (listaIncidencias != null) {
                for (IncidenciaDTO incidencia : listaIncidencias) {
                    Incidencia inci = new Incidencia();
                    inci.setIncidenceId(incidencia.getIncidenceId());
                    inci.setSourceId(incidencia.getSourceId());
                    inci.setIncidenceType(incidencia.getIncidenceType());
                    inci.setAutonomousRegion(incidencia.getAutonomousRegion());
                    inci.setProvince(incidencia.getProvince());
                    inci.setCause(incidencia.getCause());
                    inci.setCityTown(incidencia.getCityTown());
                    inci.setStartDate(incidencia.getStartDate());
                    inci.setEndDate(incidencia.getEndDate());
                    inci.setPkStart(incidencia.getPkStart());
                    inci.setPkEnd(incidencia.getPkEnd());
                    inci.setDirection(incidencia.getDirection());
                    inci.setIncidenceName(incidencia.getIncidenceName());
                    inci.setLatitude(incidencia.getLatitude());
                    inci.setLongitude(incidencia.getLongitude());

                    incidenciaRepositorio.save(inci);
                }
            }
        }
        System.out.println("Datos de incidencias cargados correctamente de la página");
    } catch (Exception e) {
        System.err.println("Error al cargar datos desde la API en la página: " + e.getMessage());
    }
}


public void tareaProgramada() {

    LocalDateTime nextRunTimeNoon = LocalDateTime.now().withHour(12).withMinute(0).withSecond(0).withNano(0);
    if (LocalDateTime.now().isAfter(nextRunTimeNoon)) {
        
        nextRunTimeNoon = nextRunTimeNoon.plusDays(1);
        incidenciaRepositorio.deleteAll();
        System.out.println("Tarea programada completada.");
    }


    long initialDelayNoon = ChronoUnit.MILLIS.between(LocalDateTime.now(), nextRunTimeNoon);


    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {

        cargarDatosDesdeApiIncidencias();
    }, initialDelayNoon, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);

    LocalDateTime nextRunTimeMidnight = LocalDateTime.now().withHour(0).withMinute(1).withSecond(0).withNano(0);
    if (LocalDateTime.now().isAfter(nextRunTimeMidnight)) {
        nextRunTimeMidnight = nextRunTimeMidnight.plusDays(1);
    }


    long initialDelayMidnight = ChronoUnit.MILLIS.between(LocalDateTime.now(), nextRunTimeMidnight);

    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
        cargarDatosDesdeApiIncidencias();
    }, initialDelayMidnight, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
}




    @EventListener(ContextRefreshedEvent.class)
    public void cargarDatosAlInicio() {
        cargarDatosDesdeApi();
        cargarDatosDesdeApiIncidencias();
    }
}
