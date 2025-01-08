package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Incidencia {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String incidenceId;
    private String sourceId;
    private String incidenceType;
    private String autonomousRegion;
    private String province;
    private String cause;
    private String cityTown;
    private String startDate;
    private String endDate;
    private String pkStart;
    private String pkEnd;
    private String direction;
    private String incidenceName;
    private String latitude;
    private String longitude;
    
    

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIncidenceId() {
		return incidenceId;
	}
	public void setIncidenceId(String incidenceId) {
		this.incidenceId = incidenceId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getIncidenceType() {
		return incidenceType;
	}
	public void setIncidenceType(String incidenceType) {
		this.incidenceType = incidenceType;
	}
	public String getAutonomousRegion() {
		return autonomousRegion;
	}
	public void setAutonomousRegion(String autonomousRegion) {
		this.autonomousRegion = autonomousRegion;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getCityTown() {
		return cityTown;
	}
	public void setCityTown(String cityTown) {
		this.cityTown = cityTown;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPkStart() {
		return pkStart;
	}
	public void setPkStart(String pkStart) {
		this.pkStart = pkStart;
	}
	public String getPkEnd() {
		return pkEnd;
	}
	public void setPkEnd(String pkEnd) {
		this.pkEnd = pkEnd;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getIncidenceName() {
		return incidenceName;
	}
	public void setIncidenceName(String incidenceName) {
		this.incidenceName = incidenceName;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
    
    
    
    
    
}
