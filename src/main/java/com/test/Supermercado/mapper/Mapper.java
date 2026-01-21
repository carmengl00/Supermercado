package com.test.Supermercado.mapper;

import java.util.stream.Collectors;

import com.test.Supermercado.dto.DetalleVentaDTO;
import com.test.Supermercado.dto.ProductoDTO;
import com.test.Supermercado.dto.SucursalDTO;
import com.test.Supermercado.dto.VentaDTO;
import com.test.Supermercado.model.Producto;
import com.test.Supermercado.model.Sucursal;
import com.test.Supermercado.model.Venta;

public class Mapper {

    //Mapeo de Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto p){
        if (p==null) return null;
        
        return ProductoDTO.builder()
            .id(p.getId())
            .nombre(p.getNombre())
            .categoria(p.getCategoria())
            .precio(p.getPrecio())
            .build();
    }

    //Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        var detalle = venta.getDetalle().stream()
        .map(det ->
                DetalleVentaDTO.builder()
                        .id(det.getProducto().getId())
                        .nombreProd(det.getProducto().getNombre())
                        .cantProd(det.getCantProd())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantProd())
                        .build()
        )
        .collect(Collectors.toList());


        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estado(venta.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }

    //Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal p){
        if (p==null) return null;
        
        return SucursalDTO.builder()
            .id(p.getId())
            .nombre(p.getNombre())
            .direccion(p.getDireccion())
            .build();
    }

}
