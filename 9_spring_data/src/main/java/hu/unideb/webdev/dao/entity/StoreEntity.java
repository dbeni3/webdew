package hu.unideb.webdev.dao.entity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "store", schema = "sakila")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int id;

    @ManyToOne
    @JoinColumn(name ="address_id")
    private AddressEntity adress;

    @Column(name ="last_update")
    private Timestamp lastUpdate;

}
