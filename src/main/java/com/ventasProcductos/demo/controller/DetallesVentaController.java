package com.ventasProcductos.demo.controller;


import com.ventasProcductos.demo.model.DetallesVenta;
import com.ventasProcductos.demo.service.DetallesVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalles_venta")
public class DetallesVentaController {

    @Autowired
    private DetallesVentaService detallesVentaService;

    // 🔹 Obtener todos los detalles de venta
    @GetMapping
    public List<DetallesVenta> getAllDetallesVenta() {
        return detallesVentaService.findAll();
    }

    // 🔹 Obtener un detalle de venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetallesVenta> getDetallesVentaById(@PathVariable int id) {
        Optional<DetallesVenta> detalle = detallesVentaService.findById(id);
        return detalle.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Crear un nuevo detalle de venta
    @PostMapping
    public ResponseEntity<DetallesVenta> createDetallesVenta(@RequestBody DetallesVenta detalle) {
        try {
            DetallesVenta nuevoDetalle = detallesVentaService.save(detalle);
            return ResponseEntity.ok(nuevoDetalle);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // 🔹 Actualizar un detalle de venta
    @PutMapping("/{id}")
    public ResponseEntity<DetallesVenta> updateDetallesVenta(@PathVariable int id, @RequestBody DetallesVenta detalleActualizado) {
        Optional<DetallesVenta> detalleExistente = detallesVentaService.findById(id);
        if (detalleExistente.isPresent()) {
            detalleActualizado.setId(id); // Aseguramos que actualice el correcto
            DetallesVenta detalleGuardado = detallesVentaService.save(detalleActualizado);
            return ResponseEntity.ok(detalleGuardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 Eliminar un detalle de venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetallesVenta(@PathVariable int id) {
        Optional<DetallesVenta> detalleExistente = detallesVentaService.findById(id);
        if (detalleExistente.isPresent()) {
            detallesVentaService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
