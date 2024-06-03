package com.example.backendreactinicial.services;

import com.example.backendreactinicial.entities.Instrumento;
import com.example.backendreactinicial.entities.Pedido;
import com.example.backendreactinicial.entities.PedidoDetalle;
import com.example.backendreactinicial.repositories.BaseRepository;
import com.example.backendreactinicial.repositories.InstrumentoRepository;
import com.example.backendreactinicial.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl extends BaseServiceImp<Pedido, Long> implements PedidoService {
    @Autowired
    private final PedidoRepository pedidoRepository;

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository, PedidoRepository pedidoRepository) {
        super(baseRepository);
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public Pedido save(Pedido pedido) throws Exception {
        Pedido savedPedido = super.save(pedido);

        for (PedidoDetalle detalle : savedPedido.getDetalles()) {
            Instrumento instrumento = detalle.getInstrumento();
            instrumento.setCantidadVendida(instrumento.getCantidadVendida() + detalle.getCantidad());
            instrumentoRepository.save(instrumento);
        }
        return savedPedido;
    }
}
