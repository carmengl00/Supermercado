package com.test.Supermercado.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {
    //datos de la venta
    private Long id;
    private LocalDate fecha;
    private String estado;

    //datos de la sucursal
    private Long idSucursal;

    //lista de detalles
    private List<DetalleVentaDTO> detalle;

    //total de la venta
    private Double total;

}