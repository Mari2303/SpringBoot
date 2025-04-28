package com.ventasProcductos.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventasProcductos.demo.model.DetallesVenta;
import com.ventasProcductos.demo.model.Producto;
import com.ventasProcductos.demo.repository.DetallesVentaRepository;
import com.ventasProcductos.demo.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetallesVentaService {

    @Autowired
    private DetallesVentaRepository detallesVentaRepository;

    @Autowired
    private ProductoRepository productoRepository; // 👉 Inyectas el repo de productos

    // Obtener todos los detalles
    public List<DetallesVenta> findAll() {
        return detallesVentaRepository.findAll();
    }

    // Obtener un detalle por ID
    public Optional<DetallesVenta> findById(int id) {
        return detallesVentaRepository.findById(id);
    }

    // Guardar o actualizar un detalle
    public DetallesVenta save(DetallesVenta detalle) {
        // 👉 Buscar el producto completo usando el ID
        Producto producto = productoRepository.findById(detalle.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalle.getProducto().getId()));

        // 👉 Asignarlo completo al detalle
        detalle.setProducto(producto);

        // 👉 Opcional: Puedes recalcular el subtotal automáticamente
        double nuevoSubtotal = producto.getPrecio() * detalle.getCantidad();
        detalle.setSubtotal(nuevoSubtotal);

        // 👉 Ahora sí guardar
        return detallesVentaRepository.save(detalle);
    }

    // Eliminar un detalle por ID
    public void deleteById(int id) {
        detallesVentaRepository.deleteById(id);
    }
}
