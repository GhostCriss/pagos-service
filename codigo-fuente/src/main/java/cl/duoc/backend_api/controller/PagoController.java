package cl.duoc.backend_api.controller;

import cl.duoc.backend_api.dto.PagoCreateDTO;
import cl.duoc.backend_api.dto.PagoResponseDTO;
import cl.duoc.backend_api.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService service;

    @PostMapping
    public ResponseEntity<PagoResponseDTO> registrarPago(@RequestBody PagoCreateDTO dto) {
        PagoResponseDTO respuesta = service.procesarPago(dto);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}