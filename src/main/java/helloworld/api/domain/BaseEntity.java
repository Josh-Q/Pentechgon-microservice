package helloworld.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_sequence_generator")
//    @SequenceGenerator(name = "base_sequence_generator", sequenceName = "base_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "base_table_generator")
//    @TableGenerator(name = "base_table_generator", table = "id_generator", pkColumnName = "id_name", pkColumnValue = "base_entity_id", valueColumnName = "id_value")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
}
