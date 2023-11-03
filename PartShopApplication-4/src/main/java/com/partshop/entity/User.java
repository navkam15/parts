package com.partshop.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private String ID;
    @Column(name = "first_name", length = 20)
    private String firstName;
    @Column(name = "last_name", length = 30)
    private String lastName;
    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "password", length = 10, nullable = false)
    private String password;

    @Column(name = "company_name", length = 20)
    private String companyName;


}
