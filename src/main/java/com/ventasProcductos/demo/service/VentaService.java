package com.ventasProcductos.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventasProcductos.demo.model.Venta;
import com.ventasProcductos.demo.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todas las ventas
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    // Obtener una venta por ID
    public Optional<Venta> findById(int id) {
        return ventaRepository.findById(id);
    }

 

    // Eliminar una venta por ID
    public void deleteById(int id) {
        ventaRepository.deleteById(id);
    }

// Obtener ventas por número de documento del usuario
public List<Venta> findByNumeroDocumentoUsuario(Integer numeroDocumento) {
    return ventaRepository.findByUsuarioNumeroDocumento(numeroDocumento);
}

public Venta save(Venta venta) {
    if (venta.getFecha() == null) {
        venta.setFecha(LocalDate.now());
    }
    return ventaRepository.save(venta);
}



}