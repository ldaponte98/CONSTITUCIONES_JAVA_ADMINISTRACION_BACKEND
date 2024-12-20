package co.org.ccb.constituciones.administracion.configuracion;

import co.org.ccb.constituciones.administracion.infraestructura.interceptores.ValidarAutenticacionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ValidarAutenticacionInterceptor validarAutenticacionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validarAutenticacionInterceptor)
                .addPathPatterns("/**")   // Aplicar a todas las rutas
                .excludePathPatterns("/v3/api-docs/**", "/swagger-ui/**", "/v1/login"); // Excluir rutas públicas
    }
}
