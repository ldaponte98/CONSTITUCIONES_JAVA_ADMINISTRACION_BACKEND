package co.org.ccb.constituciones.administracion.aplicacion.login;

import co.org.ccb.constituciones.administracion.infraestructura.entrada.ValidarAccesoRequest;
import co.org.ccb.constituciones.administracion.transversal.util.RespuestaBase;

public interface ValidarAccesoService {
    RespuestaBase validar(ValidarAccesoRequest request);
}
