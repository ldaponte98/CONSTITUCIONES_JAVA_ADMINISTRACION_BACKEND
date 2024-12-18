package co.org.ccb.constituciones.administracion.dominio.repositorio;

import co.org.ccb.constituciones.administracion.dominio.entidad.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {
}
