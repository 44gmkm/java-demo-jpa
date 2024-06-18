package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Demo;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.DemoDTO;

public class DemoMapper {
    
    public static DemoDTO map(Demo demo) {
        DemoDTO demoDTO = new DemoDTO();

        demoDTO.setId(demo.getId());
        demoDTO.setMyFloat(demo.getMyFloat());
        demoDTO.setVarchar(demo.getVarchar());
        demoDTO.setMyBool(demo.isMyBool());
        demoDTO.setMyChar(demo.getMyChar());
        demoDTO.setMyDate(demo.getMyDate());

        return demoDTO;
    }
    
    public static DemoDTO map(Optional<Demo> demoOptional) {
        if(demoOptional.isEmpty())
            return null;
        
        return DemoMapper.map(demoOptional.get());
    }

    public static List<DemoDTO> map(List<Demo> demos) {
        List<DemoDTO> demosDTO = new ArrayList<>();

        if(demos == null)
            return demosDTO;
        for(Demo demo : demos)
            demosDTO.add(DemoMapper.map(demo));

        return demosDTO;
    }

    public static Demo map(DemoDTO demoDTO) {
        Demo demo = new Demo();

        demo.setId(demoDTO.getId());
        demo.setMyFloat(demoDTO.getMyFloat());
        demo.setVarchar(demoDTO.getVarchar());
        demo.setMyBool(demoDTO.isMyBool());
        demo.setMyChar(demoDTO.getMyChar());
        demo.setMyDate(demoDTO.getMyDate());

        return demo;
    }
}
