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
@Table(name = "branch")
public class Branch {
    @Id
    @Column(name = "branch_id" ,length = 50)
    private String branch_id;

    @Column(name = "branch_location" , length = 50)
    private String location;

    @Column(name = "branch_contact" , length = 40)
    private int branch_contact;
}
