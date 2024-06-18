package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Tripulante;

public interface ITripulanteJPARepository extends JpaRepository<Tripulante, Long> {
    
    @Query("SELECT t FROM Tripulante t JOIN FETCH t.barco b")
    List<Tripulante> findAllWithBarcos();

    @Query("SELECT t FROM Tripulante t JOIN FETCH t.barco b WHERE t.id = :id")
    Optional<Tripulante> findByIdWithBarcos(Long id);
}
