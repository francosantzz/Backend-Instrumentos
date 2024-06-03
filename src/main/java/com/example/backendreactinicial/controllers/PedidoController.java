package com.example.backendreactinicial.controllers;

import com.example.backendreactinicial.entities.Pedido;
import com.example.backendreactinicial.repositories.PedidoRepository;
import com.example.backendreactinicial.services.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/pedido")
public class PedidoController extends BaseControllerImp<Pedido, PedidoServiceImpl>{
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/group-by-month-year")
    public ResponseEntity<?> getPedidosGroupedByMonthYear() {
        List<Object[]> results = pedidoRepository.countPedidosGroupedByMonthYear();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/group-by-instrument")
    public ResponseEntity<?> getPedidosGroupedByInstrument() {
        List<Object[]> results = pedidoRepository.countPedidosGroupedByInstrument();
        return ResponseEntity.ok(results);
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Pedido pedido){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(pedido));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde.\"}");
        }
    }



    @GetMapping("/search")
    public List<Pedido> getPedidos(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {
        if (fechaDesde != null && fechaHasta != null) {
            return pedidoRepository.findByFechaPedidoBetween(fechaDesde, fechaHasta);
        }
        return pedidoRepository.findAll();
    }


}
