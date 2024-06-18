package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Barco;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa.IBarcoCustomJPARepository;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa.IBarcoJPARepository;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.BarcoDTO;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.mapper.BarcoMapper;

@Service
public class BarcoService {
    
    @Autowired
    IBarcoJPARepository barcoJPARepository;

    @Autowired
    IBarcoCustomJPARepository barcoCustomJPARepository;

    public Long count(){
        return this.barcoJPARepository.count();
    }

    public List<BarcoDTO> findAll(Boolean withTripulacion, String search) {
        List<Barco> barcos = this.findAllAccordingParamenters(withTripulacion, search);
        return BarcoMapper.toDTO(barcos, withTripulacion);
    }

    public List<Barco> findAllAccordingParamenters(Boolean withTripulacion, String search) {
        if(StringUtils.isBlank(search)) {
            if(withTripulacion)
                return this.barcoJPARepository.findAllWithTripulacion();

            return this.barcoJPARepository.findAll();
        }
        
        if (withTripulacion)
            return this.barcoCustomJPARepository.findAllWithTripulacion(search);
        
        return this.barcoCustomJPARepository.findAll(search);
    }

    public BarcoDTO findById(Long id, Boolean withTripulacion) {
        if (withTripulacion) {
            Optional<Barco> barco = this.barcoJPARepository.findByIdWithTripulacion(id);
            return BarcoMapper.toDTO(barco, withTripulacion);
        }

        Optional<Barco> barco = this.barcoJPARepository.findById(id);
        return BarcoMapper.toDTO(barco, withTripulacion);
    }

    public BarcoDTO create(BarcoDTO barcoDTO, Boolean withTripulacion) {
        Barco barco = BarcoMapper.toBD(barcoDTO, withTripulacion);
        barco = this.barcoJPARepository.save(barco);
        return BarcoMapper.toDTO(barco, withTripulacion);
    }

    public void update(BarcoDTO barcoDTO, Boolean withTripulacion) {
        Barco barco = BarcoMapper.toBD(barcoDTO, withTripulacion);
        this.barcoJPARepository.save(barco);
    }

    public void delete(BarcoDTO barcoDTO, Boolean withTripulacion) {
        Barco barco = BarcoMapper.toBD(barcoDTO, withTripulacion);
        this.barcoJPARepository.delete(barco);
    }
}
