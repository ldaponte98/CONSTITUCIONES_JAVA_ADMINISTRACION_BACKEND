package co.org.ccb.constituciones.administracion.dominio.repositorio;

import co.org.ccb.constituciones.administracion.dominio.entidad.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
}
