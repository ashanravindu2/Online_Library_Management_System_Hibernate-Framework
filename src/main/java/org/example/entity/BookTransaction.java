package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "book_transaction")
public class BookTransaction implements Serializable {
    @Id
    @Column(name = "transaction_id" ,length = 50)
    private String transaction_id;

    @CreationTimestamp
    @Column(name = "barrow_date")
    private Timestamp barrow_date;

    @Column(name = "return_date")
    private Date return_date;

    @Column(name = "return_status")
    private int return_status;

    @ManyToOne
    @JoinColumn(name = "books_id")
    private Books books;

    @ManyToOne
    @JoinColumn(name = "user_gmail")
    private User user;

    public BookTransaction(String transaction_id, Date return_date, Books books, User user) {
        this.transaction_id = transaction_id;
        this.return_date = return_date;
        this.books = books;
        this.user = user;
    }

    @Override
    public String toString() {
        return "BookTransaction{" +
                "transaction_id='" + transaction_id + '\'' +
                ", return_date=" + return_date +
                ", books=" + books +
                ", user=" + user +
                '}';
    }
}
