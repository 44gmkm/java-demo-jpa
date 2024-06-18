package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa;

import java.util.List;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Tripulante;

public interface ITripulanteCustomJPARepository {
    List<Tripulante> findAll(String search);
    List<Tripulante> findAllWithBarco(String search);
}