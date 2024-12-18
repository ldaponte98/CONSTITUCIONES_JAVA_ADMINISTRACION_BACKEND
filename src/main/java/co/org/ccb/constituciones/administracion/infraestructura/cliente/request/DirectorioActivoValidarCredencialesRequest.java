package co.org.ccb.constituciones.administracion.infraestructura.cliente.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectorioActivoValidarCredencialesRequest {
    private String grant_type;
    private String username;
    private String password;
}
