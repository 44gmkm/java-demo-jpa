package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="BARCOS")
@JsonInclude(Include.NON_NULL)
public class Barco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;

    private Integer eslora;

    private float potenciaCv;

    private double potenciaKw;

    @Column(name = "es_extrangero")
    private boolean extrangero;

    @OneToMany(mappedBy = "barco")
    private List<Tripulante> tripulantes;
    
    
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
        Barco other = (Barco) obj;
        return Objects.equals(id, other.id);
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
    
    public void setExtrangero(boolean esExtrangero) {
        this.extrangero = esExtrangero;
    }
    
    public List<Tripulante> getTripulantes() {
        return tripulantes;
    }
    
    public void setTripulantes(List<Tripulante> tripulantes) {
        this.tripulantes = tripulantes;
    }
    
}
