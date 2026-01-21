package com.test.Supermercado.service;

import java.util.List;

import com.test.Supermercado.dto.SucursalDTO;

public interface ISucursalService {

    List<SucursalDTO> traerSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursal);
    SucursalDTO actualizarSucursal(Long id, SucursalDTO dto);
    void eliminarSucursal(Long id);
    
}
