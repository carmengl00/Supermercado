package com.test.Supermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Supermercado.dto.ProductoDTO;
import com.test.Supermercado.exception.NotFoundException;
import com.test.Supermercado.mapper.Mapper;
import com.test.Supermercado.model.Producto;
import com.test.Supermercado.repository.ProductoRepository;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO dto) {
        var prod = Producto.builder()
            .nombre(dto.getNombre())
            .categoria(dto.getCategoria())
            .precio(dto.getPrecio())
            .cantidad(dto.getCantidad())
            .build();
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto) {
        // Buscar si existe ese producto
        Producto prod = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        prod.setNombre(dto.getNombre());
        prod.setCategoria(dto.getCategoria());
        prod.setCantidad(dto.getCantidad());
        prod.setPrecio(dto.getPrecio());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {
        if(!repo.existsById(id)){
            throw new NotFoundException("Producto no encontrado para eliminar");
        }
        repo.deleteById(id);
    }

}
