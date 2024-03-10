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
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_gmail" ,length = 50)
    private String gmail;

    @Column(name = "user_name" , length = 50)
    private String name;

    @Column(name = "user_password" , length = 40)
    private String password;

}
