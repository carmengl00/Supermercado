package com.test.Supermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Supermercado.dto.DetalleVentaDTO;
import com.test.Supermercado.dto.VentaDTO;
import com.test.Supermercado.exception.NotFoundException;
import com.test.Supermercado.mapper.Mapper;
import com.test.Supermercado.model.DetalleVenta;
import com.test.Supermercado.model.Producto;
import com.test.Supermercado.model.Sucursal;
import com.test.Supermercado.model.Venta;
import com.test.Supermercado.repository.ProductoRepository;
import com.test.Supermercado.repository.SucursalRepository;
import com.test.Supermercado.repository.VentaRepository;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private SucursalRepository sucursalRepo;
    @Autowired
    private ProductoRepository productoRepo;



    @Override
    public List<VentaDTO> traerVentas() {
        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();
        
        VentaDTO dto;
        for(Venta v: ventas) {
            dto = Mapper.toDTO(v);
            ventasDTO.add(dto);
        }

        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO dto) {
        // Validaciones
        if(dto == null) throw new RuntimeException("VentaDTO es null");
        if(dto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if(dto.getDetalle() == null || dto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(dto.getIdSucursal()).orElse(null);
        if(suc == null) throw new NotFoundException("Sucursal no encontrada");

        //Crear la venta
        Venta vent = new Venta();
        vent.setSucursal(suc);
        vent.setFecha(dto.getFecha());
        vent.setEstado(dto.getEstado());
        vent.setTotal(dto.getTotal());

        //La lista de detalles
        //----> Aquí están los productos
        List<DetalleVenta> detalles = new ArrayList<>();

        for(DetalleVentaDTO detDTO: dto.getDetalle()){
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if(p == null)
                throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd());

            //Crear detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProducto(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
        }

        //Seteamos la lista de detalle Venta
        vent.setDetalle(detalles);

        //Guardamos en la BD
        ventaRepo.save(vent);

        //Mapeo de salida
        return Mapper.toDTO(vent);
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO dto) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new NotFoundException("Venta no encontrada");

        if(dto.getFecha() != null){
            v.setFecha(dto.getFecha());
        }
        if(dto.getEstado() != null){
            v.setEstado(dto.getEstado());
        }
        if(dto.getTotal() != null){
            v.setTotal(dto.getTotal());
        }
        
        if(dto.getIdSucursal() != null){
            Sucursal suc = sucursalRepo.findById(dto.getIdSucursal()).orElse(null);
            if(suc == null) throw new NotFoundException("Sucursal no encontrada");

            v.setSucursal(suc);
        }
        
        return Mapper.toDTO(ventaRepo.save(v));

    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new NotFoundException("Venta no encontrada");
        ventaRepo.delete(v);
    }

}
