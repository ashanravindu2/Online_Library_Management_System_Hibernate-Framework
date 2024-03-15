package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarrowBooksDTO {
    private String books_id;
    private String books_title;
    private String books_genre;
    private Timestamp barrow_date;
    private Date return_date;
    private String branch_id;
    private int return_status;


    public BarrowBooksDTO(String books_id, String books_title, String books_genre, Timestamp barrow_date, Date return_date, String branch_id) {
        this.books_id = books_id;
        this.books_title = books_title;
        this.books_genre = books_genre;
        this.barrow_date = barrow_date;
        this.return_date = return_date;
        this.branch_id = branch_id;
    }

    public BarrowBooksDTO(String books_id, String books_title, Timestamp barrow_date, Date return_date, String branch_id, int return_status) {
        this.books_id = books_id;
        this.books_title = books_title;
        this.barrow_date = barrow_date;
        this.return_date = return_date;
        this.branch_id = branch_id;
        this.return_status = return_status;
    }

    @Override
    public String toString() {
        return "BarrowBooksDTO{" +
                "books_id='" + books_id + '\'' +
                ", books_title='" + books_title + '\'' +
                ", barrow_date=" + barrow_date +
                ", return_date=" + return_date +
                ", branch_id='" + branch_id + '\'' +
                ", return_status=" + return_status +
                '}';
    }
}
