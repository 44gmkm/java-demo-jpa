package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa;

import java.util.List;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Barco;

public interface IBarcoCustomJPARepository {
    List<Barco> findAll(String search);
    List<Barco> findAllWithTripulacion(String search);
}
