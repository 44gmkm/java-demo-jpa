package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Tripulante;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa.ITripulanteCustomJPARepository;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa.ITripulanteJPARepository;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.TripulanteDTO;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.mapper.TripulanteMapper;

@Service
public class TripulanteService {

    @Autowired
    ITripulanteJPARepository tripulanteJPARepository;

    @Autowired
    ITripulanteCustomJPARepository tripulanteCustomJPARepository;

    public Long count(){
        return this.tripulanteJPARepository.count();
    }

    public List<TripulanteDTO> findAll(Boolean withBarcos, String search) {
        List<Tripulante> tripulantes = this.findAllAccordingParameters(withBarcos, search);
        return TripulanteMapper.toDTO(tripulantes, withBarcos);
    }

    public List<Tripulante> findAllAccordingParameters(Boolean withBarcos, String search) {
        if (StringUtils.isBlank(search)){
            if(withBarcos) {
                return this.tripulanteJPARepository.findAllWithBarcos();
            }
            return this.tripulanteJPARepository.findAll();
        }

        if (withBarcos) {
            return this.tripulanteCustomJPARepository.findAllWithBarco(search);
        }
        return this.tripulanteCustomJPARepository.findAll(search);
    }

    public TripulanteDTO findById(Long id, Boolean withBarco) {
        if (withBarco) {
            Optional<Tripulante> tripulante = this.tripulanteJPARepository.findByIdWithBarcos(id);
            return TripulanteMapper.toDTO(tripulante, withBarco);
        }

        Optional<Tripulante> tripulante = this.tripulanteJPARepository.findById(id);
        return TripulanteMapper.toDTO(tripulante, withBarco);
    }

    public TripulanteDTO create(TripulanteDTO tripulanteDTO, Boolean withBarco) {
        Tripulante tripulante = TripulanteMapper.toBD(tripulanteDTO, withBarco);
        tripulante = this.tripulanteJPARepository.save(tripulante);
        return TripulanteMapper.toDTO(tripulante, withBarco);
    }

    public void update(TripulanteDTO tripulanteDTO, Boolean withBarco) {
        Tripulante tripulante = TripulanteMapper.toBD(tripulanteDTO, withBarco);
        this.tripulanteJPARepository.save(tripulante);
    }

    public void delete(TripulanteDTO tripulanteDTO, Boolean withBarco) {
        Tripulante tripulante = TripulanteMapper.toBD(tripulanteDTO, withBarco);
        this.tripulanteJPARepository.delete(tripulante);
    }
}
