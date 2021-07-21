package com.example.apiproductosspringsecurity.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping()
    public List<String> getNombreProductos(){
        return productoService.getNombreProductos();
    }

    @GetMapping("/detalles")
    public List<ProductoEntity> getProductos(){
        return productoService.findAll();
    }

    @GetMapping("/id/{id}")
    public ProductoEntity getProductoByID(@PathVariable Integer id){
        return productoService.getProductoByID(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<ProductoEntity> buscarPorNombre(@PathVariable String nombre){
        return productoService.buscarPorNombre(nombre);
    }

}
