package com.example.backendreactinicial.controllers;

import com.example.backendreactinicial.entities.Instrumento;
import com.example.backendreactinicial.services.InstrumentoServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/instrumento")
public class InstrumentoController extends BaseControllerImp<Instrumento, InstrumentoServiceImp>{

    @PostMapping("/update-vendidos/{id}")
    public ResponseEntity<?> updateVendidos(@PathVariable Long id, @RequestBody Map<String, Integer> cantidadVendida) {
        try {
            Optional<Instrumento> instrumentoOpt = Optional.ofNullable(servicio.findById(id));
            if (instrumentoOpt.isPresent()) {
                Instrumento instrumento = instrumentoOpt.get();
                instrumento.setCantidadVendida(instrumento.getCantidadVendida() + cantidadVendida.get("cantidadVendida"));
                servicio.save(instrumento);
                return ResponseEntity.status(HttpStatus.OK).body(instrumento);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Instrumento no encontrado\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al actualizar la cantidad vendida\"}");
        }
    }

}
