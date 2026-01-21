package com.test.Supermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Supermercado.dto.SucursalDTO;
import com.test.Supermercado.exception.NotFoundException;
import com.test.Supermercado.mapper.Mapper;
import com.test.Supermercado.model.Sucursal;
import com.test.Supermercado.repository.SucursalRepository;

@Service
public class SucursalService implements ISucursalService{

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursal) {
        Sucursal suc = Sucursal.builder()
            .nombre(sucursal.getNombre())
            .direccion(sucursal.getDireccion())
            .build();
        
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO dto) {
        Sucursal suc = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));
        suc.setNombre(dto.getNombre());
        suc.setDireccion(dto.getDireccion());
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if(!repo.existsById(id)){
            throw new NotFoundException("Sucursal no encontrada para eliminar");
        }
        repo.deleteById(id);
    }

}
