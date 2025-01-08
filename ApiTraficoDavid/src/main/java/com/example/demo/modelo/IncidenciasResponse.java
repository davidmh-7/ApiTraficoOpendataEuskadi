
// IncidenciasResponse.java
package com.example.demo.modelo;

public class IncidenciasResponse {
    private int totalPages;
    private IncidenciaDTO[] incidences;

    // Getters and setters
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public IncidenciaDTO[] getIncidences() {
        return incidences;
    }

    public void setIncidences(IncidenciaDTO[] incidences) {
        this.incidences = incidences;
    }
}
