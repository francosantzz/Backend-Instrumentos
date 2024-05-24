package com.example.backendreactinicial.services;

import com.example.backendreactinicial.entities.PedidoDetalle;
import com.example.backendreactinicial.repositories.BaseRepository;
import com.example.backendreactinicial.repositories.PedidoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoDetalleServiceImpl extends BaseServiceImp<PedidoDetalle, Long> implements PedidoDetalleService {
    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    public PedidoDetalleServiceImpl(BaseRepository<PedidoDetalle, Long> baseRepository, PedidoDetalleRepository pedidoDetalleRepository) {
        super(baseRepository);
        this.pedidoDetalleRepository = pedidoDetalleRepository;
    }
    @Override
    public List<PedidoDetalle> saveAll(List<PedidoDetalle> detalles) throws Exception {
        try {
            return pedidoDetalleRepository.saveAll(detalles);
        } catch (Exception e) {
            throw new Exception("Error al guardar los detalles del pedido", e);
        }
    }
}
