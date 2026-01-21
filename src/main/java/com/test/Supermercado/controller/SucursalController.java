package com.test.Supermercado.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Supermercado.dto.SucursalDTO;
import com.test.Supermercado.service.ISucursalService;

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {
    
    @Autowired
    private ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> traerSucursales(){
        return ResponseEntity.ok(sucursalService.traerSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO dto){
        SucursalDTO creado = sucursalService.crearSucursal(dto);

        return ResponseEntity.created(URI.create("/api/sucursales/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucursal (@PathVariable Long id,
                                                        @RequestBody SucursalDTO dto){
        return ResponseEntity.ok(sucursalService.actualizarSucursal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarSucursal (@PathVariable Long id){
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
