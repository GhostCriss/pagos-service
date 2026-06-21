package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.dto.PagoCreateDTO;
import cl.duoc.backend_api.dto.PagoResponseDTO;
import cl.duoc.backend_api.service.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PagoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PagoService service;

    @InjectMocks
    private PagoController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void debeRegistrarPagoYRetornar201() throws Exception {
        PagoResponseDTO responseDTO = new PagoResponseDTO();
        
        when(service.procesarPago(any(PagoCreateDTO.class))).thenReturn(responseDTO);

        String jsonBody = "{\"pedidoId\": 1, \"monto\": 5000.0, \"metodoPago\": \"TARJETA\"}";

        mockMvc.perform(post("/api/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andExpect(status().isCreated());
    }
}