package co.org.ccb.constituciones.administracion.dominio.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "funcionarios", schema = "constituciones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false)
    private Integer id;
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "ACTIVO")
    private Integer activo;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
}
