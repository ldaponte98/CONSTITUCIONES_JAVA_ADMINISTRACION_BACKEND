package co.org.ccb.constituciones.administracion.infraestructura.entrada;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidarAccesoRequest {
    private String usuario;
    private String clave;
}
