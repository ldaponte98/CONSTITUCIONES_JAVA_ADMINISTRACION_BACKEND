package co.org.ccb.constituciones.administracion.errores.entrada;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {
    public GenericException(String message) {
        super(message);
    }
}
