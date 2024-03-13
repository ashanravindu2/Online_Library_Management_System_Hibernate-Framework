package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "branch")
public class Branch implements SuperEntity {
    @Id
    @Column(name = "branch_id" ,length = 50)
    private String branch_id;

    @Column(name = "branch_location" , length = 50)
    private String location;

    @Column(name = "branch_contact" , length = 40)
    private String branch_contact;

    @Column(name = "branch_avl" , length = 40)
    private String branch_avl;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "branch")
    List<Books> books = new ArrayList<>();

    public Branch(String branch_id, String location, String branch_contact, String branch_avl) {
        this.branch_id = branch_id;
        this.location = location;
        this.branch_contact = branch_contact;
        this.branch_avl = branch_avl;
    }
}
