package co.org.ccb.constituciones.administracion.aplicacion.login.impl;

import co.org.ccb.constituciones.administracion.aplicacion.login.ValidarAccesoService;
import co.org.ccb.constituciones.administracion.aplicacion.logs.LogService;
import co.org.ccb.constituciones.administracion.dominio.entidad.FuncionarioEntity;
import co.org.ccb.constituciones.administracion.dominio.entidad.RolEntity;
import co.org.ccb.constituciones.administracion.dominio.repositorio.FuncionarioRepository;
import co.org.ccb.constituciones.administracion.dominio.repositorio.FuncionarioRolRepository;
import co.org.ccb.constituciones.administracion.errores.entrada.GenericException;
import co.org.ccb.constituciones.administracion.errores.entrada.UnauthorizedException;
import co.org.ccb.constituciones.administracion.infraestructura.cliente.DirectorioActivoClient;
import co.org.ccb.constituciones.administracion.infraestructura.cliente.request.DirectorioActivoValidarCredencialesRequest;
import co.org.ccb.constituciones.administracion.infraestructura.entrada.ValidarAccesoRequest;
import co.org.ccb.constituciones.administracion.transversal.util.RespuestaBase;
import co.org.ccb.constituciones.administracion.transversal.util.UtilidadesAplicacion;
import co.org.ccb.constituciones.administracion.transversal.util.jwt.JwtUtil;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ValidarAccesoServiceImpl implements ValidarAccesoService {
    private final LogService logService;
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioRolRepository funcionarioRolRepository;
    private final DirectorioActivoClient directorioActivoClient;
    private final JwtUtil jwtUtil;
    @Override
    public RespuestaBase validar(ValidarAccesoRequest request) {
        logService.info("Inicio de sesion: " + request.getUsuario());
        var funcionario = funcionarioRepository.findByIdentificacion(request.getUsuario());
        if (funcionario == null) throw new UnauthorizedException("Funcionario no esta registrado en el aplicativo");
        if (funcionario.getActivo() == 0) throw new UnauthorizedException("Funcionario con acceso restringido en el aplicativo");
        validarAccesoEnDirectorioActivo(request);
        var roles = buscarRoles(funcionario);
        if (roles.size() == 0) throw new UnauthorizedException("Funcionario sin rol asignado en el aplicativo");
        if (roles.size() != 1) {
            if(Strings.isEmpty(request.getRol())){
                return UtilidadesAplicacion.responder("VARIOS_ROLES", roles);
            }else {
                //En este punto es la segunda vez q envia la autenticacion con el rol especifico a autenticar
                var rolesAsociados = roles.stream().filter(p -> p.getNombre() == request.getRol()).collect(Collectors.toList());
                if (rolesAsociados.size() == 0) throw new UnauthorizedException("Rol enviado no esta asociado al funcionario");
                roles = rolesAsociados;
            }
        }
        String token = this.generarToken(funcionario, roles.get(0));
        String rol = roles.get(0).getNombre();
        logService.info("Acceso exitoso a la aplicacion para usuario: " + funcionario.getIdentificacion() + " - rol: " +rol);
        return UtilidadesAplicacion.responder(rol, token);
    }

    private void validarAccesoEnDirectorioActivo(ValidarAccesoRequest request){
        try {
            var credenciales = DirectorioActivoValidarCredencialesRequest.builder()
                    .grant_type("password")
                    .username(request.getUsuario())
                    .password(request.getClave())
                    .build();
            var respuesta = directorioActivoClient.validarCredenciales(credenciales);
            if (Strings.isEmpty(String.valueOf(respuesta.get("access_token")))){
                throw new GenericException("Error al validar el token generado por el directorio activo para el usuario "+ request.getUsuario());
            }
        }catch (FeignException fe){
            if (fe.status() == HttpStatus.UNAUTHORIZED.value()) throw new UnauthorizedException("Credenciales invalidas o no existe en el directorio activo");
            else throw new GenericException(fe.getMessage());
        }catch (GenericException g){

        }catch (Exception e){
            throw new GenericException("Error al validar usuario "+ request.getUsuario()+" contra directorio activo: " + e.getMessage());
        }
    }

    private List<RolEntity> buscarRoles(FuncionarioEntity funcionario){
        var relaciones = this.funcionarioRolRepository.findAllByFuncionario_Id(funcionario.getId());
        return relaciones.stream().map(p -> p.getRol()).collect(Collectors.toList());
    }
    private String generarToken(FuncionarioEntity funcionario, RolEntity rol){
        Map<String, Object> datos = Map.of("rol", rol.getNombre());
        return jwtUtil.generateToken(funcionario.getIdentificacion(), datos);
    }
}
