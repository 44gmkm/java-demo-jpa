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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.TripulanteDTO;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.service.TripulanteService;

@RestController
@RequestMapping("/tripulantes")
public class TripulanteController {
    @Autowired
    TripulanteService tripulanteService;

    @GetMapping("/count")
    public Long count() {
        return this.tripulanteService.count();
    }

    @GetMapping("")
    public List<TripulanteDTO> findAll(@RequestParam(required = false,
                                                     defaultValue = "false")
                                                     Boolean withBarco,
                                       @RequestParam(required = false,
                                                     defaultValue = "")
                                                     String search) {        
        return this.tripulanteService.findAll(withBarco, search);
    }

    @GetMapping("/{id}")
    public TripulanteDTO findById(@PathVariable Long id,
                                  @RequestParam(required = false,
                                  defaultValue = "false")
                                  Boolean withBarco) {
        TripulanteDTO tripulante = this.tripulanteService.findById(id, withBarco);
        if(tripulante == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Entity not found.");
        }
        return tripulante;
    }

    @PostMapping("")
    @ResponseStatus(code=HttpStatus.CREATED)
    public TripulanteDTO create(@RequestBody TripulanteDTO tripulanteDTO) {
        return this.tripulanteService.create(tripulanteDTO, true);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
    public void update(@PathVariable int id,
                       @RequestBody TripulanteDTO tripulanteDTO) {
        if (id != tripulanteDTO.getId()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");       
        }
        this.tripulanteService.update(tripulanteDTO, true);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT, reason = "Entity deleted")
    public void delete(@PathVariable int id, @RequestBody TripulanteDTO tripulanteDTO) {
        if (id != tripulanteDTO.getId()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");       
        }
        this.tripulanteService.delete(tripulanteDTO, true);
    }
}
