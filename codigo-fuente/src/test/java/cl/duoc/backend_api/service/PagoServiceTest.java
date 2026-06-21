package cl.duoc.backend_api.service;

import cl.duoc.backend_api.dto.PagoCreateDTO;
import cl.duoc.backend_api.dto.PagoResponseDTO;
import cl.duoc.backend_api.model.Pago;
import cl.duoc.backend_api.repository.PagoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private PagoRepository repository;

    @InjectMocks
    private PagoService service;

    @Test
    void debeProcesarPagoExitosamente() {
        PagoCreateDTO createDTO = new PagoCreateDTO();
        createDTO.setPedidoId(50L);
        createDTO.setMonto(10000.0);
        createDTO.setMetodoPago("DEBITO");

        Pago pagoGuardado = new Pago();
        pagoGuardado.setId(1L);
        pagoGuardado.setPedidoId(50L);
        pagoGuardado.setMonto(10000.0);
        pagoGuardado.setMetodoPago("DEBITO");
        pagoGuardado.setEstado("APROBADO");
        pagoGuardado.setFechaPago(LocalDateTime.now());

        when(repository.save(any(Pago.class))).thenReturn(pagoGuardado);

        PagoResponseDTO response = service.procesarPago(createDTO);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("APROBADO", response.getEstado());
        assertEquals(10000.0, response.getMonto());
    }
}