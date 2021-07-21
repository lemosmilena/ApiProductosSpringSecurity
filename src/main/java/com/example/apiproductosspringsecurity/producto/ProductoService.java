package com.example.apiproductosspringsecurity.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    public List<ProductoEntity> findAll() {
        return productoRepository.findAll();
    }

    public ProductoEntity getProductoByID(Integer id) {
        ProductoEntity productoEntity = productoRepository.findById(id).orElse(null);
        return productoEntity;
    }

    public List<ProductoEntity> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }

    public List<String> getNombreProductos(){
        List<ProductoEntity> productoEntities = productoRepository.findAll();
        return productoEntities.stream().map(productoEntity -> productoEntity.getNombre()).toList();
    }
}
