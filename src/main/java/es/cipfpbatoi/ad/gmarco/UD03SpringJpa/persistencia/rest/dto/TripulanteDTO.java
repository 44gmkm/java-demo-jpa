package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TripulanteDTO {

    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String rol;
    
    private BarcoDTO barco;

    public TripulanteDTO() {
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

    public String getApellido1() {
        return apellido1;
    }
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public BarcoDTO getBarco() {
        return barco;
    }
    
    public void setBarco(BarcoDTO barco) {
        this.barco = barco;
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
        TripulanteDTO other = (TripulanteDTO) obj;
        return Objects.equals(id, other.id);
    }
}
