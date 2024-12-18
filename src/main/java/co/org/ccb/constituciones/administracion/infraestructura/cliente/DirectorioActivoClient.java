package co.org.ccb.constituciones.administracion.infraestructura.cliente;

import co.org.ccb.constituciones.administracion.infraestructura.cliente.request.DirectorioActivoValidarCredencialesRequest;
import co.org.ccb.constituciones.administracion.infraestructura.cliente.request.LogRequest;
import co.org.ccb.constituciones.administracion.transversal.util.RespuestaBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "directorio-activo-service", url = "${client.directorio-activo.url}")
public interface DirectorioActivoClient {
    @PostMapping(value = "", consumes = "application/x-www-form-urlencoded")
    Map<String, Object> validarCredenciales(@RequestBody DirectorioActivoValidarCredencialesRequest request);
}
