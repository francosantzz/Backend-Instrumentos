package com.example.backendreactinicial.repositories;

import com.example.backendreactinicial.entities.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    List<Pedido> findByFechaPedidoBetween(LocalDate fechaDesde, LocalDate fechaHasta);

    @Query("SELECT YEAR(p.fechaPedido), MONTH(p.fechaPedido), COUNT(p) " +
            "FROM Pedido p GROUP BY YEAR(p.fechaPedido), MONTH(p.fechaPedido)")
    List<Object[]> countPedidosGroupedByMonthYear();

    @Query("SELECT i.instrumento, COUNT(p) FROM Pedido p " +
            "JOIN p.detalles d JOIN d.instrumento i GROUP BY i.instrumento")
    List<Object[]> countPedidosGroupedByInstrument();

}
