package org.example.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TransactionTM {
    private String books_id;
    private String books_title;
    private Timestamp barrow_date;
    private Date return_date;
    private String branch_id;
    private int return_status;

}
