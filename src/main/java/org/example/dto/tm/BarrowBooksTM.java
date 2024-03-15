package org.example.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BarrowBooksTM {
    private String books_id;
    private String books_title;
    private String books_genre;
    private Timestamp barrow_date;
    private Date return_date;
    private String branch_id;
}
