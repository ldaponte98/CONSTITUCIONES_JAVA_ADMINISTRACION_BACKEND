package co.org.ccb.constituciones.administracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class ConstitucionesAdministracionApp {
    public static void main(String[] args) {
        SpringApplication.run(ConstitucionesAdministracionApp.class, args);
    }
}
