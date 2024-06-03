package com.example.backendreactinicial.controllers;

import com.example.backendreactinicial.entities.PedidoDetalle;
import com.example.backendreactinicial.services.PedidoDetalleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/pedido-detalle")
public class PedidoDetalleController extends BaseControllerImp<PedidoDetalle, PedidoDetalleServiceImpl>{

}
