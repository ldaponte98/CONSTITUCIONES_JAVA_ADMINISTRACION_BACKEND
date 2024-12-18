package co.org.ccb.constituciones.administracion.aplicacion.login.impl;

import co.org.ccb.constituciones.administracion.aplicacion.login.ValidarAccesoService;
import co.org.ccb.constituciones.administracion.aplicacion.logs.LogService;
import co.org.ccb.constituciones.administracion.dominio.repositorio.FuncionarioRepository;
import co.org.ccb.constituciones.administracion.dominio.repositorio.FuncionarioRolRepository;
import co.org.ccb.constituciones.administracion.infraestructura.entrada.ValidarAccesoRequest;
import co.org.ccb.constituciones.administracion.transversal.util.RespuestaBase;
import co.org.ccb.constituciones.administracion.transversal.util.UtilidadesAplicacion;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ValidarAccesoServiceImpl implements ValidarAccesoService {
    private final LogService logService;
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioRolRepository funcionarioRolRepository;
    @Override
    public RespuestaBase validar(ValidarAccesoRequest request) {
        logService.info("Inicio de sesion: " + request.getUsuario());

        return UtilidadesAplicacion.responder("OK", null);
    }
}
