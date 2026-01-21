package com.test.Supermercado.service;

import java.util.List;

import com.test.Supermercado.dto.VentaDTO;

public interface IVentaService {
    List<VentaDTO> traerVentas();
    VentaDTO actualizarVenta(Long id, VentaDTO dto);
    VentaDTO crearVenta(VentaDTO dto);
    void eliminarVenta(Long id);
}
