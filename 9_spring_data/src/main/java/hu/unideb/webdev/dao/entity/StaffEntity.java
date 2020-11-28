package hu.unideb.webdev.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @ManyToOne
    @JoinColumn(name ="address_id")
    private  AddressEntity address;

    @Lob
    @Column(name="picture",length=100000)
    private byte[] data;

    @Column(name ="email")
    private String email;


    @ManyToOne
    @JoinColumn(name ="store_id")
    private  StoreEntity store;

    @Column(name ="active")
    private int active;

    @Column(name ="username")
    private String userName;

    @Column(name ="password")
    private String passWord;

    @Column(name ="last_update")
    private Timestamp lastUpdate;



}
