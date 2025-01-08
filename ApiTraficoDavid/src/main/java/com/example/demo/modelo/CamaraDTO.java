package com.example.demo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CamaraDTO {
    private int totalItems;
    private int totalPages;
    private int currentPage;
    private List<Camara> cameras;
    
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Camara> getCameras() {
		return cameras;
	}
	public void setCameras(List<Camara> cameras) {
		this.cameras = cameras;
	}


}

