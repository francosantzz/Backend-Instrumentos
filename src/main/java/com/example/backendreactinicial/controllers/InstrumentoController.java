package com.example.backendreactinicial.controllers;

import com.example.backendreactinicial.entities.Instrumento;
import com.example.backendreactinicial.repositories.InstrumentoRepository;
import com.example.backendreactinicial.services.InstrumentoServiceImp;
import com.example.backendreactinicial.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/instrumento")
public class InstrumentoController extends BaseControllerImp<Instrumento, InstrumentoServiceImp>{

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/{id}/pdf")
    public ResponseEntity<?> generatePdf(@PathVariable Long id) {
        Optional<Instrumento> instrumentoOptional = instrumentoRepository.findById(id);

        if (!instrumentoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Instrumento instrumento = instrumentoOptional.get();
        ByteArrayInputStream bis = pdfService.generateInstrumentoPdf(instrumento);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + instrumento.getInstrumento() + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

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
