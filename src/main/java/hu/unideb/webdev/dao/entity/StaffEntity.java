package hu.unideb.webdev.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "staff", schema = "sakila")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="address_id")
    private  AddressEntity address;

    @Lob
    @Column(name="picture",length=100000)
    private byte[] data;

    @Column(name ="email")
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="staff")
    private StoreEntity store;

    @Column(name ="active")
    private String active;

    @Column(name ="username")
    private String userName;

    @Column(name ="password")
    private String passWord;

    @Column(name ="last_update")
    private Timestamp lastUpdate;



}
