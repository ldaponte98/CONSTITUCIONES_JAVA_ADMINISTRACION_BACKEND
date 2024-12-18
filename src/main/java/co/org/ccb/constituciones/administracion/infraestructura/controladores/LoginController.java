package co.org.ccb.constituciones.administracion.infraestructura.controladores;

import co.org.ccb.constituciones.administracion.infraestructura.entrada.PruebaRequest;
import co.org.ccb.constituciones.administracion.infraestructura.entrada.ValidarAccesoRequest;
import co.org.ccb.constituciones.administracion.transversal.util.RespuestaBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginController {
    @Tag(name = "Login")
    @SecurityRequirement(name="bearerAuth")
    @Operation(summary = "Validacion de acceso", description = "Valida la autenticacion y autorizacion de un funcionario en el sistema y en el directorio activo.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Exitoso",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RespuestaBase.class))),

            })
    ResponseEntity<RespuestaBase> validarAcceso(HttpServletRequest http, @RequestBody ValidarAccesoRequest request);
}
