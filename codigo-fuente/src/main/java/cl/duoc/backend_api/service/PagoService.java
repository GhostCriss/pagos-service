package cl.duoc.backend_api.service;

import cl.duoc.backend_api.dto.PagoCreateDTO;
import cl.duoc.backend_api.dto.PagoResponseDTO;
import cl.duoc.backend_api.model.Pago;
import cl.duoc.backend_api.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PagoService {

    @Autowired
    private PagoRepository repository;

    @Transactional
    public PagoResponseDTO procesarPago(PagoCreateDTO dto) {
        Pago pago = new Pago();
        pago.setPedidoId(dto.getPedidoId());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        
        // Lógica simulada de aprobación
        pago.setEstado("APROBADO");
        pago.setFechaPago(LocalDateTime.now());

        Pago pagoGuardado = repository.save(pago);

        return convertirADto(pagoGuardado);
    }

    private PagoResponseDTO convertirADto(Pago pago) {
        PagoResponseDTO dto = new PagoResponseDTO();
        dto.setId(pago.getId());
        dto.setPedidoId(pago.getPedidoId());
        dto.setMonto(pago.getMonto());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstado(pago.getEstado());
        dto.setFechaPago(pago.getFechaPago());
        return dto;
    }
}