package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "books")
public class Books implements SuperEntity{
    @Id
    @Column(name = "books_id" ,length = 50)
    private String books_id;

    @Column(name = "books_title" , length = 50)
    private String books_title;

    @Column(name = "books_author" , length = 40)
    private String books_author;

    @Column(name = "books_genre" , length = 40)
    private String books_genre;

    @Column(name = "books_avl" , length = 40)
    private String books_avl;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

}
