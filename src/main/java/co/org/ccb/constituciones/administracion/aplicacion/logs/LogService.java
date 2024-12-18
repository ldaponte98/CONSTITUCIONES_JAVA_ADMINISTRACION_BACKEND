package co.org.ccb.constituciones.administracion.aplicacion.logs;

public interface LogService {
    void info(String mensaje);
    void error(String mensaje);
}
