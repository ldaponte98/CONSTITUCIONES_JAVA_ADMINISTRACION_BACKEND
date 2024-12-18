package co.org.ccb.constituciones.administracion.errores.entrada;

import lombok.Getter;

@Getter
public class LogException extends RuntimeException {
    public LogException(String message) {
        super(message);
    }
}
