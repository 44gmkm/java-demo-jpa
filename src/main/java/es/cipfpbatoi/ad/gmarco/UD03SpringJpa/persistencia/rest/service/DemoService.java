package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Demo;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa.IDemoJPARepository;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.DemoDTO;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.mapper.DemoMapper;

@Service
public class DemoService {
    
    @Autowired
    IDemoJPARepository demoJPARepository;

    public Long count(){
        return this.demoJPARepository.count();
    }

    public List<DemoDTO> findAll() {
        List<Demo> demos = this.demoJPARepository.findAll();
        return DemoMapper.map(demos);
    }

    public DemoDTO findById(Long id) {
        Optional<Demo> demo = this.demoJPARepository.findById(id);
        if(demo.isEmpty())
            return null;

        return DemoMapper.map(demo);
    }

    public DemoDTO create(DemoDTO demoDTO) {
        Demo demo = DemoMapper.map(demoDTO);
        demo = this.demoJPARepository.save(demo);
        return DemoMapper.map(demo);
    }

    public void update(DemoDTO demoDTO) {
        Demo demo = DemoMapper.map(demoDTO);
        this.demoJPARepository.save(demo);
    }

    public void delete(DemoDTO demoDTO) {
        Demo demo = DemoMapper.map(demoDTO);
        this.demoJPARepository.delete(demo);
    }
}
