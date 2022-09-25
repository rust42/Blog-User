package edu.miu.bloguser.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor

@SecondaryTable(name = "Address")
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String firstName;
    private String lastName;
    private String password;

    @Column(columnDefinition = "VARCHAR(20) default USER")
    private String role;

    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(table = "Address")
    private String street;

    @Column(table = "Address")
    private String zipCode;

    @Column(table = "Address")
    private String state;

    @Column(table = "Address")
    private String country;
}
