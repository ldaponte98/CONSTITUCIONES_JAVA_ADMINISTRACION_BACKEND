package co.org.ccb.constituciones.administracion.dominio.repositorio;

import co.org.ccb.constituciones.administracion.dominio.entidad.FuncionarioRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRolRepository extends JpaRepository<FuncionarioRolEntity, Integer> {
    List<FuncionarioRolEntity> findAllByFuncionario_Id(Integer idFuncionario);
}
