package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.DemoDTO;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    DemoService demoService;

    @GetMapping("/count")
    public Long count() {
        return this.demoService.count();
    }

    @GetMapping("")
    public List<DemoDTO> findAll() {
        return this.demoService.findAll();
    }

    @GetMapping("/{id}")
    public DemoDTO findById(@PathVariable Long id) {
        DemoDTO demo = this.demoService.findById(id);
        if(demo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Entity not found.");
        }
        return demo;
    }

    @PostMapping("")
    @ResponseStatus(code=HttpStatus.CREATED)
    public DemoDTO create(@RequestBody DemoDTO demoDTO) {
        return this.demoService.create(demoDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
    public void update(@PathVariable int id, @RequestBody DemoDTO demoDTO) {
        if (id != demoDTO.getId()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");       
        }
        this.demoService.update(demoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT, reason = "Entity deleted")
    public void delete(@PathVariable int id, @RequestBody DemoDTO demoDTO) {
        if (id != demoDTO.getId()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");       
        }
        this.demoService.delete(demoDTO);
    }
}
