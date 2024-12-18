package co.org.ccb.constituciones.administracion.infraestructura.controladores.impl;

import co.org.ccb.constituciones.administracion.aplicacion.logs.LogService;
import co.org.ccb.constituciones.administracion.infraestructura.entrada.PruebaRequest;
import co.org.ccb.constituciones.administracion.transversal.util.RespuestaBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerImplTest {

    @Mock
    private LogService logService;

    @InjectMocks
    private LoginControllerImpl pruebaController;

    private PruebaRequest pruebaRequest;

    @BeforeEach
    void setUp() {
        pruebaRequest = new PruebaRequest();
        pruebaRequest = PruebaRequest.builder().variable("hola").build();
        pruebaRequest = new PruebaRequest("");
        pruebaRequest.setVariable("");
        System.out.println(pruebaRequest.getVariable());
        // Aquí puedes inicializar los campos de PruebaRequest si tiene alguno
    }

    @Test
    void probar_deberiaRegistrarLogYRetornarRespuestaExitosa() {
        // Act
        var response = pruebaController.probar(pruebaRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        RespuestaBase respuestaBase = response.getBody();
        assertNotNull(respuestaBase);
        assertTrue(respuestaBase.isExito());
        assertEquals("OK", respuestaBase.getMensaje());
        assertNull(respuestaBase.getDetalle());

        verify(logService).info("Hola mundo desde linea base");
    }

    @Test
    void probar_cuandoLogServiceFalla_deberiaPropagarExcepcion() {
        // Arrange
        doThrow(new RuntimeException("Error al registrar log"))
                .when(logService).info(anyString());

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                pruebaController.probar(pruebaRequest)
        );

        verify(logService).info("Hola mundo desde linea base");
    }

    @Test
    void probar_deberiaEjecutarseIndependientementeDelRequest() {
        // Arrange
        PruebaRequest requestNulo = null;

        // Act
        var response = pruebaController.probar(requestNulo);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(logService).info("Hola mundo desde linea base");
    }
}