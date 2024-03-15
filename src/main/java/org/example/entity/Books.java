package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Books implements Serializable {
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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "books")
    List<BookTransaction> bookTransactions = new ArrayList<>();

    public Books(String books_id, String books_title, String books_author, String books_genre, String books_avl, Branch branch) {
        this.books_id = books_id;
        this.books_title = books_title;
        this.books_author = books_author;
        this.books_genre = books_genre;
        this.books_avl = books_avl;
        this.branch = branch;
    }


    @Override
    public String toString() {
        return "Books{" +
                "books_id='" + books_id + '\'' +
                ", books_title='" + books_title + '\'' +
                ", books_author='" + books_author + '\'' +
                ", books_genre='" + books_genre + '\'' +
                ", books_avl='" + books_avl + '\'' +
                ", branch=" + branch +
                '}';
    }
}
