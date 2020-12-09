package hu.unideb.webdev.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer", schema = "sakila")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private StoreEntity store;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="email")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="address_id")
    private  AddressEntity address;

    @Column(name ="active")
    private String active;

    @Column(name ="create_date")
    private Timestamp createDate;

    @Column(name ="last_update")
    private Timestamp lastUpdate;

}
