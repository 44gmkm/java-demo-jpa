package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Barco;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.BarcoDTO;

public class BarcoMapper {
        
    public static BarcoDTO toDTO(Barco barco, Boolean withTripulacion) {
        BarcoDTO barcoDTO = new BarcoDTO();

        barcoDTO.setId(barco.getId());
        barcoDTO.setNombre(barco.getNombre());
        barcoDTO.setEslora(barco.getEslora());;
        barcoDTO.setPotenciaCv(barco.getPotenciaCv());
        barcoDTO.setPotenciaKw(barco.getPotenciaKw());
        barcoDTO.setExtrangero(barco.isExtrangero());

        if(withTripulacion)
            barcoDTO.setTripulantes(TripulanteMapper.toDTO(barco.getTripulantes(), false));

        return barcoDTO;
    }
    
    public static BarcoDTO toDTO(Optional<Barco> barcoOptional, Boolean withTripulacion) {
        if(barcoOptional.isEmpty())
            return null;
        
        return BarcoMapper.toDTO(barcoOptional.get(), withTripulacion);
    }

    public static List<BarcoDTO> toDTO(List<Barco> barcos, Boolean withTripulacion) {
        List<BarcoDTO> barcosDTO = new ArrayList<>();

        if(barcos == null)
            return barcosDTO;
        for(Barco barco : barcos)
            barcosDTO.add(BarcoMapper.toDTO(barco, withTripulacion));

        return barcosDTO;
    }

    public static Barco toBD(BarcoDTO barcoDTO, Boolean withTripulacion) {
        Barco barco = new Barco();

        barco.setId(barcoDTO.getId());
        barco.setNombre(barcoDTO.getNombre());
        barco.setEslora(barcoDTO.getEslora());;
        barco.setPotenciaCv(barcoDTO.getPotenciaCv());
        barco.setPotenciaKw(barcoDTO.getPotenciaKw());
        barco.setExtrangero(barcoDTO.isExtrangero());

        if(withTripulacion)
            barco.setTripulantes(TripulanteMapper.toBD(barcoDTO.getTripulantes(), false));

        return barco;
    }
}
