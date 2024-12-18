package co.org.ccb.constituciones.administracion.dominio.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles", schema = "constituciones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false)
    private Integer id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ACTIVO")
    private Integer activo;
    @Column(name = "FECHA_CREACION")
    private Integer fechaCreacion;
}
