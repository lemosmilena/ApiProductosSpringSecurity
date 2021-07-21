package com.example.apiproductosspringsecurity.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {


    List<ProductoEntity> findByNombreContaining(String nombre);
}
