package com.test.Supermercado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Supermercado.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    //Buscar producto por nombre
    Optional<Producto> findByNombre(String nombre);

}
