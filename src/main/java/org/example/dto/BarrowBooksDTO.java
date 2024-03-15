package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarrowBooksDTO {
    private String books_id;
    private String books_title;
    private String books_genre;
    private String barrow_date;
    private String return_date;
    private String branch_id;

}
