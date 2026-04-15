package com.jesus.microservicios.commons.commons_microservicios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jesus.microservicios.commons.commons_microservicios.services.CommonService;



public class CommonController<E, S extends CommonService<E>> {

    @Autowired
    protected S service;

    @GetMapping // LISTAR
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}") // VER
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<E> o = service.findById(id);
        if (o.isEmpty()) {
            return ResponseEntity.notFound().build();
            
        }
        return ResponseEntity.ok(o.get());
    }

    @PostMapping // CREAR
    public ResponseEntity<?> crear(@RequestBody E entity) {
        E entityDb = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);

    }


    @DeleteMapping("/{id}") // ELIMINAR
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
