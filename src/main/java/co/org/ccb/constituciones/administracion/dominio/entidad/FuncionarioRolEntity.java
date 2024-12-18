package co.org.ccb.constituciones.administracion.dominio.entidad;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionario_rol", schema = "constituciones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioRolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario_rol")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", nullable = false)
    private FuncionarioEntity funcionario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;
}
