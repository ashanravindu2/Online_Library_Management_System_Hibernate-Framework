package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_gmail" ,length = 50)
    private String gmail;

    @Column(name = "admin_name" , length = 50)
    private String name;

    @Column(name = "admin_password" , length = 40)
    private String password;

}
