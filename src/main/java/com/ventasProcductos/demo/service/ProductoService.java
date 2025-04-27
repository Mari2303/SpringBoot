package com.ventasProcductos.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventasProcductos.demo.model.Producto;
import com.ventasProcductos.demo.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public Optional<Producto> findById(int id) {
        return productoRepository.findById(id);
    }

// Guardar o actualizar un producto con validaciones manuales
public Producto save(Producto producto) {
    // Validaciones de campos vacíos
    if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
    }

    if (producto.getCategoria() == null || producto.getCategoria().trim().isEmpty()) {
        throw new IllegalArgumentException("La categoría del producto no puede estar vacía.");
    }
    if (producto.getPrecio() <= 0) {
        throw new IllegalArgumentException("El precio del producto debe ser mayor que cero.");
    }
    if (producto.getCantidad() < 0) {
        throw new IllegalArgumentException("La cantidad del producto no puede ser negativa.");
    }


    // Validación de nombre duplicado
    boolean existeProducto = productoRepository.existsByNombre(producto.getNombre());
    if (existeProducto) {
        throw new IllegalArgumentException("Ya existe un producto con el mismo nombre.");
    }

    return productoRepository.save(producto);
}

// Eliminar un producto por ID
public void deleteById(int id) {
    productoRepository.deleteById(id);
}


}