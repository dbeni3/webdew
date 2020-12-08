package hu.unideb.webdev.dao.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class)
@Entity
@Table(name = "store", schema = "sakila")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="address_id")
    private AddressEntity address;

    //@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,targetEntity = StaffEntity.class,mappedBy = "store",fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")

    private StaffEntity staff;

    @Column(name ="last_update")
    private Timestamp lastUpdate;




}
