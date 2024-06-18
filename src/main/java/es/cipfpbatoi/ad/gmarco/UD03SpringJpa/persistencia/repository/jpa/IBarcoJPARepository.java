package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Barco;

@Repository
public interface IBarcoJPARepository extends JpaRepository<Barco, Long>{

    @Query("SELECT b FROM Barco b JOIN FETCH b.tripulantes")
    List<Barco> findAllWithTripulacion();

    @Query("SELECT b FROM Barco b JOIN FETCH b.tripulantes WHERE b.id = :id")
    Optional<Barco> findByIdWithTripulacion(Long id);
}
