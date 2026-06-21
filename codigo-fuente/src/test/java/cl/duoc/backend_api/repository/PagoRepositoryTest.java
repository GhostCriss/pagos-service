package cl.duoc.backend_api.repository;

import cl.duoc.backend_api.model.Pago;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PagoRepositoryTest {

    @Autowired
    private PagoRepository repository;

    @Test
    void debeGuardarYEncontrarPago() {
        Pago pago = new Pago();
        pago.setPedidoId(200L);
        pago.setMonto(15000.0);
        pago.setMetodoPago("WEBPAY");
        pago.setEstado("APROBADO");

        Pago guardado = repository.save(pago);
        assertNotNull(guardado.getId());

        Pago encontrado = repository.findById(guardado.getId()).orElse(null);
        assertNotNull(encontrado);
        assertEquals(15000.0, encontrado.getMonto());
        assertEquals("WEBPAY", encontrado.getMetodoPago());
    }
}