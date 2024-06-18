package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Tripulante;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.TripulanteDTO;

public class TripulanteMapper {
       
    public static TripulanteDTO toDTO(Tripulante tripulante, Boolean withBarco) {
        TripulanteDTO tripulanteDTO = new TripulanteDTO();

        tripulanteDTO.setId(tripulante.getId());
        tripulanteDTO.setNombre(tripulante.getNombre());
        tripulanteDTO.setApellido1(tripulante.getApellido1());
        tripulanteDTO.setApellido2(tripulante.getApellido2());
        tripulanteDTO.setRol(tripulante.getRol());

        if(withBarco)
            tripulanteDTO.setBarco(BarcoMapper.toDTO(tripulante.getBarco(), false));

        return tripulanteDTO;
    }
    
    public static TripulanteDTO toDTO(Optional<Tripulante> tripulanteOptional, Boolean withBarco) {
        if(tripulanteOptional.isEmpty())
            return null;
        
        return TripulanteMapper.toDTO(tripulanteOptional.get(), withBarco);
    }

    public static List<TripulanteDTO> toDTO(List<Tripulante> tripulantes, Boolean withBarco) {
        List<TripulanteDTO> tripulantesDTO = new ArrayList<>();

        if(tripulantes == null)
            return tripulantesDTO;

        for(Tripulante tripulante : tripulantes)
            tripulantesDTO.add(TripulanteMapper.toDTO(tripulante, withBarco));

        return tripulantesDTO;
    }

    public static Tripulante toBD(TripulanteDTO tripulanteDTO, Boolean withBarco) {
        Tripulante tripulante = new Tripulante();

        tripulante.setId(tripulanteDTO.getId());
        tripulante.setNombre(tripulanteDTO.getNombre());
        tripulante.setApellido1(tripulanteDTO.getApellido1());
        tripulante.setApellido2(tripulanteDTO.getApellido2());
        tripulante.setRol(tripulanteDTO.getRol());
        
        if(withBarco)
            tripulante.setBarco(BarcoMapper.toBD(tripulanteDTO.getBarco(), false));

        return tripulante;
    }

    public static List<Tripulante> toBD(List<TripulanteDTO> tripulantesDTO, Boolean withBarco) {
        List<Tripulante> tripulantes = new ArrayList<>();

        if(tripulantesDTO == null)
            return tripulantes;

        for (TripulanteDTO tripulante : tripulantesDTO)
            tripulantes.add(TripulanteMapper.toBD(tripulante, withBarco));

        return tripulantes;
    }
}
