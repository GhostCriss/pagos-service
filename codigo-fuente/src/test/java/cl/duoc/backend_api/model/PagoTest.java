package cl.duoc.backend_api.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class PagoTest {

    @Test
    void debeAsignarYRecuperarValores() {
        Pago pago = new Pago();
        pago.setId(1L);
        pago.setPedidoId(100L);
        pago.setMonto(5000.0);
        pago.setMetodoPago("TARJETA");
        pago.setEstado("APROBADO");
        
        LocalDateTime ahora = LocalDateTime.now();
        pago.setFechaPago(ahora);

        assertEquals(1L, pago.getId());
        assertEquals(100L, pago.getPedidoId());
        assertEquals(5000.0, pago.getMonto());
        assertEquals("TARJETA", pago.getMetodoPago());
        assertEquals("APROBADO", pago.getEstado());
        assertEquals(ahora, pago.getFechaPago());
    }
}