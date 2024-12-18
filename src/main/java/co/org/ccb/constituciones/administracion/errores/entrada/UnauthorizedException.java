package co.org.ccb.constituciones.administracion.errores.entrada;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
