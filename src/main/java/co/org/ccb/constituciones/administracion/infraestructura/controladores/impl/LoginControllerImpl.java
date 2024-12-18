package co.org.ccb.constituciones.administracion.infraestructura.controladores.impl;

import co.org.ccb.constituciones.administracion.aplicacion.login.ValidarAccesoService;
import co.org.ccb.constituciones.administracion.aplicacion.logs.LogService;
import co.org.ccb.constituciones.administracion.infraestructura.controladores.LoginController;
import co.org.ccb.constituciones.administracion.infraestructura.entrada.PruebaRequest;
import co.org.ccb.constituciones.administracion.infraestructura.entrada.ValidarAccesoRequest;
import co.org.ccb.constituciones.administracion.transversal.util.RespuestaBase;
import co.org.ccb.constituciones.administracion.transversal.util.UsuarioSesion;
import co.org.ccb.constituciones.administracion.transversal.util.UtilidadesApi;
import co.org.ccb.constituciones.administracion.transversal.util.UtilidadesAplicacion;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {
    private final ValidarAccesoService validarAccesoService;
    @Override
    @PostMapping("/login")
    public ResponseEntity<RespuestaBase> validarAcceso(HttpServletRequest http, @RequestBody ValidarAccesoRequest request) {
        UtilidadesApi.session = UsuarioSesion.builder()
                .service(http.getRequestURI())
                .build();
        return ResponseEntity.ok(validarAccesoService.validar(request));
    }
}
