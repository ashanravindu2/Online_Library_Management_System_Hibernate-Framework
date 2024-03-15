package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "user_gmail" ,length = 50)
    private String gmail;

    @Column(name = "user_name" , length = 50)
    private String name;

    @Column(name = "user_password" , length = 40)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    List<BookTransaction> bookTransactions = new ArrayList<>();

    public User(String gmail, String name, String password) {
        this.gmail = gmail;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "gmail='" + gmail + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
