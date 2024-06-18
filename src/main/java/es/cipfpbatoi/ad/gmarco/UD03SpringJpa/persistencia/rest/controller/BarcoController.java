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

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.dto.BarcoDTO;
import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.rest.service.BarcoService;

@RestController
@RequestMapping("/barcos")
public class BarcoController {
    @Autowired
    BarcoService barcoService;

    @GetMapping("/count")
    public Long count() {
        return this.barcoService.count();
    }

    @GetMapping("")
    public List<BarcoDTO> findAll(
            @RequestParam(required = false,
                          defaultValue = "false")
                          Boolean withTripulacion,
            @RequestParam(required = false,
                          defaultValue = "")
                          String search) {
        return this.barcoService.findAll(withTripulacion, search);
    }

    @GetMapping("/{id}")
    public BarcoDTO findById(@PathVariable Long id,
            @RequestParam(required = false,
                          defaultValue = "false")
                          Boolean withTripulacion) {
        BarcoDTO barco = this.barcoService.findById(id, withTripulacion);
        if(barco == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Entity not found.");
        }
        return barco;
    }

    @PostMapping("")
    @ResponseStatus(code=HttpStatus.CREATED)
    public BarcoDTO create(@RequestBody BarcoDTO barco) {
        return this.barcoService.create(barco, true);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
    public void update(@PathVariable int id, @RequestBody BarcoDTO barco) {
        if (id != barco.getId()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");       
        }
        this.barcoService.update(barco, true);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT, reason = "Entity deleted")
    public void delete(@PathVariable int id, @RequestBody BarcoDTO barco) {
        if (id != barco.getId()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");       
        }
        this.barcoService.delete(barco, false);
    }
}
