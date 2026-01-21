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

import com.test.Supermercado.dto.VentaDTO;
import com.test.Supermercado.service.IVentaService;

@RestController
@RequestMapping("/api/venta")
public class VentaController {
    
    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas(){
        return ResponseEntity.ok(ventaService.traerVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO dto){
        VentaDTO creado = ventaService.crearVenta(dto);

        return ResponseEntity.created(URI.create("/api/ventas/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta (@PathVariable Long id,
                                                        @RequestBody VentaDTO dto){
        return ResponseEntity.ok(ventaService.actualizarVenta(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarVenta (@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
