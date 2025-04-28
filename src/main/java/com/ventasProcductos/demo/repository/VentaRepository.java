package com.ventasProcductos.demo.repository;
import java.util.List;


import com.ventasProcductos.demo.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByUsuarioNumeroDocumento(Integer numeroDocumento);
}