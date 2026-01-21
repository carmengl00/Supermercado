package com.test.Supermercado.service;

import java.util.List;

import com.test.Supermercado.dto.ProductoDTO;

public interface IProductoService {
    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO dto);
    ProductoDTO actualizarProducto(Long id, ProductoDTO dto);
    void eliminarProducto(Long id);
}
