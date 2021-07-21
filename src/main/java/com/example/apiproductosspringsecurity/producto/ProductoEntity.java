package com.example.apiproductosspringsecurity.producto;

import javax.persistence.*;

@Entity
@Table(name = "productos")
public class ProductoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer producto_id;
    private String nombre;
    private Integer precio;

    public ProductoEntity() {
    }

    public ProductoEntity(String nombre, Integer precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public ProductoEntity(Integer producto_id, String nombre, Integer precio) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ProductoEntity{" +
                "producto_id=" + producto_id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
