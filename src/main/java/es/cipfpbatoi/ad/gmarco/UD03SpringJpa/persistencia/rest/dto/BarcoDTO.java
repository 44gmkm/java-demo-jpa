package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BarcoDTO {

    private Long id;
    private String nombre;
    private Integer eslora;
    private float potenciaCv;
    private double potenciaKw;
    private boolean extrangero;

    private List<TripulanteDTO> tripulantes;
    
    public BarcoDTO(){
        super();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEslora() {
        return eslora;
    }
    public void setEslora(Integer eslora) {
        this.eslora = eslora;
    }
    public float getPotenciaCv() {
        return potenciaCv;
    }
    public void setPotenciaCv(float potenciaCv) {
        this.potenciaCv = potenciaCv;
    }
    public double getPotenciaKw() {
        return potenciaKw;
    }
    public void setPotenciaKw(double potenciaKw) {
        this.potenciaKw = potenciaKw;
    }
    public boolean isExtrangero() {
        return extrangero;
    }
    public void setExtrangero(boolean extrangero) {
        this.extrangero = extrangero;
    }
    
    public List<TripulanteDTO> getTripulantes() {
        return tripulantes;
    }
    
    public void setTripulantes(List<TripulanteDTO> tripulacion) {
        this.tripulantes = tripulacion;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        return true;
        if (obj == null)
        return false;
        if (getClass() != obj.getClass())
        return false;
        BarcoDTO other = (BarcoDTO) obj;
        return Objects.equals(id, other.id);
    }
}
