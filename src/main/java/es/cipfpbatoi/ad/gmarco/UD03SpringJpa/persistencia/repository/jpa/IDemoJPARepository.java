package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Demo;

public interface IDemoJPARepository extends JpaRepository<Demo, Long>{
    
}
