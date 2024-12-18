package co.org.ccb.constituciones.administracion.infraestructura.cliente;

import co.org.ccb.constituciones.administracion.infraestructura.cliente.request.LogRequest;
import co.org.ccb.constituciones.administracion.transversal.util.RespuestaBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "registrar-log-service", url = "${client.transversal.url}")
public interface TransversalClient {
    @PostMapping("/logs")
    RespuestaBase registrarLog(@RequestBody LogRequest request);
}
